package com.geektech.a1homework4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    EditText editText1;
    EditText editText2;
    ContactModel contactModel;
    private static final int GALLERY_REQUEST_CODE = 12;
    private static String  uriImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        imageView = findViewById(R.id.edit_photo);
        editText1 = findViewById(R.id.edit_name);
        editText2 = findViewById(R.id.edit_number);


        Intent intent = new Intent();
        intent = getIntent();
        ContactModel contactModel = (ContactModel) intent.getSerializableExtra("hgh");
        if (contactModel != null ){
            editText1.setText(contactModel.getName());
            editText2.setText(contactModel.getPhone());

        }
    }



    public void openGallery(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            uriImage = String.valueOf(data.getData());
        }
    }


    public void openMainActivity(View view) {
        Intent intent = new Intent();
        ContactModel contactModel = new ContactModel();
        String str1 = editText1.getText().toString();
        String str2 = editText2.getText().toString();

        contactModel.setImage(uriImage);
        contactModel.setName(str1);
        contactModel.setPhone(str2);
        intent.putExtra("hgh",contactModel);
        setResult(RESULT_OK,intent);
        finish();
    }


}