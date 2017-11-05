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


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intentResult = this.getIntent();

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

        int nb_contact = intentResult.getIntExtra("nb_contact", 0);  //On récupère le nombre de contact
        String[] contacts = intentResult.getStringArrayExtra("Contact"); //On récupère la transmission des contacts dans un tableau

        int vide = 0; //On initialise un compteur qui va servir à définir la longueur du tableau définitif en plus d'afficher la phrase si aucun contact n'est sélectionné

        for (int i = 0; i < nb_contact; i++) {
            if (contacts[i] == null) {
                vide++; //Incrémentation de ce compteur en cas de paramètre vide
            }
        }

        int long_contactList; //Variable pour la longueur du tableau de retour de contact sélectionnés

        if ((nb_contact - vide) == 0) {
            long_contactList = 1; //Initialisation de la taille à 1 afin de pouvoir afficher la phrase
        } else {
            long_contactList = nb_contact - vide;
        }

        String[] ListArray = new String[long_contactList]; //Initialisation du tableau de retour de contact avec une taille "dynamique"

        if (vide == nb_contact) {
            ListArray[0] = "Aucun contact sélectionné.";
        }

        int j = 0;
        for (int i = 0; i < nb_contact; i++) {
            if (contacts[i] != null) {
                ListArray[j] = contacts[i]; //Remplissage du tableau avec seulement des paramètres valide
                j++;
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ListArray); //Préparation de l'adapter avec le layout compris dans l'API, préparation des données du tableau dans ce layout
        ListView listView = (ListView) findViewById(R.id.ContactList); //Sélection de la listView
        listView.setAdapter(adapter); //Remplissage de la listView par l'adapter
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
