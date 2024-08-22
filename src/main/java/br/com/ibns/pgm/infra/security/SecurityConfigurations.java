package br.com.ibns.pgm.infra.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


    /*
    1 - A interface AuthenticationManager(usada no AutenticacaoController) é do Spring.
        Porém, ele não injeta de forma automática o objeto
        AuthenticationManager, precisamos configurar isso
        no Spring Security. Como não configuramos,
        ele não cria o objeto AuthenticationManager e lança uma exceção.

       2 - Vou criar  um método  público, cujo retorno é o objeto AuthenticationManager
           e o nome será 'authenticationManager'.

       3 - Esse é o método que   informa Spring como
            injetar objetos. Portanto, vou inclluir a anotacao @Bean.

       4 -A anotação @Bean serve para exportar uma classe para o Spring,
          fazendo com que ele consiga carregá-la e realizar a sua injeção de
          dependência em outras classes.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        //getAuthenticationManager()  cria o objeto AuthenticationManager
        return configuration.getAuthenticationManager();
    }


}
