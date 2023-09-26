package io.github.CaiqueAlves.localizacao.domain.repository;

import io.github.CaiqueAlves.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
