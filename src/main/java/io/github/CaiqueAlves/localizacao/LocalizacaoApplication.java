package io.github.CaiqueAlves.localizacao;

import io.github.CaiqueAlves.localizacao.domain.entity.Cidade;
import io.github.CaiqueAlves.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication 
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {
		listarCidadesPorQuantidadeHabitantes();
	}

	void listarCidadesPorQuantidadeHabitantes(){
		cidadeRepository.findByHabitantesLessThanEqualAndNomeLike(700000L, "%Salva%").forEach(System.out::println);
	}

	void listarCidadesPorNome(){
		cidadeRepository.findByNomeLike("%porto%").forEach(System.out::println);
	}

	void listarCidadesPorHabitantes(){
		cidadeRepository.findByHabitantes(23234780L).forEach(System.out::println);
	}

	@Transactional
	void salvarCidade(){
		var cidade = new Cidade(1L, "São Paulo", 12396372L);
		cidadeRepository.save(cidade);
	}

	void listarCidades(){
		cidadeRepository.findAll().forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}
}
