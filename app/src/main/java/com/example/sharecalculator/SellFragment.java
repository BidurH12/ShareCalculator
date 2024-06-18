package com.example.sharecalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SellFragment extends Fragment {
    EditText pp,sp,no;
    RadioGroup radio;
    RadioButton r1,r2;

    TextView Amt,broker,sebon,capital,capitalg,pl,total,PL;
    Button calculate;
    View view=null;


    public SellFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sell, container, false);
    }

    @Override
    public void onStart() {
        view=getView();

        pp=view.findViewById(R.id.pp);
        sp=view.findViewById(R.id.sp);
        no=view.findViewById(R.id.no);
        Amt=view.findViewById(R.id.Amt);
        broker=view.findViewById(R.id.broker);
        sebon=view.findViewById(R.id.sebon);
        capital=view.findViewById(R.id.capital);
        capitalg=view.findViewById(R.id.capitalg);
        pl=view.findViewById(R.id.pl);
        PL=view.findViewById(R.id.PL);
        total=view.findViewById(R.id.total);
        calculate=view.findViewById(R.id.calculate);
        radio=view.findViewById(R.id.radio);
        r1=view.findViewById(R.id.r1);
        r2=view.findViewById(R.id.r2);


        super.onStart();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double PP = Double.parseDouble(pp.getText().toString());
                    double SP = Double.parseDouble(sp.getText().toString());
                    int No = Integer.parseInt(no.getText().toString());
                    double Amount = SP * No;
                    double Amount1 = PP * No;
                    double A = Math.round(Amount * 100) / 100.0;
                    double Sebon = Amount * 0.00015;
                    double Sebon1 = Amount1 * 0.00015;
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
                    double Broker1;
                    if (Amount1 < 50000) {
                        Broker1 = Amount1 * 0.004;
                    } else if (Amount1 > 50000 && Amount1 < 500000) {
                        Broker1 = Amount1 * 0.0037;
                    } else if (Amount1 > 500000 && Amount1 < 2000000) {
                        Broker1 = Amount1 * 0.0034;
                    } else if (Amount1 > 2000000 && Amount1 < 5000000) {
                        Broker1 = Amount1 * 0.0030;
                    } else {
                        Broker1 = Amount1 * 0.0027;
                    }
                    double B = Math.round(Broker * 100) / 100.0;
                    double Pay = (PP * No) + Broker1 + Sebon1 + 25;
                    double Capital = Amount - Pay - Broker - Sebon;

                    double C = Math.round(Capital * 100) / 100.0;
                    double tax;
                    if (r1.isChecked()) {
                        tax = Capital * 0.075;
                    } else {
                        tax = Capital * 0.05;
                    }

                    if (Capital < 0) {
                        tax = 0;
                        C = 0;
                    }

                    double T = Math.round(tax * 100) / 100.0;
                    double receive = Amount - Broker - Sebon - tax - 25;
                    double R = Math.round(receive * 100) / 100.00;
                    double proLo = receive - Pay;
                    double pro = Math.round(proLo * 100) / 100.00;

                    if (pro > 0) {
                        PL.setText("Profit");
                        Toast.makeText(getContext(), "Congratulations! You have a Profit of " + pro, Toast.LENGTH_SHORT).show();
                    } else {
                        PL.setText("Loss");
                        Toast.makeText(getContext(), "Bad Luck! You have a Loss of " + -1 * pro, Toast.LENGTH_SHORT).show();
                        pro = -1 * pro;

                    }


                    String Total = String.valueOf(A);
                    String Seb = String.valueOf(S);
                    String broke = String.valueOf(B);
                    String Cap = String.valueOf(C);
                    String Receive = String.valueOf(R);
                    String CG = String.valueOf(T);
                    String ProLO = String.valueOf(pro);


                    //Print

                    Amt.setText("Rs " + Total);
                    sebon.setText("Rs " + Seb);
                    broker.setText("Rs " + broke);
                    capital.setText("Rs " + Cap);
                    capitalg.setText("Rs " + CG);
                    total.setText("Rs " + Receive);
                    pl.setText("Rs " + ProLO);
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Required Field is empty", Toast.LENGTH_LONG).show();;
                }
            }
        });




    }
}