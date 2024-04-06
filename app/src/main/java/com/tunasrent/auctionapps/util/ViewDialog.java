package com.tunasrent.auctionapps.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.tunasrent.auctionapps.R;

public class ViewDialog {

    Activity activity;
    Dialog dialog;
    public ViewDialog(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {

        dialog  = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_loading_layout);

        ImageView gifImageView = dialog.findViewById(R.id.custom_loading_imageView);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gifImageView);
        Glide.with(activity)
                .load(R.drawable.logo_tunas)
                .placeholder(R.drawable.logo_tunas)
                .centerCrop()
                .crossFade()
                .into(imageViewTarget);

        dialog.show();
    }

    public void hideDialog(){
        dialog.dismiss();
    }

}
