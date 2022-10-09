package com.web.admin.SpringWeb.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.web.admin.SpringWeb.models.Administrador;

// Vamos usar esse arquivo para falar para o Hibernate que essa vai ser a interface responsável por tratar toda minha Entidade.

// O 'CrudRepository<Administrador, Integer>' significa que vamos usar métodos prontos do CRUD;
// O 'CrudRepository' está fazendo menção à minha classe 'Administrador'
// O 'Integer' significa que vamos ter um autoincrement para que a interface possa tratar os dados com inteiros

public interface AdministradoresRepo extends CrudRepository<Administrador, Integer> {
    
}
