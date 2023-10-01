package io.github.CaiqueAlves.localizacao.domain.repository;

import io.github.CaiqueAlves.localizacao.domain.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    List<Cidade> findByNome(String nome);

    List<Cidade> findByNomeStartingWith(String nome);

    List<Cidade> findByNomeEndingWith(String nome);

    List<Cidade> findByNomeContaining(String nome);

    @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
    List<Cidade> findByNomeLike(String nome, Sort sort);

    @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
    Page<Cidade> findByNomeLike(String nome, Pageable pageable);

    List<Cidade> findByHabitantes(long habitantes);

    //menor que
    List<Cidade> findByHabitantesLessThan(long habitantes);

    //maior que
    List<Cidade> findByHabitantesGreaterThan(long habitantes);

    //maior igual
    List<Cidade> findByHabitantesGreaterThanEqual(long habitantes);

    //listar cidades com numero de habitantes >= e nome parecido com ""
    List<Cidade> findByHabitantesLessThanEqualAndNomeLike(long habitantes, String nome);
}
