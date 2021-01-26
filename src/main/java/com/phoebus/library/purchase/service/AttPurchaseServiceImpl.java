package com.phoebus.library.purchase.service;


import com.phoebus.library.exceptions.PurchaseInconsistencyInDataException;
import com.phoebus.library.purchase.Purchase;
import com.phoebus.library.purchase.PurchaseDTO;
import com.phoebus.library.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AttPurchaseServiceImpl implements AttPurchaseService{
    private final PurchaseRepository repository;


    @Override
    public void attPurchase(Long id, PurchaseDTO purchaseDTO) {
        Purchase purchase = repository.findById(id).orElseThrow(PurchaseInconsistencyInDataException::new);

        purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
        purchase.setPurchaseIsCompleted(purchaseDTO.isPurchaseCompleted());
        purchase.setCustomer(purchaseDTO.getCustomer());
        purchase.setPriceToPay(purchaseDTO.getPriceToPay());
        purchase.setShoppingList(purchaseDTO.getShoppingList());



    }
}
