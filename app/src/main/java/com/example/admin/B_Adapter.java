package com.example.admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class B_Adapter extends RecyclerView.Adapter<B_Adapter.productViewHolder> {

    private Context mCtx;
    private List<Model_Booking> bList;

    public B_Adapter(Context mCtx, List<Model_Booking> bList) {
        this.mCtx = mCtx;
        this.bList = bList;
    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.b_recyclerlayout, parent, false);
        return new productViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productViewHolder holder, int position) {
        Model_Booking modelbooking = bList.get(position);
        holder.textViewName.setText("Vehicle Owner Name: "+ modelbooking.getConame());
        holder.textViewST.setText("Starting Time: " + modelbooking.getStime());
        holder.textViewBH.setText("Duration: " + modelbooking.getBhours() + "Hours");
        holder.textViewbill.setText("Bill: " + modelbooking.getFee());

    }

    @Override
    public int getItemCount() {
        return bList.size();
    }

    class productViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewST, textViewBH, textViewbill;

        public productViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_pname1);
            textViewST = itemView.findViewById(R.id.text_view_st);
            textViewBH = itemView.findViewById(R.id.text_view_td);
            textViewbill=itemView.findViewById(R.id.text_bill);
        }
    }
}
