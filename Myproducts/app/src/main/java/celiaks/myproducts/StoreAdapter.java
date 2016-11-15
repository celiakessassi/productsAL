package celiaks.myproducts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import celiaks.myproducts.R;
import celiaks.myproducts.Store;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import android.view.Menu;

import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toast;

public class StoreAdapter extends ArrayAdapter<Store> {

    public StoreAdapter(Context context, ArrayList<Store> stores) {
        super(context, 0, stores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Store store = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = View.inflate(getContext(),R.layout.stores_list,null);

        }
        // Lookup view for data population

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        // Populate the data into the template view using the data object
        name.setText(store.name);
        address.setText(store.address);
        // Return the completed view to render on screen
        return convertView;
    }
}