package br.com.projetoapi.projeto.DAO;

import br.com.projetoapi.projeto.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuario extends CrudRepository<Usuario, Integer> {
}
