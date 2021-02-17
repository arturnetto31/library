package com.phoebus.library.purchase.service;

import com.phoebus.library.purchase.PurchaseDTO;

@FunctionalInterface
public interface SavePurchaseService {
    void save(PurchaseDTO purchaseDTO);
}
