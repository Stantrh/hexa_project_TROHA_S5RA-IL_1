package org.iut.mastermind.domain.partie;

public record Joueur(String nom) {

    // constructeur

    // equals
    @Override
    public boolean equals(Object o) {
        // on vérifie aussi (warning intelliJ) que les objets soient bien de la même classe lors de l'appel de la méthode pour ne pas faire un cast dangereux
        if (getClass() != o.getClass())
            return false;

        Joueur j = (Joueur) o;
        return this.nom.equals(j.nom());
    }

}
