package com.t.termuxgapps.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.t.termuxgapps.R;
import com.t.termuxgapps.model.GithubUser;

import java.util.List;

public class GithubAdapter extends ArrayAdapter<GithubUser> {

    private Context context;
    private List<GithubUser> data;



    //construktur
    public GithubAdapter(@NonNull Context context, int resource, @NonNull List<GithubUser> data) {
        super(context, resource ,data);

        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        GithubUser gu = data.get(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());

        view = inflater.inflate(R.layout.github_user, parent, false);



        TextView textViewName = (TextView) view.findViewById(R.id.txt_name);
        TextView textViewBio = (TextView) view.findViewById(R.id.txt_bio);
        ImageView imageViewUser = (ImageView) view.findViewById(R.id.author_image);

        TextView textViewFollower = (TextView) view.findViewById(R.id.txt_follower);
        TextView textViewFollowing = (TextView) view.findViewById(R.id.txt_following);
        TextView name = (TextView) view.findViewById(R.id.txt_test);

        GithubUser gitHubRepo = data.get(position);
        if(name != null)
        {
            name.setText(gitHubRepo.getName());
        }

        Picasso.get().load(gu.getAvatarUrl()).into(imageViewUser);
        textViewName.setText(gu.getLogin());
        textViewBio.setText(gu.getHtmlUrl());

        textViewFollower.setText(gu.getFollowersUrl());
        textViewFollowing.setText(gu.getFollowingUrl());



        return view;
    }
}
