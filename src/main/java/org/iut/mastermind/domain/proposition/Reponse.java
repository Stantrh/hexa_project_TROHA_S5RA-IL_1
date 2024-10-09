package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();
    private int position;

    public Reponse(String mot) {
        this.motSecret = mot;
        this.position = 0;
    }

    // on récupère la lettre à la position dans le résultat
    public Lettre lettre(int position) {
        return this.resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        for (int i = 0; i < essai.length(); i++) {
            char caractere = essai.charAt(i);
            this.position = i;
            this.resultat.add(i, this.evaluationCaractere(caractere));
        }
        this.position = 0;
    }

    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        boolean toutesPlacees = true;
        int i = 0;
        while(i < this.resultat.size() && toutesPlacees){
            if(!(this.resultat.get(i) == Lettre.PLACEE))
                toutesPlacees = false;
            i++;
        }
        return toutesPlacees;
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    // renvoie le statut du caractère
    private Lettre evaluationCaractere(char carCourant) {
        if(this.estPlace(carCourant))
            return Lettre.PLACEE;
        else if(this.estPresent(carCourant))
            return Lettre.NON_PLACEE;
        else
            return Lettre.INCORRECTE;
    }

    // le caractère est présent dans le mot secret
    private boolean estPresent(char carCourant) {
        return this.motSecret.contains(""+carCourant);
    }

    // le caractère est placé dans le mot secret
    private boolean estPlace(char carCourant) {
        return carCourant == this.motSecret.charAt(this.position);
    }
}
