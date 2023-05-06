package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.dto.pagination.PaginationParams;
import io.github.khanhdpdx01.backend.dto.pagination.PaginationResponse;
import io.github.khanhdpdx01.backend.dto.partner.PartnerDto;
import io.github.khanhdpdx01.backend.entity.Partner;
import io.github.khanhdpdx01.backend.service.PartnerService;
import io.github.khanhdpdx01.backend.util.PaginationAndSortUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {
    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllWithPaging(@Valid PaginationParams params) {
        Page<Partner> partnerDtoPage = partnerService.getAllWithPaging(
                params.getPage(),
                params.getSize(),
                params.getSort(),
                params.getKeyword());
        PaginationResponse<Partner> response = PaginationAndSortUtil.map(partnerDtoPage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PARTNER')")
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id) {
        Partner partner = partnerService.getDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(partner);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody PartnerDto partnerDto) {
        Partner partner = partnerService.create(partnerDto);
        return ResponseEntity.status(HttpStatus.OK).body(partner);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PARTNER')")
    public ResponseEntity<?> update(@RequestBody Partner updatePartner) {
        partnerService.update(updatePartner);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
