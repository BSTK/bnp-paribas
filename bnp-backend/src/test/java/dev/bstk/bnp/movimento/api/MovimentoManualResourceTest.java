package dev.bstk.bnp.movimento.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.bnp.movimento.api.request.MovimentoManualRequest;
import dev.bstk.bnp.movimento.model.MovimentoManualProjection;
import dev.bstk.bnp.movimento.service.MovimentoManualService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MovimentoManualResource.class)
class MovimentoManualResourceTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String URL_MOVIMENTO_MANUAL = "/v1/movimento-manual";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovimentoManualService movimentoManualService;

    @Test
    @DisplayName("Deve carregar a lista de movimentos")
    void t1() throws Exception {
        Mockito
            .when(movimentoManualService.movimentos())
            .thenReturn(
                List.of(
                    movimento()
                )
            );

        mockMvc.perform(get(URL_MOVIMENTO_MANUAL))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].mes").value("01"))
            .andExpect(jsonPath("$[0].ano").value("2024"))
            .andExpect(jsonPath("$[0].codigoProduto").value("P01"))
            .andExpect(jsonPath("$[0].descricaoProduto").value("DESCP01"))
            .andExpect(jsonPath("$[0].numeroLancamento").value("001"))
            .andExpect(jsonPath("$[0].descricao").value("Venda P01"))
            .andExpect(jsonPath("$[0].valor").value("10"));
    }

    @Test
    @DisplayName("Deve cadastrar um novo movimento manual")
    void t2() throws Exception {
        final var request = new MovimentoManualRequest();
        request.setMes((short) 8);
        request.setAno((short) 2024);
        request.setCodigoProduto("P01");
        request.setCodigoCosif("COS01");
        request.setDescricao("Venda P01");
        request.setValor(new BigDecimal("1000.50"));

        mockMvc.perform(
                post(URL_MOVIMENTO_MANUAL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(MAPPER.writeValueAsString(request))
            )
            .andExpect(status().isCreated());
    }

    private MovimentoManualProjection movimento() {
        return new MovimentoManualProjection() {
            @Override
            public Short getMes() { return 1; }

            @Override
            public Short getAno() { return 2024; }

            @Override
            public String getCodigoProduto() { return "P01"; }

            @Override
            public String getDescricaoProduto() { return "DESCP01"; }

            @Override
            public Integer getNumeroLancamento() { return 1; }

            @Override
            public String getDescricao() { return "Venda P01"; }

            @Override
            public BigDecimal getValor() { return BigDecimal.valueOf(10); }
        };
    }

}
