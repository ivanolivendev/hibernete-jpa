package org.hibernetejpa.models;

import jakarta.persistence.*;
import org.hibernate.action.internal.OrphanRemovalAction;
import org.hibernetejpa.enums.EstadoCivil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private  String nome;
    private LocalDate dataNasciemnto;
    @Transient
    private Integer idade;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "veiculo_id")

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos = new ArrayList<>();
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="rua",column = @Column(name = "rua_comercial")),
//            @AttributeOverride(name = "bairro", column = @Column(name = "bairro_comercial")),
//            @AttributeOverride(name = "cep", column = @Column(name = "cep_comercial"))}
//    )
//    private Endereco endereco;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "end_usu",
//            joinColumns = @JoinColumn(name = "id_usuarioX"), // Coluna da tabela atual (Usuario)
//            inverseJoinColumns = @JoinColumn(name = "id_enderecoX") // Coluna da outra tabela (Endereco)
//    )
//    private List<Endereco> enderecos  = new ArrayList<>();



//    public Endereco getEnderecoComercial() {
//        return enderecoComercial;
//    }
//
//    public void setEnderecoComercial(Endereco enderecoComercial) {
//        this.enderecoComercial = enderecoComercial;
//    }

//    private Endereco enderecoComercial;




    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;




    public LocalDate getDataNasciemnto() {
        return dataNasciemnto;
    }


    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    //    public Endereco getEndereco() {
//        return endereco;
//    }
//
//    public void setEndereco(Endereco endereco) {
//        this.endereco = endereco;
//    }


//    public List<Endereco> getEnderecos() {
//        return enderecos;
//    }

//    public void setEnderecos(List<Endereco> enderecos) {
//        this.enderecos = enderecos;
//    }

    public void setDataNasciemnto(LocalDate dataNasciemnto) {
        this.dataNasciemnto = dataNasciemnto;
    }

    public UUID getId() {
        return id;
    }




    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    public void addVeiculo(Veiculo veiculo) {
        if (veiculo != null) {
            this.veiculos.add(veiculo);
            veiculo.setUsuario(this); // O pulo do gato: vincula o veículo ao usuário atual
        }
    }
}
