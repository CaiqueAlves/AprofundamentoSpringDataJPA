package io.github.CaiqueAlves.localizacao.service;

import io.github.CaiqueAlves.localizacao.domain.entity.Cidade;
import io.github.CaiqueAlves.localizacao.domain.repository.CidadeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository){
        this.repository = repository;
    }

    public void listarCidadesPorQuantidadeHabitantes(){
        repository.findByHabitantesLessThanEqualAndNomeLike(700000L, "%Salva%").forEach(System.out::println);
    }

    public void listarCidadesPorNome(){
        Pageable pageable = PageRequest.of(2, 3);
        repository
                .findByNomeLike("%%%", pageable)
                .forEach(System.out::println);
    }

    public void listarCidadesPorHabitantes(){
        repository.findByHabitantes(23234780L).forEach(System.out::println);
    }

    @Transactional
    void salvarCidade(){
        var cidade = new Cidade(1L, "São Paulo", 12396372L);
        repository.save(cidade);
    }

    void listarCidades(){
        repository.findAll().forEach(System.out::println);
    }
}
