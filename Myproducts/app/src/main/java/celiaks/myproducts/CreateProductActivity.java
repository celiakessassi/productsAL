package celiaks.myproducts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateProductActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        TextView Textv = (TextView)findViewById(R.id.name);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("data");
            Textv.setText(j);
        }
    }

    public void RegisterProduct(View view){
        EditText name_f = (EditText) findViewById(R.id.name);
        EditText price_f = (EditText) findViewById(R.id.price);
        EditText date_f = (EditText) findViewById(R.id.date);
        EditText cu_f = (EditText) findViewById(R.id.cu);
        String name = name_f.getText().toString();
        Integer price = 0;
        if(!price_f.getText().toString().isEmpty())
        price = Integer.parseInt(price_f.getText().toString());
        String date = date_f.getText().toString();
        String cu = cu_f.getText().toString();

        db = new DatabaseHelper(this);
        boolean result = db.insertProduct(name,price,0,date,cu);

        //logfield.setText(name);
        if (result==true){
            Toast.makeText(CreateProductActivity.this,"Data inserted",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(CreateProductActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(CreateProductActivity.this, ProductActivity.class);
        startActivity(intent);
        finish();
    }
}
