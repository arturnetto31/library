package com.phoebus.library.purchase.v1;


import com.phoebus.library.purchase.Purchase;
import com.phoebus.library.purchase.PurchaseDTO;
import com.phoebus.library.purchase.service.DeletePurchaseService;
import com.phoebus.library.purchase.service.SavePurchaseService;
import com.phoebus.library.purchase.service.AttPurchaseService;
import com.phoebus.library.purchase.service.GetPurchaseService;
import com.phoebus.library.purchase.service.ListPurchaseService;
import com.phoebus.library.purchase.service.ListPagePurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/v1/purchases")
public class PurchaseControllerV1 {

    private final DeletePurchaseService deletePurchaseService;
    private final SavePurchaseService savePurchaseService;
    private final AttPurchaseService attPurchaseService;
    private final GetPurchaseService getPurchaseService;
    private final ListPurchaseService listPurchaseService;
    private final ListPagePurchaseService listPagePurchaseService;


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
    public PurchaseDTO getPurchaseById(@PathVariable("id") Long id) {
        return getPurchaseService.getPurchase(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDTO> listPurchases(){
        return listPurchaseService.listPurchases();
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public Page<PurchaseDTO> findPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return listPagePurchaseService.listPurchaseOnPage(page, size);
    }
}
