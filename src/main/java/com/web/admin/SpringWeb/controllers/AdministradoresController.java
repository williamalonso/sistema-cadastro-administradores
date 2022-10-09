package com.web.admin.SpringWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdministradoresController {
    @RequestMapping("/administradores")
    public String index() {
        return "administradores/index";
    }
    
}
