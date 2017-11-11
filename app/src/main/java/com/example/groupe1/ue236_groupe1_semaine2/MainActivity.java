package com.example.groupe1.ue236_groupe1_semaine2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


//import com.example.groupe1.ue236_groupe1_semaine2.R;

public class MainActivity extends AppCompatActivity {


    // Intent intent;

    int nb_contact = 4;
    //String[] contacts = new String[nb_contact];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*populateContactsList();*/

        // Construct the data source
        ArrayList<Contact> arrayOfContacts = Contact.getContacts();
        // Create the adapter to convert the array to views
        final CustomContactsAdapter adapter = new CustomContactsAdapter(this, arrayOfContacts);
        // Attach the adapter to a ListView
        final ListView listView = (ListView) findViewById(R.id.lvContacts);
        listView.setAdapter(adapter);


        Button bouton_1 = (Button) findViewById(R.id.bouton_1);

        bouton_1.setOnClickListener (new OnClickListener() {
            @Override
            public void onClick (View v) {
                nouvelleActivite(v);
            }
        });

        final Button selectall = (Button) findViewById(R.id.selectall);
        selectall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // toutes les checkbox checked
                // ou recup toute la ListView

                for(int i = 0; i < adapter.getCount(); i++){
                    listView.setItemChecked(i, true);
                }

                SparseBooleanArray checked = listView.getCheckedItemPositions();

            }
        });

        }



/*    private void populateContactsList() {
        // Construct the data source
        ArrayList<Contact> arrayOfContacts = Contact.getContacts();
        // Create the adapter to convert the array to views
        CustomContactsAdapter adapter = new CustomContactsAdapter(this, arrayOfContacts);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvContacts);
        listView.setAdapter(adapter);
    }*/



    public void nouvelleActivite(View view) {
        Intent startNewActivity = new Intent(this, SecondActivity.class);
        startNewActivity.putExtra("nb_contact", nb_contact);
        //startNewActivity.putExtra("Contact", contacts);
        startActivity(startNewActivity);
    }
}
