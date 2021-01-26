package com.phoebus.library.purchase.service;


import com.phoebus.library.purchase.PurchaseDTO;

@FunctionalInterface
public interface GetPurchaseService {
    public PurchaseDTO getPurchase(Long id);
}
