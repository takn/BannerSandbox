package com.google.android.gms.example.bannerexample;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class AndroidUtils {
    public static int dpToPixels(int dp, Context context) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int pixelsToDP(float px,Context context) {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }

    public static Point getScreenPixels(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

}
