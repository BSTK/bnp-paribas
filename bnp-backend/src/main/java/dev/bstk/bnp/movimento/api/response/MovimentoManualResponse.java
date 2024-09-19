package dev.bstk.bnp.movimento.api.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MovimentoManualResponse {

    String mes;
    String ano;
    String codigoProduto;
    String descricaoProduto;
    String numeroLancamento;
    String descricao;
    BigDecimal valor;
}
