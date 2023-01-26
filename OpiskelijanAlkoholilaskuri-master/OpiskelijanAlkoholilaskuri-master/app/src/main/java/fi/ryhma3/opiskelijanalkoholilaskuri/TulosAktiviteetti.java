/**
 * Aktiviteetti, mikä näyttää lopputuloksen eli promillemäärän ja alkoholin palamiseen kuluvan ajan.
 * Ensin otetaan laskuriaktiviteetista vastaan käyttäjän syöttämät tiedot (paino, annokset ja tunnit) jonka jälkeen
 * lisätään ne muuttujiin. Tämän jälkeen kutsutaan laskurin metodeja, millä määritetään promillemäärä ja alkoholin palamisaika.
 * @author Sanna Lohkovuori
 */

package fi.ryhma3.opiskelijanalkoholilaskuri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;


public class TulosAktiviteetti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulos_aktiviteetti);


        Intent intent = getIntent();
        String sukupuoli = intent.getStringExtra(LaskuriAktiviteetti.EXTRA_SUKUPUOLI);
        int paino = intent.getIntExtra(LaskuriAktiviteetti.EXTRA_PAINO, 0);
        int annokset = intent.getIntExtra(LaskuriAktiviteetti.EXTRA_ANNOKSET, 0);
        int tunnit = intent.getIntExtra(LaskuriAktiviteetti.EXTRA_TUNNIT, 0);

        Laskuri laskuri = new Laskuri();

        TextView promilleTeksti = findViewById(R.id.promilleTeksti);
        double promillet = (laskuri.haePromillet(sukupuoli, paino, annokset, tunnit));
        if(promillet < 0){
            promilleTeksti.setText("0 ‰");
        } else {
            promilleTeksti.setText(String.format(" %.2f ‰", promillet));
        }


        TextView palamisaikaTeksti = findViewById(R.id.palamisaikaTeksti);
        double palamisaika = (laskuri.haePalamisaika(paino, annokset, tunnit));
        if(palamisaika < 0){
            palamisaikaTeksti.setText("0 h");
        } else {
            palamisaikaTeksti.setText(String.format(" %.0f h", palamisaika));
        }
    }
}