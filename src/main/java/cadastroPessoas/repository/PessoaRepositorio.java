package cadastroPessoas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cadastroPessoas.model.Pessoa;

public interface PessoaRepositorio extends CrudRepository<Pessoa, Long> {
	public List<Pessoa> findAll();
}
