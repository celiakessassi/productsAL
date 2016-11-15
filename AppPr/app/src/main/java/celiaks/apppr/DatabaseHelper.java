package celiaks.apppr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import android.util.Log;

/**
 * Created by ck on 03/10/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbprices.db";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table products (id  INTEGER PRIMARY KEY AUTOINCREMENT , name varchar(40), format varchar(40), code varchar(255)  ); ");
        db.execSQL("create table prices (id  INTEGER PRIMARY KEY AUTOINCREMENT ,product_id INTEGER, price INTEGER, Address varchar(255), date varchar(20) , cu varchar(10)  ); ");
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table IF EXISTS  products");
        db.execSQL("Drop Table IF EXISTS  prices");
        onCreate(db);
    }

    public Cursor getProduct(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="SELECT * FROM  products where id=? ";
        Cursor res = db.rawQuery(sql,new String[] {id});
        return res;
    }

    public Cursor getProduct_list( ){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="SELECT * FROM  products ";
        Cursor res = db.rawQuery(sql,null);
        return res;
    }

    public Cursor getProduct_price(String product_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="SELECT * FROM  prices where product_id=? order by price";
        Cursor res = db.rawQuery(sql,new String[] {product_id});
        return res;
    }

    public boolean isStored(String format,String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select id from products where format=? and code=?",new String[] { format, code });
        return (res.getCount()>0) ;
    }
    public Long insertProduct(String name,String format,String code,String price,String address,String cu){
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("format", format);
        values.put("code", code);

        Long product_id =  db.insert("products",null,values);

        values.clear();
        values.put("price", price);
        values.put("address", address);
        values.put("product_id",product_id );
        if (cu.isEmpty())cu="DA";
        values.put("cu",cu );
        values.put("date",currentDate() );




        Long res = db.insert("prices",null,values);

        return res;

    }
     boolean deleteProduct(String j){
        SQLiteDatabase db = this.getWritableDatabase();
         String sql ;
         try {
             sql = "Delete from products where products.id=?" ;
             db.execSQL(sql,new String[] {j} );
             sql = "Delete from prices where prices.product_id=?" ;
             db.execSQL(sql,new String[] {j} );

        } catch (SQLException e) {
            return false;
        }
        return true;
    }



    boolean UpdateProduct(String name,String j){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ;
        try {
            sql = "Update  products set name=? where products.id=?" ;
            db.execSQL(sql,new String[] {name,j} );

        } catch (SQLException e) {
            return false;
        }
        return true;

    }
    Long storeprice(Price price){
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();


        values.put("price", price.price);
        values.put("address", price.address);
        values.put("product_id",price.product_id );

        if (price.cu.isEmpty())price.cu="DA";
            values.put("cu",price.cu );
        if (price.date.isEmpty())price.date=currentDate();
        values.put("date",price.date );

        Long res = db.insert("prices",null,values);

        return res;
    }

   boolean updateprice(Price price,String j){
        SQLiteDatabase db = this.getWritableDatabase();

       String sql ;
       try {
           sql = "Update  prices set price=?, address=? , cu=? , date=? where id=?" ;
           db.execSQL(sql,new String[] {price.price,price.address,price.cu,price.date,j} );

       } catch (SQLException e) {
           return false;
       }
       return true;

    }

    Price getPrice(String price_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="SELECT * FROM  prices where id=? order by price";
        Cursor res = db.rawQuery(sql,new String[] {price_id});
        if (res.getCount()>0){
            res.moveToFirst();
            Price price = new Price(res.getString(0),res.getString(2),res.getString(3));
            price.cu =res.getString(5);
            price.date =res.getString(4);

            return price;
        }
        return null;

    }

    boolean removeprice(String j){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ;
        try {
            sql = "Delete from prices where prices.id=?" ;
            db.execSQL(sql,new String[] {j} );

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    String getProductIDByCode(String format ,String code){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="SELECT * FROM  products where format=? and code=? ";
        Cursor res = db.rawQuery(sql,new String[] {format , code});
        if (res.getCount()>0){
            res.moveToFirst();
            return res.getString(0);
        }
        return null;
    }

    String currentDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(cal.getTime());
    }

}
