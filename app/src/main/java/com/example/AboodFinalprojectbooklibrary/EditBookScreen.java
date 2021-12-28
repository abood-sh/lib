package com.example.AboodFinalprojectbooklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EditBookScreen extends AppCompatActivity {

    DataBase db;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        ImageView image =findViewById(R.id.imageBookIv);
        TextView name =findViewById(R.id.bookNameTv2);
        TextView author=findViewById(R.id.bookAuthorTv2);
        TextView release=findViewById(R.id.bookReleaseTv2);
        TextView pages=findViewById(R.id.bookPageTv2);
        TextView category=findViewById(R.id.bookCategoryTv2);
        Button edit=findViewById(R.id.EditButton);

        db=new DataBase(this);

        Intent i =getIntent();
        book=(Book)i.getSerializableExtra("book");
        Uri uri=Uri.parse(book.getImage());
        image.setImageURI(uri);
        name.setText(book.getName()+"");
        author.setText(book.getAuthor()+"");
        release.setText(book.getRelease()+"");
        pages.setText(book.getPages()+"");
        category.setText(book.getCategory()+"");

        setTitle(name.getText().toString());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getBaseContext(), UpdateBook.class);
                intent1.putExtra("book",book);
                startActivity(intent1);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.deletemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.delete:
                db.deleteBook(book);
                Intent i=new Intent(getBaseContext(), SelectBook.class);
                startActivity(i);
                finish();
                break;
        }

        return true;
    }

}