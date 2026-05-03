package org.hibernetejpa.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.UserTransaction;

import java.io.Serializable;
import java.util.UUID;

public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private UUID id;
    private String name;
    private String salario;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }



}
