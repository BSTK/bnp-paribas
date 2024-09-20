package dev.bstk.bnp.movimento.api;

import dev.bstk.bnp.helper.MapperHelper;
import dev.bstk.bnp.movimento.api.request.MovimentoManualRequest;
import dev.bstk.bnp.movimento.api.response.MovimentoManualResponse;
import dev.bstk.bnp.movimento.model.MovimentoManual;
import dev.bstk.bnp.movimento.model.MovimentoManualProjection;
import dev.bstk.bnp.movimento.service.MovimentoManualService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> incluirMovimento(@RequestBody @Valid final MovimentoManualRequest request) {
        final var movimentoManual = MapperHelper.to(request, MovimentoManual.class);
        movimentoManualService.incluirMovimento(movimentoManual);

        return ResponseEntity.status(HttpStatus.CREATED).build();
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

    private MovimentoManualResponse movimentoResponse(final MovimentoManualProjection movimentoManual) {
        return MovimentoManualResponse
            .builder()
            .valor(movimentoManual.getValor())
            .descricao(movimentoManual.getDescricao())
            .codigoProduto(movimentoManual.getCodigoProduto())
            .mes(String.format("%02d", movimentoManual.getMes()))
            .ano(String.valueOf(movimentoManual.getAno()))
            .descricaoProduto(movimentoManual.getDescricaoProduto())
            .numeroLancamento(String.format("%03d", movimentoManual.getNumeroLancamento()))
            .build();
    }
}
