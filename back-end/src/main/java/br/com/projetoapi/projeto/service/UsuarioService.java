package br.com.projetoapi.projeto.service;

import br.com.projetoapi.projeto.dto.UsuarioDto;
import br.com.projetoapi.projeto.model.Usuario;
import br.com.projetoapi.projeto.repository.IUsuario;
import br.com.projetoapi.projeto.security.Token;
import br.com.projetoapi.projeto.security.TokenUtil;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private IUsuario repository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(IUsuario repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    //#GET
    public List<Usuario> listarUsuario(){
        List<Usuario> lista = repository.findAll();
        return lista;
    }

    //#POST
    public Usuario criarUsuario(Usuario usuario){
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        Usuario usuarioNovo = repository.save(usuario);
        return usuarioNovo;
    }

    //#PUT
    public Usuario editarUsuario(Usuario usuario){
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        Usuario usuarioEditado = repository.save(usuario);
        return usuarioEditado;
    }

    //#DELETE
    public Boolean excluirUsuario(Integer id){
        repository.deleteById(id);
        return true;
    }

    //#Validar Senha
    public Boolean validarSenha(Usuario usuario) {
        String senha = repository.getById(usuario.getId()).getSenha();
        Boolean valid = passwordEncoder.matches(usuario.getSenha(), senha);
        return valid;
    }

    public Token gerarToken(@Valid UsuarioDto usuario) {
        Usuario user = repository.findBynomeOrEmail(usuario.getNome(), usuario.getEmail());
        if(user != null){
            Boolean valid = passwordEncoder.matches(usuario.getSenha(), user.getSenha());
            if(valid){
                return new Token(TokenUtil.createToken(user));
            }
        }
        return null;
    }
}
