package com.example.sharecalculator;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;


public class SharesFragment extends Fragment {
    RecyclerView recycler;
    ExtendedFloatingActionButton Add;
    View view=null;
    ArrayList<share> MyShares=new ArrayList<>();
    DB db;
    shareAdapter adapter;



    public SharesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db=new DB(getContext());
        view=inflater.inflate(R.layout.fragment_shares, container, false);
        recycler=view.findViewById(R.id.recycler);
        Add=view.findViewById(R.id.Add);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        super.onStart();




        MyShares.clear();
        Cursor cursor1=new DB(getContext()).read();
        try{
            while(cursor1.moveToNext()){
                share obj1=new share(cursor1.getInt(0),cursor1.getString(1), cursor1.getString(2), cursor1.getString(3), cursor1.getString(4), cursor1.getString(5) );
                MyShares.add(obj1);
            }} catch (Exception e) {
            Toast.makeText(getContext(), " "+e, Toast.LENGTH_LONG).show();
        }

        adapter=new shareAdapter(getContext(),MyShares);

        recycler.setAdapter(adapter);


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.add);
                EditText name=dialog.findViewById(R.id.sname);
                EditText price=dialog.findViewById(R.id.price);
                EditText no=dialog.findViewById(R.id.no);
                TextView pay=dialog.findViewById(R.id.pay);
                TextView per=dialog.findViewById(R.id.per);
                Button save=dialog.findViewById(R.id.save);
                Button calc=dialog.findViewById(R.id.calc);


                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            double Price = Double.parseDouble(price.getText().toString());
                            String Buy = name.getText().toString();
                            String Bp = price.getText().toString();
                            String Number = no.getText().toString();
                            String Payment = pay.getText().toString();
                            String PerP = per.getText().toString();
                            String re = db.add(Buy, Number, Bp, Payment, PerP);
                            Toast.makeText(getContext(), re, Toast.LENGTH_SHORT).show();
                            MyShares.add(new share(Buy, Number, Bp, Payment, PerP));
                            adapter.notifyItemInserted(MyShares.size() - 1);
                            recycler.scrollToPosition(MyShares.size() - 1);
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Required Field is empty", Toast.LENGTH_LONG).show();;
                        }

                    }
                });
                calc.setOnClickListener(new View.OnClickListener() {
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

                            String PerT = String.valueOf(Pe);
                            String Tot = String.valueOf(P);


                            pay.setText("Rs " + Tot);
                            per.setText("Rs " + PerT);
                        } catch (NumberFormatException e) {
                            Toast.makeText(getContext(), "Required Field is empty", Toast.LENGTH_LONG).show();;
                        }

                    }
                });


                dialog.show();
            }
        });





        // Inflate the layout for this fragment
        return view;
    }



}