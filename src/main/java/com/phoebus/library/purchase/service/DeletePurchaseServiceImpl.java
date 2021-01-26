package com.phoebus.library.purchase.service;


import com.phoebus.library.exceptions.PurchaseNotFoundException;
import com.phoebus.library.purchase.Purchase;
import com.phoebus.library.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeletePurchaseServiceImpl implements DeletePurchaseService{

    private final PurchaseRepository repository;

    @Override
    public void delete(Long id) {
        Purchase purchase = repository.findById(id).orElseThrow(PurchaseNotFoundException::new);
        repository.delete(purchase);
    }
}
