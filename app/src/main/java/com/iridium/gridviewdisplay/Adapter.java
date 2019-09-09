
package com.iridium.gridviewdisplay;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private static int count = 0;

    public static List<Image> image;

    public Adapter(List<Image> image)
    {
        this.image = image;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_image, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.setImage(image.get(position).getImageUrl());

    }

    @Override
    public int getItemCount() {
        return count = image.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "VIEW_HOLDER";

        View mView;
        private ImageView imageView;
        private Button removeBtn;


        public ViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
            removeBtn = mView.findViewById(R.id.removeBtn);
            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        removeItemAt(getAdapterPosition());
                }
            });
        }

        private void removeItemAt(int position)
        {
            image.remove(position);
        }


        public void setImage(Uri imageUrl)
        {
            imageView = mView.findViewById(R.id.grid_image_view);
            Picasso.get().load(String.valueOf(imageUrl)).into(imageView);
        }


    }
}
