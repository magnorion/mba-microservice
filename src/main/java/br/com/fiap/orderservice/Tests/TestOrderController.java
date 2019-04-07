package br.com.fiap.orderservice.Tests;

import br.com.fiap.orderservice.Entity.OrderEntity;
import br.com.fiap.orderservice.Entity.Pagamento;
import br.com.fiap.orderservice.Entity.Produto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(MockitoJUnitRunner.class)
public class TestOrderController {

    @Mock
    Map<String, OrderEntity> result;

    @InjectMocks
    OrderEntity entity;

    @Autowired
    private MockMvc mvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(OrderEntity.class);
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1, "um produto legal", 100, 1));

        Pagamento pagamento = new Pagamento(1, 50000000, "10/10/2020", "Master");

        this.entity = new OrderEntity(2, "lucas@teste.com", "teste",
                "rua teste", produtos, pagamento, "10/10/2019", "em andamento");
    }

    @Test
    public void FindObjectOrder() {
        Mockito.when(result.get("Order")).thenReturn(entity);
        assertEquals(entity, result.get("Order"));
    }

    @Test
    public void SaveOrder() {
        try {
            this.mvc.perform(post("/order/save")
                    .content(toJson(this.entity))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void FindOrderById() {
        try {
            this.mvc.perform(get("/order/findById" + this.entity.getId())
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
