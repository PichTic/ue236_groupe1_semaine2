package com.example.groupe1.ue236_groupe1_semaine2;

/**
 * Created by Toorop on 11/11/2017.
 */

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomContactsAdapter extends ArrayAdapter<Contact> {
    public CustomContactsAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }

        // Get the data item for this position
        Contact contact = getItem(position);

        // Lookup view for data population
        TextView tvNom = (TextView) convertView.findViewById(R.id.tvNom);
        TextView tvPrenom = (TextView) convertView.findViewById(R.id.tvPrenom);
        // Populate the data into the template view using the data object
        tvNom.setText(contact.getNom());
        tvPrenom.setText(contact.getPrenom());
        // Retourne la vue
        return convertView;
    }
}