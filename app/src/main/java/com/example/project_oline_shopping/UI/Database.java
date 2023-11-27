package com.example.project_oline_shopping.UI;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class Database  extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;
    private static String databaseName="ShoppingDatabase";

    public Database(Context context)
    {
        super(context,databaseName,null,1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //  db.execSQL("INSERT INTO employee (name,title,phone,dept_id) VALUES ('Ahmed','HR','01098023499',"+2+")");


        db.execSQL("create table customer(cust_id integer Primary key Autoincrement  , cust_name text , cust_username text ,cust_password text , cust_gender text ,cust_birthday text , cust_job text  )");
        db.execSQL("create table orders(ord_id integer Primary key Autoincrement , ord_Date  text , ord_Addres ,ord_cust_id integer ,FOREIGN KEY(ord_cust_id) REFERENCES customer(cust_id)  )");
        db.execSQL("create table categories(cat_id integer Primary key Autoincrement , cat_name text )");
        db.execSQL("create table products(pro_id integer Primary key Autoincrement  , pro_name text , pro_price integer , pro_quantity integer ,pro_cat_id integer ,FOREIGN KEY(pro_cat_id) REFERENCES categories(cat_id) )");
        db.execSQL("create table orderdetails(ord_id integer REFERENCES orders(ord_id),pro_id integer REFERENCES products(pro_id) ,Primary key(ord_id,pro_id))");
        db.execSQL("create table shopcard(product_id integer Primary key Autoincrement  , product_name text , product_price integer , product_quantity integer)");

        db.execSQL("INSERT INTO categories (cat_name) VALUES ('Jeans')");
        db.execSQL("INSERT INTO categories (cat_name) VALUES ('Joes')");
        db.execSQL("INSERT INTO categories (cat_name) VALUES ('Tshirt')");

        db.execSQL("INSERT INTO products (pro_name,pro_price,pro_quantity,pro_cat_id) VALUES ('jeans_xl' ,"+200+", "+3+",1)");
        db.execSQL("INSERT INTO products (pro_name,pro_price,pro_quantity,pro_cat_id) VALUES ('jeans_kids_xl' ,"+150+", "+3+",1)");
        db.execSQL("INSERT INTO products (pro_name,pro_price,pro_quantity,pro_cat_id) VALUES ('jeans_men_2xl' ,"+250+", "+3+",1)");


        db.execSQL("INSERT INTO products (pro_name,pro_price,pro_quantity,pro_cat_id) VALUES ('men_shoes' ,"+200+", "+3+",2)");
        db.execSQL("INSERT INTO products (pro_name,pro_price,pro_quantity,pro_cat_id) VALUES ('women_shoes' ,"+150+", "+3+",2)");
        db.execSQL("INSERT INTO products (pro_name,pro_price,pro_quantity,pro_cat_id) VALUES ('kid_shoes' ,"+100+", "+3+",2)");


        db.execSQL("INSERT INTO products (pro_name,pro_price,pro_quantity,pro_cat_id) VALUES ('men_tshirt' ,"+200+", "+3+",3)");
        db.execSQL("INSERT INTO products (pro_name,pro_price,pro_quantity,pro_cat_id) VALUES ('women_tshirt' ,"+250+", "+3+",3)");
        db.execSQL("INSERT INTO products (pro_name,pro_price,pro_quantity,pro_cat_id) VALUES ('kid_tshirt' ,"+150+", "+3+",3)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists customer");
        db.execSQL("drop table if exists orders");
        db.execSQL("drop table if exists categories");
        db.execSQL("drop table if exists products");
        db.execSQL("drop table if exists orderdetails");
        onCreate(db);

    }
        // 3 FRAGMENTS
    public ArrayList<Product> returnProducts(int Category) {
        String [] row ={String.valueOf(Category)} ;
        ArrayList<Product> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select pro_name,pro_price from products where pro_cat_id like ?",row) ;
        Product p;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                p = new Product(cursor.getString(0), cursor.getString(1), cursor.getString(0));
                list.add(p);
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }



    public Boolean VerifyProduct(String name) {
        boolean isFound = false;
        String[] row = {name};
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select product_name from shopcard  where product_name=?",row);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                if (cursor.getString(0).equalsIgnoreCase(name)) {
                    isFound = true;
                    break;
                }
            }
        }
        return isFound;
    }

  // 3 FRAGMENTS
    public void AddItem(Product p) {

        sqLiteDatabase= getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("product_name", p.getName());
        values.put("product_price", p.getPrice());
        values.put("product_quantity", 1);
        // Insert the new row, returning the primary key value of the new row
        sqLiteDatabase.insert("shopcard", null, values);
        sqLiteDatabase.close();
    }

    public Cursor ProductSearch(String name) {
        boolean isFound = false;
        String[] row = {name};
        sqLiteDatabase=getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from shopcard where product_name like ?",row) ;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                if (cursor.getString(1).equalsIgnoreCase(name)) {
                    break;
                }
            }
        }
        return cursor;
    }

    public void IncreaseQuantity(String name) {
        Cursor cursor = ProductSearch(name);
        ContentValues row = new ContentValues();
        row.put("product_name", cursor.getString(1));
        row.put("product_price", cursor.getString(2));
        int newQuantity = cursor.getInt(3) + 1;
        row.put("product_quantity", newQuantity);
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update("shopcard", row, "product_id=" + cursor.getInt(0) +"" , null);
        cursor.close();
        sqLiteDatabase.close();
    }

    public void DecreaseQuantity(String name) {
        Cursor cursor = ProductSearch(name);
        ContentValues row = new ContentValues();
        row.put("product_name", cursor.getString(1));
        row.put("product_price", cursor.getString(2));
        int newQuantity = cursor.getInt(3) - 1;
        row.put("product_quantity", newQuantity);

        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update("shopcard", row, "product_id=" + cursor.getInt(0) +"" , null);
        cursor.close();
        sqLiteDatabase.close();
    }


    public Cursor showCart() {
        sqLiteDatabase = getReadableDatabase();
        Cursor curs = sqLiteDatabase.rawQuery("select product_name,product_price,product_quantity from shopcard",null);
        if (curs != null)
            curs.moveToFirst();
        sqLiteDatabase.close();
        return curs;
    }

     // fill class product >>> recycleview
    public List<Product> ReturnCart() {
        List<Product> l = new ArrayList<>();
        Cursor cursor = showCart();
        while (!cursor.isAfterLast()) {
            l.add(new Product(cursor.getString(0), String.valueOf(cursor.getInt(1)), cursor.getInt(2),cursor.getString(0)));
            cursor.moveToNext();
        }
        cursor.close();
        return l;
    }

    public void EmptyCart() {
        sqLiteDatabase= getWritableDatabase();
        sqLiteDatabase.execSQL("delete from " + "shopcard");
        sqLiteDatabase.close();
    }

    public void RemoveItem(Product p) {
        sqLiteDatabase  =getWritableDatabase();
        sqLiteDatabase.delete("shopcard", "product_name="+ p.getName() +"", null);
        sqLiteDatabase.close();
    }

}
