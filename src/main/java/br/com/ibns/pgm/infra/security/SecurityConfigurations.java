package br.com.ibns.pgm.infra.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


//É nesta classe que vamos concentrar as informações de segurança
// do Spring Security
//Adicioando a annotation '@EnableWebSecurity' para personalizar as configurações de segurança,

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {


    /*O  SecurityFilterChain do Spring é usado para
    configurar o processo de autenticação e de autorização


    A anotação @Bean no Spring Framework desempenha um papel
    fundamental na configuração e gerenciamento de beans.
    Ela indica ao container Spring que o método anotado é
    responsável por criar e configurar um objeto que será gerenciado pelo
    container. Em outras palavras, o método retorna um bean que pode ser
    injetado em outras partes da sua aplicação.
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{

        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
