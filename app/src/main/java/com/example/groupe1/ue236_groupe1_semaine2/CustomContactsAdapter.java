package com.example.groupe1.ue236_groupe1_semaine2;

/**
 * Created by Toorop on 11/11/2017.
 */

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class CustomContactsAdapter extends ArrayAdapter<Contact> {
    private static final String TAG = "CustomContactsAdapter";
    Context context;

    public CustomContactsAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);

    }


    /*public void readContacts () {

        ContentResolver cr = context.getContentResolver();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cur = cr.query(uri, projection, selection, selectionArgs, sortOrder);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String Nom = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                int num = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if (num == 1) {
                    Uri uri2 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                    String selection2 = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?";
                    String[] selectionArgs2 = new String[]{id};
                    Cursor cur2 = cr.query(uri2, projection, selection2, selectionArgs2, sortOrder);
                    while (cur2.moveToNext()) {
                        String phone = cur2.getString(cur2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                }
            }

        }

    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }

        // Get the data item for this position
        Contact contact = getItem(position);

        // Lookup view for data population
        TextView Nom = (TextView) convertView.findViewById(R.id.Nom);
        // Populate the data into the template view using the data object
        Nom.setText(contact.getNom());
        // Retourne la vue
        return convertView;
    }


}

