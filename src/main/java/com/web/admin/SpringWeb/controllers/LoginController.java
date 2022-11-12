package com.web.admin.SpringWeb.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.admin.Servico.CookieService;
import com.web.admin.SpringWeb.models.Administrador;
import com.web.admin.SpringWeb.repositorio.AdministradoresRepo;

@Controller
public class LoginController {

    @Autowired // precisamos dessa notação para usar o repositório 'AdministradoresRepo'
    private AdministradoresRepo repo; // variável do tipo AdministradoresRepo (interface) que usa funções prontas de CRUD. Usamos para acessar o Banco de dados, para validar se o usuário existe ou não
    
    @GetMapping("/login") // url
    public String index() { // redireciona user para página de fazer login
        return "login/index";
    }

    @PostMapping("/logar") // url
    public String logar(Model model, Administrador admParam, String lembrar, HttpServletResponse response) throws IOException { // quando usuário enviar form do login. Aqui estmos usando "Model model" para o caso de precisarmos enviar uma mensagem de erro de login para o usuário, aí colocamos a informação atributo 'model' e passamos para o template.
    // no lugar de "Administrador admParam" também poderíamos fazer: "String email, String senha, String lembrar"
    // O "HttpServletResponse response" é para criar um cookie para proteger a rota do login. Para usar o cookie, nós criamos a pasta "Servico" com a classe "CookieService.java"
    
        // vamos validar se o dado vindo do Form existe no banco para permitir o Login. Para isso criamos o método 'Login()' no "AdministradoresRepo"
        Administrador adm = this.repo.Login(admParam.getEmail(), admParam.getSenha()); // se der tudo certo, teremos como resposta um objeto
        
        if( adm != null ) { // se o Login deu certo, fazemos um redirect
            int tempoLogado = (60*60); // 1 hora de cookie
            if(lembrar != null) {
                tempoLogado = (60*60*24*365); // Se usuário marcou caixa "Lembrar" na tela do Login, fazemos 1 ano de cookie
            }
            CookieService.setCookie(response, "usuarioId", String.valueOf(adm.getId()), tempoLogado); // o "usuariosId" vai ser o nome do cookie que vamos gravar
            CookieService.setCookie(response, "nomeUsuario", String.valueOf(adm.getNome()), tempoLogado); // esse Cookie é para gravar o nome do usuário e exibir na tela após o Login no sistema
            return "redirect:/";
        }

        // Se o login der errado, enviamos a variável "erro" para o template, com a mensagem "Usuário ou senha inválidos"
        model.addAttribute("erro", "Usuário ou senha inválidos");

        return "login/index";
        
    }

    @GetMapping("/sair") // quando clicar para sair do sistema
    public String logar(HttpServletResponse response) throws IOException { // esse response traz as informações dos meus Cookies
        CookieService.setCookie(response, "usuarioId", "", 0); // aqui estamos colocando o tempo do Cookie para 0 segundos e com valor vazio
        return "redirect:/login";
    }

}
