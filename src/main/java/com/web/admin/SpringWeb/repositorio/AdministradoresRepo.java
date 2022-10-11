package com.web.admin.SpringWeb.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.web.admin.SpringWeb.models.Administrador;

// Vamos usar esse arquivo para falar para o Hibernate que essa vai ser a interface responsável por tratar toda minha Entidade.

// O 'CrudRepository<Administrador, Integer>' significa que vamos usar métodos prontos do CRUD;
// O 'CrudRepository' está fazendo menção à minha classe 'Administrador'
// O 'Integer' significa que vamos ter um autoincrement para que a interface possa tratar os dados com inteiros

public interface AdministradoresRepo extends CrudRepository<Administrador, Integer> {
    //@Query(value="select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END from administradores where id = :id", nativeQuery = true) // Aqui estamos usando uma query direto no Banco que verifica se existe o 'id' informado
    //public boolean exist(int id); // função que invoca a query acima
}
