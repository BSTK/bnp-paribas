package dev.bstk.bnp.movimento.service;

import dev.bstk.bnp.movimento.model.MovimentoManual;
import dev.bstk.bnp.movimento.repository.MovimentoManualRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovimentoManualService {

    private final MovimentoManualRepository movimentoManualRepository;


    public List<MovimentoManual> movimentos() {
        return movimentoManualRepository.findAll();
    }

    public MovimentoManual incluirMovimento(final MovimentoManual movimentoManual) {
        return null;
    }
}
