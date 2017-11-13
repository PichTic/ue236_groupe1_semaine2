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

      // Afficher contacts sélectionnés :
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

      // Textes prédéfinis :
        ListView listViewWish = (ListView) findViewById(R.id.wishlist);

        Resources res = getResources();
        String[] text = new String[10];

        text[0] = res.getString(R.string.voeux_anniversaire1, "...");
        text[1] = res.getString(R.string.voeux_anniversaire2, "...");
        text[2] = res.getString(R.string.voeux_anniversaire3, "...");
        text[3] = res.getString(R.string.voeux_fete, "...");
        text[4] = res.getString(R.string.voeux_noel1, "...");
        text[5] = res.getString(R.string.voeux_noel2, "...");
        text[6] = res.getString(R.string.voeux_mariage, "...");
        text[7] = res.getString(R.string.voeux_nouvel_an1, "...");
        text[8] = res.getString(R.string.voeux_nouvel_an2, "...");
        text[9] = res.getString(R.string.voeux_nouvel_an3, "...");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, text);
        listViewWish.setAdapter(adapter);

    // Les boutons
        Button validation = (Button) findViewById(R.id.bouton_validation);
        Button annulation = (Button) findViewById(R.id.bouton_annulation);

      // Le bouton envoi de message :
        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    /*
    public void creerToast(View view) {
        Toast.makeText(getApplicationContext(), "Le bouton de validation a été cliqué.", Toast.LENGTH_SHORT).show();
        Intent startOldActivity = new Intent(this, MainActivity.class);
        startActivity(startOldActivity);
    }
    */

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
