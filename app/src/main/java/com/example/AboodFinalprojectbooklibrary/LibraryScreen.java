package com.example.AboodFinalprojectbooklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LibraryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        RecyclerView recyclerView=findViewById(R.id.liblraryRv);

        setTitle("Library");
        DataBase db=new DataBase(this);

        ArrayList<Category> categoryes=db.ShowAllCategory();

        AdapterLibrary adapter=new AdapterLibrary(this,categoryes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
class AdapterLibrary extends RecyclerView.Adapter<AdapterLibrary.AdapterLibraryHolder> {

    ArrayList<Category> categoryes;
    LibraryScreen aLibrary;


    public AdapterLibrary(LibraryScreen aLibrary, ArrayList<Category> categoryes) {
        this.aLibrary=aLibrary;
        this.categoryes = categoryes;
    }

    @NonNull
    @Override
    public AdapterLibraryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(aLibrary).inflate(R.layout.item_category,parent,false);
        return new AdapterLibraryHolder(view);
    }

    @Override
    public void onBindViewHolder( AdapterLibrary.AdapterLibraryHolder holder, int position) {
        Category category=categoryes.get(position);
        holder.name.setText(category.getName()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(aLibrary, SelectBook.class);
                intent.putExtra("id",category.getId());
                intent.putExtra("name",category.getName());
                aLibrary.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryes.size();
    }

    public class AdapterLibraryHolder extends RecyclerView.ViewHolder{

        TextView name;

        public AdapterLibraryHolder(View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.libraryName);
        }
    }
}