package dev.bstk.bnp.movimento.api.request;

import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentoManualRequest {

    @NotEmpty
    private String mes;

    @NotEmpty
    private String ano;

    @NotEmpty
    private String produto;

    @NotEmpty
    private String cosif;

    @NotEmpty
    private String descricao;

    @NotEmpty
    private BigDecimal valor;
}
