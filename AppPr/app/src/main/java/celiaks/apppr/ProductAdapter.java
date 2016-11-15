package celiaks.apppr;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ck on 03/10/16.
 */

public class ProductAdapter  extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, ArrayList<Product> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = View.inflate(getContext(),R.layout.products_row,null);

        }
        // Lookup view for data population

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.ly) ;

        layout.setTag(product.id);
        layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProductsDetailsActivity.class);
                intent.putExtra("data", view.findViewById(R.id.ly).getTag().toString());
                view.getContext().startActivity(intent);
            }
        });

        // Populate the data into the template view using the data object
        name.setText(product.name);

        price.setText(product.price.price+" "+product.price.cu);
        address.setText(product.price.address);

        // Return the completed view to render on screen
        return convertView;
    }


}
