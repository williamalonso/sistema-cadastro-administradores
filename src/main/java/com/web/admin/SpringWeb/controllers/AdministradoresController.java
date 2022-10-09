package com.web.admin.SpringWeb.controllers;

import java.util.List;

import com.web.admin.SpringWeb.models.Administrador;
import com.web.admin.SpringWeb.repositorio.AdministradoresRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class AdministradoresController {

    @Autowired // toda vez que precisarmos utilizar a interface 'AdministradoresRepo', vai ser criada uma nova instância dela automaticamente
    private AdministradoresRepo repo; // variável do tipo AdministradoresRepo (interface) que usa funções prontas de CRUD


    @RequestMapping("/administradores") // quando a url for '/administradores'
    public String index(Model model) { //esse "Model model" faz com que a gente possa adicionar dados que vamos enviar para nosso template html

        List<Administrador> administradores = (List<Administrador>)repo.findAll(); // a variável "administradores" é do tipo List, ou seja, na verdade é uma lista e não variável, e busca todos os dados do Banco. O "List<Administrador>" na direita é um casting para ele trazer os resultados de findAll() e converter para o tipo List.

        model.addAttribute("administradores", administradores); // O primeiro atributo "administradores" é o atributo que vamos enviar para o html. O seguno parâmetro que vamos enviar é uma lista com os dados do Banco

        return "administradores/index";
    }
    
}
