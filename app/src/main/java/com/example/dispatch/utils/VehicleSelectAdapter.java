package com.example.dispatch.utils;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.dispatch.R;

import java.util.List;

public class VehicleSelectAdapter extends PagerAdapter {

    private List<VehicleSelectModel> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public VehicleSelectAdapter(List<VehicleSelectModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=LayoutInflater.from(context);
        View  view= layoutInflater.inflate(R.layout.item,container,false);

        ImageView imageView;
        TextView title,desc;

        imageView=view.findViewById(R.id.VehicleImage);
        title=view.findViewById(R.id.VehicleTitle);
        desc=view.findViewById(R.id.VehicleDescription);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDescription());

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
