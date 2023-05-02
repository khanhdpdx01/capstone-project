package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.partner.PartnerDto;
import io.github.khanhdpdx01.backend.entity.Partner;
import io.github.khanhdpdx01.backend.exception.ApiRequestException;
import io.github.khanhdpdx01.backend.mapper.PartnerMapper;
import io.github.khanhdpdx01.backend.repository.PartnerRepository;
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

@Service
@PropertySource("classpath:messages.properties")
public class PartnerService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
    private final PartnerRepository partnerRepository;

    @Value("${partner.not-found}")
    private String PARTNER_NOT_FOUND;

    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public Page<Partner> getAllWithPaging(int page, int size, String[] sort, String keyword) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);

        Page<Partner> pageRoom;

        if (keyword == null || StringUtils.isBlank(keyword)) {
            pageRoom = partnerRepository.findAll(pageable);
        } else {
            pageRoom = partnerRepository.search(keyword, pageable);
        }

        return new PageImpl<>(pageRoom.getContent(), pageable, pageRoom.getTotalElements());
    }

    public Partner getDetail(Long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(PARTNER_NOT_FOUND));

        return partner;
    }

    public Partner create(PartnerDto partnerDto) {
        Partner partner = PartnerMapper.INSTANCE.dtoToEntity(partnerDto);

        Partner createdPartner = partnerRepository.save(partner);
        logger.info("Partner create success: " + partner.getId());

        return createdPartner;
    }

    public void update(Partner updatePartner) {
        Partner partner = getDetail(updatePartner.getId());

        partnerRepository.save(updatePartner);
        logger.info("Partner update success" + partner.getId());
    }
}
