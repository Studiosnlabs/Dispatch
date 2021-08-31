package com.example.dispatch.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dispatch.R;

import java.util.ArrayList;

public class BatchRecyclerAdapter extends RecyclerView.Adapter<BatchRecyclerAdapter.ViewHolder> {
    private static final String TAG="Batch List Adapter";

    ArrayList<BatchUserFeed> arrayList;
    public BatchRecyclerAdapter(ArrayList<BatchUserFeed> arrayList){
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.batchpostfragment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BatchRecyclerAdapter.ViewHolder holder, int position) {

        BatchUserFeed batchUserFeed=arrayList.get(position);

        holder.CourierName.setText(batchUserFeed.getCourierName());
        holder.Location.setText(batchUserFeed.getLocation());
        holder.Destination.setText(batchUserFeed.getDestination());
        holder.Departure.setText(batchUserFeed.getDeparture());
        holder.Arrival.setText(batchUserFeed.getArrival());
        holder.Phone.setText(batchUserFeed.getPhone());
        holder.Slots.setText(batchUserFeed.getSlotsLeft());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView CourierName;
        TextView Location;
        TextView Destination;
        TextView Departure;
        TextView Arrival;
        TextView Phone;
        TextView Slots;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CourierName=itemView.findViewById(R.id.courierName);
            Location=itemView.findViewById(R.id.locationData);
            Destination=itemView.findViewById(R.id.destinationData);
            Departure=itemView.findViewById(R.id.departureData);
            Arrival=itemView.findViewById(R.id.arrivalData);
            Phone=itemView.findViewById(R.id.phoneData);
            Slots=itemView.findViewById(R.id.slotsData);

        }
    }
}
