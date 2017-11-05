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
    String Vincent;
    String Melissa;
    String Marion;
    String Emmanuel;

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
        Intent startNewActivity = new Intent(this, Main2Activity.class);
        startNewActivity.putExtra("V", Vincent);
        startNewActivity.putExtra("M", Marion);
        startNewActivity.putExtra("Me", Melissa);
        startNewActivity.putExtra("Em", Emmanuel);
        startActivity(startNewActivity);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {

            case R.id.checkBox:
                if (checked) {
                    final String contact1 = getString(R.string.contact_1);
                    Marion = contact1;
                } else {
                    Marion = "";
                }
                break;

            case R.id.checkBox2:
                if (checked) {
                    final String contact2 = getString(R.string.contact_2);
                    Vincent = contact2;
                } else {
                    Vincent = "";
                }
                break;
            case R.id.checkBox3:
                if (checked) {
                    final String contact3 = getString(R.string.contact_3);
                    Melissa = contact3;
                } else {
                    Melissa = "";
                }
                break;
            case R.id.checkBox4:
                if (checked) {
                    final String contact4 = getString(R.string.contact_4);
                    Emmanuel = contact4;
                } else {
                    Emmanuel = "";
                }
                break;
        }
    }
}
