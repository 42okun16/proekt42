package com.example.proekt42;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageButton DOP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        DOP = findViewById(R.id.DOP);
        DOP.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.photo_5402429157805256739_w), 750, 800, true));
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
        };
    public void onClick(View v) {
        Intent i;
        if (v.getId() == R.id.imageButton) {
            i = new Intent(MainActivity.this, ekran2.class);
            startActivity(i);
        }
    }
}