package dev.bstk.bnp.movimento.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimentoManualPK implements Serializable {

    private Short mes;
    private Short ano;
    private String codigoCosif;
    private String codigoProduto;
    private Integer numeroLancamento;
}
