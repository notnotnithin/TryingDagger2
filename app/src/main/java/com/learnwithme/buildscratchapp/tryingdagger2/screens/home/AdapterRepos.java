package com.learnwithme.buildscratchapp.tryingdagger2.screens.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.learnwithme.buildscratchapp.tryingdagger2.models.GithubRepo;
import com.learnwithme.buildscratchapp.tryingdagger2.screens.HomeActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Nithin on 03/05/2017.
 */

public class AdapterRepos extends BaseAdapter {
    private final List<GithubRepo> repoList =new ArrayList<>(0);
    private final Context context;
    private final Picasso picasso;

    @Inject
    public AdapterRepos(HomeActivity context, Picasso picasso) {
        this.context = context;
        this.picasso = picasso;
    }

    @Override
    public int getCount() {
        return repoList.size();
    }

    @Override
    public Object getItem(int position) {
        return repoList.get(position);
    }

    public boolean hasStableIds() {
        return true;
    }

    @Override
    public long getItemId(int position) {
        return repoList.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RepoListItem repoListItem;
        if(convertView != null) {
            repoListItem = new RepoListItem(context,picasso);
        }
        else {
            repoListItem = RepoListItem.class.cast(convertView);
        }
        repoListItem.setRepo(repoList.get(position));
        return repoListItem;
    }

    public void swapData(Collection<GithubRepo> githubRepos) {
        repoList.clear();
        if(githubRepos != null) {
            repoList.addAll(githubRepos);
        }
        notifyDataSetChanged();
    }
}