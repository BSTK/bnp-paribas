package dev.bstk.bnp.movimento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "MOVIMENTO_MANUAL")
@IdClass(MovimentoManualPK.class)
public class MovimentoManual implements Serializable {

    @Id
    @Column(name = "DAT_MES")
    private Short mes;

    @Id
    @Column(name = "DAT_ANO")
    private Short ano;

    @Id
    @Column(name = "NUM_LANCAMENTO")
    private Integer numeroLancamento;

    @Id
    @Column(name = "COD_PRODUTO")
    private String codigoProduto;

    @Id
    @Column(name = "COD_COSIF")
    private String codigoCosif;

    @Column(name = "DES_DESCRICAO")
    private String descricao;

    @CreationTimestamp
    @Column(name = "DAT_MOVIMENTO")
    private LocalDateTime dataMovimento;

    @Column(name = "COD_USUARIO")
    private String codigoUsuario;

    @Column(name = "VAL_VALOR")
    private BigDecimal valor;

    @Transient
    private String descricaoProduto;

    @PrePersist
    private void preInsert() {
        setCodigoUsuario("TESTE");
        setDataMovimento(LocalDateTime.now());
    }
}
