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
     * �����??�t
     */
    private static final int SHAKE_MOVE_VALUE = 8;
    /**
     * Scrollview?����view
     */
    private View innerView;
    /**
     * ??innerView�̪쪺Y��m
     */
    private float startY;
    /**
     * ??��linnerView���j�p��m
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
     * ?�Ӧ�View
     * �bxml���Ҧ������[?�����Z?��
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            innerView = getChildAt(0);
        }
    }

    /**
     * ?�Ӧ�ViewGroup
     * ��^true, �I���D�N�ƥ�
     * ��^false, ?�ƥ�???onTouchEvent()�M�l����dispatchTouchEvent()
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        // �P? ??�l���� or ����l�����?
        // �p�G??�l����A?��^ false, �l����????�ƥ�
        // �p�G����l�����?�A?��^ true, ��?����
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startY = ev.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float currentY = ev.getY();
                float scrollY = currentY - startY;

                // �O�_��^ true
                return Math.abs(scrollY) > SHAKE_MOVE_VALUE;
            }
        }
        // �q?��^ false
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

                    // ???��̤W�Ϊ̳̤U?�N��?�A??�A??��?����
                    if (isNeedMove()) {
                        if (outRect.isEmpty()) {
                            // �O�s���`��������m
                            outRect.set(innerView
                                            .getLeft(),  innerView.getTop(),
                                    innerView.getRight(),
                                    innerView.getBottom());
                        }
                        // ��?����
                        // ?�� deltaY/2 ?�F�ާ@�^?��n
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
     * ??��???
     */
    public void animation() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, outRect.top - innerView.getTop());
        ta.setDuration(400);
        // ?�t?�� ?�F��?�^?��n
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
                // ?�minnerView�^�쥿�`��������m
                innerView.layout(outRect.left,
                        outRect.top, outRect.right, outRect.bottom);
                outRect.setEmpty();
                animationFinish = true;
            }
        });
        innerView.startAnimation(ta);
    }

    /**
     * �O�_�ݭn????
     */
    public boolean isNeedAnimation() {
        return !outRect.isEmpty();
    }

    /**
     * �O�_�ݭn��?����
     */
    public boolean isNeedMove() {
        int offset = innerView.getMeasuredHeight() - getHeight();
        offset = (offset < 0) ? 0: offset;
        int scrollY = getScrollY();
        return (offset == 0 || scrollY == offset);
    }
}