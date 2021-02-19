package com.phoebus.library.purchase;


import com.phoebus.library.exceptions.PurchaseNotFoundException;
import com.phoebus.library.purchase.service.DeletePurchaseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify delete service")
public class DeletePurchaseServiceTest {
    @Mock
    private PurchaseRepository repository;

    private DeletePurchaseServiceImpl deletePurchaseServiceImpl;

    @BeforeEach
    void setUp() {
        this.deletePurchaseServiceImpl = new DeletePurchaseServiceImpl(repository);
    }

    @Test
    @DisplayName("Should delete a purchase")
    void shouldDeletePurchase(){
        when(repository.existsById(anyLong())).thenReturn(true);
        deletePurchaseServiceImpl.delete(1L);

        verify(repository,times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("Should not delete a purchase")
    void shouldNotDeletePurchase() {
        when(repository.existsById(anyLong())).thenReturn(false);
        Assertions.assertThrows(PurchaseNotFoundException.class, () -> deletePurchaseServiceImpl.delete(anyLong()));

        verify(repository,times(0)).deleteById(anyLong());
    }

}
