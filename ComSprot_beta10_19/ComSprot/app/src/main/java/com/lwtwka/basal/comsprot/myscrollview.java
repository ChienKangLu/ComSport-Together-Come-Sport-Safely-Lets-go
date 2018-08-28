package com.lwtwka.basal.comsprot;
        import android.app.Activity;
        import android.content.Context;
        import android.graphics.Rect;
        import android.util.AttributeSet;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.animation.TranslateAnimation;
        import android.widget.ImageView;
        import android.widget.ScrollView;

        import com.google.android.gms.drive.Contents;

/**
 * ElasticScrollView��?�ʪ�ScrollView
 */
public class myscrollview extends ScrollView {

    private View inner;// �lView

    private float y;// ???y��?
    private float y1;// ???y��?
    private float y2;// ��_?y��?

    private Rect normal = new Rect();// �x��(?���u�O?�Φ��A�u�O�Τ_�P?�O�_�ݭn??.)

    private boolean isCount = false;// �O�_?�l?��

    private boolean isMoveing = false;// �O�_?�l��?.

    private ImageView imageView;

    private int initTop, initbottom;// ��l����
    private int top, bottom;// ��???���סC

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public myscrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private OnHeaderRefreshListener mOnHeaderRefreshListener;

    /***
     * ���u XML �ͦ�??�u�@����.?��?�b�ͦ�??���̦Z?�ΡA�b�Ҧ��l??�K�[�����Z. �Y�Ϥl?��?�F onFinishInflate
     * ��k�A�]???�Τ�?����k�A��?��k�o�H?��.
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
    }

    /** touch �ƥ�?�z **/
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner != null) {
            commOnTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    /***
     * �D�N�ƥ�
     *
     * @param ev
     */
    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                y1 = ev.getY();
                top = initTop = imageView.getTop();
                bottom = initbottom = imageView.getBottom();
                break;

            case MotionEvent.ACTION_UP:
                y2 = ev.getY();
                //y2-y1>0��ܤU��?�@
                if (isMoveing&&(y2-y1>0)){
                    Log.e("jj", "�U��?��");
                    mOnHeaderRefreshListener.onHeaderRefresh(this);
                }

                isMoveing = false;
                // ����Q?.
                if (isNeedAnimation()) {

                    animation();

                }


                break;
            /***
             * �ư��X�Ĥ@����??��A�]?�Ĥ@��?�k�o��y��?�A �bMotionEvent.ACTION_DOWN��?������A
             * �]?��?�OMyScrollView��touch�ƥ�??���FLIstView���Ĥlitem�W��.�ҥH?�ĤG��?��?�l.
             * �M�ӧ�?�]�n?���l�ơA�N�O�Ĥ@����?��?��?��?�Z��?0. ���Z??���̤F�N���`?��.
             */
            case MotionEvent.ACTION_MOVE:

                final float preY = y;// ���U?��y��?
                float nowY = ev.getY();// ??y��?
                int deltaY = (int) (nowY - preY);// ��?�Z��
                if (!isCount) {
                    deltaY = 0; // �b?���n?0.
                }

                if (deltaY < 0 && top <= initTop)
                    return;

                // ???��̤W�Ϊ̳̤U?�N��?�A??�A??��?����
                isNeedMove();

                if (isMoveing) {
                    // ��l��?���x��
                    if (normal.isEmpty()) {
                        // �O�s���`��������m
                        normal.set(inner.getLeft(), inner.getTop(),inner.getRight(), inner.getBottom());
                    }

                    // ��?����
                    inner.layout(inner.getLeft(), inner.getTop() + deltaY / 3,inner.getRight(), inner.getBottom() + deltaY / 3);

                    top += (deltaY / 6);
                    bottom += (deltaY / 6);
                    imageView.layout(imageView.getLeft(), top,imageView.getRight(), bottom);
                }

                isCount = true;
                y = nowY;
                break;

            default:
                break;

        }
    }

    /***
     * �^???
     */
    public void animation() {

        TranslateAnimation taa = new TranslateAnimation(0, 0, top + 200,
                initTop + 200);
        taa.setDuration(200);
        imageView.startAnimation(taa);
        imageView.layout(imageView.getLeft(), initTop, imageView.getRight(),
                initbottom);

        // ??��???
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
                normal.top);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // ?�m�^�쥿�`��������m
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();

        isCount = false;
        y = 0;// ����Q?�n?0.

    }

    // �O�_�ݭn????
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    /***
     * �O�_�ݭn��?���� inner.getMeasuredHeight():?�����O����?����
     *
     * getHeight()�G?�����O�̹�������
     *
     * @return
     */
    public void isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // 0�O?���A�Z����?�O����
//		if (scrollY == 0 || scrollY == offset) {
//			isMoveing = true;
//		}
        if (scrollY == 0) {
            isMoveing = true;
        }
    }

    public interface OnHeaderRefreshListener {
        public void onHeaderRefresh(myscrollview view);
    }

    public void setOnHeaderRefreshListener(OnHeaderRefreshListener headerRefreshListener) {
        mOnHeaderRefreshListener = headerRefreshListener;
    }

}