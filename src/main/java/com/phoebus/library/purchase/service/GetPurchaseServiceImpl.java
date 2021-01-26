package com.phoebus.library.purchase.service;


import com.phoebus.library.exceptions.PurchaseNotFoundException;
import com.phoebus.library.purchase.Purchase;
import com.phoebus.library.purchase.PurchaseDTO;
import com.phoebus.library.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPurchaseServiceImpl implements GetPurchaseService {

    private final PurchaseRepository repository;

    @Override
    public PurchaseDTO getPurchase(Long id) {
        Purchase purchase = repository.findById(id).orElseThrow(PurchaseNotFoundException::new);

        return PurchaseDTO.from(purchase);

    }
}
