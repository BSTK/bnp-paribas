package dev.bstk.bnp.movimento.repository;

import dev.bstk.bnp.movimento.model.Produto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoReposiroty extends JpaRepository<Produto, Long> {

    @Query("""
        SELECT p
        FROM Produto p
        LEFT JOIN FETCH p.cosifs
        WHERE p.codigo = :codigoProduto
        """
    )
    Optional<Produto> produtosPorCodigo(@Param("codigoProduto") final String codigoProduto);
}
