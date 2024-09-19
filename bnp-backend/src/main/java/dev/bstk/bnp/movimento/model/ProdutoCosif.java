package dev.bstk.bnp.movimento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "PRODUTO_COSIF")
public class ProdutoCosif implements Serializable {

    @Id
    @Column(name = "COD_COSIF")
    private String codigoCosif;

    @Column(name = "COD_CLASSIFICACAO")
    private String codigoClassificacao;

    @Column(name = "STA_STATUS")
    private char status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ProdutoCosif that)) {
            return false;
        }

        return Objects.equals(codigoCosif, that.codigoCosif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoCosif);
    }
}