package com.example.AboodFinalprojectbooklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {


    public DataBase(Context context){
        super(context,"library",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table category (id integer primary key autoincrement , name text)");

        sqLiteDatabase.execSQL("Create table book (id integer primary key autoincrement , image text ,name text , author text , releas text , pages text , category integer ,FOREIGN KEY (category) REFERENCES category (id))");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertBook(Book book){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("image",book.getImage());
        contentValues.put("name",book.getName());
        contentValues.put("author",book.getAuthor());
        contentValues.put("releas",book.getRelease());
        contentValues.put("pages",book.getPages());
        contentValues.put("category",book.getCategory());
        long result=db.insert("book",null,contentValues);

        return result !=-1;
    }

    public boolean insertCategory(Category category){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",category.getName());
        long result=db.insert("category",null,contentValues);

        return result !=-1;
    }

    public boolean updateBook(int nameNormall,String uri,String name,String author,String release,String pages,int category){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("image",uri);
        contentValues.put("name",name);
        contentValues.put("author",author);
        contentValues.put("releas",release);
        contentValues.put("pages",pages);
        contentValues.put("category",category);
        int result=db.update("book",contentValues,"id=?",new String[]{nameNormall+""});

            return result >0;
    }

    public boolean deleteBook(Book book){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("image",book.getImage());
        contentValues.put("name",book.getName());
        contentValues.put("author",book.getAuthor());
        contentValues.put("releas",book.getRelease());
        contentValues.put("pages",book.getPages());
        contentValues.put("category",book.getCategory());
        int result=db.delete("book","name=?",new String[]{book.getName()});

        return result >0;
    }

    public ArrayList<Category> ShowAllCategory(){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Category> categoryes=new ArrayList<Category>();
        Cursor c=db.rawQuery("select * from category",null);
       if (c.moveToFirst()){
           do {
               int id=c.getInt(c.getColumnIndexOrThrow("id"));
               String name=c.getString(c.getColumnIndexOrThrow("name"));
               Category category=new Category(id,name);
               categoryes.add(category);
           }while (c.moveToNext());
       }
        db.close();
        return categoryes;
    }


    public ArrayList<Book> ShowOneBook(int num){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Book> books=new ArrayList<Book>();
        Cursor c=db.rawQuery(" select * from book where category=? ",new String[]{num+""});
        if (c.moveToFirst()){
            do {
                int id=c.getInt(0);
                String image=c.getString(1);
                String name=c.getString(2);
                String author =c.getString(3);
                String releas=c.getString(4);
                String pages =c.getString(5);
                int category=c.getInt(6);
                Book book=new Book(id,image,name,author,releas,pages,category);
                books.add(book);
            }while (c.moveToNext());
        }
        c.close();
        return books;
    }
}
