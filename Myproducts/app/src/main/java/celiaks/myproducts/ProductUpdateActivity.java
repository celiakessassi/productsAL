package celiaks.myproducts;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ProductUpdateActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_update);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("data");
            if (!j.isEmpty()){


                db = new DatabaseHelper(this);
                Cursor res = db.getProduct(j);
                res.moveToFirst();


                if (res.getCount()>0){
                    EditText name_f = (EditText) findViewById(R.id.name);
                    EditText price_f = (EditText) findViewById(R.id.price);
                    EditText date_f = (EditText) findViewById(R.id.date);
                    EditText cu_f = (EditText) findViewById(R.id.cu);


                    name_f.setTag(res.getString(0));
                    name_f.setText(res.getString(1));
                    price_f.setText(res.getString(2));
                    date_f.setText(res.getString(4));

                    cu_f.setText(res.getString(5));
                }
            }
        }
    }

    public void UpdateProduct(View view){

        EditText name_f = (EditText) findViewById(R.id.name);
        EditText price_f = (EditText) findViewById(R.id.price);
        EditText date_f = (EditText) findViewById(R.id.date);
        EditText cu_f = (EditText) findViewById(R.id.cu);

        String id = name_f.getTag().toString();
        db = new DatabaseHelper(this);
        Boolean result  =  db.execute("Update products set " +
                "name ='" + name_f.getText().toString()+"'"+
                ", price ='" + price_f.getText().toString()+"'"+
                ", date ='" + date_f.getText().toString()+"'"+
                ", cu ='" + cu_f.getText().toString()+"'"+
                " where id= "+id+";");
        //logfield.setText(name);
        if (result==true){
            Toast.makeText(ProductUpdateActivity.this,"Product Updated",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(ProductUpdateActivity.this,"Product not Updated",Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(ProductUpdateActivity.this, ProductActivity.class);
        startActivity(intent);
        finish();
    }
    public void DeleteProduct(View view){
        EditText name_f = (EditText) findViewById(R.id.name);
        String id = name_f.getTag().toString();
        db = new DatabaseHelper(this);
        db.execute("Delete from products where id = "+id+";");

        Intent intent = new Intent(ProductUpdateActivity.this, ProductActivity.class);
        startActivity(intent);
        finish();
    }
}
