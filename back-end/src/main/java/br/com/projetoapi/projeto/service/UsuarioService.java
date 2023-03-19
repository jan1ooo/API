package br.com.projetoapi.projeto.service;

import br.com.projetoapi.projeto.dto.UsuarioDto;
import br.com.projetoapi.projeto.model.Usuario;
import br.com.projetoapi.projeto.repository.IUsuario;
import br.com.projetoapi.projeto.security.Token;
import br.com.projetoapi.projeto.security.TokenUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    private final IUsuario repository;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioService(IUsuario repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    //#GET
    public List<Usuario> listarUsuario(){
        logger.info("Usuario: " + getLogado() + " Listando Usuarios");
        return repository.findAll();
    }

    //#POST
    public Usuario criarUsuario(Usuario usuario){
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        logger.info("Usuario: " + getLogado() + " Criando Usuarios");
        return repository.save(usuario);
    }

    //#PUT
    public Usuario editarUsuario(Usuario usuario){
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        logger.info("Usuario: " + getLogado() + " Editando Usuarios " + usuario);
        return repository.save(usuario);
    }

    //#DELETE
    public Boolean excluirUsuario(Integer id){
        repository.deleteById(id);
        logger.info("Usuario: " + getLogado() + " Excluindo Usuario");
        return true;
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

    private String getLogado(){
        Authentication userLogado = SecurityContextHolder.getContext().getAuthentication();
        if(!(userLogado instanceof AnonymousAuthenticationToken)){
            return userLogado.getName();
        }
        return "null";
    }
}
