package com.web.admin.Servico.Autenticacao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration // Estamos colocando uma configuração no sistema
public class LoginInterceptorAppConfig extends WebMvcConfigurerAdapter {

    // Essa classe faz uma configuração de filtros que ele vai passar antes de cada ação. A cada requisição que eu fizer, vamos passar primeiro por esse interpretador.
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
            .excludePathPatterns( // Aqui vamos excluir todos esses filtros abaixo
                "/login",
                "/error",
                "/logar",
                "/img/**",
                "/vendor/**",
                "/js/**",
                "/favicon.ico",
                "/css/**"
            );
    }
}
