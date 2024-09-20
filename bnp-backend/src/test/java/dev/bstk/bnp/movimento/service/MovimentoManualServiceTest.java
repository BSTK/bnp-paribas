package dev.bstk.bnp.movimento.service;

import dev.bstk.bnp.movimento.model.MovimentoManual;
import dev.bstk.bnp.movimento.model.MovimentoManualProjection;
import dev.bstk.bnp.movimento.repository.MovimentoManualRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovimentoManualServiceTest {

    @Mock
    private MovimentoManualRepository movimentoManualRepository;

    @Captor
    private ArgumentCaptor<MovimentoManual> movimentoManualCaptor;


    @InjectMocks
    private MovimentoManualService movimentoManualService;


    @Test
    @DisplayName("Deve carregar lista de movimentos")
    void t1() {
        Mockito
            .when(movimentoManualRepository.movimentos())
            .thenReturn(
                List.of(
                    Mockito.mock(MovimentoManualProjection.class),
                    Mockito.mock(MovimentoManualProjection.class)
                )
            );

        final var movimentos = movimentoManualService.movimentos();

        Mockito.verify(movimentoManualRepository).movimentos();

        Assertions
            .assertThat(movimentos)
            .isNotEmpty()
            .hasSize(2);
    }

    @Test
    @DisplayName("Deve incluir um novo movimento")
    void t2() {
        final var numeroLancamento = 10;
        final MovimentoManual movimentoManual = new MovimentoManual();
        movimentoManual.setMes((short) 2);
        movimentoManual.setAno((short) 2024);

        Mockito
            .when(movimentoManualRepository.numeroLancamento(
                movimentoManual.getMes(),
                movimentoManual.getAno()
            )).thenReturn(numeroLancamento);

        movimentoManualService.incluirMovimento(movimentoManual);
        Mockito.verify(movimentoManualRepository).save(movimentoManualCaptor.capture());

        final var movimentoManualSalvo = movimentoManualCaptor.getValue();

        Assertions.assertThat(movimentoManualSalvo).isNotNull();
        Assertions.assertThat(movimentoManualSalvo.getNumeroLancamento()).isEqualTo(numeroLancamento);
    }
}
