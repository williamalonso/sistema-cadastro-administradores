package com.web.admin.SpringWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
	public String index(Model model) { //esse "Model model" faz com que a gente possa adicionar dados que vamos enviar para nosso template html

		model.addAttribute("nome", "William"); // "nome" é o atributo que vamos enviar para o html

		return "home/index";
	}
}
