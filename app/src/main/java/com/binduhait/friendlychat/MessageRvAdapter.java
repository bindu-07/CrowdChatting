package com.binduhait.friendlychat;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MessageRvAdapter extends RecyclerView.Adapter<MessageRvAdapter.MessageAdapterViewHolder> {

    private final SharedPreferences sharedPreferences;
    private final String SHARED_PREF = "MY_SHARED_PREF";
    private List<FriendlyMessage> objects;
    private Context context;

    public MessageRvAdapter(Context context, List<FriendlyMessage> objects) {
        super();
        sharedPreferences = context.getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        this.objects = objects;
    }

    @NonNull
    @Override
    public MessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapterViewHolder holder, int position) {

        FriendlyMessage message = objects.get(position);
        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            holder.messageTextView.setVisibility(View.GONE);
            holder.photoImageView.setVisibility(View.VISIBLE);
            Glide.with(holder.photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .into(holder.photoImageView);
        } else {
            holder.messageTextView.setVisibility(View.VISIBLE);
            holder.photoImageView.setVisibility(View.GONE);
            holder.messageTextView.setText(message.getText());
        }
        holder.authorTextView.setText(message.getName());
        if(message.getTime()!=null)
            holder.timeTv.setText(message.getTime());
        else holder.timeTv.setVisibility(View.GONE);

        String currentUser = sharedPreferences.getString("username",null);
        if(message.getName().equalsIgnoreCase(currentUser)){
            holder.layout.setGravity(Gravity.END);
        }else {
            holder.layout.setGravity(Gravity.START);
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public static class MessageAdapterViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView photoImageView;
        TextView messageTextView;
        TextView authorTextView;
        LinearLayout layout;
        TextView timeTv;

        MessageAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            photoImageView = itemView.findViewById(R.id.photoImageView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            authorTextView = itemView.findViewById(R.id.nameTextView);
            layout = itemView.findViewById(R.id.parent_item);
            timeTv = itemView.findViewById(R.id.timeTextView);
        }
    }
}