package dev.bstk.bnp.movimento.repository;

import dev.bstk.bnp.movimento.model.MovimentoManual;
import dev.bstk.bnp.movimento.model.MovimentoManualId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoManualRepository extends JpaRepository<MovimentoManual, MovimentoManualId> { }
