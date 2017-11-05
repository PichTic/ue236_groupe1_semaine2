package com.example.groupe1.ue236_groupe1_semaine2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.groupe1.ue236_groupe1_semaine2.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView showResult = (TextView) findViewById(R.id.textView);

        Intent intentResult = this.getIntent();

        String Vincent = intentResult.getStringExtra("V");
        String Melissa = intentResult.getStringExtra("M");
        String Emmanuel = intentResult.getStringExtra("Me");
        String Marion = intentResult.getStringExtra("Em");

        String Liste = "";

        if ((Vincent == null) && (Melissa == null) && (Emmanuel == null) && (Marion == null)) {
            Liste = "Aucun contact sélectionné.";
        }
        if (Vincent != null) {
            Liste += Vincent + "\n";
        }
        if (Melissa != null) {
            Liste += Melissa + "\n";
        }
        if (Emmanuel != null) {
            Liste += Emmanuel + "\n";
        }
        if (Marion != null) {
            Liste += Marion;
        }

        showResult.setText(Liste);

        Button bouton_2 = (Button) findViewById(R.id.bouton_2);
        Button bouton_3 = (Button) findViewById(R.id.bouton_3);

        bouton_2.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                creerToast(v);
            }
        });

        bouton_3.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                retourActivite(v);
            }
        });
    }

    public void creerToast(View view) {
        Toast.makeText(getApplicationContext(), "Le bouton de validation a été cliqué.", Toast.LENGTH_SHORT).show();
        Intent startOldActivity = new Intent(this, MainActivity.class);
        startActivity(startOldActivity);
    }

    public void retourActivite(View view) {
        Intent backToOldActivity = new Intent(this, MainActivity.class);
        startActivity(backToOldActivity);
    }

}
