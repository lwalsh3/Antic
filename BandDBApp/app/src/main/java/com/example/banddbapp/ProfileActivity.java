package com.example.banddbapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class ProfileActivity extends AppCompatActivity {

    private final int SELECT_PHOTO = 1;

    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        File file = new File(getApplicationContext().getFilesDir(), "antic.json");
        if (file.exists()) {
            try {
                JSONObject json = new JSONArray(file).getJSONObject(0);
                EditText txtName = findViewById(R.id.txtName);
                txtName.setText(json.getString("name"));
                EditText txtBio = findViewById(R.id.txtBio);
                txtBio.setText(json.getString("bio"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ImageButton btnPhoto = findViewById(R.id.btnPhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK), SELECT_PHOTO);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scroll:
                Intent intent = new Intent(ProfileActivity.this, ListActivity.class);
                startActivity(intent);
                return (true);
            case R.id.swipe:
                startActivity(new Intent(ProfileActivity.this, SwipeActivity.class));
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onPause() {
        super.onPause();

        EditText txtName = findViewById(R.id.txtName);
        EditText txtBio = findViewById(R.id.txtBio);

        File path = getApplicationContext().getFilesDir();
        File file = new File(path, "antic.json");
        JSONObject json = new JSONObject();

        try {
            json.put("name", txtName.getText().toString());
            json.put("bio", txtBio.getText().toString());

            Writer stream = new BufferedWriter(new FileWriter(file));
            stream.write(json.toString());
            stream.close();
            Toast.makeText(getApplicationContext(), "Profile saved.", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.d("EX", "onPause: " + e);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (requestCode == SELECT_PHOTO) {
            if (resultCode == RESULT_OK) {
                try {
                    ImageButton btnPhoto = findViewById(R.id.btnPhoto);
                    btnPhoto.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(imageReturnedIntent.getData())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}