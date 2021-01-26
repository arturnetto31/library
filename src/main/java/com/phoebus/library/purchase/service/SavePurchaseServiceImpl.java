package com.phoebus.library.purchase.service;


import com.phoebus.library.purchase.Purchase;
import com.phoebus.library.purchase.PurchaseDTO;
import com.phoebus.library.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePurchaseServiceImpl implements SavePurchaseService{
    private final PurchaseRepository repository;

    @Override
    public void save(Purchase purchase) {
        repository.save(purchase);
        PurchaseDTO.from(purchase);
    }
}
