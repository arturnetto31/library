package com.phoebus.library.purchase.service;


import com.phoebus.library.purchase.PurchaseDTO;

@FunctionalInterface
public interface AttPurchaseService {
    public void attPurchase(Long id, PurchaseDTO purchaseDTO);
}
