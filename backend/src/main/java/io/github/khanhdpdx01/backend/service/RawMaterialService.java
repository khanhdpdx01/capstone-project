package io.github.khanhdpdx01.backend.service;


import io.github.khanhdpdx01.backend.dto.raw_material.RawMaterialDto;
import io.github.khanhdpdx01.backend.entity.Ingredient;
import io.github.khanhdpdx01.backend.entity.RawMaterial;
import io.github.khanhdpdx01.backend.entity.User;
import io.github.khanhdpdx01.backend.exception.ApiRequestException;
import io.github.khanhdpdx01.backend.mapper.IngredientMapper;
import io.github.khanhdpdx01.backend.mapper.RawMaterialMapper;
import io.github.khanhdpdx01.backend.repository.RawMaterialRepository;
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
public class RawMaterialService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
    private final AuthenticationFacade authenticationFacade;
    private final RawMaterialRepository rawMaterialRepository;

    @Value("${raw-material.not-found}")
    private String RAW_MATERIAL_NOT_FOUND;

    @Value("${storage.file.directory}")
    private String fileDirectory;

    public RawMaterialService(RawMaterialRepository rawMaterialRepository, AuthenticationFacade authenticationFacade) {
        this.rawMaterialRepository = rawMaterialRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public RawMaterial getDetail(Long id) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(RAW_MATERIAL_NOT_FOUND));

        return rawMaterial;
    }

    public RawMaterial createOrUpdate(RawMaterialDto rawMaterialDto, List<MultipartFile> certificates, List<MultipartFile> images) {
        if (rawMaterialDto.getId() != null) {
            getDetail(rawMaterialDto.getId());
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

        RawMaterial rawMaterial = RawMaterialMapper.INSTANCE.dtoToEntity(rawMaterialDto);
        rawMaterial.setCertificatePath(StringUtil.join(FileUtil.getFilenameArray(certificates)));
        rawMaterial.setImagePath(StringUtil.join(FileUtil.getFilenameArray(images)));

        if (rawMaterialDto.getId() == null) {
            User representative = new User();
            representative.setId(authenticationFacade.getUserId());
            rawMaterial.setRepresentative(representative);
        } else {
            User user = new User();
            user.setId(authenticationFacade.getUserId());
            rawMaterial.setUpdatedBy(user);
        }

        RawMaterial returnedRawMaterial = rawMaterialRepository.save(rawMaterial);

        if (rawMaterialDto.getId() == null) {
            logger.info("Raw material create success: " + rawMaterial.getId());
        } else {
            logger.info("Raw material update success: " + rawMaterial.getId());
        }

        return returnedRawMaterial;
    }
}
