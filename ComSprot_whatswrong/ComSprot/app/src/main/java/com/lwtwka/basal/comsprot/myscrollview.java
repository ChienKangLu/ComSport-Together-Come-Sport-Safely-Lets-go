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
 * ElasticScrollView有?性的ScrollView
 */
public class myscrollview extends ScrollView {

    private View inner;// 子View

    private float y;// ???y坐?
    private float y1;// ???y坐?
    private float y2;// 抬起?y坐?

    private Rect normal = new Rect();// 矩形(?里只是?形式，只是用于判?是否需要??.)

    private boolean isCount = false;// 是否?始?算

    private boolean isMoveing = false;// 是否?始移?.

    private ImageView imageView;

    private int initTop, initbottom;// 初始高度
    private int top, bottom;// 拖???高度。

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public myscrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private OnHeaderRefreshListener mOnHeaderRefreshListener;

    /***
     * 根据 XML 生成??工作完成.?函?在生成??的最后?用，在所有子??添加完之后. 即使子?覆?了 onFinishInflate
     * 方法，也???用父?的方法，使?方法得以?行.
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
    }

    /** touch 事件?理 **/
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner != null) {
            commOnTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    /***
     * 触摸事件
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
                //y2-y1>0表示下拉?作
                if (isMoveing&&(y2-y1>0)){
                    Log.e("jj", "下拉?束");
                    mOnHeaderRefreshListener.onHeaderRefresh(this);
                }

                isMoveing = false;
                // 手指松?.
                if (isNeedAnimation()) {

                    animation();

                }


                break;
            /***
             * 排除出第一次移??算，因?第一次?法得知y坐?， 在MotionEvent.ACTION_DOWN中?取不到，
             * 因?此?是MyScrollView的touch事件??到到了LIstView的孩子item上面.所以?第二次?算?始.
             * 然而我?也要?行初始化，就是第一次移?的?候?滑?距离?0. 之后??准确了就正常?行.
             */
            case MotionEvent.ACTION_MOVE:

                final float preY = y;// 按下?的y坐?
                float nowY = ev.getY();// ??y坐?
                int deltaY = (int) (nowY - preY);// 滑?距离
                if (!isCount) {
                    deltaY = 0; // 在?里要?0.
                }

                if (deltaY < 0 && top <= initTop)
                    return;

                // ???到最上或者最下?就不?再??，??移?布局
                isNeedMove();

                if (isMoveing) {
                    // 初始化?部矩形
                    if (normal.isEmpty()) {
                        // 保存正常的布局位置
                        normal.set(inner.getLeft(), inner.getTop(),inner.getRight(), inner.getBottom());
                    }

                    // 移?布局
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
     * 回???
     */
    public void animation() {

        TranslateAnimation taa = new TranslateAnimation(0, 0, top + 200,
                initTop + 200);
        taa.setDuration(200);
        imageView.startAnimation(taa);
        imageView.layout(imageView.getLeft(), initTop, imageView.getRight(),
                initbottom);

        // ??移???
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
                normal.top);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // ?置回到正常的布局位置
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();

        isCount = false;
        y = 0;// 手指松?要?0.

    }

    // 是否需要????
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    /***
     * 是否需要移?布局 inner.getMeasuredHeight():?取的是控件的?高度
     *
     * getHeight()：?取的是屏幕的高度
     *
     * @return
     */
    public void isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // 0是?部，后面那?是底部
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