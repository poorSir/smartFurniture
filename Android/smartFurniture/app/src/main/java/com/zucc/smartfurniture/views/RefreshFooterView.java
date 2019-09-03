package com.zucc.smartfurniture.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreFooterLayout;
import com.zucc.smartfurniture.R;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/3$ 10:29$
 * Description:swipeToLayout 加载更多控件
 * <p/>
 */
public class RefreshFooterView extends SwipeLoadMoreFooterLayout{
    /**进度圈*/
    private ProgressBar progressBar;
    /**加载完成图片*/
    private ImageView ivSuccess;
    /**上拉文字*/
    private TextView  tvLoadMore;
    /**最少上拉长度*/
    private int         mFooterHeight;
    public RefreshFooterView(Context context) {
        super(context,null);
    }

    public RefreshFooterView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        mFooterHeight = getResources().getDimensionPixelOffset(R.dimen.load_more_footer_height_twitter);
    }

    public RefreshFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        tvLoadMore = (TextView) findViewById(R.id.tvLoadMore);
    }

    @Override
    public void onPrepare() {
        super.onPrepare();
        ivSuccess.setVisibility(View.GONE);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        super.onMove(y, isComplete, automatic);
        if(!isComplete){
            ivSuccess.setVisibility(GONE);
            progressBar.setVisibility(GONE);
            if(-y >= mFooterHeight){
                tvLoadMore.setText(getResources().getString(R.string.load_more_reflesh));
            }else {
                tvLoadMore.setText(getResources().getString(R.string.load_more_up));
            }
        }
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        tvLoadMore.setText(getResources().getString(R.string.load_more_ing));
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onRelease() {
        super.onRelease();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        progressBar.setVisibility(GONE);
        ivSuccess.setVisibility(VISIBLE);
    }

    @Override
    public void onReset() {
        super.onReset();
        ivSuccess.setVisibility(GONE);
    }
}
