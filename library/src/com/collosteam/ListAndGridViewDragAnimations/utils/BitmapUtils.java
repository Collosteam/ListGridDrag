package com.collosteam.ListAndGridViewDragAnimations.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

/**
 * Created by Droid on 13.11.13.
 */
public class BitmapUtils {

    public static final String TAG = "{BitmapUtils}";

    Context mContext;




    public BitmapUtils(Context mContext ) {
        this.mContext = mContext;
    }






    public static Bitmap createCircle(Bitmap bitmap) {
        Bitmap photo = bitmap;

        int photoDiametr = Math.min(photo.getWidth(), photo.getHeight());

        {
            if (photo != null) {
                Bitmap output = Bitmap.createBitmap(photoDiametr, photoDiametr, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(output);
                final int color = 0xff424242;
                final Paint paint = new Paint();
                final Rect rect = new Rect(0, 0, photoDiametr, photoDiametr);
                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(color);
                canvas.drawCircle(photoDiametr / 2, photoDiametr / 2, photoDiametr / 2, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(photo, rect, rect, paint);
                return output;
            }
        }
        return photo;
    }

    // Create Grid Menu Circle
    public static Bitmap createCircle(Drawable drawable) {

        Bitmap photo = drawableToBitmap(drawable);

        {

            if (photo != null) {
                Bitmap output = Bitmap.createBitmap(photo.getWidth(), photo.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(output);

                final int color = 0xff424242;
                final Paint paint = new Paint();
                final Rect rect = new Rect(0, 0, photo.getWidth(), photo.getHeight());
                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(color);
//                paint.setShadowLayer(15, 0, 0, Color.BLUE);
                canvas.drawCircle(photo.getWidth() / 2, photo.getWidth() / 2, (float) (photo.getWidth() / 2.15), paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(photo, rect, rect, paint);
                return output;
            }

        }
        return photo;
    }

    // Create Grid Menu Circle
    public static Bitmap createCircle(Drawable drawable, int width) {

        Bitmap photo = drawableToBitmap(drawable);
        {
            if (photo != null) {
                Bitmap output = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(output);
                final int color = 0xff424242;
                final Paint paint = new Paint();
                final Rect rect = new Rect(0, 0,width, width);
                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(color);
//                paint.setShadowLayer(15, 0, 0, Color.BLUE);
                canvas.drawCircle(width / 2, width / 2, (float) (width / 2.25), paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(photo, rect, rect, paint);
                return output;
            }
        }
        return photo;
    }


    // Create Grid Menu Circle
    public static Bitmap createCircle(int color, int width) {

        Bitmap output = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0 ,0);
        paint.setColor(color);
        canvas.drawCircle(width / 2, width / 2, (float) (width / 2.25), paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        return output;}


    // Convert Drawable to Bitmap
    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    // Draw calendar Circle
    public static Bitmap drawCircleToCalendar(int color, int size) {

        final int radius = size/2;

        final Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(color);
        canvas.drawCircle(size/2, size/2, radius, paint);

        return output;

    }


    public static Bitmap drawWhiteCircleAround(Bitmap output) {

        final int size = output.getHeight() ;
        final int radius = output.getHeight()/2;
        int widthStroce = radius/10<=0 ? 1 :  radius/10;
        final Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        paint.setStrokeWidth(widthStroce);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(size/2, size/2 , radius-widthStroce/2, paint);
        return output;

    }

    // Covert Dp to Pixel
    public static float convertDpToPixel(float dp, Context context){

        final Resources resources = context.getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        final float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    // Convert Pixel to Dp
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    // get Pixel Padding to Grid View element
    public static int getPaddingGridView(DisplayMetrics metrics, Context context ){
        // get Display Width
        final int width = metrics.widthPixels;
        final int behindOffsetRes = (int)convertDpToPixel(72f, context);

        final int formula = ((width - behindOffsetRes)/3 - (int)convertDpToPixel(5f, context))/2;

        return formula;
    }

    // get pixel Row width
    public static int getRowWidthGridView(DisplayMetrics metrics, Context context ){
        // get Display Width
        final int width = metrics.widthPixels;
        final int behindOffsetRes = (int)convertDpToPixel(72f, context);
        final int formula = ((width - behindOffsetRes)/3 - (int)convertDpToPixel(10f, context) );
        return formula;
    }

    // Get Img Size
    public static int getResSize(Context context, int id){
        int size = 0;
        final BitmapFactory.Options dimensions = new BitmapFactory.Options();
        dimensions.inJustDecodeBounds = true;
        final Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), id, dimensions);

        size = dimensions.outWidth;

        return size;
    }

}
