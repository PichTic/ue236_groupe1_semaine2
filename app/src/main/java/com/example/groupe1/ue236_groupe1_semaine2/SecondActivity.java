package com.example.groupe1.ue236_groupe1_semaine2;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
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

    private static final int PERMISSIONS_REQUEST_SEND_SMS = 100 ;
    String phoneNo;
    String message;
    ArrayList<Contact> contacts = new ArrayList<Contact>();
    final Voeux voeux = new Voeux(); //Création de l'objet
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
        final String[] toformattext = res.getStringArray(R.array.phrases_voeux_a_remplir);
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
                long[] checkeditem = listViewWish.getCheckedItemIds(); //Fonction qui bloque le choix sur une seule phrase
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
        Button changename = (Button) findViewById(R.id.changename);
        Button annulation = (Button) findViewById(R.id.bouton_annulation);

      // Le bouton envoi de message :
        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] prenom = new String[contacts.size()]; //On récupère les noms de la liste de contact dans un tableau
                for(int i = 0; i < contacts.size(); i++) {
                    prenom[i] = contacts.get(i).getNom();
                }
                voeux.setFormatedtext(toformattext);
                voeux.Formattext(prenom); //On initialise la phrase choisie avec le(s) prénom(s)
                list_voeux_perso = voeux.getFormatedtext(); //On récupère le tableau obtenu ci-dessus
                confirmationEnvoi(v);
            }
        });

      //Le Bouton de changement de nom
        changename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nouvelleActivite(v);
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
    public void confirmationEnvoi (final View view) {

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
                sendSMSMessage();
                retourActivite(view);
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
    protected void sendSMSMessage() {
        // Control de la version du SDK et si j'ai la permission d'acceder aux Contacts
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                phoneNo = contacts.get(i).getTel();
                message = list_voeux_perso[i];
                // Add the phone number in the data
                String SMS_SENT = "SMS Envoyé";
                String SMS_DELIVERED = "SMS Reçu";

                PendingIntent sentPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(SMS_SENT), 0);
                PendingIntent deliveredPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(SMS_DELIVERED), 0);

// For when the SMS has been sent
                registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        switch (getResultCode()) {
                            case Activity.RESULT_OK:
                                Toast.makeText(context, "Envoi du SMS réussi", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                Toast.makeText(context, "Erreur générique", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_NO_SERVICE:
                                Toast.makeText(context, "Service inaccessible", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_NULL_PDU:
                                Toast.makeText(context, "Pas de PDU renseigné", Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_RADIO_OFF:
                                Toast.makeText(context, "Réseau hors ligne", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }, new IntentFilter(SMS_SENT));

// For when the SMS has been delivered
                registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        switch (getResultCode()) {
                            case Activity.RESULT_OK:
                                Toast.makeText(getBaseContext(), "SMS envoyé", Toast.LENGTH_SHORT).show();
                                break;
                            case Activity.RESULT_CANCELED:
                                Toast.makeText(getBaseContext(), "SMS non envoyé", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }, new IntentFilter(SMS_DELIVERED));

// Get the default instance of SmsManager
                SmsManager smsManager = SmsManager.getDefault();
// Send a text based SMS
                smsManager.sendTextMessage(phoneNo, null, message, sentPendingIntent, deliveredPendingIntent);


            }
        }
    }

    public void nouvelleActivite(View view) {
        Intent startNewActivity = new Intent(this, ChangeNameActivity.class);
        startNewActivity.putParcelableArrayListExtra("Contacts", contacts);
        startActivity(startNewActivity);
    }

    // Méthode bouton annulation :
    public void retourActivite(View view) {
        Intent backToOldActivity = new Intent(this, MainActivity.class);
        startActivity(backToOldActivity);
    }

    /* Essai pour régler un problème de permission pour l'envoi de sms, mais la méthode checkSelfPermission a été implanté en version 22
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    if (ContextCompat.checkSelfPermission(SecondActivity,
    Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {

        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(SecondActivity,
                Manifest.permission.READ_CONTACTS)) {

            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

        } else {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(SecondActivity,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }*/
}
