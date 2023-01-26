/**
 * Laskuriaktiviteetti kuvastaa näkymää missä kysytään käyttäjän sukupuoli, juodut alkoholiannokset ja juomiseen käytetty aika.
 * Näiden avulla lasketaan promillemäärä sekä alkoholin palamiseen kuluva aika.
 * @author Valerio Tatananni
 */

package fi.ryhma3.opiskelijanalkoholilaskuri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.Toast;

public class LaskuriAktiviteetti extends AppCompatActivity {
    private final String MIES = "mies";
    private final String NAINEN = "nainen";
    private String valittuPainike;
    public static final String EXTRA_SUKUPUOLI = "fi.ryhma3.opiskelijanalkoholilaskuri.LaskuriAktiviteetti.EXTRA_SUKUPUOLI";
    public static final String EXTRA_PAINO = "fi.ryhma3.opiskelijanalkoholilaskuri.LaskuriAktiviteetti.EXTRA_PAINO";
    public static final String EXTRA_ANNOKSET = "fi.ryhma3.opiskelijanalkoholilaskuri.LaskuriAktiviteetti.EXTRA_ANNOKSET";
    public static final String EXTRA_TUNNIT = "fi.ryhma3.opiskelijanalkoholilaskuri.LaskuriAktiviteetti.EXTRA_TUNNIT";
    EditText painoSyöttö;
    EditText alkoholiAnnosSyöttö;
    EditText tuntiSyöttö;
    RadioGroup sukupuoliValitsin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laskuri_aktiviteetti);

        painoSyöttö = (EditText) findViewById(R.id.painoSyöttö);
        alkoholiAnnosSyöttö = (EditText) findViewById(R.id.alkoholiAnnosSyöttö);
        tuntiSyöttö = (EditText) findViewById(R.id.tuntiSyöttö);
        sukupuoliValitsin = findViewById(R.id.sukupuoliValitsin);

        SharedPreferences prefGet = getSharedPreferences("MyTestPref" , Activity.MODE_PRIVATE);
        String sukupuoli = prefGet.getString("Sukupuoli", "tuntematon");
        int paino = prefGet.getInt("Paino", 0);
        if(paino >= 40 && !sukupuoli.equals("tuntematon")){
            painoSyöttö.setText(Integer.toString(paino));
            if(sukupuoli.equals("nainen")){
                sukupuoliValitsin.check(R.id.naisNappula);
                valittuPainike = "nainen";
            } else if(sukupuoli.equals("mies")){
                sukupuoliValitsin.check(R.id.miesNappula);
                valittuPainike = "mies";
            }

        }
    }

    /**
     * Metodi, mikä tarkistaa mitä radiopainiketta on painettu ja muuttaa valittuPainike muuttujan joko mieheksi tai naiseksi.
     * @param view radiopainike, joka annetaan parametrina
     * @see ://developer.android.com/guide/topics/ui/controls/radiobutton#java
     */
    public void radioPainikePainettu(View view) {
        // Onko painike painettu?
        boolean checked = ((RadioButton) view).isChecked();

        // Tarkista, kun radiopainiketta painetaan
        switch (view.getId()) {
            case R.id.miesNappula:
                if (checked)
                    valittuPainike = MIES;
                break;
            case R.id.naisNappula:
                if (checked)
                    valittuPainike = NAINEN;
                break;
        }
    }

    /**
     * Painike, mitä painamalla lähetetään käyttäjän syöttämät tiedot seuraavaan aktiviteettiin.
     * Metodi myös tarkistaa, että kaikki kentät ovat varmasti täytetty ja että ne ovat oikeanlaisia.
     * @param view laske-painike
     */
    public void laske(View view) {
        Intent intent = new Intent(this, TulosAktiviteetti.class);

        String input1 = painoSyöttö.getText().toString();
        String input2 = alkoholiAnnosSyöttö.getText().toString();
        String input3 = tuntiSyöttö.getText().toString();

        if (TextUtils.isEmpty(input1) || TextUtils.isEmpty(input2) || TextUtils.isEmpty(input3)) {
            Toast.makeText(LaskuriAktiviteetti.this, "Tyhjä kenttä ei sallittu!", Toast.LENGTH_SHORT).show();
            return;
        }

        int paino = Integer.parseInt(painoSyöttö.getText().toString());
        int annokset = Integer.parseInt(alkoholiAnnosSyöttö.getText().toString());
        int tunnit = Integer.parseInt(tuntiSyöttö.getText().toString());

        if (paino < 40 || paino > 120){
            CharSequence text = "Syötä paino 40kg - 120kg väliltä";
            errorViesti(text);
            return;
        }

        if(annokset < 1) {
            CharSequence text = "Syötä vähintään 1 alkoholiannos!";
            errorViesti(text);
            return;

        }

        if (annokset > 30){
            CharSequence text = "Olet juonut aivan liikaa. Soita 112.";
            errorViesti(text);
            return;
        }

        if (tunnit < 0){
            CharSequence text = "Syötä vähintään 1 tunti.";
            errorViesti(text);
            return;
        }

        if (tunnit > 168){
            CharSequence text = "Liian pitkä putki päällä!?";
            errorViesti(text);
            return;
        }

        if (valittuPainike == null) {
            CharSequence text = "Valitse sukupuoli!";
            errorViesti(text);
            return;
        }

        intent.putExtra(EXTRA_SUKUPUOLI, valittuPainike);
        intent.putExtra(EXTRA_PAINO, paino);
        intent.putExtra(EXTRA_ANNOKSET, annokset);
        intent.putExtra(EXTRA_TUNNIT, tunnit);

        SharedPreferences prefPut = getSharedPreferences("MyTestPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("Sukupuoli", valittuPainike);
        prefEditor.putInt("Paino", paino);
        prefEditor.commit();

        startActivity(intent);

    }

    /**
     *
     * @param text virheteksti, mikä ilmestyy jos käyttäjä syöttää virheellistä tietoa
     */
    private void errorViesti(CharSequence text) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
