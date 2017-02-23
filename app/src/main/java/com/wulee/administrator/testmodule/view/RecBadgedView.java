package com.wulee.administrator.testmodule.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;

import com.wulee.administrator.testmodule.R;
import com.wulee.administrator.testmodule.view.badge.RecBadge;


/**
 * An extension to {@link BadgedView} which has a Rectangle Badge.
 * Created by chaos on 2015/11/25.
 */

public class RecBadgedView extends BadgedView {
    private int recbadgeGravity;
    private boolean badgeBoundsSet = false;

    public RecBadgedView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RecBadgedView, 0, 0);
        recbadgeGravity = a.getInt(R.styleable.RecBadgedView_recbadgeGravity, Gravity.TOP | Gravity
                .LEFT);
        a.recycle();
    }

    @Override
    public void initBadge(Context context) {
        badge = new RecBadge(context, badgeText, badgeColor, badgeTextColor);
        badge.setPadding(badgePadding);
        badge.setTextSize(badgeTextSize);
        badge.setCornerRadius(badgeCornerRadius);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (drawBadge) {
            badge.draw(canvas);

            if (!badgeBoundsSet) {
                layoutBadge();
            }
        }
    }

    private void layoutBadge() {
        Rect badgeBounds = badge.getBounds();

        Gravity.apply(recbadgeGravity,
                badge.getIntrinsicWidth(),
                badge.getIntrinsicHeight(),
                new Rect(0, 0, getWidth(), getHeight()),
                badgePadding,
                badgePadding,
                badgeBounds);
        badge.setBounds(badgeBounds);
        badgeBoundsSet = true;
    }
}
