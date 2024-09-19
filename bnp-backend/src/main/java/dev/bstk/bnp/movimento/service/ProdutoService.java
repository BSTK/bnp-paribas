package dev.bstk.bnp.movimento.service;

import dev.bstk.bnp.movimento.model.Produto;
import dev.bstk.bnp.movimento.model.ProdutoCosif;
import dev.bstk.bnp.movimento.repository.ProdutoReposiroty;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoReposiroty produtoReposiroty;


    public List<Produto> produtos() {
        return produtoReposiroty.findAll();
    }

    public List<ProdutoCosif> cosifs(final String codigoProduto) {
        return produtoReposiroty
            .produtosPorCodigo(codigoProduto)
            .stream()
            .map(Produto::getCosifs)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }
}
