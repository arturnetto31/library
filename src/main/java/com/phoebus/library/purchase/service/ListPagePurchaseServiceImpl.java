package com.phoebus.library.purchase.service;

import com.phoebus.library.purchase.PurchaseDTO;
import com.phoebus.library.purchase.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListPagePurchaseServiceImpl implements ListPagePurchaseService{

    public PurchaseRepository repository;

    @Override
    public Page<PurchaseDTO> listPurchaseOnPage(Pageable pageable) {
        return PurchaseDTO.from(repository.findAll(pageable));
    }
}
