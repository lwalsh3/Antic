package com.example.banddbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SwipeActivity extends AppCompatActivity {

    private int currentPost;
    private int imageId;

    private String currentImageRef;

    private Button dislikeButton;
    private Button likeButton;
    private Button saveImageButton;

    private Menu mMenu;
    private ArrayList<Band> posts;

    private TextView nameTextView;
    private ImageView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        dislikeButton = findViewById(R.id.buttonDislike);
        likeButton = findViewById(R.id.buttonLike);
        saveImageButton = findViewById(R.id.buttonSaveImage);

        posts = new ArrayList<>();
        Resources res = getApplicationContext().getResources();
        String[] bands = res.getStringArray(R.array.bands);
        String[] descriptions = res.getStringArray(R.array.descriptions);
        for (int i = 0; i < bands.length; i++) {
            posts.add(new Band(i + 1, bands[i], descriptions[i]));
        }

        currentPost = 0;
        nameTextView = (TextView) this.findViewById(R.id.postName);
        descriptionTextView = (ImageView) this.findViewById(R.id.imageDisplay);

        currentImageRef = posts.get(currentPost).getDescription();
        imageId = this.getResources().getIdentifier(currentImageRef, "drawable", "com.example.banddbapp");
        nameTextView.setText(posts.get(currentPost).getName());
        descriptionTextView.setImageResource(imageId);

        dislikeButton.setOnClickListener((new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentPost++;
                if (currentPost >= posts.size())
                {
                    currentPost = 0;
                }
                currentImageRef = posts.get(currentPost).getDescription();
                imageId = getResources().getIdentifier(currentImageRef, "drawable", "com.example.banddbapp");
                nameTextView.setText(posts.get(currentPost).getName());
                descriptionTextView.setImageResource(imageId);
            }
        }));

        likeButton.setOnClickListener((new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentPost++;
                if (currentPost >= posts.size())
                {
                    currentPost = 0;
                }
                currentImageRef = posts.get(currentPost).getDescription();
                imageId = getResources().getIdentifier(currentImageRef, "drawable", "com.example.banddbapp");
                nameTextView.setText(posts.get(currentPost).getName());
                descriptionTextView.setImageResource(imageId);
            }
        }));

        saveImageButton.setOnClickListener((new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO save Image from current post to local storage of device
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.scroll:
            Intent intent = new Intent(SwipeActivity.this, ListActivity.class);
            startActivity(intent);
            return(true);
        case R.id.profile:
            startActivity(new Intent(SwipeActivity.this, ProfileActivity.class));
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }
}
