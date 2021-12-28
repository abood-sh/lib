package com.example.AboodFinalprojectbooklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateBook extends AppCompatActivity {

    ImageView image;
    Uri uri;
    DataBase db;
    Book book;
    int nameNormall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
        image=findViewById(R.id.UPimageIv);
        FloatingActionButton fab=findViewById(R.id.UPbuttonImage);
        TextView name =findViewById(R.id.UPbookNameEt);
        TextView author=findViewById(R.id.UPauthorEt);
        TextView release=findViewById(R.id.UPreleaseEt);
        TextView pages=findViewById(R.id.UPpagesEt);
        TextView category=findViewById(R.id.UPcategoryEt);
        Button update=findViewById(R.id.UPbuttonCreate);



        db=new DataBase(this);
        Intent i=getIntent();
        book=(Book) i.getSerializableExtra("book");

        image.setImageURI(Uri.parse(book.getImage()));
        name.setText(book.getName() );
        author.setText(book.getAuthor());
        release.setText(book.getRelease());
        pages.setText(book.getPages());
        category.setText(book.getCategory()+"");
        nameNormall=book.getId();

        uri=Uri.parse(book.getImage());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String img = String.valueOf(uri);
                int cat=Integer.parseInt(category.getText().toString());
                String nameBook = name.getText().toString();
                String nameAuthor = author.getText().toString();
                String rel = release.getText().toString();
                String pag = pages.getText().toString();
                if (nameBook ==null) {
                    Toast.makeText(getBaseContext(), "Invalid Book Name", Toast.LENGTH_LONG).show();
                } else if (nameAuthor ==null) {
                    Toast.makeText(getBaseContext(), "Invalid Author Name", Toast.LENGTH_LONG).show();
                } else if (rel==null) {
                    Toast.makeText(getBaseContext(), "Invalid Release Year", Toast.LENGTH_LONG).show();
                } else if (pag==null) {
                    Toast.makeText(getBaseContext(), "Invalid Pages Number", Toast.LENGTH_LONG).show();
                } else if (uri==null){
                    Toast.makeText(getBaseContext(), "Invalid Image,Please select valid image", Toast.LENGTH_LONG).show();
                }else {
                    db.updateBook(nameNormall,img,nameBook,nameAuthor,rel,pag,cat);
                    Intent intent=new Intent(getBaseContext(), SelectBook.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1 && resultCode==RESULT_OK){

            uri=data.getData();
            image.setImageURI(uri);
        }
    }
}