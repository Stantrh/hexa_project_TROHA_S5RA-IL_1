package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();

    public Reponse(String mot) {
        this.motSecret = mot;
    }

    // on récupère la lettre à la position dans le résultat
    public Lettre lettre(int position) {
        return this.resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        IntStream.range(0, essai.length())
                .forEach(i -> {
                    char carac = essai.charAt(i);
                    this.resultat.add(i, this.evaluationCaractere(carac, i));
                });
    }

    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        return this.resultat.stream()
                .allMatch(lettre -> lettre == Lettre.PLACEE);
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    // renvoie le statut du caractère
    private Lettre evaluationCaractere(char carCourant, int position) {
        return this.estPlace(carCourant, position) ? Lettre.PLACEE :
                this.estPresent(carCourant) ? Lettre.NON_PLACEE : Lettre.INCORRECTE;
    }

    // le caractère est présent dans le mot secret
    private boolean estPresent(char carCourant) {
        return this.motSecret.contains(""+carCourant);
    }

    // le caractère est placé dans le mot secret
    private boolean estPlace(char carCourant, int position) {
        return carCourant == this.motSecret.charAt(position);
    }
}
