package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.product.ProductDto;
import io.github.khanhdpdx01.backend.entity.Product;
import io.github.khanhdpdx01.backend.entity.User;
import io.github.khanhdpdx01.backend.exception.ApiRequestException;
import io.github.khanhdpdx01.backend.mapper.ProductMapper;
import io.github.khanhdpdx01.backend.repository.ProductRepository;
import io.github.khanhdpdx01.backend.security.AuthenticationFacade;
import io.github.khanhdpdx01.backend.util.FileUtil;
import io.github.khanhdpdx01.backend.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@PropertySource("classpath:messages.properties")
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);

    private final ProductRepository productRepository;
    private final AuthenticationFacade authenticationFacade;

    @Value("${product.not-found}")
    private String PRODUCT_NOT_FOUND;

    @Value("${storage.file.directory}")
    private String fileDirectory;

    public ProductService(ProductRepository productRepository, AuthenticationFacade authenticationFacade) {
        this.productRepository = productRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public Product getDetail(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(PRODUCT_NOT_FOUND));

        return product;
    }

    public Product createOrUpdate(ProductDto productDto, List<MultipartFile> certificates, List<MultipartFile> images) {
        if (productDto.getId() != null) {
            getDetail(productDto.getId());
        }

        Path path = Paths.get(fileDirectory, Long.toString(authenticationFacade.getUserId()));
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            logger.error("Create directory not success: ", path);
        }

        for (MultipartFile certificate : certificates) {
            FileUtil.save(path, certificate);
        }

        for (MultipartFile image : images) {
            FileUtil.save(path, image);
        }

        Product product = ProductMapper.INSTANCE.dtoToEntity(productDto);
        product.setCertificatePath(StringUtil.join(FileUtil.getFilenameArray(certificates)));
        product.setImagePath(StringUtil.join(FileUtil.getFilenameArray(images)));

        if (productDto.getId() == null) {
            User createdBy = new User();
            createdBy.setId(authenticationFacade.getUserId());
            product.setCreatedBy(createdBy);
        } else {
            User user = new User();
            user.setId(authenticationFacade.getUserId());
            product.setUpdatedBy(user);
        }

        Product returnedProduct = productRepository.save(product);

        if (productDto.getId() == null) {
            logger.info("Product create success: " + product.getId());
        } else {
            logger.info("Product update success: " + product.getId());
        }

        return returnedProduct;
    }
}
