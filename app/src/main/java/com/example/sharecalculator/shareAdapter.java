package com.example.sharecalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class shareAdapter extends RecyclerView.Adapter<shareAdapter.ViewHolder> {

    Context context;
    DB db;
    ArrayList<share> MyShares=new ArrayList<>();

    shareAdapter(Context context,ArrayList<share> MyShares){
        this.context=context;
        this.MyShares=MyShares;
    }



    @NonNull
    @Override
    public shareAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.shares,parent,false);
       ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull shareAdapter.ViewHolder holder, int position) {


        share details=MyShares.get(position);
         holder.Name.setText(MyShares.get(position).name);
         holder.Quantity.setText(MyShares.get(position).quantity);

        holder.card.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 try {
                     Intent intent=new Intent(context, shareDetails.class);
                     intent.putExtra("Name",details.name);
                     intent.putExtra("Quantity",details.quantity);
                     intent.putExtra("BP",details.bp);
                     intent.putExtra("Total",details.total);
                     intent.putExtra("Per",details.per);
                     context.startActivity(intent);
                 } catch (Exception e) {
                     Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();;
                 }
             }
         });

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Delete Data");
                builder.setMessage("Do you sure want to delete this Shares?");
                builder.setIcon(R.drawable.baseline_delete_24);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyShares.remove(position);
                        notifyItemRemoved(position);
                        db=new DB(context);
                        try{
                            db.Delete(details.getId());
                        } catch (Exception e) {
                            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_LONG).show();;
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return MyShares.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Quantity;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.Name);
            Quantity=itemView.findViewById(R.id.Quantity);
            card=itemView.findViewById(R.id.card);
        }
    }
}
