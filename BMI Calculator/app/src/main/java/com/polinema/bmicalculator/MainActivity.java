package com.polinema.bmicalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    //component
    EditText editTextTinggi,editTextBerat,editTextUmur,editTextGoal;
    Button buttonHitung;
    RadioGroup radioGroupGender;
    RadioButton radioButtonGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTinggi = findViewById(R.id.et_height);
        editTextBerat = findViewById(R.id.et_weight);
        editTextGoal = findViewById(R.id.et_goal);
        editTextUmur = findViewById(R.id.et_age);

        radioGroupGender = findViewById(R.id.rg_gender);
        Integer pilihGender = radioGroupGender.getCheckedRadioButtonId();
        radioButtonGender = findViewById(pilihGender);

        buttonHitung = findViewById(R.id.btn_calculate);
        buttonHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMI(view);
            }
        });
    }

    public void calculateBMI(View view) {
        String tinggiStr = editTextTinggi.getText().toString();
        String bobotStr = editTextBerat.getText().toString();

        if (tinggiStr != null && !"".equals(tinggiStr)
                && bobotStr != null  &&  !"".equals(bobotStr)){

            float tinggiValue = Float.parseFloat(tinggiStr) / 100;
            float bobotValue = Float.parseFloat(bobotStr);

            float bmi = bobotValue / (tinggiValue * tinggiValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";
        String infoUmur = editTextUmur.getText().toString();

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.terlalu_sangat_kurus);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.sangat_kurus);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.kurus);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.gemuk);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.cukup_gemuk);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.sangat_gemuk);
        } else {
            bmiLabel = getString(R.string.terlalu_sangat_gemuk);
        }


        bmiLabel = "Hasil Penghitungan BMI : " + bmi + "\n\n" + " Kategori: " + "(" + bmiLabel + ")" + "\n\n" + "Umur : " + infoUmur;

        AlertDialog.Builder tampilBMI = new AlertDialog.Builder(this);

        tampilBMI.setTitle("Hasil Penghitunan BMI");

        tampilBMI.setMessage(bmiLabel).setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });

        AlertDialog alertDialog = tampilBMI.create();
        alertDialog.show();
    }
}
