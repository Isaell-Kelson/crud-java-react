package br.com.unidac.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "collaborator")
public class Collaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

    @OneToMany(mappedBy = "collaborator", cascade = CascadeType.ALL)
    private List<Breakfast> breakfasts = new ArrayList<>();

    public Collaborator() {

    }

    public Collaborator(String name, String cpf) {
        super();
        this.name = name;
        this.cpf = cpf;
    }

    public void addBreakfast(Breakfast breakfast) {
        breakfasts.add(breakfast);
        breakfast.setCollaborator(this);
    }

    public List<Breakfast> getBreakfasts() {
        return breakfasts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;

    }
}
