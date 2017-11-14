package com.example.groupe1.ue236_groupe1_semaine2;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity_main);

        //Récupération des contacts et affichage des noms
        ArrayList<Contact> contacts = getIntent().getExtras().getParcelableArrayList("Contacts");
        TextView retourcontact = (TextView) findViewById(R.id.RecapContact);
        String ListNoms;
        if(contacts == null)
        {
            ListNoms = "Rien de sélectionné.";
        }
        else {
            ListNoms = "Sélectionnés : ";
            for (int i = 0; i < contacts.size(); i++) {
                ListNoms += contacts.get(i).getNom() + "; ";
            }
        }
        retourcontact.setText(ListNoms);


        //Préparation de la listview pour les phrases de voeux
        final ListView listViewWish = (ListView) findViewById(R.id.wishlist);

        //Initialisation de l'objet Voeux, la fonction getResources() ne fonctionne pas dans la classe Voeux.java malgré l'import donc on est obligé de le faire manuellement ici
        Resources res = getResources();
        String[] originaltext = res.getStringArray(R.array.phrases_voeux_vierge);
        Voeux voeux = new Voeux(); //Création de l'objet
        voeux.setOriginaltext(originaltext); //Remplissage de l'objet avec les phrase prédéfinies vierges
        String[] listvoeux = voeux.getOriginaltext(); //Remplissage d'un array avec la liste des phrases

        //Affichage de l'array dans la listview
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, listvoeux);
        listViewWish.setAdapter(adapter);

        listViewWish.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id)
            {
                long[] checkeditem = listViewWish.getCheckedItemIds();
                if(checkeditem != null) {
                    for(int i = 0; i < checkeditem.length; i++)
                    {
                        listViewWish.setItemChecked((int)checkeditem[i], false);
                    }
                }
                listViewWish.setItemChecked(position, true);
            }
        });

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
