package com.example.groupe1.ue236_groupe1_semaine2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    }

    public void nouvelleActivite (View view) {
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
                    Marion = "Marion";
                } else {
                    Marion = "";
                }
                break;

            case R.id.checkBox2:
                if (checked) {
                    Vincent = "Vincent";
                } else {
                    Vincent = "";
                }
                break;
            case R.id.checkBox3:
                if (checked) {
                    Melissa = "Melissa";
                } else {
                    Melissa = "";
                }
                break;
            case R.id.checkBox4:
                if (checked) {
                    Emmanuel = "Emmanuel";
                } else {
                    Emmanuel = "";
                }
                break;

            default:
                break;
        }
    }
}


