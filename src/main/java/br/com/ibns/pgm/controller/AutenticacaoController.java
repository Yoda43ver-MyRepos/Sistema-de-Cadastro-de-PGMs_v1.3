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


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;


    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenciacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        //quando chamamos o manager.authenticate(token),
        // o próprio Spring encontra a classe AutenticacaoService
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }

}
