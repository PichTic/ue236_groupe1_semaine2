package com.example.groupe1.ue236_groupe1_semaine2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity_main);


        ArrayList<Contact> arrayofcontacts = getIntent().getExtras().getParcelable("Contacts");

        Button bouton_2 = (Button) findViewById(R.id.bouton_2);
        Button bouton_3 = (Button) findViewById(R.id.bouton_3);

        bouton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creerToast(v);
            }
        });

        bouton_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retourActivite(v);
            }
        });


        String Recap;

        if(arrayofcontacts.isEmpty())
        {
            Recap = "Vous n'avez rien sélectionné.";
        }
        else {

            Recap = "Vous avez sélectionné : ";

            for (int i = 0; i < arrayofcontacts.size(); i++) {
                Recap += arrayofcontacts.get(i).getPrenom() + " " + arrayofcontacts.get(i).getNom() + " ";
            }
        }

        TextView textview = (TextView) findViewById(R.id.ContactList);
        textview.setText(Recap);

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
