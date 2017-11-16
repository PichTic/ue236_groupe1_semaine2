package com.example.groupe1.ue236_groupe1_semaine2;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChangeNameActivity extends AppCompatActivity {

    ArrayList<Contact> contacts = new ArrayList<Contact>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);

        //Récupération des contacts et affichage des noms
        contacts = getIntent().getExtras().getParcelableArrayList("Contacts");
        // Création de l'adapter qui va convertir l'array en view
        final CustomContactsAdapter adapter = new CustomContactsAdapter(this, contacts);
        // Je link l'adapter à ma Listview
        final ListView listView = (ListView) findViewById(R.id.lvContactName);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                for(int i = 0; i < contacts.size(); i++){
                    if(contacts.get(i).isChangenom()) {
                        contacts.get(i).setChangenom();
                    }
                }
                contacts.get(position).setChangenom();
            }
        });

        Button changename = (Button) findViewById(R.id.buttonvalid_edittext);
        Button fin = (Button) findViewById(R.id.bouton_terminer);
        final EditText getchangedname = (EditText) findViewById(R.id.getchangename);

        changename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkckedcontrol = 0;
                for(int i = 0; i < contacts.size(); i++) {
                    if(contacts.get(i).isChangenom()) {
                        checkckedcontrol++;
                    }
                }
                if(checkckedcontrol > 0) {
                    String new_name;
                    new_name = getchangedname.getText().toString();
                    for(int i = 0; i < contacts.size(); i++) {
                        if(contacts.get(i).isChangenom()) {
                            contacts.get(i).setNom(new_name);
                        }
                    }
                    RestartActivity(v);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Aucun contact n'est sélectionné.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Le Bouton de changement de nom
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nouvelleActivite(v);
            }
        });

    }

    public void RestartActivity(View view) {
        Intent startNewActivity = new Intent(this, ChangeNameActivity.class);
        startNewActivity.putParcelableArrayListExtra("Contacts", contacts);
        startActivity(startNewActivity);
    }

    public void nouvelleActivite(View view) {
        Intent startNewActivity = new Intent(this, SecondActivity.class);
        startNewActivity.putParcelableArrayListExtra("Contacts", contacts);
        startActivity(startNewActivity);
    }
}
