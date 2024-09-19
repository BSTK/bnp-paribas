package dev.bstk.bnp.movimento.api;

import dev.bstk.bnp.helper.MapperHelper;
import dev.bstk.bnp.movimento.api.request.MovimentoManualRequest;
import dev.bstk.bnp.movimento.api.response.MovimentoManualResponse;
import dev.bstk.bnp.movimento.api.response.ProdutoCosifResponse;
import dev.bstk.bnp.movimento.api.response.ProdutoResponse;
import dev.bstk.bnp.movimento.model.MovimentoManual;
import dev.bstk.bnp.movimento.service.MovimentoManualService;
import dev.bstk.bnp.movimento.service.ProdutoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/movimento-manual")
public class MovimentoManualResource {

    private final ProdutoService produtoService;
    private final MovimentoManualService movimentoManualService;


    @PostMapping
    public ResponseEntity<MovimentoManualResponse> incluirMovimento(@RequestBody @Valid final MovimentoManualRequest request) {
        movimentoManualService.incluirMovimento(new MovimentoManual());

        return ResponseEntity.ok(new MovimentoManualResponse());
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponse>> produtos() {
        final var produtos = produtoService.produtos();
        final var produtosResponse = MapperHelper.to(produtos, ProdutoResponse.class);

        return ResponseEntity.ok(produtosResponse);
    }

    @GetMapping("/cosif/{codigoProduto}")
    public ResponseEntity<List<ProdutoCosifResponse>> produtos(@PathVariable("codigoProduto") final String codigoProduto) {
        final var cosifs = produtoService.cosifs(codigoProduto);
        final var cosifsResponse = MapperHelper.to(cosifs, ProdutoCosifResponse.class);

        return ResponseEntity.ok(cosifsResponse);
    }
}
