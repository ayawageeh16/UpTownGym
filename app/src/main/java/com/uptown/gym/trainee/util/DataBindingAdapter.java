package com.uptown.gym.trainee.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DataBindingAdapter {


    /**
     * This Method Setup RecyclerView LayoutManager, Adapter, And Adapter List Of Data
     *
     * @param recyclerView
     * @param list
     * @param <T>
     */
    @BindingAdapter("setupRecyclerView")
    public static <T> void setupRecyclerView(RecyclerView recyclerView, List<T> list) {

        // Check if the list of objects is null, if true return
        if (list == null) {
            return;
        }
        // Check if layoutManager of the recyclerView is null, if true set new LayoutManger to the recyclerView
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }
        // Set recyclerView HasFixedSizes to true so that when changing the contents of the adapter does not change it's height or the width.
        recyclerView.setHasFixedSize(true);

        // Set recyclerView Adapter
//        OrdersRecyclerViewAdapter adapter = new OrdersRecyclerViewAdapter();
//        recyclerView.setAdapter(adapter);
//        adapter.setOrderList((List<OrdersItems>) list);
    }


    /**
     * Binding Method to bind string image to imageView
     * @param view ImageView
     * @param imageUrl Image of type String
     */
    @BindingAdapter(value = {"addImageToView", "placeholder"}, requireAll = false)
    public static void setImageUsingGlide(final ImageView view, String imageUrl, Drawable placeHolder) {
        if (imageUrl != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(placeHolder)
                    .error(placeHolder)
                    .signature(new ObjectKey(imageUrl));

            Glide.with(view.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageUrl)
                    .apply(requestOptions)
                    .into(view);
        }
    }

//
//    @BindingAdapter(value = {"imageUrl", "placeholder"}, requireAll = false)
//    public static void setImageUrl(ImageView imageView, String url,
//                                   Drawable placeHolder) {
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(placeHolder);
//        requestOptions.error(placeHolder);
//
//        Glide.with(imageView.getContext())
//                .setDefaultRequestOptions(requestOptions)
//                .load(url).into(imageView);
//    }
}
