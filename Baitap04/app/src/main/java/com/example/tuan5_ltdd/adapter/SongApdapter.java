package com.example.tuan5_ltdd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tuan5_ltdd.R;
import com.example.tuan5_ltdd.model.SongModel;

import java.util.List;

public class SongApdapter extends RecyclerView.Adapter<SongApdapter.SongViewHolder> {
    private static final String TAG = "SongAdapter";
    private List<SongModel> mSongs;
    private Context mContext;

    public SongApdapter(Context context,List<SongModel> datas) {
        mContext = context;
        mSongs = datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    private LayoutInflater mLayoutInflater;

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.row_item_song, parent, false);
        return new SongViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
// Get song in mSong via position
        SongModel song = mSongs.get(position);
//bind data to viewholder
        holder.tvCode.setText(song.getmCode());
        holder.tvTitle.setText(song.getmTitle());
        holder.tvLyric.setText(song.getmLyric());
        holder.tvArtist.setText(song.getmArtist());
    }
    @Override
    public int getItemCount() {
        return mSongs.size();
    }
    class SongViewHolder extends RecyclerView. ViewHolder {
        private TextView tvCode;
        private TextView tvTitle;
        private TextView tvLyric;
        private TextView tvArtist;

        public SongViewHolder(View itemView) {
            super(itemView);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvLyric = (TextView) itemView.findViewById(R.id.tv_lyric);
            tvArtist = (TextView) itemView.findViewById(R.id.tv_artist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SongModel song = mSongs.get(getAdapterPosition());
                    Toast.makeText(mContext, song.getmTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
