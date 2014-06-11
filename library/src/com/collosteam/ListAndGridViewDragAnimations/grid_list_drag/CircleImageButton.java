package com.collosteam.ListAndGridViewDragAnimations.grid_list_drag;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.collosteam.ListAndGridViewDragAnimations.R;
import com.collosteam.ListAndGridViewDragAnimations.utils.BitmapUtils;

/**
 * Created by Droid on 12.03.14.
 * <p/>
 * Class conteins view and delegate all
 */
public class CircleImageButton extends LinearLayout {

    View view;
    TextView text;
    ImageView circle;
    ImageView srcImg;
    TextView badge;


    public int getSrcID() {
        return srcID;
    }

    public boolean setSrcID(int srcID) {
        if (srcID == this.srcID) {
            return true;
        }
        this.srcID = srcID;
        return false;
    }

    int srcID = R.drawable.ic_launcher;


    int defoultLayoutIdGrid = R.layout.item_drag_grid_view;
    int defoultLayoutIdList = R.layout.item_drag_list_view;

    public int getLayoutID() {
        return layoutID;
    }

    /**
     * Used this in getView method on adapter
     *
     * @param layoutID - Linear layout  R.layout.item_drag_grid_view or  R.layout.item_drag_list_view layout
     */
    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
        init(getContext());
    }

    int layoutID = defoultLayoutIdList;


    /**
     * Default colors
     */
    int defaultColorIdYellow = R.color.dit_yellow;
    int defaultColorIdNude = R.color.dit_nude;


    public int getColorID() {
        return colorID;
    }

    /**
     * Return true if colorIF == this.colorID
     */
    public boolean setColorID(int colorID) {
        if (colorID == this.colorID) {
            return true;
        }
        this.colorID = colorID;
        return false;
    }

    int colorID = defaultColorIdNude;

    boolean isBackgroundDraw = false;

    public int getWidthBackground() {
        return width;
    }

    public void setWidthBackground(int width) {
        this.width = width;
    }

    int width = 0;


    public CircleImageButton(Context context) {
        super(context);
        init(context);
    }

    public CircleImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        if (!isCorrectLayout())
            throw new RuntimeException("Invalid layout, use only  R.layout.item_drag_grid_view or  R.layout.item_drag_list_view layout");
        text = (TextView) findViewById(R.id.text);
        circle = (ImageView) findViewById(R.id.circle);
        badge = (TextView) findViewById(R.id.badge);
        srcImg = (ImageView) findViewById(R.id.srcimg);
        setResource(srcID);
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = BitmapUtils.getRowWidthGridView(metrics, context);
        setBackgroundColor(colorID);
    }


    /**
     * Set drawable Id
     *
     * @param srcID - drawable id
     */
    public void setResource(int srcID) {
        setSrcID(srcID);
        if (srcImg != null) {
            srcImg.setImageResource(this.srcID);          //TODO need optimisation
        }
    }

    /**
     * Set curent count of message if count more 0
     */
    public void setCount(int count) {
        if (badge != null && count > 0) {
            badge.setVisibility(VISIBLE);
            badge.setText(String.valueOf(count));
        } else {
            badge.setVisibility(INVISIBLE);
        }
    }

    private boolean isCorrectLayout() {
        return layoutID == defoultLayoutIdList || layoutID == defoultLayoutIdGrid ? true : false;
    }


    public void setBackgroundColor(int colorID) {
        if (!setColorID(colorID) || !isBackgroundDraw) {
            setBackground(new BitmapDrawable(getResources(), BitmapUtils.createCircle(getResources().getColor(colorID), width)));
        }

    }

    public void setBackground(Drawable drawable) {
        if (circle != null) {
            if (Build.VERSION.SDK_INT < 16)
                circle.setBackgroundDrawable(drawable);
            else {
                circle.setBackground(drawable);
            }
            isBackgroundDraw = true;
        }

    }

    public void setText(CharSequence texts) {
        if (text != null) {
            text.setText(texts);
        }
    }


}
