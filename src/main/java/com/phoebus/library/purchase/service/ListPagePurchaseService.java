package com.phoebus.library.purchase.service;

import com.phoebus.library.purchase.PurchaseDTO;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface ListPagePurchaseService {
    Page<PurchaseDTO> listPurchaseOnPage(Integer page, Integer size);
}
