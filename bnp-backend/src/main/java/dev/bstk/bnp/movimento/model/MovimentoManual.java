package dev.bstk.bnp.movimento.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "MOVIMENTO_MANUAL")
public class MovimentoManual implements Serializable {

    @EmbeddedId
    private MovimentoManualId id;

    @Column(name = "DES_DESCRICAO")
    private String descricao;

    @Column(name = "DAT_MOVIMENTO")
    private LocalDate dataMovimento;

    @Column(name = "COD_USUARIO")
    private String codigoUsuario;

    @Column(name = "VAL_VALOR")
    private BigDecimal valor;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof MovimentoManual that)) {
            return false;
        }

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
