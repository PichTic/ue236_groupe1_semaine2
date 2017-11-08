package com.example.groupe1.ue236_groupe1_semaine2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

//import com.example.groupe1.ue236_groupe1_semaine2.R;

public class MainActivity extends AppCompatActivity {

    // Intent intent;

    int nb_contact = 4;
    String[] contacts = new String[nb_contact];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bouton_1 = (Button) findViewById(R.id.bouton_1);

        bouton_1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                nouvelleActivite(v);
            }
        });
    }

    public void nouvelleActivite(View view) {
        Intent startNewActivity = new Intent(this, SecondActivity.class);
        startNewActivity.putExtra("nb_contact", nb_contact);
        startNewActivity.putExtra("Contact", contacts);
        startActivity(startNewActivity);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {

            case R.id.checkcontact1:
                if (checked) {
                    final String contact1 = getString(R.string.contact_1);
                    contacts[0] = contact1;
                } else {
                    contacts[0] = "";
                }
                break;

            case R.id.checkcontact2:
                if (checked) {
                    final String contact2 = getString(R.string.contact_2);
                    contacts[1] = contact2;
                } else {
                    contacts[1] = "";
                }
                break;
            case R.id.checkcontact3:
                if (checked) {
                    final String contact3 = getString(R.string.contact_3);
                    contacts[2] = contact3;
                } else {
                    contacts[2] = "";
                }
                break;
            case R.id.checkcontact4:
                if (checked) {
                    final String contact4 = getString(R.string.contact_4);
                    contacts[3] = contact4;
                } else {
                    contacts[3] = "";
                }
                break;
        }
    }
}
