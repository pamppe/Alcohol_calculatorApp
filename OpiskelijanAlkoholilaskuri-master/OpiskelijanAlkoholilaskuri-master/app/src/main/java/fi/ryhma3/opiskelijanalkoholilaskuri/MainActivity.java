/**
 * MainActivity on sovelluksen etusivu, missä tuodaan ilmi käyttäjällä että mistä sovelluksessa on kyse.
 * Activityn ilme on tehty XML muokkaamalla ja lisätty määrityksiä varjostuksille, väreille ja lisätty määritys sovellukseen että se pysyy aina portrait modessa.
 * Portrait mode on lisättyy kaikki activityihin.
 * @author Emil Lehtonen
*/
package fi.ryhma3.opiskelijanalkoholilaskuri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Tätä nappulaa painalla siirrytään seuraavaan aktivityyn
     * @param view ruudulla näkyvä nappula (Aloita)
     */
    public void napinPainallus(View view){
        Intent intent = new Intent(this, LaskuriAktiviteetti.class);
        startActivity(intent);
    }
}