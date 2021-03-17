package com.geektech.a1homework4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.ItemClickListener{
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    List<ContactModel> list;
    int positionItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerVw);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mainAdapter  = new MainAdapter((ArrayList<ContactModel>) list,MainActivity.this);
        recyclerView.setAdapter(mainAdapter);

        mainAdapter.setOnClickListener(this);
    }

    public void openActivityTwo(View view) {
        Intent intent = new Intent(MainActivity.this ,MainActivity2.class  );
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null){
            ContactModel contactModel = (ContactModel) data.getSerializableExtra("hgh");

                list.add(contactModel);
                mainAdapter.notifyDataSetChanged();

        }
        if (requestCode == 2 && resultCode== RESULT_OK){
            ContactModel contactModel = (ContactModel) data.getSerializableExtra("hgh");
            list.remove(positionItem);
            mainAdapter.notifyItemRemoved(positionItem);
            mainAdapter.notifyItemRangeChanged(positionItem,mainAdapter.getItemCount());
            list.add(positionItem,contactModel);
            mainAdapter.notifyItemInserted(positionItem);
        }
    }


    @Override
    public void onItemClick(int position) {
        positionItem = position;
        Intent intent = new Intent(MainActivity.this , MainActivity2.class);
        intent.putExtra("hgh",list.get(position));
        startActivityForResult(intent,2);
    }
}