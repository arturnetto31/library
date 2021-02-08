package com.phoebus.library.purchase.service;

import com.phoebus.library.purchase.PurchaseDTO;

import java.util.List;

@FunctionalInterface
public interface ListPurchaseService {
    public List<PurchaseDTO> listPurchases();
}
