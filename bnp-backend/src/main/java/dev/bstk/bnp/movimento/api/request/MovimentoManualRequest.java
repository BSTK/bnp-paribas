package dev.bstk.bnp.movimento.api.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentoManualRequest {

    @NotNull
    @Min(1)
    @Max(12)
    private Short mes;

    @NotNull
    private Short ano;

    @NotEmpty
    private String codigoProduto;

    @NotEmpty
    private String codigoCosif;

    @NotEmpty
    private String descricao;

    @NotNull
    private BigDecimal valor;
}
