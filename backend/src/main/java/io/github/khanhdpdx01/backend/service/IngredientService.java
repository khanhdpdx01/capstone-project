package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.ingredient.IngredientDto;
import io.github.khanhdpdx01.backend.entity.Ingredient;
import io.github.khanhdpdx01.backend.entity.User;
import io.github.khanhdpdx01.backend.exception.ApiRequestException;
import io.github.khanhdpdx01.backend.mapper.IngredientMapper;
import io.github.khanhdpdx01.backend.repository.IngredientRepository;
import io.github.khanhdpdx01.backend.security.AuthenticationFacade;
import io.github.khanhdpdx01.backend.util.FileUtil;
import io.github.khanhdpdx01.backend.util.PaginationAndSortUtil;
import io.github.khanhdpdx01.backend.util.StringUtil;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@PropertySource("classpath:messages.properties")
public class IngredientService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);

    private final IngredientRepository ingredientRepository;
    private final AuthenticationFacade authenticationFacade;

    @Value("${ingredient.not-found}")
    private String INGREDIENT_NOT_FOUND;

    @Value("${storage.file.directory}")
    private String fileDirectory;

    public IngredientService(IngredientRepository ingredientRepository, AuthenticationFacade authenticationFacade) {
        this.ingredientRepository = ingredientRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public Page<Ingredient> getAllWithPaging(int page, int size, String[] sort, String keyword) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);

        Page<Ingredient> pageRoom;

        if (keyword == null || StringUtils.isBlank(keyword)) {
            pageRoom = ingredientRepository.findAll(pageable);
        } else {
            pageRoom = ingredientRepository.search(keyword, pageable);
        }

        return new PageImpl<>(pageRoom.getContent(), pageable, pageRoom.getTotalElements());
    }

    public List<Ingredient> getAllWithoutPaging() {
        return ingredientRepository.findAll();
    }

    public Ingredient getDetail(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(INGREDIENT_NOT_FOUND));

        return ingredient;
    }

    public Ingredient createOrUpdate(IngredientDto ingredientDto, List<MultipartFile> certificates, List<MultipartFile> images) {
        if (ingredientDto.getId() != null) {
            getDetail(ingredientDto.getId());
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

        Ingredient ingredient = IngredientMapper.INSTANCE.dtoToEntity(ingredientDto);
        ingredient.setCertificatePath(StringUtil.join(FileUtil.getFilenameArray(certificates)));
        ingredient.setImagePath(StringUtil.join(FileUtil.getFilenameArray(images)));

        if (ingredientDto.getId() == null) {
            User partner = new User();
            partner.setId(authenticationFacade.getUserId());
            ingredient.setPartner(partner);
        } else {
            User user = new User();
            user.setId(authenticationFacade.getUserId());
            ingredient.setUpdatedBy(user);
        }

        Ingredient returnedIngredient = ingredientRepository.save(ingredient);

        if (ingredientDto.getId() == null) {
            logger.info("Ingredient create success: " + ingredient.getId());
        } else {
            logger.info("Ingredient update success: " + ingredient.getId());
        }

        return returnedIngredient;
    }
}
