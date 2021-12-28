package com.example.AboodFinalprojectbooklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddBook extends AppCompatActivity {

    ImageView image;
    FloatingActionButton fab;
    Spinner spinner;
    EditText name,author,release,pages;
    Button create;

    Uri uri;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);
        setTitle("Create Book");

        image=findViewById(R.id.imageIv);
        fab=findViewById(R.id.buttonImage);
        spinner=findViewById(R.id.spinner);
        name=findViewById(R.id.bookNameEt);
        author=findViewById(R.id.authorEt);
        release=findViewById(R.id.releaseEt);
        pages=findViewById(R.id.pagesEt);
        create=findViewById(R.id.buttonCreate);

        db=new DataBase(this);
        ArrayList<Category> data=db.ShowAllCategory();

        AdapterAddBook a=new AdapterAddBook(this,R.layout.item_spinner,data);
        spinner.setAdapter(a);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent=new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,1);

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String image = String.valueOf(uri);
                int sp = spinner.getSelectedItemPosition()+1;
                String nameBook = name.getText().toString();
                String nameAuthor = author.getText().toString();
                String rel = release.getText().toString();
                String pag = pages.getText().toString();
                Book book = new Book(image, nameBook, nameAuthor, rel, pag, sp);
                if (nameBook==null) {
                    Toast.makeText(getBaseContext(), " Book Name is Empty", Toast.LENGTH_LONG).show();
                } else if (nameAuthor.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid Author Name is Empty", Toast.LENGTH_LONG).show();
                } else if (rel==null) {
                    Toast.makeText(getBaseContext(), " Release Year is Empty", Toast.LENGTH_LONG).show();
                } else if (pag==null) {
                    Toast.makeText(getBaseContext(), " Number is Empty", Toast.LENGTH_LONG).show();
                } else if (uri==null){
                    Toast.makeText(getBaseContext(), "No select  image", Toast.LENGTH_LONG).show();
                }else {
                    db.insertBook(book);
                    Intent intent=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
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

class AdapterAddBook extends BaseAdapter {

    ArrayList<Category> categoryes;
    int res;
    AddBook aBook;

    public AdapterAddBook(AddBook aBook, int res, ArrayList<Category> categoryes) {
        this.categoryes = categoryes;
        this.res = res;
        this.aBook = aBook;
    }

    @Override
    public int getCount() {
        return categoryes.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;
        if (v==null){
            v= LayoutInflater.from(aBook).inflate(R.layout.item_spinner,null,false);
        }
        TextView tv_name=v.findViewById(R.id.spinnerName);
        Category category=categoryes.get(i);
        tv_name.setText(category.getName());
        return v;
    }
}
