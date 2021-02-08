package com.phoebus.library.purchase.service;

import com.phoebus.library.purchase.Purchase;
import com.phoebus.library.purchase.PurchaseDTO;
import com.phoebus.library.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListPurchaseServiceImpl implements ListPurchaseService {

    private final PurchaseRepository repository;

    @Override
    public List<PurchaseDTO> listPurchases() {
        List<Purchase> purchases = repository.findAll();
        return PurchaseDTO.from(purchases);
    }
}
