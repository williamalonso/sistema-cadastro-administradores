package com.web.admin.SpringWeb.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.admin.Servico.CookieService;

@Controller
public class HomeController {
    @RequestMapping("/")
	public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException { //esse "Model model" faz com que a gente possa adicionar dados que vamos enviar para nosso template html

		model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario")); // "nome" é o atributo que vamos enviar para o html. Aqui estamos puxando o nome do Cookie que é salvo na hora que é feito o Login

		return "home/index";
	}
}
