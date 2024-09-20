package dev.bstk.bnp.movimento.model;

import java.math.BigDecimal;

public interface MovimentoManualProjection {

    Short getMes();
    Short getAno();
    String getCodigoProduto();
    String getDescricaoProduto();
    Integer getNumeroLancamento();
    String getDescricao();
    BigDecimal getValor();
}
