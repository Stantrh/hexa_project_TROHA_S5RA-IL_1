package org.iut.mastermind.domain.proposition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.iut.mastermind.domain.proposition.Lettre.*;

@DisplayName("Test sur la proposition:")
public class TestProposition {

    @Test
    @DisplayName("une lettre est incorrecte")
    public void casLettreIncorrecte() {
        var motADeviner = "S";
        var reponse = this.compareProposition("Z", motADeviner);
        assertResultat(reponse, INCORRECTE);
    }

    @Test
    @DisplayName("une lettre est placée")
    public void casLettrePlacee() {
        var motADeviner = "S";
        var reponse = this.compareProposition("S", motADeviner);
        assertResultat(reponse, PLACEE);
    }

    @Test
    @DisplayName("une lettre est incorrecte, une non placée")
    public void casDeuxiemeLettreMalPlacee() {
        var motADeviner = "SO";
        var reponse = this.compareProposition("ZS", motADeviner);
        assertResultat(reponse,  INCORRECTE, NON_PLACEE);
    }

    @Test
    @DisplayName("une lettre est incorrecte, non placée, placée")
    public void casCombinaisons() {
        var motADeviner = "SOL";
        var reponse = this.compareProposition("ZSL", motADeviner);
        assertResultat(reponse,  INCORRECTE, NON_PLACEE, PLACEE);
    }

    @Test
    @DisplayName("toutes les lettres sont placées")
    void casToutesLettresPlacees() {
        var motADeviner = "SOLID";
        var reponse = this.compareProposition("SOLID", motADeviner);
        assertThat(reponse.lettresToutesPlacees()).isTrue();
    }

    @Test
    @DisplayName("la proposition n'est pas correcte")
    void casLettresIncorrectes() {
        var motAdeviner = "SOLID";
        var reponse = this.compareProposition("SOL*D", motAdeviner);
        assertThat(reponse.lettresToutesPlacees()).isFalse();
    }

    @Test
    @DisplayName("vérifie la taille du résultat")
    void casAccesLettres() {
        var motAdeviner = "SOLID";
        var reponse = this.compareProposition("SOL*D", motAdeviner);
        assertThat(reponse.lettresResultat()).hasSize(5);
    }


    private void assertResultat(Reponse reponse, Lettre... resultatAttendu) {
        for (int position = 0; position < resultatAttendu.length; position++) {
            Lettre attendue = resultatAttendu[position];
            assertThat(reponse.lettre(position)).isEqualTo(attendue);
        }
    }


    private Reponse compareProposition(String essai, String motADeviner){
        Reponse reponse = new Reponse(motADeviner);
        reponse.compare(essai);
        return reponse;
    }
}
