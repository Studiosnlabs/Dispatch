package com.example.dispatch.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dispatch.R;

import java.util.ArrayList;

public class ExpressRecyclerAdapter extends RecyclerView.Adapter<ExpressRecyclerAdapter.ViewHolder> {
private static final String TAG="Express list Adapter";

        ArrayList<ExpressDriverFeed> arrayList;
public ExpressRecyclerAdapter(ArrayList<ExpressDriverFeed> arrayList){
        this.arrayList=arrayList;
        }

@NonNull
@Override
public ExpressRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.post_fragment,parent,false);
        return new ViewHolder(view);


        }

    @Override
    public void onBindViewHolder(@NonNull ExpressRecyclerAdapter.ViewHolder holder, int position) {

        ExpressDriverFeed expressDriverFeed=arrayList.get(position);

        holder.PackagePostImage.setImageBitmap(expressDriverFeed.getPackagePostImage());
        holder.UserAddressPost.setText(expressDriverFeed.getUserAddressPost());
        holder.UserDestinationPost.setText(expressDriverFeed.getUserDestinationPost());
        holder.UsernamePost.setText(expressDriverFeed.getUsernamePost());
        holder.ProfileImage.setImageResource(expressDriverFeed.getProfileImage());
        holder.Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressDriverFeed.setPrice();
            }
        });


    }



@Override
public int getItemCount() {
        return arrayList.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView PackagePostImage;
    ImageView ProfileImage;
    TextView UsernamePost;
    TextView UserAddressPost;
    TextView UserDestinationPost;
    TextView MoreInfoPost;
    Button Accept;

    public ViewHolder(@NonNull View itemView){
        super(itemView);

        ProfileImage=itemView.findViewById(R.id.userProfileImage);
        PackagePostImage= itemView.findViewById(R.id.PackageImageView);
        UsernamePost=itemView.findViewById(R.id.profileName);
        UserAddressPost=itemView.findViewById(R.id.userAddressPost);
        UserDestinationPost=itemView.findViewById(R.id.DestinationAddress);
        MoreInfoPost=itemView.findViewById(R.id.MoreInfo);
        Accept=itemView.findViewById(R.id.AcceptRegular);



    }
}
}

