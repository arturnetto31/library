package com.phoebus.library.purchase.service;

import com.phoebus.library.purchase.Purchase;

@FunctionalInterface
public interface SavePurchaseService {
    void save(Purchase purchase);
}
