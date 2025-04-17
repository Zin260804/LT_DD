package com.example.baitap10.Vidu1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baitap10.R;
import com.example.baitap10.bai3.UploadVideoActivity;
import com.example.baitap10.bai3.VideoModel3;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class VideosFireBaseAdapter extends FirebaseRecyclerAdapter<VideoModel3, VideosFireBaseAdapter.MyHolder> {

    private boolean isFav = false;

    public VideosFireBaseAdapter(@NonNull FirebaseRecyclerOptions<VideoModel3> options) {
        super(options);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_video_row_vd1, parent, false);
        return new MyHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyHolder holder, int position, @NonNull VideoModel3 model) {
        Log.d("FirebaseItem", "Loaded video: " + model.getTitle());

        holder.textVideoTitle.setText(model.getTitle());
        holder.textVideoDescription.setText(model.getDescription());

        holder.videoProgressBar.setVisibility(View.VISIBLE);
        holder.videoView.setVideoURI(Uri.parse(model.getVideoUrl()));

        holder.videoView.setOnPreparedListener(mp -> {
            holder.videoProgressBar.setVisibility(View.GONE);
            mp.start();

            float videoRatio = (float) mp.getVideoWidth() / mp.getVideoHeight();
            float screenRatio = (float) holder.videoView.getWidth() / holder.videoView.getHeight();
            float scale = videoRatio / screenRatio;

            if (scale >= 1f) {
                holder.videoView.setScaleX(scale);
            } else {
                holder.videoView.setScaleY(1f / scale);
            }

            mp.setLooping(true);
        });

        holder.videoView.setOnCompletionListener(mp -> mp.start());

        holder.favorites.setOnClickListener(v -> {
            holder.favorites.setImageResource(R.drawable.ic_fill_favorite);
            model.setLikes(model.getLikes() + 1); // tăng like

            // Cập nhật lên Firebase
            String videoKey = getRef(position).getKey(); // lấy key firebase
            if (videoKey != null) {
                FirebaseDatabase.getInstance().getReference("videos")
                        .child(videoKey)
                        .child("likes")
                        .setValue(model.getLikes());
            }
        });

        holder.dislikes.setOnClickListener(v -> {
            holder.favorites.setImageResource(R.drawable.ic_favorite);
            model.setLikes(Math.max(model.getDislikes() + 1, 0)); // giảm like nhưng không âm
            String videoKey = getRef(position).getKey(); // lấy key firebase
            if (videoKey != null) {
                FirebaseDatabase.getInstance().getReference("videos")
                        .child(videoKey)
                        .child("dislikes")
                        .setValue(model.getLikes());
            }
        });

        holder.imUpload.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, UploadVideoActivity.class);
            context.startActivity(intent);
        });

        holder.textDislikes.setText(String.valueOf(model.getDislikes()));
        holder.textFavorites.setText(String.valueOf(model.getLikes()));
        holder.textEmail.setText(model.getUserEmail());

        String personId = model.getUserId();
        if (personId != null && !personId.isEmpty()) {
            FirebaseDatabase.getInstance().getReference("users")
                    .child(personId)
                    .get()
                    .addOnSuccessListener(snapshot -> {
                        if (snapshot.exists()) {
                            // Lấy avatar
                            String avatarUrl = snapshot.child("avatarUrl").getValue(String.class);
                            if (avatarUrl != null && !avatarUrl.isEmpty()) {
                                Glide.with(holder.itemView.getContext())
                                        .load(avatarUrl)
                                        .into(holder.imPerson); // hiển thị avatar
                            }
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Firebase", "Lỗi khi lấy user info: " + e.getMessage());
                    });
        }

        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        String userId = auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : "unknown";
        if (!userId.isEmpty()) {
            FirebaseDatabase.getInstance().getReference("users")
                    .child(userId)
                    .get()
                    .addOnSuccessListener(snapshot -> {
                        if (snapshot.exists()) {
                            // Lấy avatar
                            String avatarUrl = snapshot.child("avatarUrl").getValue(String.class);
                            if (avatarUrl != null && !avatarUrl.isEmpty()) {
                                Glide.with(holder.itemView.getContext())
                                        .load(avatarUrl)
                                        .into(holder.imUser); // hiển thị avatar
                            }
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Firebase", "Lỗi khi lấy user info: " + e.getMessage());
                    });
        }
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        VideoView videoView;
        ProgressBar videoProgressBar;
        TextView textVideoTitle, textVideoDescription, textFavorites, textDislikes, textEmail;
        ImageView imPerson, favorites, imShare, imMore,imUpload,imUser, dislikes;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            videoProgressBar = itemView.findViewById(R.id.videoProgressBar);
            textVideoTitle = itemView.findViewById(R.id.textVideoTitle);
            textVideoDescription = itemView.findViewById(R.id.textVideoDescription);
            imPerson = itemView.findViewById(R.id.imPerson);
            favorites = itemView.findViewById(R.id.favorites);
            imShare = itemView.findViewById(R.id.imShare);
            imMore = itemView.findViewById(R.id.imMore);
            imUpload = itemView.findViewById(R.id.imUpload);
            textFavorites = itemView.findViewById(R.id.txtFavorites);
            textDislikes = itemView.findViewById(R.id.txtDislikes);
            imUser = itemView.findViewById(R.id.imageUser);
            dislikes = itemView.findViewById(R.id.dislikes);
            textEmail = itemView.findViewById(R.id.textEmail);
        }
    }
}

