package com.example.groupe1.ue236_groupe1_semaine2;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {


    private ArrayList<Contact> arrayOfContacts = new ArrayList<Contact>();

    // cf. https://stackoverflow.com/a/37808706/4698318
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showContacts();

        // Création de l'adapter qui va convertir l'array en view
        final CustomContactsAdapter adapter = new CustomContactsAdapter(this, arrayOfContacts);
        // Je link l'adapter à ma Listview
        final ListView listView = (ListView) findViewById(R.id.lvContacts);
        listView.setAdapter(adapter);


        Button bouton_1 = (Button) findViewById(R.id.bouton_1);

        bouton_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nouvelleActivite(v);
            }
        });

        final Button selectall = (Button) findViewById(R.id.selectall);
        selectall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // toutes les checkbox checked
                // ou recup toute la ListView

                for (int i = 0; i < adapter.getCount(); i++) {
                    listView.setItemChecked(i, true);
                }

                SparseBooleanArray checked = listView.getCheckedItemPositions();

            }
        });

    }


    /**
     * Afficher les contacts dans la ListView
     */
    private void showContacts()
    {

        // Control de la version du SDK et si j'ai la permission d'acceder aux Contacts
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            ContentResolver cr = getContentResolver();
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);


            if (cursor.moveToFirst()) {

                do {
                    Contact contact = new Contact();
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    contact.setNom(name);
                    int num = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    if (num == 1) {
                        Uri uri2 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                        String selection2 = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?";
                        String[] projection = null;
                        String[] selectionArgs2 = null;
                        String sortOrder = null;
                        Cursor cur2 = cr.query(uri2, projection, selection2, selectionArgs2, sortOrder);
                        while (cur2.moveToNext()) {
                            String phone = cur2.getString(cur2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            contact.setTel(phone);
                        }
                    }
                    //@todo récupérer le numéro de téléphone du contact
                    contact.setIsChecked(false);

                    arrayOfContacts.add(contact);

                } while (cursor.moveToNext());
            }
            cursor.close();

        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showContacts();
            } else {
                Toast.makeText(this, "Autoriser l'acces à vos contact", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void nouvelleActivite(View view) {
        Intent startNewActivity = new Intent(this, SecondActivity.class);
        for (int i = 0; i < arrayOfContacts.size(); i++) {
            if (!arrayOfContacts.get(i).getIsChecked()) {
                arrayOfContacts.remove(i);
            }
        }
        startNewActivity.putExtra("Contacts", arrayOfContacts);
        startActivity(startNewActivity);
    }

}
