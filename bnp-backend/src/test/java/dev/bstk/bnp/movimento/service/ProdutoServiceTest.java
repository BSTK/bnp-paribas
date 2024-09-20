package dev.bstk.bnp.movimento.service;


import dev.bstk.bnp.movimento.model.Produto;
import dev.bstk.bnp.movimento.model.ProdutoCosif;
import dev.bstk.bnp.movimento.repository.ProdutoReposiroty;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoReposiroty produtoReposiroty;

    @InjectMocks
    private ProdutoService produtoService;


    @Test
    @DisplayName("Deve carregar lista de produtos")
    void t1() {
        Mockito
            .when(produtoReposiroty.findAll())
            .thenReturn(produtos());

        final var produtos = produtoService.produtos();

        Mockito.verify(produtoReposiroty).findAll();

        Assertions
            .assertThat(produtos)
            .isNotEmpty();
    }

    @Test
    @DisplayName("Deve carregar lista de cosifs")
    void t2() {
        final var codigoCosif = "P1";
        final var produto = produtos().get(0);

        Mockito
            .when(produtoReposiroty.produtosPorCodigo(codigoCosif))
            .thenReturn(Optional.of(produto));

        final var cosifs = produtoService.cosifs(codigoCosif);

        Assertions
            .assertThat(cosifs)
            .isNotEmpty()
            .hasSize(2);
    }

    @Test
    @DisplayName("Deve lançar exception ao bsucar a lista de cosif com um codigo de produto inválido")
    void t3() {
        final var codigoCosif = "P99";

        Mockito
            .when(produtoReposiroty.produtosPorCodigo(codigoCosif))
            .thenReturn(Optional.empty());

        Assertions
            .assertThatThrownBy(() -> produtoService.cosifs(codigoCosif))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Código Produto inválido!");
    }

    private List<Produto> produtos() {
        return List.of(
            Produto.builder()
                .codigo("P1")
                .cosifs(List.of(
                    ProdutoCosif.builder().build(),
                    ProdutoCosif.builder().build()
                )).build(),
            Produto.builder()
                .codigo("P2")
                .cosifs(List.of(ProdutoCosif.builder().build())).build()
        );
    }
}
