package br.com.ibns.pgm.controller;

import br.com.ibns.pgm.domain.usuario.DadosAutenciacao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
1 - O processo de autenticação está na classe AutenticacaoService.
    Vou chamar o método loadUserByUsername,pois é ele que usa o repository
    para efetuar o select no banco de dados.


2 -  Porém, não devo chamar a classe service de forma direta no Spring Security.
     Vou usar outra classe do Spring que  que vai chamar a AutenticacaoService.

 3 -  A classe 'AuthenticationManager' do Spring é
      responsável por disparar o processo de autenticação.
*/

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
/*
        *** ATENÇÃO ***
1 - A interface AuthenticationManager é do Spring.
    Porém, ele não injeta de forma automática o objeto
    AuthenticationManager, precisamos configurar isso
    no Spring Security.
    Como não configurei, ele não cria o objeto AuthenticationManager e
    lança uma exceção.

   2 - Vou criar  um método  público (na classe:SecurityConfiguration),
       cujo retorno é o objeto AuthenticationManager
        e o nome será 'authenticationManager'.
 */

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenciacao dados){
        var token =  new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        //authenticate(token)devolve o objeto que representa o usuário autenticado no sistema.
         var  authentication = manager.authenticate(token);
         return ResponseEntity.ok().build();
    }

}
