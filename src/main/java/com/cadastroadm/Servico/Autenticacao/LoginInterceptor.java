package com.cadastroadm.Servico.Autenticacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cadastroadm.Servico.CookieService;

@Component
public class LoginInterceptor implements HandlerInterceptor{
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        try {
            if( CookieService.getCookie(request, "usuarioId") != null ) { // se o meu cookie for diferente de null, permite o usuário acessar o sistema
                return true;
            }
        }
        catch(Exception erro) {}

        response.sendRedirect("/login"); // se não achar o cookie, retorna a página de login
        return false;
    }

}
