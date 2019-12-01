package com.example.banddbapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private Menu mMenu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.scroll:
            Intent intent = new Intent(ProfileActivity.this, ListActivity.class);
            startActivity(intent);
            return(true);
        case R.id.swipe:
            startActivity(new Intent(ProfileActivity.this, SwipeActivity.class));
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }
}
