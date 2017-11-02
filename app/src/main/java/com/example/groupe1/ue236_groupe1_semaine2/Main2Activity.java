package com.example.groupe1.ue236_groupe1_semaine2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        showResult.setText(Vincent+" , "+Melissa+" , "+Emmanuel+" , "+Marion);
    }

    public void creerToast (View view) {
            Toast.makeText(getApplicationContext(), "Le bouton de validation a été cliqué.", Toast.LENGTH_SHORT).show();
        Intent startOldActivity = new Intent(this, MainActivity.class);
        startActivity(startOldActivity);
    }

    public void retourActivite (View view) {
        Intent backToOldActivity = new Intent(this, MainActivity.class);
        startActivity(backToOldActivity);
    }

}
