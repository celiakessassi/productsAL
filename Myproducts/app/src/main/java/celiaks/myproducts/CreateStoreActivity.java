package celiaks.myproducts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class CreateStoreActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_store);

        TextView Textv = (TextView)findViewById(R.id.storename);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("data");
            Textv.setText(j);
        }
    }
    public void RegisterStore(View view){
        EditText storename = (EditText) findViewById(R.id.storename);
        EditText storeaddress = (EditText) findViewById(R.id.storeaddress);
        String name = storename.getText().toString();
        String address = storeaddress.getText().toString();

        db = new DatabaseHelper(this);
        boolean result = db.insertStore(name,address);

        //logfield.setText(name);
        if (result==true){
            Toast.makeText(CreateStoreActivity.this,"Data inserted",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(CreateStoreActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(CreateStoreActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
