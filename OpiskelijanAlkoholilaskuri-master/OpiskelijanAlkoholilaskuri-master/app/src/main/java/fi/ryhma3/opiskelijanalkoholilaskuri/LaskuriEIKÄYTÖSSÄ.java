package fi.ryhma3.opiskelijanalkoholilaskuri;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class LaskuriEIKÄYTÖSSÄ {
    private int paino, alkoholiAnnokset, tunnit;
    private String sukupuoli;
    double promillet;
    int jakaja = 120;
    double palamisaika;


    public LaskuriEIKÄYTÖSSÄ(String sukupuoli, int paino, int alkoholiAnnokset, int tunnit) {
        this.sukupuoli = sukupuoli;
        this.paino = paino;
        this.alkoholiAnnokset = alkoholiAnnokset;
        this.tunnit = tunnit;
    }

    public int haePaino() {
        return paino;
    }

    public int haeAnnokset() {
        return alkoholiAnnokset;
    }

    public double haePromillet() {

    if (paino >= 40 && paino < 50) {
        promillet = alkoholiAnnokset / 2.5;
    }

    if (paino >= 50 && paino < 60) {
        promillet = alkoholiAnnokset / 3.1;
    }

    if (paino >= 60 && paino < 70) {
        promillet = alkoholiAnnokset / 3.7;
    }

    if (paino >= 70 && paino < 80) {
        promillet = alkoholiAnnokset / 4.37;
    }

    if (paino >= 80 && paino < 90) {
        promillet = alkoholiAnnokset / 5.0;
    }

    if (paino >= 90 && paino < 100) {
        promillet = alkoholiAnnokset / 5.65;
    }

    if (paino >= 100 && paino < 110) {
        promillet = alkoholiAnnokset / 6.25;
    }

    if (paino >= 110 && paino < 120) {
        promillet = alkoholiAnnokset / 6.87;
    }

    if (paino == 120) {
        promillet = alkoholiAnnokset / 7.5;
    }

    if (sukupuoli.equals("nainen")){
        promillet *= 1.2;
    }

    if (sukupuoli.equals("mies")) {
        promillet *= 1.0;
    }

    return promillet;
}

    public double haePalamisaika() {
        double paino2 = paino;
        palamisaika = jakaja * alkoholiAnnokset / paino2 - tunnit;
        return palamisaika;
    }

}
