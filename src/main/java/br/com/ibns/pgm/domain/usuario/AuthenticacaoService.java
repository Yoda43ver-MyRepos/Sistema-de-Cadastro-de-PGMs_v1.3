package br.com.ibns.pgm.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//Esta classe contem a lógica de autenticação  no projeto.
@Service
public class AuthenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;


    // O Spring chama este  método  de forma automática ao efetuarmos o login.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);

    }




}
