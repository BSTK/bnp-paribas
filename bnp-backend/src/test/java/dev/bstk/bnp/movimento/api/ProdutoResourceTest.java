package dev.bstk.bnp.movimento.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.bstk.bnp.movimento.model.Produto;
import dev.bstk.bnp.movimento.model.ProdutoCosif;
import dev.bstk.bnp.movimento.service.ProdutoService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProdutoResource.class)
class ProdutoResourceTest {

    private static final String URL_PRODUTOS = "/v1/produtos";
    private static final String URL_COSIFS = "/v1/produtos/cosif/{codigoProduto}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    @DisplayName("Deve carregar a lista de produtos")
    void t1() throws Exception {
        Mockito
            .when(produtoService.produtos())
                .thenReturn(
                    List.of(
                        Produto.builder().descricao("P1").codigo("CP1").build(),
                        Produto.builder().descricao("P2").codigo("CP1").build()
                    )
                );

        mockMvc.perform(get(URL_PRODUTOS))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].codigo").value("CP1"))
            .andExpect(jsonPath("$[0].descricao").value("P1"));
    }

    @Test
    @DisplayName("Deve carregar a lista de cosifs dado um código de produto")
    void t2() throws Exception {
        Mockito
            .when(produtoService.cosifs("CP1"))
            .thenReturn(
                List.of(
                    ProdutoCosif.builder().codigoCosif("CP1").codigoClassificacao("CLA01").build(),
                    ProdutoCosif.builder().codigoCosif("CP2").codigoClassificacao("CLA02").build()
                )
            );

        mockMvc.perform(get(URL_COSIFS, "CP1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].codigoCosif").value("CP1"))
            .andExpect(jsonPath("$[0].codigoClassificacao").value("CLA01"));
    }
}
