package com.phoebus.library.purchase.service;

import com.phoebus.library.purchase.PurchaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPagePurchaseService {
    Page<PurchaseDTO> listPurchaseOnPage(Pageable pageable);
}
