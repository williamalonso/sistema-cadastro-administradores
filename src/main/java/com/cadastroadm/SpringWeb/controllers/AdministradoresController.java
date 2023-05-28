package com.cadastroadm.SpringWeb.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cadastroadm.SpringWeb.models.Administrador;
import com.cadastroadm.SpringWeb.repositorio.AdministradoresRepo;

@Controller
@ComponentScan("com.cadastroadm.Servico.Autenticacao")
public class AdministradoresController {
    
    @Autowired // toda vez que precisarmos utilizar a interface 'AdministradoresRepo', vai ser criada uma nova instância dela automaticamente
    private AdministradoresRepo repo; // variável do tipo AdministradoresRepo (interface) que usa funções prontas de CRUD

    @RequestMapping("/") // quando a url for '/administradores'. Também poderia ser @GetMapping
    public String index(Model model) throws UnsupportedEncodingException { //esse "Model model" faz com que a gente possa adicionar dados que vamos enviar para nosso template html

        List<Administrador> administradores = (List<Administrador>)repo.findAll(); // a variável "administradores" é do tipo List, ou seja, na verdade é uma lista e não variável, e busca todos os dados do Banco. O "List<Administrador>" na direita é um casting para ele trazer os resultados de findAll() e converter para o tipo List.

        model.addAttribute("administradores", administradores); // O primeiro atributo "administradores" é o atributo que vamos enviar para o html. O segundo parâmetro que vamos enviar é uma lista com os dados do Banco
        // model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));

        return "home/index";
    }

    @RequestMapping("/administradores/novo") // também poderia ser @GetMapping
    public String novo() {
        return "administradores/novo";
    }

    // Função que cria dado no Banco
    @PostMapping("/administradores/criar")
    public String criar(Administrador administrador) { // usando o "Administrador administrador" o Spring já pega os dados do formulário e converte para um objeto do tipo Administrador, pronto para uso.
        repo.save(administrador); // aqui estamos salvando o objeto "administrador" no Banco, usando a variável "repo" declarada lá em cima
        return "redirect:/"; // após salvar, redireciona para minha lista (tabela do html)
    }

    // Função que exclui
    @GetMapping("/administradores/{id}/excluir")
    public String excluir(@PathVariable int id) { // o PathVariable faz com que eu possa receber o id
        repo.deleteById(id); // deleta o dado no Banco
        return "redirect:/";
    }

    // Função que busca dados de um ID
    @GetMapping("/administradores/{id}/buscar")
    public String busca(@PathVariable int id, Model model) { // o PathVariable faz com que eu possa receber o id
        Optional<Administrador> administrador = (Optional<Administrador>)repo.findById(id); // Busca os dados de um ID. Como o Banco pode retornar nulo, temos que usar o "Optional"
        
        try { // esse try catch é para o caso de o "administrador.get()" der erro na busca, por exemplo, se tentar buscar um ID muito alto e que não existe no Banco

            model.addAttribute("administrador", administrador.get()); // O primeiro atributo "administrador" é o atributo que vamos enviar para o html. O segundo parâmetro é o que busca os dados do banco na linha acima. Aqui estamos usando o ".get()" porque usamos o "Optional" acima, ou seja, passamos o Optional ao invés do objeto Administrador.

        } catch(Exception err) {

            return "redirect:/";

        }
        return "/administradores/editar"; // retorna para o html editar.html na pasta "administradores"
    }
    
    // Função que atualiza um ID no Banco
    @PostMapping("/administradores/{id}/atualizar")
    public String atualiza(@PathVariable int id, Administrador administrador) {
        
        // Exemplo usando query escrita na mão
        // if(!repo.exist(id)) { // Se não existir o 'id' no Banco, redireciona para a página principal. Aqui estamos usando um método criando a query lá no "AdministradoresRepo"
        //     return "redirect:/administradores";
        // }

        // usando um método CRUD já existente
        if(!repo.existsById(id)) { // Se não existir o 'id' no Banco, redireciona para a página principal.
            return "redirect:/";
        }

        repo.save(administrador);  // Se estiver tudo ok, atualizamos o objeto no Banco
        return "redirect:/";
        
    }
    
}
