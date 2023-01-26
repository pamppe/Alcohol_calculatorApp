/**
 * Laskuri, missä on metodeja millä laskea promillemäärä ja alkoholin palamisaika
 * @author Valerio Tatananni
 */
package fi.ryhma3.opiskelijanalkoholilaskuri;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Laskuri {
    private final double SUKUPUOLIKERROIN_NAINEN = 1.1;
    private final double SUKUPUOLIKERROIN_MIES = 0.9;
    private final String NAINEN = "nainen";
    private final int JAKAJA = 120;

    public Laskuri() {
    }

    /**
     * @param paino käyttäjän paino
     * @param alkoholiAnnokset käyttäjän juomat alkoholiannokset
     * @return promillemäärä
     */
    private double laskePromillet(int paino, int alkoholiAnnokset) {
        if (paino >= 40 && paino < 50) {
            return alkoholiAnnokset / 2.5;
        }

        if (paino >= 50 && paino < 60) {
            return alkoholiAnnokset / 3.1;
        }

        if (paino >= 60 && paino < 70) {
            return alkoholiAnnokset / 3.7;
        }

        if (paino >= 70 && paino < 80) {
            return alkoholiAnnokset / 4.37;
        }

        if (paino >= 80 && paino < 90) {
            return alkoholiAnnokset / 5.0;
        }

        if (paino >= 90 && paino < 100) {
            return alkoholiAnnokset / 5.65;
        }

        if (paino >= 100 && paino < 110) {
            return alkoholiAnnokset / 6.25;
        }

        if (paino >= 110 && paino < 120) {
            return alkoholiAnnokset / 6.87;
        }

        if (paino == 120) {
            return alkoholiAnnokset / 7.5;
        }

        return 0;
    }

    /**
     * Laskee sukupuolikertoimen, mikä on naisilla hieman korkeampi
     */
    private double haeSukupuoliKerroin(String sukupuoli) {
        if (sukupuoli.equals(NAINEN)){
            return SUKUPUOLIKERROIN_NAINEN;
        }
        return SUKUPUOLIKERROIN_MIES;
    }

    /**
     * Laskee promillemäärän sukupuolikertoimella
     * @param sukupuoli käyttäjän sukupuoli
     * @param paino käyttäjän paino
     * @param alkoholiAnnokset käyttäjän juomat alkoholiannokset
     * @param tunnit käyttäjän käyttämät tunnit juomiseen
     * @return promillemäärä * sukupuolikerroin
     */
    public double haePromillet(String sukupuoli, int paino, int alkoholiAnnokset, int tunnit) {
        double kokonaisPromillet = laskePromillet(paino, alkoholiAnnokset);
        double sukuPuoliKerroin = haeSukupuoliKerroin(sukupuoli);
        double palamisaika = haePalamisaika(paino, alkoholiAnnokset, 0);
        double tunnissaVähenevätPromillet = kokonaisPromillet / palamisaika;
        double vähentyneetPromillet = tunnissaVähenevätPromillet * tunnit;
        return kokonaisPromillet * sukuPuoliKerroin - vähentyneetPromillet;
    }

    /**
     * Laskee alkoholin palamiseen kuluvan ajan
     * @param paino käyttäjän paino
     * @param alkoholiAnnokset käyttäjän juomat alkoholiannokset
     * @param tunnit käyttäjän käyttämät tunnit juomiseen
     * @return palamisaika
     */
    public double haePalamisaika(int paino, int alkoholiAnnokset, int tunnit) {
        return (double) JAKAJA * alkoholiAnnokset / paino - tunnit;
    }

}
