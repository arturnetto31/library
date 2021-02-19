package com.phoebus.library.purchase;

import com.phoebus.library.exceptions.PurchaseInconsistencyInDataException;
import com.phoebus.library.purchase.service.AttPurchaseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.phoebus.library.purchase.builders.PurchaseBuilder.createPurchase;
import static com.phoebus.library.purchase.builders.PurchaseBuilderDTO.createPurchaseDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify edit purchase service")
public class EditPurchaseServiceTest {
    @Mock
    private PurchaseRepository repository;

    private AttPurchaseServiceImpl attPurchaseServiceImpl;

    @BeforeEach
    void setUp() {
        this.attPurchaseServiceImpl = new AttPurchaseServiceImpl(repository);
    }

    @Test
    @DisplayName("Should edit a purchase")
    void shouldEditPurchase() {
        Optional<Purchase> purchaseOptional = Optional.of(createPurchase().build());
        PurchaseDTO purchaseDTO = createPurchaseDTO().build();

        when(repository.findById(anyLong())).thenReturn(purchaseOptional);
        attPurchaseServiceImpl.attPurchase(anyLong(),purchaseDTO);

        ArgumentCaptor<Purchase> captorPurchase = ArgumentCaptor.forClass(Purchase.class);
        verify(repository,times(1)).save(captorPurchase.capture());

        Purchase result = captorPurchase.getValue();

        assertAll("Purchase",
                () -> assertThat(result.getCustomer(), is(purchaseDTO.getCustomer())),
                () -> assertThat(result.getShoppingList(), is(purchaseDTO.getShoppingList())),
                () -> assertThat(result.getPurchaseDate(), is("19-02-2021")),
                () -> assertThat(result.getPriceToPay(), is(150.2)),
                () -> assertThat(result.isPurchaseIsCompleted(),is(true))
                );
    }
    @Test
    @DisplayName("Should not edit a purchase")
    void shouldNotEditPurchase() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(PurchaseInconsistencyInDataException.class,() -> attPurchaseServiceImpl.attPurchase(1L,createPurchaseDTO().build()));

        verify(repository,times(0)).save(ArgumentMatchers.any(Purchase.class));
    }
}
