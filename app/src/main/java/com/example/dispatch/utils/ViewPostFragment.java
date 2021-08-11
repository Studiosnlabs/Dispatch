package com.example.dispatch.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dispatch.R;

public class ViewPostFragment extends Fragment {
    SquareImageView PackagePostImage;
    TextView UsernamePost;
    TextView UserAddressPost;
    TextView UserDestinationPost;
    TextView MoreInfoPost;
    Button AcceptButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.post_fragment,container,false);

        PackagePostImage= (SquareImageView) view.findViewById(R.id.PackageImageView);
        UsernamePost=view.findViewById(R.id.profileName);
        UserAddressPost=view.findViewById(R.id.userAddressPost);
        UserDestinationPost=view.findViewById(R.id.DestinationAddress);
        MoreInfoPost=view.findViewById(R.id.MoreInfo);
        AcceptButton=view.findViewById(R.id.AcceptRegular);




        return view;
    }


}
