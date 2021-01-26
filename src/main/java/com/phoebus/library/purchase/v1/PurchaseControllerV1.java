package com.phoebus.library.purchase.v1;


import com.phoebus.library.purchase.Purchase;
import com.phoebus.library.purchase.PurchaseDTO;
import com.phoebus.library.purchase.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/v1/purchases")
public class PurchaseControllerV1 {

    private DeletePurchaseService deletePurchaseService;
    private SavePurchaseService savePurchaseService;
    private AttPurchaseService attPurchaseService;
    private GetPurchaseService getPurchaseService;
    private ListPurchaseService listPurchaseService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        deletePurchaseService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Purchase purchase) {
        savePurchaseService.save(purchase);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void attPurchase(@PathVariable("id") Long id, @RequestBody PurchaseDTO purchaseDTO) {
        attPurchaseService.attPurchase(id,purchaseDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void getPurchaseById(@PathVariable("id") Long id) {
        getPurchaseService.getPurchase(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void listPurchases(){
        listPurchaseService.listPurchases();
    }
}
