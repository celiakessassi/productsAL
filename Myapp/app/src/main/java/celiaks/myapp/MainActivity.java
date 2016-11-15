package celiaks.myapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Products> myproducts= new ArrayList<Products>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PopulateProductsList();
        PopulateListView();
    }

    private void PopulateProductsList() {
        myproducts.add(new Products("shompoi",320,"DA","fdf"));
    }


    private void PopulateListView() {
        ArrayAdapter<Products> adapter = new MylistAdapter();
        ListView list = (ListView) findViewById(R.id.item_list);
        list.setAdapter(adapter);
    }

    private  class MylistAdapter extends ArrayAdapter<Products>{

        public MylistAdapter() {

            super(MainActivity.this, R.layout.product_list_view, myproducts);
        }


    }

 /*   @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void AddButtonClick(View v){
        EditText field = (EditText) findViewById(R.id.editText);
        String ToDo = (field).getText().toString();
        if (ToDo.isEmpty()) return ;
        arrayAdapterToDo.add(ToDo);
        field.setText("");
    }
    */
}
