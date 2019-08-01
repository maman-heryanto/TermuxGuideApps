package com.t.termuxgapps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.t.termuxgapps.adapter.GithubAdapter;
import com.t.termuxgapps.helper.ServiceGenerator;
import com.t.termuxgapps.model.GithubUser;
import com.t.termuxgapps.service.GithubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainRef extends Fragment {

    public MainRef() {
    }

    RelativeLayout view;
    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.main_ref, container, false);
        getActivity().setTitle("Github Referensi Tools");


        //fab show
        FloatingActionButton floatingActionButton = ((MainActivity) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.show();
        }

        listView = (ListView) view.findViewById(R.id.list_user_github);

        GithubService githubService = ServiceGenerator.build().create(GithubService.class);

        Call <List<GithubUser>> callAsync = githubService.getUsers(20, 1);

        callAsync.enqueue(new Callback<List<GithubUser>>() {

            @Override
            public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                List<GithubUser> results = response.body();

                GithubAdapter adapter = new GithubAdapter(getActivity(), 0, results);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });



        return view;
    }
}
