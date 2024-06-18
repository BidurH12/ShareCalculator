package com.example.sharecalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class BuyFragment extends Fragment {
    View view=null;
    EditText price,no;
    TextView Amt,sebon,broker,pay,per;
    Button calculate;


    public BuyFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy, container, false);


    }

    @Override
    public void onStart() {
        super.onStart();
        View view=getView();
        price=view.findViewById(R.id.price);
        no=view.findViewById(R.id.no);
        Amt=view.findViewById(R.id.Amt);
        sebon=view.findViewById(R.id.sebon);
        broker=view.findViewById(R.id.broker);
        calculate=view.findViewById(R.id.calculate);
        pay=view.findViewById(R.id.pay);
        per=view.findViewById(R.id.per);

            calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   try {
                       double Price = Double.parseDouble(price.getText().toString());
                       int No = Integer.parseInt(no.getText().toString());
                       double Amount = Price * No;
                       double A = Math.round(Amount * 100) / 100.0;
                       double Sebon = Amount * 0.00015;
                       double S = Math.round(Sebon * 100) / 100.0;


                       double Broker;
                       if (Amount < 50000) {
                           Broker = Amount * 0.004;
                       } else if (Amount > 50000 && Amount < 500000) {
                           Broker = Amount * 0.0037;
                       } else if (Amount > 500000 && Amount < 2000000) {
                           Broker = Amount * 0.0034;
                       } else if (Amount > 2000000 && Amount < 5000000) {
                           Broker = Amount * 0.0030;
                       } else {
                           Broker = Amount * 0.0027;
                       }
                       double B = Math.round(Broker * 100) / 100.0;


                       double Pay = Amount + Sebon + Broker + 25;
                       double P = Math.round(Pay * 100) / 100.0;
                       double Per = Pay / No;
                       double Pe = Math.round(Per * 100) / 100.0;


                       String Total = String.valueOf(A);
                       String Seb = String.valueOf(S);
                       String broke = String.valueOf(B);
                       String PerT = String.valueOf(Pe);
                       String Tot = String.valueOf(P);

                       //Printing Started

                       Amt.setText("Rs " + Total);
                       sebon.setText("Rs " + Seb);
                       broker.setText("Rs " + broke);
                       pay.setText("Rs " + Tot);
                       per.setText("Rs " + PerT);
                   } catch (NumberFormatException e) {
                       Toast.makeText(getContext(), "Required Field is empty", Toast.LENGTH_LONG).show();;
                   }

                }
            });



    }
}