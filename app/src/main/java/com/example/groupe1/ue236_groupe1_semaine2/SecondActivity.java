package com.example.groupe1.ue236_groupe1_semaine2;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
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


    final Voeux voeux = new Voeux(); //Création de l'objet
    ArrayList<Contact> contacts = new ArrayList<Contact>();
    String[] list_voeux_perso = new String[contacts.size()];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity_main);

        //Récupération des contacts et affichage des noms
        contacts = getIntent().getExtras().getParcelableArrayList("Contacts");
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
        String[] toformattext = res.getStringArray(R.array.phrases_voeux_a_remplir);
        voeux.setOriginaltext(originaltext); //Remplissage de l'objet avec les phrase prédéfinies vierges
        voeux.setFormatedtext(toformattext);
        String[] listvoeux = voeux.getOriginaltext(); //Remplissage d'un array avec la liste des phrases

        //Affichage de l'array dans la listview
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listvoeux);
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
                voeux.id = position;
            }
        });

        Button validation = (Button) findViewById(R.id.bouton_validation);
        Button annulation = (Button) findViewById(R.id.bouton_annulation);

      // Le bouton envoi de message :
        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] prenom = new String[contacts.size()]; //On récupère les noms de la liste de contact dans un tableau
                for(int i = 0; i < contacts.size(); i++) {
                    prenom[i] = contacts.get(i).getNom();
                }
                voeux.Formattext(prenom); //On initialise la phrase choisie avec le(s) prénom(s)
                list_voeux_perso = voeux.getFormatedtext(); //On récupère le tableau obtenu ci-dessus
                confirmationEnvoi(v);
            }
        });

      // Le Bouton annulation :
        annulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retourActivite(v);
            }
        });

    }

    // Méthodes bouton validation :
    public void confirmationEnvoi (View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
        // Titre de l'alertdialog
        builder.setTitle("Confirmation envoi");
        // Message de l'alertdialog
        builder.setMessage("Souhaitez-vous envoyer ce(s) message(s) ?");
        // Pour le bouton "non"
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        "Message(s) non-envoyé(s)",
                        Toast.LENGTH_LONG).show();
            }
        });
        // Pour le bouton "oui"
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        "Message(s) envoyé(s)",
                        Toast.LENGTH_LONG).show();
            }
        });
        builder.show();

/*envoi sms
if builder.setPositiveButton = true
message = listViewWish
nom = ListNoms
SmsManager.getDefault().sendTextMessage(nom, null, message, null);

sources :
https://developer.android.com/reference/android/telephony/SmsManager.html
http://a-renouard.developpez.com/tutoriels/android/sms/
*/

    }

    // Méthode bouton annulation :
    public void retourActivite(View view) {
        Intent backToOldActivity = new Intent(this, MainActivity.class);
        startActivity(backToOldActivity);
    }

}
