package br.com.fabiohgbarbosa.secretfriend.people.domain.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fabio on 08/09/15.
 */
@Entity
public class People implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Nome não pode ser nulo")
    private String nome;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail não pode ser nulo")
    @Column(unique = true)
    private String email;

    public People() {
    }

    public People(final Long id, final String nome, final String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public People(final String nome, final String email) {
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
