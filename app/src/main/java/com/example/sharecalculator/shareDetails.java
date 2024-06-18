package com.example.sharecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class shareDetails extends AppCompatActivity {

    TextView name,quantity,Bp,pay,per;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_details);

        name=(TextView) findViewById(R.id.name);
        quantity=findViewById(R.id.quantity);
        Bp=findViewById(R.id.Bp);
        pay=findViewById(R.id.pay);
        per=findViewById(R.id.per);

        String Name=getIntent().getStringExtra("Name");
        String Quantity=getIntent().getStringExtra("Quantity");
        String BP=getIntent().getStringExtra("BP");
        String Total=getIntent().getStringExtra("Total");
        String Per=getIntent().getStringExtra("Per");

        name.setText(Name);
        quantity.setText(Quantity);
        Bp.setText(BP);
        pay.setText(Total);
        per.setText(Per);

    }
}