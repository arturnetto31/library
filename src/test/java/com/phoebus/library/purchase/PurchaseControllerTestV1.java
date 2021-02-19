package com.phoebus.library.purchase;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoebus.library.exceptions.PurchaseNotFoundException;
import com.phoebus.library.purchase.service.AttPurchaseService;
import com.phoebus.library.purchase.service.DeletePurchaseService;
import com.phoebus.library.purchase.service.GetPurchaseService;
import com.phoebus.library.purchase.service.ListPagePurchaseService;
import com.phoebus.library.purchase.service.ListPurchaseService;
import com.phoebus.library.purchase.service.SavePurchaseService;
import com.phoebus.library.purchase.v1.PurchaseControllerV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.phoebus.library.purchase.builders.PurchaseBuilderDTO.createPurchaseDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PurchaseControllerV1.class)
@DisplayName("Test to verify purchase controller v1")
public class PurchaseControllerTestV1 {
    private final String URL_PURCHASE = "/v1/purchase";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeletePurchaseService deletePurchaseService;

    @MockBean
    private AttPurchaseService editPurchaseService;

    @MockBean
    private GetPurchaseService getPurchaseService;

    @MockBean
    private ListPagePurchaseService listPagePurchaseService;

    @MockBean
    private ListPurchaseService listPurchaseService;

    @MockBean
    private SavePurchaseService savePurchaseService;

    @Test
    @DisplayName("Should delete a purchase")
    void shouldDeletePurchase() throws Exception {
        mockMvc.perform(delete(URL_PURCHASE+"/{id}", 1L).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deletePurchaseService).delete(1L);
    }

    @Test
    @DisplayName("Should edit a purchase")
    void shouldEditPurchase() throws Exception {
        mockMvc.perform(put(URL_PURCHASE+"/{id}",1L).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
        .content(readJson("purchaseUpdate.json")))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(editPurchaseService).attPurchase(eq(1L),any(PurchaseDTO.class));
    }

    @Test
    @DisplayName("Should get a purchase")
    void shouldGetPurchase() throws Exception {

        PurchaseDTO purchaseDTO = createPurchaseDTO().build();
        when(getPurchaseService.getPurchase(anyLong())).thenReturn(purchaseDTO);

        mockMvc.perform(get(URL_PURCHASE+"/{id}",1L).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.customer.id", is(1)))
                .andExpect(jsonPath("$.shoppingList.[0].id", is(1)))
                .andExpect(jsonPath("$.purchaseDate", is("19-02-2021")))
                .andExpect(jsonPath("$.priceToPay", is(150.2)))
                .andExpect(jsonPath("$.purchaseCompleted", is(true)));

        verify(getPurchaseService).getPurchase(1L);
    }

    @Test
    @DisplayName("Should not get a purchase")
    void shouldNotGetPurchase() throws Exception{
        when(getPurchaseService.getPurchase(anyLong())).thenThrow(new PurchaseNotFoundException());

        mockMvc.perform(get(URL_PURCHASE+"/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getPurchaseService).getPurchase(1L);
    }

    @Test
    @DisplayName("Should list all purchases")
    void shouldListPurchase() throws Exception {
        List<PurchaseDTO> purchaseList = Arrays.asList(createPurchaseDTO().id(1L).build(),createPurchaseDTO().id(2L).build());
        when(listPurchaseService.listPurchases()).thenReturn(purchaseList);

        MvcResult mvcResult = mockMvc.perform(get(URL_PURCHASE).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(2))).andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(purchaseList)).isEqualToIgnoringWhitespace(resultResponseBody);

        verify(listPurchaseService).listPurchases();

    }

    @Test
    @DisplayName("Should get list page")
    void shouldGetListPage() throws Exception {
        Page<PurchaseDTO> purchaseDTOPage = new PageImpl<>(Collections.singletonList(createPurchaseDTO().build()));

        Pageable pageable = PageRequest.of(0,2);

        when(listPagePurchaseService.listPurchaseOnPage(pageable)).thenReturn(purchaseDTOPage);

        MvcResult mvcResult = mockMvc.perform(get(URL_PURCHASE+"/page/?page=0&size=2").accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(11))).andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(objectMapper.writeValueAsString(purchaseDTOPage)).isEqualToIgnoringWhitespace(resultResponseBody);

        verify(listPagePurchaseService).listPurchaseOnPage(pageable);

    }

    @Test
    @DisplayName("Should save a purchase")
    void shouldSavePurchase() throws Exception {
        mockMvc.perform(post(URL_PURCHASE).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
        .content(readJson("purchaseDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(savePurchaseService).save(any(PurchaseDTO.class));

    }

    @Test
    @DisplayName("Should not save without customer")
    void shouldNotSavePurchaseWithoutCustomer() throws Exception {
        PurchaseDTO purchaseDTO = createPurchaseDTO().customer(null).build();

        mockMvc.perform(post(URL_PURCHASE)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(purchaseDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should not save without shopping list")
    void shouldNotSavePurchaseWithoutShoppingList() throws Exception {
        PurchaseDTO purchaseDTO = createPurchaseDTO().shoppingList(null).build();

        mockMvc.perform(post(URL_PURCHASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchaseDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should not save without date")
    void shouldNotSavePurchaseWithoutDate() throws Exception {
        PurchaseDTO purchaseDTO = createPurchaseDTO().purchaseDate("").build();

        mockMvc.perform(post(URL_PURCHASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchaseDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should not save without price")
    void shouldNotSavePurchaseWithoutPrice() throws Exception {
        PurchaseDTO purchaseDTO = createPurchaseDTO().priceToPay(-1).build();

        mockMvc.perform(post(URL_PURCHASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchaseDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    public static String readJson(String file) throws Exception {
        byte [] bytes = Files.readAllBytes(Paths.get("src/test/resources/dataJson/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
