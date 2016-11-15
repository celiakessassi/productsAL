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

public class PriceAdapter extends ArrayAdapter<Price> {
    private Context mContext;

    public PriceAdapter(Context context, ArrayList<Price> price) {
        super(context, 0, price);this.mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Price price = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = View.inflate(getContext(),R.layout.price_row,null);

        }
        // Lookup view for data population

        TextView pricet = (TextView) convertView.findViewById(R.id.price);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.ly) ;

        layout.setTag(price.id);
        layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(mContext instanceof ProductsDetailsActivity){
                    ((ProductsDetailsActivity)mContext).PrompPriceHandlerForm(view,view.findViewById(R.id.ly).getTag().toString());
                }

            }
        });

        // Populate the data into the template view using the data object
        date.setText(price.date);
        pricet.setText(price.price+" "+ price.cu);
        address.setText(price.address);

        // Return the completed view to render on screen
        return convertView;
    }
}
