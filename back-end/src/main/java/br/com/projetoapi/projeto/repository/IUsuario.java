package br.com.projetoapi.projeto.repository;

import br.com.projetoapi.projeto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuario extends JpaRepository<Usuario, Integer> {

    public Usuario findBynomeOrEmail(String nome, String email);
}
