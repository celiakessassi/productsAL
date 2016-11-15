package celiaks.apppr;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductsDetailsActivity extends AppCompatActivity {
DatabaseHelper db;
    private PriceAdapter adapter;
    private String Product_id;
    private TextView namef ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_details);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("data");
            Product_id=j;
            if (!j.isEmpty()){

                db = new DatabaseHelper(this);
                Cursor res = db.getProduct(j);
                res.moveToFirst();

                if (res.getCount()>0){
                     namef = (TextView) findViewById(R.id.name);
                    namef.setTag(res.getString(0));
                    namef.setText(res.getString(1));

                    // set prices
                    Cursor pr_res = db.getProduct_price(j);
                    ListView lvItems = (ListView) findViewById(R.id.prices_listview);

                    ArrayList<Price> array = new ArrayList<Price>();

                    adapter = new PriceAdapter(this, array);
                    lvItems.setAdapter(adapter);
                    db = new DatabaseHelper(this);

                    while (pr_res.moveToNext()){

                        Price price=new Price(pr_res.getString(0),pr_res.getString(2),pr_res.getString(3));
                        price.cu =pr_res.getString(5);
                        price.date =pr_res.getString(4);
                        adapter.add(price);
                    }
                }


            }
        }
    }


    void DeleteProduct(){
        boolean res= db.deleteProduct(Product_id);
        if(res){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(ProductsDetailsActivity.this,"Product deleted",Toast.LENGTH_LONG).show();
            startActivity(intent);

        }else {
            Toast.makeText(ProductsDetailsActivity.this,"Error Product not deleted",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.delete:
                DeleteProduct();
                return true;
            case R.id.edit:
                prompEditdialog();
                return true;
            case R.id.addprice:
                PrompPriceForm();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    void prompEditdialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View  update_product = inflater.inflate(R.layout.update_product, null);
        EditText name= (EditText) update_product.findViewById(R.id.name);
        name.setText(db.getProduct(Product_id).getString(1));
        alertDialogBuilder.setTitle("Edit product name");
        alertDialogBuilder.setView(update_product);
        alertDialogBuilder.setCancelable(true)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText name= (EditText) update_product.findViewById(R.id.name);
                        boolean res = db.UpdateProduct(name.getText().toString(),Product_id);
                        if (res)
                        {
                            namef.setText(name.getText().toString());
                            Toast.makeText(ProductsDetailsActivity.this,"product updated ",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(ProductsDetailsActivity.this,"Error Product not updated",Toast.LENGTH_LONG).show();

                        }
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        ;
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    void PrompPriceForm(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View  create_price = inflater.inflate(R.layout.create_price, null);
        alertDialogBuilder.setTitle("Create price");
        alertDialogBuilder.setView(create_price);
        alertDialogBuilder.setCancelable(true)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        final AlertDialog al=alertDialog;

        //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText pricef = (EditText) create_price.findViewById(R.id.price);
                EditText address = (EditText) create_price.findViewById(R.id.address);
                EditText cu = (EditText) create_price.findViewById(R.id.cu);
                EditText date = (EditText) create_price.findViewById(R.id.date);

                Price price = new Price(null,pricef.getText().toString(),address.getText().toString());
                price.cu = cu.getText().toString() ;
                price.date = date.getText().toString() ;
                price.product_id = Product_id ;
                Long res=db.storeprice(price);
                if (res>0){
                    al.dismiss();
                    Intent intent = getIntent();

                    finish();
                    startActivity(intent);
                    Toast.makeText(ProductsDetailsActivity.this,"Data inserted",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    void PrompPriceHandlerForm(View view, final String price_id){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View  update_price = inflater.inflate(R.layout.update_price, null);


        alertDialogBuilder.setTitle("Update price");
        alertDialogBuilder.setView(update_price);
        alertDialogBuilder
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
        .setNegativeButton("remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        EditText pricef = (EditText) update_price.findViewById(R.id.price);
        EditText address = (EditText) update_price.findViewById(R.id.address);
        EditText cu = (EditText) update_price.findViewById(R.id.cu);
        EditText date = (EditText) update_price.findViewById(R.id.date);

        Price price = db.getPrice(price_id);

        pricef.setText(price.price);
        address.setText(price.address);
        cu.setText(price.cu);
        date.setText(price.date);

        final AlertDialog al=alertDialog;
        final AlertDialog al2 = alertDialog;


        //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               boolean res=db.removeprice(price_id);
                if (res){
                    al2.dismiss();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    Toast.makeText(ProductsDetailsActivity.this,"price deleted",Toast.LENGTH_LONG).show();
                }
            }
        });


        //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText pricef = (EditText) update_price.findViewById(R.id.price);
                EditText address = (EditText) update_price.findViewById(R.id.address);
                EditText cu = (EditText) update_price.findViewById(R.id.cu);
                EditText date = (EditText) update_price.findViewById(R.id.date);

                Price price = new Price(null,pricef.getText().toString(),address.getText().toString());
                price.cu = cu.getText().toString() ;
                price.date = date.getText().toString() ;
                boolean res=db.updateprice(price,price_id);
                if (res){
                    al2.dismiss();
                    Intent intent = getIntent();

                    finish();
                    startActivity(intent);
                    Toast.makeText(ProductsDetailsActivity.this,"Data updated",Toast.LENGTH_LONG).show();

                }
            }
        });
    }


}
