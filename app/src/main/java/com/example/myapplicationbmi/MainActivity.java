package com.example.myapplicationbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private TextView showbmi;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        height = findViewById(R.id.edHeight);
        weight = findViewById(R.id.edWeight);
        showbmi = findViewById(R.id.tvShowBMI);
        imageView = findViewById(R.id.ivShow);
    }

    public void calBMI(View view) {
        double bmi = Double.parseDouble(bmi_value());
        String txt = "";
            if(bmi < 18.5){
                txt = "體重過輕";
                imageView.setImageResource(R.drawable.light);
            }else if(bmi > 25){
                txt = "體重過重";
                imageView.setImageResource(R.drawable.weight);
            }else{
                txt = "體重正常";
                imageView.setImageResource(R.drawable.normal);
            }
            //showbmi.setText(String.valueOf(bmi) + txt);
            showbmi.setText(String.valueOf(bmi) + txt);


    }

    private String bmi_value() {
        if(height.getText().toString().isEmpty() || weight.getText().toString().isEmpty()){
            showbmi.setText("請輸入身高或體重的數值");
            return "0.0";
        }else {
            double h = Double.parseDouble(height.getText().toString());
            double w = Double.parseDouble(weight.getText().toString());
            double bmi = w / ((h / 100.0) * (h / 100.0));
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            return decimalFormat.format(bmi);
        }
    }

    public void showToast(View view) {
        String bmi = bmi_value();
        Toast toast = Toast.makeText(this, bmi, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.LEFT,0,0);
        toast.show();
    }

    public void show_alertdialog(View view) {
//        String[] a = {"Red","Green","Blue"};
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("你的BMI")
//                .setMessage(bmi_value())
//                .setItems(a,null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "ABC", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("abc",null)
                .setIcon(R.drawable.ic_launcher_foreground)
                .show();
    }

    public void showResult(View view) {
        Intent intent = new Intent(this,ResultActivity2.class);
//        intent.putExtra("BMI",bmi_value());
//        intent.putExtra("height",180);
        Bundle bundle = new Bundle();
        bundle.putString("BMI",bmi_value());
        bundle.putInt("height",180);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void showList(View view) {
//        Intent intent = new Intent(this, ListActivity.class);
        Intent intent = new Intent(this, BMIActiviy.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("height",height.getText().toString());
//        bundle.putString("weight",weight.getText().toString());
//        intent.putExtras(bundle);
        startActivity(intent);
    }
}