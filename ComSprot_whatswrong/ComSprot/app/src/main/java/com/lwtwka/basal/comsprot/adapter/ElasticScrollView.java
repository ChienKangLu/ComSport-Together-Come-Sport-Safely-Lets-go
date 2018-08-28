package com.lwtwka.basal.comsprot.adapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by Tindle Wei.
 */
public class ElasticScrollView extends ScrollView {

    /**
     * もл??畉
     */
    private static final int SHAKE_MOVE_VALUE = 8;
    /**
     * Scrollview?场view
     */
    private View innerView;
    /**
     * ??innerView程Y竚
     */
    private float startY;
    /**
     * ??﹍innerView竚
     */
    private Rect outRect = new Rect();

    private boolean animationFinish = true;

    public ElasticScrollView(Context context) {
        super(context);
    }

    public ElasticScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * ?┯View
     * xml┮ΤガЫ?Чぇ?︽
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            innerView = getChildAt(0);
        }
    }

    /**
     * ?┯ViewGroup
     * true, 篒郉篘ㄆン
     * false, ?ㄆン???onTouchEvent()㎝北ンdispatchTouchEvent()
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        // ? ??北ン or 北ン菲?
        // 狦??北ン? false, 北ン????ㄆン
        // 狦北ン菲?? true, 菲?ガЫ
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startY = ev.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float currentY = ev.getY();
                float scrollY = currentY - startY;

                // 琌 true
                return Math.abs(scrollY) > SHAKE_MOVE_VALUE;
            }
        }
        // 纐? false
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (innerView == null) {
            return super.onTouchEvent(ev);
        } else {
            myTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    public void myTouchEvent(MotionEvent ev) {
        if (animationFinish) {

            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startY = ev.getY();
                    super.onTouchEvent(ev);
                    break;

                case MotionEvent.ACTION_UP:
                    startY = 0;
                    if (isNeedAnimation()) {
                        animation();
                    }
                    super.onTouchEvent(ev);
                    break;

                case MotionEvent.ACTION_MOVE:
                    final float preY =
                            startY == 0 ? ev.getY() : startY;
                    float nowY = ev.getY();
                    int deltaY = (int) (preY - nowY);
                    startY = nowY;

                    // ???程┪程?碞ぃ?????簿?ガЫ
                    if (isNeedMove()) {
                        if (outRect.isEmpty()) {
                            // 玂タ盽ガЫ竚
                            outRect.set(innerView
                                            .getLeft(),  innerView.getTop(),
                                    innerView.getRight(),
                                    innerView.getBottom());
                        }
                        // 簿?ガЫ
                        // ?ń deltaY/2 ?巨蔨?
                        innerView.layout(innerView.getLeft(),
                                innerView.getTop() - deltaY / 2,
                                innerView.getRight(),
                                innerView.getBottom() - deltaY / 2);
                    } else {
                        super.onTouchEvent(ev);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * ??簿???
     */
    public void animation() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, outRect.top - innerView.getTop());
        ta.setDuration(400);
        // ?硉?て ?ノ?蔨?
        ta.setInterpolator(new DecelerateInterpolator());
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animationFinish = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                innerView.clearAnimation();
                // ?竚innerViewタ盽ガЫ竚
                innerView.layout(outRect.left,
                        outRect.top, outRect.right, outRect.bottom);
                outRect.setEmpty();
                animationFinish = true;
            }
        });
        innerView.startAnimation(ta);
    }

    /**
     * 琌惠璶????
     */
    public boolean isNeedAnimation() {
        return !outRect.isEmpty();
    }

    /**
     * 琌惠璶簿?ガЫ
     */
    public boolean isNeedMove() {
        int offset = innerView.getMeasuredHeight() - getHeight();
        offset = (offset < 0) ? 0: offset;
        int scrollY = getScrollY();
        return (offset == 0 || scrollY == offset);
    }
}