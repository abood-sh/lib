package com.example.AboodFinalprojectbooklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class SelectBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_book);

        RecyclerView recyclerView=findViewById(R.id.bookRv);

        DataBase db=new DataBase(this);

        Intent intent =getIntent();
        int id=intent.getIntExtra("id",0);
        String nameCategory=intent.getStringExtra("name");

        int category=id;

        setTitle(nameCategory+"");

        ArrayList<Book> books=db.ShowOneBook(category);
        AdapterBook a = new AdapterBook(this, books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(a);
    }


}
class AdapterBook extends RecyclerView.Adapter<AdapterBook.BookHolder> {

    ArrayList<Book> books;
    SelectBook aSelectBook;


    public AdapterBook(SelectBook aSelectBook, ArrayList<Book> books) {
        this.aSelectBook = aSelectBook;
        this.books = books;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(aSelectBook).inflate(R.layout.item_book,parent,false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterBook.BookHolder holder, int position) {
        Book book=books.get(position);

        Uri uri=Uri.EMPTY.parse(book.getImage()+"");
        holder.image.setImageURI(uri);
        holder.name.setText(book.getName());
        holder.release.setText(book.getRelease()+"");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(aSelectBook, EditBookScreen.class);
                intent.putExtra("book",book);
                aSelectBook.startActivity(intent);
                aSelectBook.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name,release;

        public BookHolder(View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.bookIv);
            name=itemView.findViewById(R.id.bookNameTv);
            release=itemView.findViewById(R.id.bookRelTv);

        }
    }
}
