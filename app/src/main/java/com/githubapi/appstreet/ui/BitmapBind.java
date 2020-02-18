package com.githubapi.appstreet.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.githubapi.appstreet.BaseApplication;
import com.githubapi.appstreet.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class BitmapBind {

    public static void bindBitmapToImage(ImageView imageView, String url) {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
                imageView.setImageBitmap(bitmap1);
                BaseApplication.getInstance().imageCacheProvider.add(url, bitmap1);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };

        if (BaseApplication.getInstance().imageCacheProvider.isDataAvailable(url)) {
            imageView.setImageBitmap(BaseApplication.getInstance().imageCacheProvider.get(url));
        } else {
            Picasso
                    .get()
                    .load(Uri.parse(url))
                    .placeholder(R.drawable.ic_octoface)
                    .noFade()
                    .error(R.drawable.ic_octoface)
                    .into(target);
            imageView.setTag(target);
        }
    }
}
