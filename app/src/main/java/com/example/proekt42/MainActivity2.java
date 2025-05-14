package com.example.proekt42;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView txt_diagnoz, txt_analiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt_analiz = findViewById(R.id.analiz);
        txt_diagnoz = findViewById(R.id.diagnoz);
        Intent i = getIntent();
        if (i != null){
            String result = i.getStringExtra("result");
            String analiz, diagnoz;
            diagnoz = result.split("\n")[0];
            analiz = result.split("\n")[1];
            txt_analiz.setText(analiz);
            txt_diagnoz.setText(diagnoz);
        }
    }
}