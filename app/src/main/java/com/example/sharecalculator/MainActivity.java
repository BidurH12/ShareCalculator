package com.example.sharecalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tab;
    Toolbar tool;

    ViewPager view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab=findViewById(R.id.tab);
        view=findViewById(R.id.view);
        tool=findViewById(R.id.tool);
        setSupportActionBar(tool);

        Adapter ad=new Adapter(getSupportFragmentManager());
        view.setAdapter(ad);
        tab.setupWithViewPager(view);


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Exit Application");
        alert.setMessage("Do you sure want to exit?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }
}