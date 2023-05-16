package com.example.loginapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.OnClickItemListener;
import com.example.loginapp.R;
import com.example.loginapp.models.Requests;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    List<Requests> lsRequest;
    OnClickItemListener onClickItemListener;

    public RequestAdapter(List<Requests> lsRequest, OnClickItemListener onClickItemListener) {
        this.lsRequest = lsRequest;
        this.onClickItemListener = onClickItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_item_recycler_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txvID.setText(lsRequest.get(position).getId()+"");
        holder.txvRegistrationNumber.setText(lsRequest.get(position).getRegistrationNumber());
        holder.txvVehicleNumber.setText(lsRequest.get(position).getVehicleName());
    }

    @Override
    public int getItemCount() {
        return lsRequest.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txvID, txvRegistrationNumber, txvVehicleNumber;
        LinearLayout llListItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llListItem = itemView.findViewById(R.id.lay_item_ll);
            txvID = itemView.findViewById(R.id.txv_request_id);
            txvRegistrationNumber = itemView.findViewById(R.id.txv_registration_number);
            txvVehicleNumber = itemView.findViewById(R.id.txv_vehicle_number);
        }
    }
}
