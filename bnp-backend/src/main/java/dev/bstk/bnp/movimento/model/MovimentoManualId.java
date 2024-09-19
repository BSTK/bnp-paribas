package dev.bstk.bnp.movimento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class MovimentoManualId implements Serializable {

    @Column(name = "DAT_MES")
    private Short mes;

    @Column(name = "DAT_ANO")
    private Short ano;

    @Column(name = "NUM_LANCAMENTO")
    private Integer numeroLancamento;

    @ManyToOne
    @JoinColumn(name = "COD_PRODUTO")
    private Produto produto;

    @OneToOne
    @JoinColumn(name = "COD_COSIF")
    private ProdutoCosif produtoCosif;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof MovimentoManualId that)) {
            return false;
        }

        return Objects.equals(mes, that.mes) && Objects.equals(ano, that.ano)
            && Objects.equals(numeroLancamento, that.numeroLancamento)
            && Objects.equals(produto, that.produto) && Objects.equals(produtoCosif,
            that.produtoCosif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mes, ano, numeroLancamento, produto, produtoCosif);
    }
}
