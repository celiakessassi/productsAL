package celiaks.myproducts;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;


    private ArrayList<Store> stores;
    private ArrayAdapter<Store> storesAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // ADD HERE
        lvItems = (ListView) findViewById(R.id.lvItems);


        ArrayList<Store> arrayOfUsers = new ArrayList<Store>();

        StoreAdapter adapter = new StoreAdapter(this, arrayOfUsers);

        lvItems.setAdapter(adapter);
        db = new DatabaseHelper(this);
        Cursor res = db.getStores();
        if (res.getCount()==0){
            TextView nodatamsg = (TextView) findViewById(R.id.nodatamsg);
            nodatamsg.setText("no store");
            //nodatamsg.setVisibility(1);
            return ;
        }
        while (res.moveToNext()){
            // Add item to adapter
            Store store = new Store(res.getString(1), res.getString(2));
            adapter.add(store);
        }
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main, menu);

        return true;

    }

    public void createstore(View view) {
             EditText Mainfield = (EditText) findViewById(R.id.Mainfield);
            Intent intent = new Intent(MainActivity.this, CreateStoreActivity.class);
            String store_name = Mainfield.getText().toString();
            intent.putExtra("data", store_name);
            startActivity(intent);
            finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.openStores:
                intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.openProducts:
                intent = new Intent(MainActivity.this, ProductActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
