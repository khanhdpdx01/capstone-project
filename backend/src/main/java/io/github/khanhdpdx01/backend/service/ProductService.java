package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.product.ProductDto;
import io.github.khanhdpdx01.backend.entity.Product;
import io.github.khanhdpdx01.backend.entity.User;
import io.github.khanhdpdx01.backend.exception.ApiRequestException;
import io.github.khanhdpdx01.backend.mapper.ProductMapper;
import io.github.khanhdpdx01.backend.repository.ProductRepository;
import io.github.khanhdpdx01.backend.security.AuthenticationFacade;
import io.github.khanhdpdx01.backend.util.FileUtil;
import io.github.khanhdpdx01.backend.util.PaginationAndSortUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.github.khanhdpdx01.backend.common.AppConstant.ADMIN;

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

        Product product = ProductMapper.INSTANCE.dtoToEntity(productDto);

        if (images.size() > 0) {
            List<String> imgListAsStr =
                    images
                            .stream()
                            .map(image -> UUID.randomUUID().toString())
                            .collect(Collectors.toList());

            int i = 0;
            for (; i < imgListAsStr.size(); ++i) {
                FileUtil.save(Paths.get(fileDirectory, imgListAsStr.get(i)), images.get(i));
            }

            product.setImagePath(imgListAsStr.stream().collect(Collectors.joining()));
        }

        if (certificates.size() > 0) {
            List<String> certificateListAsStr =
                    certificates
                            .stream()
                            .map(certificate -> UUID.randomUUID().toString())
                            .collect(Collectors.toList());

            int i = 0;
            for (; i < certificateListAsStr.size(); ++i) {
                FileUtil.save(Paths.get(fileDirectory, certificateListAsStr.get(i)), certificates.get(i));
            }

            product.setCertificatePath(certificateListAsStr.stream().collect(Collectors.joining()));
        }

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

    public Page<Product> getAllWithPaging(int page, int size, String[] sort, String keyword) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);
        Long userId = authenticationFacade.getUserId();
        String role = authenticationFacade.getRole();

        Page<Product> pageRoom;

        if (keyword == null || StringUtils.isBlank(keyword)) {
            if (ADMIN.equals(role)) {
                pageRoom = productRepository.findAll(pageable);
            } else {
                pageRoom = productRepository.findAll(userId, pageable);
            }
        } else {
            if (ADMIN.equals(role)) {
                pageRoom = productRepository.search(null, keyword, pageable);
            } else {
                pageRoom = productRepository.search(userId, keyword, pageable);
            }
        }

        return new PageImpl<>(pageRoom.getContent(), pageable, pageRoom.getTotalElements());
    }

    public Object getAllWithoutPaging() {
        Long userId = authenticationFacade.getUserId();
        return productRepository.findAllWithoutPaging(userId);
    }
}
