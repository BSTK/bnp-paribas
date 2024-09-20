package dev.bstk.bnp.movimento.repository;

import dev.bstk.bnp.movimento.model.MovimentoManual;
import dev.bstk.bnp.movimento.model.MovimentoManualProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoManualRepository extends JpaRepository<MovimentoManual, Short> {

    @Query("""
        SELECT COALESCE(MAX(m.numeroLancamento), 0) + 1 AS numeroLancamento
        FROM MovimentoManual m
        WHERE m.mes = :mes
        AND   m.ano = :ano
        """)
    Integer numeroLancamento(@Param("mes") final Short mes, @Param("ano") final Short ano);

    @Query(value = """
        SELECT
            m.DAT_MES        as mes,
            m.DAT_ANO        as ano,
            m.COD_PRODUTO    as codigoProduto,
            p.DES_PRODUTO    as descricaoProduto,
            m.NUM_LANCAMENTO as numeroLancamento,
            m.DES_DESCRICAO  as descricao,
            m.VAL_VALOR      as valor
        FROM MOVIMENTO_MANUAL   m
        LEFT JOIN PRODUTO       p  ON p.COD_PRODUTO = m.COD_PRODUTO
        ORDER BY m.DAT_MOVIMENTO DESC
        """, nativeQuery = true
    )
    List<MovimentoManualProjection> movimentos();
}
