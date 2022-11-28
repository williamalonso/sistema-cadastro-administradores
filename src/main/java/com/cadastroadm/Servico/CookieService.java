package com.cadastroadm.Servico;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieService {
    // Essa função é para gravar cookie
    public static void setCookie(HttpServletResponse response, String Key, String Valor, int segundos) throws IOException { // esse response é um recurso do Http onde a gente escreve alguma coisa. Nesse caso, o que vai ser escrito é o Cookie.

        Cookie cookie = new Cookie(Key, URLEncoder.encode( Valor, "UTF-8" )); // cria o cookie. O "Key" vai ser o nome desse cookie, e o valor estamos colocando no UTF-8 para aceitar espaços e acentuações
        cookie.setMaxAge(segundos); // é a duração/tempo que o cookie vai ficar ativo
        cookie.setHttpOnly(true); // não permite o cookie ser alterado por javascript, garantindo mais segurança.
        response.addCookie(cookie); // adiciona o cookie ao response

    }

    // Essa função é para ler cookie
    public static String getCookie(HttpServletRequest request, String key) throws UnsupportedEncodingException {
        // No "HttpServletResponse" eu gravo o cookie e no "HttpServletRequest" eu recupero esse cookie
        
        String valor = Optional.ofNullable(request.getCookies()) // nessa linha ele traz todos os meus cookies ou null. Ele verifica se o cookie não é null
            .flatMap(cookies -> Arrays.stream(cookies) // traz os cookies parseados em um array
                .filter(cookie -> key.equals(cookie.getName())) // filtra os cookies pelo nome
                .findAny()
            ).map(e -> e.getValue()) // pega o valor do cookie e retorna
            .orElse(null); // se não achar o cookie, retorna null
        
        valor = URLDecoder.decode(valor, "UTF-8"); // faz um Decode do valor para exibir a mensagem no site
        return valor;

    }
}
