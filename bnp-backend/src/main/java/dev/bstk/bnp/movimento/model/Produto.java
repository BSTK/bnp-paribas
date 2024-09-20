package dev.bstk.bnp.movimento.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUTO")
public class Produto implements Serializable {

    @Id
    @Column(name = "COD_PRODUTO")
    private String codigo;

    @Column(name = "DES_PRODUTO")
    private String descricao;

    @Column(name = "STA_STATUS")
    private char status;

    @JoinColumn(name = "COD_PRODUTO")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoCosif> cosifs = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Produto produto)) {
            return false;
        }

        return Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
