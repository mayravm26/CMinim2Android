package edu.upc.dsa.martianslog.service;

import android.media.Image;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.dsa.martianslog.models.UsuariRanking;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder>

{
    private List<UsuariRanking> usuariRankingList;
    public RankingAdapter(List<UsuariRanking> usuariRankingList)
    {
        this.usuariRankingList=usuariRankingList;
    }
    public int getItemCount()
    {
        return usuariRankingList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView usernameTextView;
        public TextView puntos;
        public Image avatarImageView;
        public ViewHolder(View ItemView)
        {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            pointsTextView = itemView.findViewById(R.id.pointsTextView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);

        }
    }
}
