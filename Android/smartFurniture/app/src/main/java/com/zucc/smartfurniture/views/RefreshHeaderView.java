package com.zucc.smartfurniture.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;
import com.zucc.smartfurniture.R;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/3$ 10:52$
 * Description:SeiperTolayout 下拉刷新 头部
 * <p/>
 */
public class RefreshHeaderView extends SwipeRefreshHeaderLayout{
    /**刷新下拉图标*/
    private ImageView ivArrow;
    /**下拉刷新成功图标*/
    private ImageView ivSuccess;
    /**下拉刷新显示文字*/
    private TextView tvRefresh;
    /**下拉刷新头部高度*/
    private ProgressBar progressBar;
    /**下拉刷新头部高度*/
    private int mHeaderHeight;
    /**下拉刷新向下动画*/
    private Animation rotateUp;
    /**下拉刷新向上动画*/
    private Animation rotateDown;
    private boolean rotated = false;

    public RefreshHeaderView(Context context) {
        super(context,null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_twitter);
        rotateUp = AnimationUtils.loadAnimation(context,R.anim.rotate_up);
        rotateDown =AnimationUtils.loadAnimation(context,R.anim.rotate_down);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText(getResources().getString(R.string.refresh_ing));
    }

    @Override
    public void onPrepare() {
        super.onPrepare();
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        super.onMove(y, isComplete, automatic);
        if(!isComplete){
            ivArrow.setVisibility(VISIBLE);
            progressBar.setVisibility(GONE);
            ivSuccess.setVisibility(GONE);
            if(y > mHeaderHeight){
                tvRefresh.setText(getResources().getString(R.string.refresh_start));
                if(!rotated){
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateUp);
                    rotated = true;
                }
            }else if( y < mHeaderHeight){
                if(rotated){
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateDown);
                    rotated = false;
                }
                tvRefresh.setText(getResources().getString(R.string.refresh_down));
            }
        }
    }

    @Override
    public void onRelease() {
        super.onRelease();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        rotated = false;
        ivSuccess.setVisibility(VISIBLE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText(getResources().getString(R.string.refresh_finish));
    }
}
