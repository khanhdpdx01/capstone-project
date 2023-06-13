package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.delivery.DeliveryDto;
import io.github.khanhdpdx01.backend.entity.Delivery;
import io.github.khanhdpdx01.backend.mapper.DeliveryMapper;
import io.github.khanhdpdx01.backend.repository.DeliveryRepository;
import io.github.khanhdpdx01.backend.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void deliver(DeliveryDto deliveryDto) {
        Delivery delivery = DeliveryMapper.INSTANCE.dtoToEntity(deliveryDto);
        delivery.setListStamp(StringUtil.join(deliveryDto.getListStampObj()));

        deliveryRepository.save(delivery);
        logger.info("Deliver success");
    }
}
