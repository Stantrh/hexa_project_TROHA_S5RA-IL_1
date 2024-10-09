package org.iut.mastermind.domain.partie;

import java.util.Objects;

public class Joueur {

    private final String nom;

    // constructeur
    public Joueur(String nom) {
        this.nom = nom;
    }

    // getter nom joueur
    public String getNom() {
        return this.nom;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }

    // equals
    @Override
    public boolean equals(Object o) {
        Joueur j = (Joueur) o;
        return this.nom.equals(j.getNom());
    }

}
