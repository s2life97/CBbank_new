package com.saleskit.cbbank.features.home.activity;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;

import com.saleskit.cbbank.R;

import java.io.Serializable;

public class SpannableBuilder {
    private SpannableString mSpanStr;

    public static SpannableBuilder init(String s) {
        SpannableBuilder builder = new SpannableBuilder();
        builder.mSpanStr =  new SpannableString(s);
        return builder;
    }

    public SpannableBuilder makeBold(String ss) {
        if (mSpanStr == null)
            mSpanStr = new SpannableString(ss);
        int index = mSpanStr.toString().indexOf(ss);
        if (index != -1) {
            mSpanStr.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), index, index + ss.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return this;
    }

    public SpannableBuilder setSpan(String ss, Object o) {
        if (mSpanStr == null)
            mSpanStr = new SpannableString(ss);
        int index = mSpanStr.toString().indexOf(ss);
        if (index != -1) {
            mSpanStr.setSpan(o, index, index + ss.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return this;
    }

    public SpannableBuilder setColor(String ss, int color) {
        if (mSpanStr == null)
            mSpanStr = new SpannableString(ss);
        int index = mSpanStr.toString().indexOf(ss);
        if (index != -1) {
            mSpanStr.setSpan(new ForegroundColorSpan(color), index, index + ss.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return this;
    }

    public SpannableBuilder makeLink(final Context context, String span, final Serializable object) {
        int index = mSpanStr.toString().indexOf(span);
        if (index == -1) {
            return this;
        }

        ClickableSpan clickableSpan = new ClickableSpan() {

            @Override
            public void onClick(View view) {
                //do something with the object
            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setColor(context.getResources().getColor(R.color.gray));
            }
        };

        setSpan(span, clickableSpan);
        makeBold(span);
        return this;
    }

    public SpannableString create() {
        return mSpanStr;
    }
}
