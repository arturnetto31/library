package com.phoebus.library.purchase;

import com.phoebus.library.exceptions.PurchaseNotFoundException;
import com.phoebus.library.purchase.service.GetPurchaseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.phoebus.library.purchase.builders.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify get purchase service")
public class GetPurchaseServiceTest {

    @Mock
    private PurchaseRepository repository;

    private GetPurchaseServiceImpl getPurchaseServiceImpl;

    @BeforeEach
    void setUp() {
        this.getPurchaseServiceImpl = new GetPurchaseServiceImpl(repository);
    }

    @Test
    @DisplayName("Should get a purchase")
    void shouldGetPurchase() {
        Optional<Purchase> purchaseOptional = Optional.of(createPurchase().build());

        when(repository.findById(anyLong())).thenReturn(purchaseOptional);

        PurchaseDTO result = this.getPurchaseServiceImpl.getPurchase(1L);

        assertAll("Purchase",
                () -> assertThat(result.getCustomer(),is(purchaseOptional.get().getCustomer())),
                () -> assertThat(result.getShoppingList(), is(purchaseOptional.get().getShoppingList())),
                () -> assertThat(result.getPurchaseDate(), is("19-02-2021")),
                () -> assertThat(result.getPriceToPay(), is(150.2)),
                () -> assertThat(result.isPurchaseCompleted(), is(true))
                );

    }
    @Test
    @DisplayName("Should not get a purchase")
    void shouldNotGetPurchase() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(PurchaseNotFoundException.class, () -> getPurchaseServiceImpl.getPurchase(1L));

        verify(repository).findById(anyLong());
    }
}
