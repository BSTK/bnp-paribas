package dev.bstk.bnp.movimento.api.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentoManualResponse {

    String mes;
    String ano;
    String produto;
    String cosif;
    String descricao;
    BigDecimal valor;
}
