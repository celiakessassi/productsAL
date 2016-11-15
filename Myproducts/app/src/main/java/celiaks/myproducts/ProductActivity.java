package celiaks.myproducts;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        // ADD HERE
        ListView lvItems = (ListView) findViewById(R.id.lvItems);

        ArrayList<Product> array = new ArrayList<Product>();

        ProductAdapter adapter = new ProductAdapter(this, array);

        lvItems.setAdapter(adapter);
        db = new DatabaseHelper(this);
        Cursor res = db.getProducts();
        if (res.getCount()==0){
            TextView nodatamsg = (TextView) findViewById(R.id.nodatamsg);
            nodatamsg.setText("no product");
            return ;
        }
        while (res.moveToNext()){
          //   Add item to adapter
            Product product = new Product(res.getString(0),res.getString(1), res.getString(2),res.getString(3),res.getString(4),res.getString(5));
            adapter.add(product);
        }
    }

    public void createproduct(View view) {
        EditText Mainfield = (EditText) findViewById(R.id.Mainfield);
        Intent intent = new Intent(ProductActivity.this, CreateProductActivity.class);
        String Product_name = Mainfield.getText().toString();
        intent.putExtra("data", Product_name);
        startActivity(intent);
        finish();
    }


}
