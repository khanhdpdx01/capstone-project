package io.github.khanhdpdx01.backend.dto.delivery;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeliveryDto {
    private String sender;
    private String senderAddress;
    private String recipient;
    private String recipientAddress;
    private List<String> listStampObj;
}
