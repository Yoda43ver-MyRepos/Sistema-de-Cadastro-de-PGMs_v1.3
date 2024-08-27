package br.com.ibns.pgm.infra;


import br.com.ibns.pgm.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("{api.security.token.secret}")
    private String secret;

    //O metodo abaixo retorna uma String, que representa o token a ser gerado
    public String gerarToken(Usuario usuario) {
             try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Cad_PGM")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar token: " + TokenService.class + "," + exception);
        }
    }


    public String getSubject(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Cad_PGM")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    private Instant dataExpiracao() {
       // return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-2:00"));
        ZoneOffset zoneOffset = ZoneOffset.of("Z"); // Criando um ZoneOffset UTC
        return LocalDateTime.now().plusHours(2).atOffset(zoneOffset).toInstant();
    }

}
