package com.saleskit.cbbank.features.appointment;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<String> images;
    private Context context;
    private OnItemClicklistener onItemClicklistener;
    private UpdateResultView updateResultView;
    private boolean canEdit;

    public ImageAdapter(List<String> images, Context context, UpdateResultView updateResultView, boolean canEdit) {
        this.images = images;
        this.context = context;
        this.updateResultView = updateResultView;
        this.canEdit = canEdit;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.iv_close)
        ImageView ivClose;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            Glide.with(context).load(Constants.BASE_IMAGEURL + images.get(position)).apply(new RequestOptions()
                    .placeholder(R.drawable.placeholder).diskCacheStrategy(DiskCacheStrategy.ALL)).into(ivLogo);
            itemView.setOnClickListener(v -> {
                final Dialog dialogZoomImage = DialogUtil.builder(context, R.layout.dialog_zoom_image);
                PhotoView photoView = dialogZoomImage.findViewById(R.id.photo_zoom);
                Glide.with(context).load(Constants.BASE_IMAGEURL + images.get(position)).into(photoView);
                dialogZoomImage.show();
            });
            if(canEdit){
                ivClose.setVisibility(View.VISIBLE);
                ivClose.setOnClickListener(v -> updateResultView.onDelete(position));
            } else {
                ivClose.setVisibility(View.GONE);
            }

        }
    }
}
