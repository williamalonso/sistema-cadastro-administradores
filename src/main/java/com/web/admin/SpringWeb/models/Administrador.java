package com.web.admin.SpringWeb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity // Estamos informando que essa classe é uma Entidade. Isso significa que essa classe vai criar minha tabela no banco de dados, além de buscar dados, etc.
@Table(name="administradores") // É opcional informar o nome da minha tabela, mas aqui vamos informar/mapear. O nome da classe fica no singular, e da tabela fica no plural
public class Administrador {

    // Aqui criamos os atributos que vão pro banco de dados
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // incrementa automaticamente o 'id'
    @Column(name = "id") // Nome da minha coluna. Isso é opcional
    private int id; // Nome do meu atributo

    @Column(name = "nome", length = 100, nullable = false) // Estamos dizendo que essa coluna vai ter um varchar de tamanho 100 ao invés de 255 padrão. Também estamos dizendo que esse campo/coluna não pode ser nulo.
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "observacao")
    @Type(type = "text") // Podemos também definir o tipo da coluna. Aqui vai ser 'text' pois podemos digitar muito nesse campo
    private String observacao;

    // Getters e Setters dos atributos:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        // return senha;
        // Se quisermos colocar os caracteres da senha com um asterisco '*' para exibir no template, podemos fazer:
        return senha.substring(0, 3) + "****"; // Aqui ele mostra 3 caracteres da senha e depois o resto fica com asterisco.
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
