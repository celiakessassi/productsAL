package celiaks.myproducts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ck on 02/10/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="dbapp.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        //create store db
        String sql;
       sql = "create table stores (id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(50) , address varchar(255) ); ";
        db.execSQL(sql);
      //  create products db
        sql = "create table products (id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(50) , price INTEGER, store INTEGER , date varchar(50) , cu varchar(10) ); ";
        db.execSQL(sql);
        //  create products db
        sql = "create table prices (id INTEGER PRIMARY KEY AUTOINCREMENT, price INTEGER , store_id INTEGER, date varchar(50) , product_id INTEGER ,  cu varchar(10)  ); ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS stores ");
        db.execSQL("DROP TABLE IF EXISTS products ");
        onCreate(db);
    }
    public boolean insertStore(String name_v ,String address_v ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name_v);
        contentValues.put("address",address_v);
        long result = db.insert("stores",null,contentValues);
        if (result==-1) return false;
        return true;
    }

    public Cursor getStores( ){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =
                "Select * from stores ORDER BY id desc; ";
        Cursor res = db.rawQuery(sql,null);
        return res;
    }

    public boolean insertProduct(String name ,Integer price , Integer store,String date , String cu  ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("price",price);
        contentValues.put("store",store);
        contentValues.put("date",date);
        contentValues.put("cu",cu);
        long result = db.insert("products",null,contentValues);
        if (result==-1) return false;
        return true;
    }

    public Cursor getProducts( ){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql =
                "Select * from products ORDER BY id desc; ";
        Cursor res = db.rawQuery(sql,null);
        return res;
    }


    public Cursor getProduct(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql =
                "Select * from products Where id = "+id+"; ";
        Cursor res = db.rawQuery(sql,null);
        return res;
    }

    public Cursor getPrices(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql =
                "Select * from prices Where product_id = "+id+"; ";
        Cursor res = db.rawQuery(sql,null);
        return res;
    }

    public Cursor getPrice(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql =
                "Select * from prices Where id = "+id+"; ";
        Cursor res = db.rawQuery(sql,null);
        return res;
    }

    public boolean insertPrice(String price ,String store_id,String date,String product_id,String cu ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("price",price);
        contentValues.put("store_id",store_id);
        contentValues.put("date",date);
        contentValues.put("product_id",product_id);
        contentValues.put("cu",cu);
        long result = db.insert("stores",null,contentValues);
        if (result==-1) return false;
        return true;
    }

    public Cursor getStore(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql =
                "Select * from stores Where id = "+id+"; ";
        Cursor res = db.rawQuery(sql,null);
        return res;
    }

    public boolean execute(String sql){
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
