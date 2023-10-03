package io.github.CaiqueAlves.localizacao.service;

import io.github.CaiqueAlves.localizacao.domain.entity.Cidade;
import io.github.CaiqueAlves.localizacao.domain.repository.CidadeRepository;
import static io.github.CaiqueAlves.localizacao.domain.repository.specs.CidadeSpecs.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

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

    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);
        return repository.findAll(example);
    }


    public void listarCidadesByNomeSpec(){
        repository
                .findAll(propertyEqual("nome","São Paulo").and(idEqual(1L)))
                .forEach(System.out::println);
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(filtro.getId() != null){
            specs = specs.and(idEqual(filtro.getId()));
        }

        if(StringUtils.hasText(filtro.getNome())){
            specs = specs.and(nomeLike(filtro.getNome()));
        }

        if(filtro.getHabitantes() != null){
            specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
        }

        repository.findAll(specs).forEach(System.out::println);
    }
}
