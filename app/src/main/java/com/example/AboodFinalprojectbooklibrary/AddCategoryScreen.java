package com.example.AboodFinalprojectbooklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCategoryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        EditText name;
        Button creatCategory;
        DataBase db;

        setTitle("Create Category");
        name=findViewById(R.id.categoryEt);
        creatCategory=findViewById(R.id.categoryButton);

        db=new DataBase(this);

        creatCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameCategory= name.getText().toString();
                if (nameCategory.isEmpty()){
                    Toast.makeText(getBaseContext()," created Category Faild",Toast.LENGTH_SHORT).show();

                }else if (nameCategory !=null){
                    Category category=new Category();
                    category.setName(nameCategory);
                    db.insertCategory(category);
                    Toast.makeText(getBaseContext()," created Category Done",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);
                }


            }
        });
    }
}