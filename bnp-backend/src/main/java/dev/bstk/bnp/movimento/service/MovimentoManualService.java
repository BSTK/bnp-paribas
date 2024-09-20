package dev.bstk.bnp.movimento.service;

import dev.bstk.bnp.movimento.model.MovimentoManual;
import dev.bstk.bnp.movimento.model.MovimentoManualProjection;
import dev.bstk.bnp.movimento.repository.MovimentoManualRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovimentoManualService {

    private final MovimentoManualRepository movimentoManualRepository;


    public List<MovimentoManualProjection> movimentos() {
        return movimentoManualRepository.movimentos();
    }

    public void incluirMovimento(final MovimentoManual movimentoManual) {
        final var numeroLancamento = movimentoManualRepository.numeroLancamento(
            movimentoManual.getMes(),
            movimentoManual.getAno()
        );

        movimentoManual.setNumeroLancamento(numeroLancamento);

        movimentoManualRepository.save(movimentoManual);
    }
}
