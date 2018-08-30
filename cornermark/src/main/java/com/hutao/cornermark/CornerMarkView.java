package com.hutao.cornermark;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 带角标的图标
 */
public class CornerMarkView extends LinearLayout {

    private String textValue;
    private int textColor;
    private int textSize;
    private Drawable textDrawable;
    private int mPadding;
    private String numValue;
    private int numSize;
    private int numColor;
    private Drawable numDrawable;
    private TextView numView;
    private TextView titleView;

    public CornerMarkView(Context context) {
        this(context, null);
    }

    public CornerMarkView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerMarkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //注意xml值sp,但是取出来以后会被转换为px;
        //值单位sp 这样取出的值跟xml赋值的是一样的
        textSize = numSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics());
        mPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CornerMarkView, defStyleAttr, 0);

        textValue = array.getString(R.styleable.CornerMarkView_text);
        textColor = array.getColor(R.styleable.CornerMarkView_text_color, Color.GRAY);
        //这样取出来值是sp转为px结果
        textSize = array.getDimensionPixelSize(R.styleable.CornerMarkView_text_size, textSize);
        textDrawable = array.getDrawable(R.styleable.CornerMarkView_drawable);
        textDrawable.setBounds(0, 0, textDrawable.getMinimumWidth(), textDrawable.getMinimumHeight());
        mPadding = array.getDimensionPixelSize(R.styleable.CornerMarkView_drawable_padding, mPadding);
        numValue = array.getString(R.styleable.CornerMarkView_num);
        //这样取出来值是sp转为px结果
        numSize = array.getDimensionPixelSize(R.styleable.CornerMarkView_num_size, numSize);
        numColor = array.getColor(R.styleable.CornerMarkView_num_color, Color.GRAY);
        numDrawable = array.getDrawable(R.styleable.CornerMarkView_num_bg);
        numDrawable.setBounds(0, 0, numDrawable.getMinimumWidth(), numDrawable.getMinimumHeight());
        array.recycle();
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.corner_mark_view, this);
        titleView = findViewById(R.id.title_id);
        titleView.setText(textValue);
        titleView.setTextColor(textColor);
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        titleView.setCompoundDrawablePadding(mPadding);
        titleView.setCompoundDrawables(null, textDrawable, null, null);
//        titleView.setOnClickListener(this);
        numView = findViewById(R.id.mark_num_id);
        numView.setText(numValue);
        numView.setVisibility(TextUtils.isEmpty(numValue) ? GONE : VISIBLE);
        numView.setTextSize(TypedValue.COMPLEX_UNIT_PX, numSize);
        numView.setTextColor(numColor);
        numView.setBackgroundDrawable(numDrawable);
    }

    /**
     * 设置文本
     * @param title
     */
    public void setTitle(String title) {
        titleView.setText(title);
    }

    public String getTitle() {
        return titleView.getText().toString();
    }

    /**
     * 设置图标
     * @param rId
     */
    public void setDrawable(int rId) {
        Drawable drawable = getResources().getDrawable(rId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        titleView.setCompoundDrawables(null, drawable, null, null);
    }

    /**
     * 设置角标
     * @param num
     */
    public void setNum(int num) {
        if (num > 0) {
            numView.setText(String.valueOf(num));
            numView.setVisibility(VISIBLE);
        }
    }
}
