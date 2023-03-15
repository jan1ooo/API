package br.com.projetoapi.projeto.service;

import br.com.projetoapi.projeto.model.Usuario;
import br.com.projetoapi.projeto.repository.IUsuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private IUsuario repository;

    public UsuarioService(IUsuario repository){
        this.repository = repository;
    }

    //#GET
    public List<Usuario> listarUsuario(){
        List<Usuario> lista = repository.findAll();
        return lista;
    }

    //#POST
    public Usuario criarUsuario(Usuario usuario){
        Usuario usuarioNovo = repository.save(usuario);
        return usuarioNovo;
    }

    //#PUT
    public Usuario editarUsuario(Usuario usuario){
        Usuario usuarioEditado = repository.save(usuario);
        return usuarioEditado;
    }

    //#DELETE
    public Boolean excluirUsuario(Integer id){
        repository.deleteById(id);
        return true;
    }

}
