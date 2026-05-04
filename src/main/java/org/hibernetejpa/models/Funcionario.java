package org.hibernetejpa.models;

import jakarta.persistence.*;
import jakarta.transaction.UserTransaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private UUID id;
    private String name;
    private Double salario;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "funcionario_projeto",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "projeto_id")
    )
    private List<Projeto> projetos = new ArrayList<>();

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

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }
}
