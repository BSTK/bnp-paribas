package dev.bstk.bnp.movimento.api;

import dev.bstk.bnp.movimento.api.request.MovimentoManualRequest;
import dev.bstk.bnp.movimento.api.response.MovimentoManualResponse;
import dev.bstk.bnp.movimento.model.MovimentoManual;
import dev.bstk.bnp.movimento.service.MovimentoManualService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/movimento-manual")
public class MovimentoManualResource {

    private final MovimentoManualService movimentoManualService;


    @PostMapping
    public ResponseEntity<MovimentoManualResponse> incluirMovimento(@RequestBody @Valid final MovimentoManualRequest request) {
        movimentoManualService.incluirMovimento(new MovimentoManual());

        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<List<MovimentoManualResponse>> movimentos() {
        final var movimentos = movimentoManualService.movimentos();
        final var movimentosResponse = movimentos
            .stream()
            .map(this::movimentoResponse)
            .toList();

        return ResponseEntity.ok(movimentosResponse);
    }

    private MovimentoManualResponse movimentoResponse(final MovimentoManual movimentoManual) {
        final var movimentoId = movimentoManual.getId();

        return MovimentoManualResponse
            .builder()
            .mes(String.format("%02d", movimentoId.getMes()))
            .ano(String.format("%02d", movimentoId.getAno()))
            .codigoProduto(movimentoId.getProduto().getCodigo())
            .descricaoProduto(movimentoId.getProduto().getDescricao())
            .numeroLancamento(String.valueOf(movimentoId.getNumeroLancamento()))
            .descricao(movimentoManual.getDescricao())
            .valor(movimentoManual.getValor())
            .build();
    }
}
