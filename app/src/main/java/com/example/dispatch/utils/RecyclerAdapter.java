package com.example.dispatch.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.dispatch.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG="Regular list Adapter";

    ArrayList<RegularDriversFeed> arrayList;
public RecyclerAdapter(ArrayList<RegularDriversFeed> arrayList){
    this.arrayList=arrayList;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.post_fragment,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    RegularDriversFeed regularDriversFeed=arrayList.get(position);

    holder.UserAddressPost.setText(regularDriversFeed.getUserAddressPost());
    holder.UserDestinationPost.setText(regularDriversFeed.getUserDestinationPost());
    holder.UsernamePost.setText(regularDriversFeed.getUsernamePost());
    holder.PackagePostImage.setImageBitmap(regularDriversFeed.getPackagePostImage());
    holder.ProfileImage.setImageBitmap(regularDriversFeed.getProfileImage());


    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SquareImageView PackagePostImage;
        CircleImageView ProfileImage;
        TextView UsernamePost;
        TextView UserAddressPost;
        TextView UserDestinationPost;
        TextView MoreInfoPost;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            ProfileImage=itemView.findViewById(R.id.userProfileImage);
            PackagePostImage= itemView.findViewById(R.id.PackageImageView);
            UsernamePost=itemView.findViewById(R.id.profileName);
            UserAddressPost=itemView.findViewById(R.id.userAddressPost);
            UserDestinationPost=itemView.findViewById(R.id.DestinationAddress);
            MoreInfoPost=itemView.findViewById(R.id.MoreInfo);


        }
    }
}
