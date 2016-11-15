package celiaks.apppr;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private DatabaseHelper db ;
    private ProductAdapter adapter;

    private String format;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        // ADD HERE
        ListView lvItems = (ListView) findViewById(R.id.products_listview);

        ArrayList<Product> array = new ArrayList<Product>();

         adapter = new ProductAdapter(this, array);

        lvItems.setAdapter(adapter);
        db = new DatabaseHelper(this);
        Cursor res = db.getProduct_list();
        if (res.getCount()==0){
            TextView nodatamsg = (TextView) findViewById(R.id.nodatamsg);
            nodatamsg.setText("no product");
            return ;
        }
        while (res.moveToNext()){

            Cursor pro_cur = db.getProduct_price(res.getString(0));
            pro_cur.moveToFirst();
            Price price=new Price(pro_cur.getString(0),pro_cur.getString(2),pro_cur.getString(3));
            price.cu =pro_cur.getString(5);
            price.date =pro_cur.getString(4);

            Product product = new Product(res.getString(0),res.getString(1),price);

            adapter.add(product);
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {

              code = scanningResult.getContents();
              format = scanningResult.getFormatName();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            if(db.isStored(format,code)){
                String product_id = db.getProductIDByCode(format,code);

                if(!(product_id==null)){
                    Log.d("TAG", "onActivityResult: works  ouy");
                    Intent intentf = new Intent(this, ProductsDetailsActivity.class);
                    intentf.putExtra("data", product_id);
                    startActivity(intentf);
                }
            }else{
                LayoutInflater inflater = getLayoutInflater();

                final View  dialoglayout = inflater.inflate(R.layout.create_product, null);

                alertDialogBuilder.setMessage("value donest exist");
                alertDialogBuilder.setView(dialoglayout);
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("Store product", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                CreateProduct(dialoglayout);
                            }
                        });
            }

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.scanb:
                IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                scanIntegrator.initiateScan();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void CreateProduct(View view ) {
        EditText name = (EditText) view.findViewById(R.id.name);
        EditText pricef = (EditText) view.findViewById(R.id.price);
        EditText address = (EditText) view.findViewById(R.id.address);

        EditText cu = (EditText) view.findViewById(R.id.cu);

        if (name.getText().toString().isEmpty()|pricef.getText().toString().isEmpty()|address.getText().toString().isEmpty())
        {
            return ;
        }
        Long res = db.insertProduct(name.getText().toString(),format,code,pricef.getText().toString(),address.getText().toString(),cu.getText().toString());
        if (res>0){
            TextView nodatamsg = (TextView) findViewById(R.id.nodatamsg);
            if (!nodatamsg.getText().toString().isEmpty()) nodatamsg.setText("");
            Price price = new Price(null,pricef.getText().toString(),address.getText().toString());
            price.cu = cu.getText().toString();
            adapter.insert(new Product(String.valueOf(res),name.getText().toString(),price),0);
            Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();

        }

    }
}
