package com.example.proekt42;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ekran2 extends AppCompatActivity {
    final int VERY_LOW = 1, LOW = 2, NORMAL = 3, HIGH = 4, VERY_HIGH = 5, UNKNOWN = 6;
    ImageButton btn_result;
    EditText editHemo;
    EditText editEretr;
    EditText editColor;
    EditText editDiametr;
    EditText editCrobem;
    EditText editCrhemogveret;
    EditText editCkonhemogveret;
    EditText editRetiko;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran2);
        btn_result = findViewById(R.id.btn_result);
        btn_result.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.photo_5433932304834752198_w), 750, 400, true));
        editHemo = findViewById(R.id.editHemo);
        editEretr = findViewById(R.id.editEretr);
        editColor = findViewById(R.id.editColor);
        editDiametr = findViewById(R.id.editDiametr);
        editCrobem = findViewById(R.id.editCrobem);
        editCrhemogveret = findViewById(R.id.editCrhemogveret);
        editCkonhemogveret = findViewById(R.id.editCkonhemogveret);
        editRetiko = findViewById(R.id.editRetiko);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    public void onClick(View v) {
        Intent i;
        if (v.getId() == R.id.btn_result) {
            double hemo, eretr, col, diamM, diamS, diamB, diam, crobem, crhemo, ckoncentr, retiko;
            String []diameters;
            try {
            diameters = editDiametr.getText().toString().split(", ");
            diamM = Double.parseDouble(diameters[0]);
            diamS = Double.parseDouble(diameters[1]);
            diamB = Double.parseDouble(diameters[2]);
            hemo = Double.parseDouble(editHemo.getText().toString());
            eretr = Double.parseDouble(editEretr.getText().toString());
            col = Double.parseDouble(editColor.getText().toString());
            crobem = Double.parseDouble( editCrobem.getText().toString());
            crhemo = Double.parseDouble( editCrhemogveret.getText().toString());
            ckoncentr = Double.parseDouble( editCkonhemogveret.getText().toString());
            retiko = Double.parseDouble( editRetiko.getText().toString());
            String result = full_analysis(hemo, eretr, col, crobem, crhemo, ckoncentr, retiko, diamM, diamS, diamB);
            if (result.equals("UNKNOWN\nUNKNOWN")){
                Toast.makeText(this, "АНОМАЛЬНОЕ СОЧЕТАНЕ АНАЛИЗОВ", Toast.LENGTH_LONG).show();
                return;
            }
            i = new Intent(ekran2.this, MainActivity2.class);
            i.putExtra("result", result);
            startActivity(i);
            } catch(Exception ex1) {
                Toast.makeText(this, "ЧТО-ТО ВВЕДЕНО НЕ ТАК", Toast.LENGTH_LONG).show();
            }
            try {
                hemo = Double.parseDouble(editHemo.getText().toString());
            } catch(Exception ex2) {
                Toast.makeText(this, "ГЕМОГЛОБИН ВВЕДЕН НЕ ТАК", Toast.LENGTH_SHORT).show();
            }
            try {
                eretr = Double.parseDouble(editEretr.getText().toString());
            } catch(Exception ex2) {
                Toast.makeText(this, "ЭРИТРОЦИТЫ ВВЕДЕНЫ НЕ ТАК", Toast.LENGTH_SHORT).show();
            }
            try {
                col = Double.parseDouble(editColor.getText().toString());
            } catch(Exception ex2) {
                Toast.makeText(this, "ЦВЕТОЙ ПОКАЗАТЕЛЬ ВВЕДЕН НЕ ТАК", Toast.LENGTH_SHORT).show();
            }
            try {
                crobem = Double.parseDouble( editCrobem.getText().toString());
            } catch(Exception ex2) {
                Toast.makeText(this, "СРЕДНИЙ ОБЪЕМ ЭРИТРОЦИТОВ ВВЕДЕН НЕ ТАК", Toast.LENGTH_SHORT).show();
            }
            try {
                crhemo = Double.parseDouble( editCrhemogveret.getText().toString());
            } catch(Exception ex2) {
                Toast.makeText(this, "СРЕД.КОНЦЕНТРАЦ.ГЕМОГЛ В ЭРИТРОЦ ВВЕДЕНА НЕ ТАК", Toast.LENGTH_SHORT).show();
            }
            try {
                ckoncentr = Double.parseDouble( editCkonhemogveret.getText().toString());
            } catch(Exception ex2) {
                Toast.makeText(this, "СРЕД.КОНЦЕНТРАЦ.ГЕМОГЛ В ЭРИТРОЦ ВВЕДЕНА НЕ ТАК", Toast.LENGTH_SHORT).show();
            }
            try {
                retiko = Double.parseDouble( editRetiko.getText().toString());
            } catch(Exception ex2) {
                Toast.makeText(this, "РЕТИКУЛОЦИТЫ ВВЕДЕНЫ НЕ ТАК", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public int calculate_hemoglobin(double hemoglobin) {
        if (hemoglobin <= 70) return VERY_LOW;
        if (hemoglobin <= 110) return LOW;
        if (hemoglobin <= 160) return NORMAL;
        if (hemoglobin >= 160) return HIGH;
        return UNKNOWN;
    }

    public int calculate_eretrow(double eretrow) {
        if (eretrow <= 2.0) return VERY_LOW;
        if (eretrow <= 3.5) return LOW;
        if (eretrow <= 5.5) return NORMAL;
        if (eretrow <= 6.5) return HIGH;
        if (eretrow <= 7.5) return VERY_HIGH;
        return UNKNOWN;
    }

    public int calculate_color(double color) {
        if (color <= 0.7) return VERY_LOW;
        if (color <= 0.9) return LOW;
        if (color <= 1.05) return NORMAL;
        if (color >= 1.10) return HIGH;
        if (color <= 1.25) return VERY_HIGH;
        return UNKNOWN;
    }
    public int calculate_diametrm(double diametrm) {
        if (diametrm <= 11.0) return LOW;
        if (diametrm <= 12.5) return NORMAL;
        else return HIGH;
    }

    public int calculate_diametrc(double diametrc) {
        if (diametrc <= 74.5) return LOW;
        if (diametrc <= 75.0) return NORMAL;
        else return HIGH;
    }

    public int calculate_diametrb(double diametrb) {
        if (diametrb <= 11.0) return LOW;
        if (diametrb <= 12.5) return NORMAL;
        else return HIGH;
    }
    public int calculate_all_diameters(double small, double medium, double high){
        int sm = calculate_diametrm(small);
        int med = calculate_diametrc(medium);
        int hi = calculate_diametrb(high);
        if (sm == NORMAL && med == NORMAL && hi == NORMAL) return NORMAL;
        if (sm == HIGH && med == LOW && hi == LOW) return LOW;
        if (sm == LOW && med == HIGH && hi == LOW) return LOW;
        if (sm == LOW && med == LOW && hi == HIGH) return LOW;
        if (sm == HIGH && med == LOW && hi == HIGH) return HIGH;
        if (sm == HIGH && med == HIGH && hi == LOW) return HIGH;
        if (sm == LOW && med == HIGH && hi == HIGH) return HIGH;
        if (sm == LOW && med == NORMAL && hi == HIGH) return HIGH;
        return UNKNOWN;
    }
    public int calculate_crobem(double crobem) {
        if (crobem <= 55) return VERY_LOW;
        if (crobem <= 76) return LOW;
        if (crobem <= 96) return NORMAL;
        if (crobem <= 100) return HIGH;
        if (crobem <= 110) return VERY_HIGH;
        return UNKNOWN;
    }

    public int calculate_crhemogveret(double crhemogveret) {
        if (crhemogveret <= 19) return VERY_LOW;
        if (crhemogveret <= 30) return LOW;
        if (crhemogveret <= 34) return NORMAL;
        if (crhemogveret <= 40) return HIGH;
        if (crhemogveret >= 42) return VERY_HIGH;
        return UNKNOWN;
    }

    public int calculate_crkonhemogveret(double crkonhemogveret) {
        if (crkonhemogveret <= 22) return VERY_LOW;
        if (crkonhemogveret <= 30) return LOW;
        if (crkonhemogveret <= 38) return NORMAL;
        if (crkonhemogveret <= 46) return HIGH;
        if (crkonhemogveret <= 54) return VERY_HIGH;
        return UNKNOWN;
    }

    public int calculate_retikylo(double retikylo) {
        if (retikylo <= 0.5) return VERY_LOW;
        if (retikylo <= 2) return LOW;
        if (retikylo <= 12) return NORMAL;
        if (retikylo <= 20) return HIGH;
        if (retikylo <= 34) return VERY_HIGH;
        return UNKNOWN;
    }

    public String full_analysis(double hemoglobin, double eretrow, double color, double crobem, double crhemogveret, double crkonhemogveret, double retikylo, double small, double medium, double high) {
            int hemoglobin_level = calculate_hemoglobin(hemoglobin);
            int eretrow_level = calculate_eretrow(eretrow);
            int color_level = calculate_color(color);
            int diametr_level = calculate_all_diameters(small, medium, high);
            int crobem_level = calculate_crobem(crobem);
            int crhemogveret_level = calculate_crhemogveret(crhemogveret);
            int crkonhemogveret_level = calculate_crkonhemogveret(crkonhemogveret);
            int retikylo_level = calculate_retikylo(retikylo);
            if (hemoglobin_level == VERY_LOW && eretrow_level == VERY_LOW && color_level == HIGH && diametr_level == HIGH && crobem_level == VERY_HIGH && crhemogveret_level == VERY_HIGH && crkonhemogveret_level == LOW && retikylo_level == LOW)
                return "В12-дефицитная анемия \n Миелограмма,Желудочная секреция,Кал на яйца гельминтов,гастроскопия";
        if (hemoglobin_level == VERY_LOW && eretrow_level == VERY_LOW && color_level == HIGH && diametr_level == HIGH && crobem_level == VERY_HIGH && crhemogveret_level == VERY_HIGH && crkonhemogveret_level == HIGH&& retikylo_level == LOW)
            return "В12-дефицитная анемия \n Миелограмма,Желудочная секреция,Кал на яйца гельминтов,гастроскопия";
        if (hemoglobin_level == LOW && eretrow_level == NORMAL && color_level == LOW && diametr_level == LOW && crobem_level == NORMAL && crhemogveret_level == LOW && crkonhemogveret_level == LOW && retikylo_level == NORMAL)
                return "Железодефицитная анемия \n Сывороточное железо,ОЖСС,%Насыщения трансферрина железом";
        if (hemoglobin_level == LOW && eretrow_level == LOW && color_level == LOW && diametr_level == LOW && crobem_level == LOW && crhemogveret_level == LOW && crkonhemogveret_level == LOW && retikylo_level == NORMAL)
            return "Железодефицитная анемия \n Сывороточное железо,ОЖСС,%Насыщения трансферрина железом";
        if (hemoglobin_level == LOW && eretrow_level == LOW && color_level == NORMAL && diametr_level == NORMAL && crobem_level == NORMAL && crhemogveret_level == NORMAL && crkonhemogveret_level == NORMAL && retikylo_level == VERY_HIGH)
                return "Гемолитическая анемия \n Билирубин сыворотки,Проба Кумбса,Продолжительность жизни эритроцитов,Аномальные гемоглобины,Ферменты эритроцитов";
        if (hemoglobin_level == LOW && eretrow_level == LOW && color_level == NORMAL && diametr_level == HIGH && crobem_level == HIGH && crhemogveret_level == HIGH && crkonhemogveret_level == LOW && retikylo_level == VERY_LOW)
                return "Гипопластическая анемия \n Лейкоцитарнал формула,Тромбоциты,Миелограмма";
        if (hemoglobin_level == LOW && eretrow_level == LOW && color_level == NORMAL && diametr_level == NORMAL && crobem_level == NORMAL && crhemogveret_level == NORMAL && crkonhemogveret_level == NORMAL && retikylo_level == VERY_LOW)
            return "Гипопластическая анемия \n Лейкоцитарнал формула,Тромбоциты,Миелограмма";
        if (hemoglobin_level == VERY_LOW && eretrow_level == VERY_LOW && color_level == NORMAL && diametr_level == HIGH && crobem_level == HIGH && crhemogveret_level == HIGH && crkonhemogveret_level == LOW && retikylo_level == VERY_LOW)
            return "Гипопластическая анемия \n Лейкоцитарнал формула,Тромбоциты,Миелограмма";
        if (hemoglobin_level == VERY_LOW && eretrow_level == VERY_LOW && color_level == NORMAL && diametr_level == NORMAL && crobem_level == NORMAL && crhemogveret_level == NORMAL && crkonhemogveret_level == NORMAL && retikylo_level == VERY_LOW)
            return "Гипопластическая анемия \n Лейкоцитарнал формула,Тромбоциты,Миелограмма";
        if (hemoglobin_level == LOW && eretrow_level == LOW && color_level == NORMAL && diametr_level == LOW && crobem_level == HIGH && crhemogveret_level == HIGH && crkonhemogveret_level == NORMAL && retikylo_level == VERY_HIGH)
                return "Гемолитическая анемия \n Билирубин сыворотки,Проба Кумбса,Продолжительность жизни эритроцитов,Аномальные гемоглобины,Ферменты эритроцитов";
        if (hemoglobin_level == VERY_LOW && eretrow_level == VERY_LOW && color_level == NORMAL && diametr_level == LOW && crobem_level == HIGH && crhemogveret_level == HIGH && crkonhemogveret_level == NORMAL && retikylo_level == VERY_HIGH)
            return "Гемолитическая анемия \n Билирубин сыворотки,Проба Кумбса,Продолжительность жизни эритроцитов,Аномальные гемоглобины,Ферменты эритроцитов";
        if (hemoglobin_level == VERY_LOW && eretrow_level == VERY_LOW && color_level == NORMAL && diametr_level == NORMAL && crobem_level == NORMAL && crhemogveret_level == NORMAL && crkonhemogveret_level == NORMAL && retikylo_level == VERY_HIGH)
            return "Гемолитическая анемия \n Билирубин сыворотки,Проба Кумбса,Продолжительность жизни эритроцитов,Аномальные гемоглобины,Ферменты эритроцитов";
         return "UNKNOWN\nUNKNOWN";
    }
/*
    public String full_analysis(double hemoglobin, double eretrow, double color, double diametrm, double diametrc, double diametrb, double crobem, double crhemogveret, double crkonhemogveret, double retikylo) {
        int hemoglobin_level = calculate_hemoglobin(hemoglobin);
        int eretrow_level = calculate_eretrow(eretrow);
        int color_level = calculate_color(color);
        int diametr_level = calculate_all_diameters(double small, double medium, double high);
        int crobem_level = calculate_crobem(crobem);
        int crhemogveret_level = calculate_crhemogveret(crhemogveret);
        int crkonhemogveret_level = calculate_crkonhemogveret(crkonhemogveret);
        int retikylo_level = calculate_retikylo(retikylo);
    }

    public String full_analysis(double hemoglobin, double eretrow, double color, double diametrm, double diametrc, double diametrb, double crobem, double crhemogveret, double crkonhemogveret, double retikylo) {
        int hemoglobin_level = calculate_hemoglobin(hemoglobin);
        int eretrow_level = calculate_eretrow(eretrow);
        int color_level = calculate_color(color);
        int diametr_level = calculate_all_diameters(double small, double medium, double high);
        int crobem_level = calculate_crobem(crobem);
        int crhemogveret_level = calculate_crhemogveret(crhemogveret);
        int crkonhemogveret_level = calculate_crkonhemogveret(crkonhemogveret);
        int retikylo_level = calculate_retikylo(retikylo);
    }
    public String full_analysis(double hemoglobin, double eretrow, double color, double diametrm, double diametrc, double diametrb, double crobem, double crhemogveret, double crkonhemogveret, double retikylo) {
        int hemoglobin_level = calculate_hemoglobin(hemoglobin);
        int eretrow_level = calculate_eretrow(eretrow);
        int color_level = calculate_color(color);
        int diametr_level = calculate_all_diameters(double small, double medium, double high);
        int crobem_level = calculate_crobem(crobem);
        int crhemogveret_level = calculate_crhemogveret(crhemogveret);
        int crkonhemogveret_level = calculate_crkonhemogveret(crkonhemogveret);
        int retikylo_level = calculate_retikylo(retikylo);
    }
    public String full_analysis(double hemoglobin, double eretrow, double color, double diametrm, double diametrc, double diametrb, double crobem, double crhemogveret, double crkonhemogveret, double retikylo) {
        int hemoglobin_level = calculate_hemoglobin(hemoglobin);
        int eretrow_level = calculate_eretrow(eretrow);
        int color_level = calculate_color(color);
        int diametr_level = calculate_all_diameters(double small, double medium, double high);
        int crobem_level = calculate_crobem(crobem);
        int crhemogveret_level = calculate_crhemogveret(crhemogveret);
        int crkonhemogveret_level = calculate_crkonhemogveret(crkonhemogveret);
        int retikylo_level = calculate_retikylo(retikylo);
    }*/
}