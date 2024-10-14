package com.fire.propagation.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "propagation_steps")
public class PropagationStep implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "step_number")
    private int stepNumber;

    @ElementCollection
    @CollectionTable(name = "burning_trees", joinColumns = @JoinColumn(name = "step_id"))
    @Column(name = "coordinates")
    private List<String> burningTrees;  // Liste des coordonnées des arbres en feu

    @ElementCollection
    @CollectionTable(name = "dead", joinColumns = @JoinColumn(name = "step_id"))
    @Column(name = "coordinates")
    private List<String> dead;  // Liste des coordonnées des arbres mortes qui sont devenues cendres

    public PropagationStep() {}

    public PropagationStep(int stepNumber, List<String> burningTrees, List<String> dead) {
        this.stepNumber = stepNumber;
        this.burningTrees = burningTrees;
        this.dead = dead;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public List<String> getBurningTrees() {
        return burningTrees;
    }

    public void setBurningTrees(List<String> burningTrees) {
        this.burningTrees = burningTrees;
    }

    public List<String> getDead() {
        return dead;
    }

    public void setDead(List<String> dead) {
        this.dead = dead;
    }
}

