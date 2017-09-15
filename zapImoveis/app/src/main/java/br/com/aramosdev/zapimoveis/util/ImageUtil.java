package br.com.aramosdev.zapimoveis.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class ImageUtil {

    public static void loadImageInto(String path, ImageView imageView, Context context) {
        DrawableTypeRequest load = Glide.with(context)
                .load(path);
        load.into(imageView);
    }
}
