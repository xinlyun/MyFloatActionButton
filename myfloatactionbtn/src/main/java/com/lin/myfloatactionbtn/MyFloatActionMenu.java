package com.lin.myfloatactionbtn;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by xinlyun on 15-9-22.
 */
public class MyFloatActionMenu extends ViewGroup {
    private int width,icon,count=0;
    private Posi[] posis ;
    private int[] iconPosi;
    private static final int LEFT_TOP=0,LEFT_BOTTOM=1,RIGHT_TOP=2,RIGHT_BOTTOM=3;

    private boolean flagOpenorClose = false,flag =true;

    public MyFloatActionMenu(Context context) {
        super(context);
    }

    public MyFloatActionMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyFloatActionMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    AddMyFloatActionButton myFloatActionButton;
    private void init(Context context,AttributeSet attrs){

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyFloatActionMenu);
//      width = (int)typedArray.getDimension(R.styleable.MyFloatActionMenu_mfam_width,400);
        width = typedArray.getDimensionPixelSize(R.styleable.MyFloatActionMenu_mfam_width,400);
        icon = typedArray.getResourceId(R.styleable.MyFloatActionMenu_mfam_icon,R.drawable.ic_fab_star);
        int mPosi = typedArray.getInt(R.styleable.MyFloatActionMenu_mfam_posi,LEFT_TOP);
        Log.d("MyFLoatActionMenu", "width " + width);
        initPosiData(mPosi);
        myFloatActionButton = new AddMyFloatActionButton(getContext());
        myFloatActionButton.lock();
        myFloatActionButton.init(icon, width / 3);

        myFloatActionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTouch()) {
                    if (flagOpenorClose) {
                        for (int i = 0; i < getChildCount() - 1; i++) {
                            MyFloatActionButton vx = (MyFloatActionButton) getChildAt(i);
                            vx.cloesit(75+50*i);
                        }
                        AddMyFloatActionButton vx = (AddMyFloatActionButton) getChildAt(getChildCount()-1);
                        vx.clockwise(300);
                        flagOpenorClose = false;
                    } else {
                        for (int i = 0; i < getChildCount() - 1; i++) {
                            MyFloatActionButton vx = (MyFloatActionButton) getChildAt(i);
                            vx.reshow(75+50*i);
                        }
                        AddMyFloatActionButton vx = (AddMyFloatActionButton) getChildAt(getChildCount()-1);
                        vx.anticlockwise(300);
                        flagOpenorClose = true;
                    }
                }
            }
        });
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("MyFLoatActionMenu", "onLayout");
        if(count==0)count = getChildCount();
            for(int i= 0;i<getChildCount();i++){
                View v = getChildAt(i);
                v.layout(iconPosi[0],iconPosi[1], iconPosi[2], iconPosi[3]);
//                v.setX(5f);
//                v.setY(5f);
                ((MyFloatActionButton)v).setWidth(width / 4);
                ((MyFloatActionButton)v).setXY(posis[i]);
//                v.setVisibility(View.INVISIBLE);

            }
            if(getChildCount()==count){
                addView(myFloatActionButton);
                myFloatActionButton.layout(0,0,width/3,width/3);
            }

    }
    long time =0;
    private boolean isTouch(){
        if(System.currentTimeMillis()-time>350){
            time = System.currentTimeMillis();
            return true;
        }
            return false;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("MyFLoatActionMenu","onDraw");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width,width);
        Log.d("MyFLoatActionMenu", "onMeasure");
    }

    class Posi{
        int l,t,r,b;
        Posi(int l,int t,int r,int b){
            this.l = l;
            this.t = t;
            this.r = r;
            this.b = b;
        }
    }
    private int touchPosi(MotionEvent event){
        for(int i=0;i<posis.length;i++){
            if(touch(event,posis[i]))return i;
        }
        return 100;
    }

    private boolean touch(MotionEvent event,Posi posi){
        if(event.getX()>posi.l&&event.getX()<posi.r)
        {
            if (event.getY()>posi.t&&event.getY()<posi.b)
                return true;
            else return false;
        }
        return false;
    }


    private void initPosiData(int i){
        switch (i){
            case LEFT_TOP:
                posis = new Posi[]{
                        new Posi(width * 3 / 4, 0, width, width / 4),
                        new Posi((int)(width*0.65f),(int)(width*3/8),(int)(width*0.9f),(int)(width*5/8)),
                        new Posi((int)(width*3/8),(int)(width*0.65f),(int)(width*5/8),(int)(width*0.9f)),
                        new Posi(0,width*3/4,width/4,width),
                        new Posi(0,0,width/3,width/3),
//                new Posi(0,width*3/4,width/4,width)
                };
                iconPosi = new int[]{5,5, width / 3 + 5, width / 3 + 5};
                break;
            case LEFT_BOTTOM:
                posis = new Posi[]{
                        new Posi(0, 0, width/4, width/4),
                        new Posi((int)(width*3/8),(int)(width*0.10f),(int)(width*5/8),(int)(width*0.35f)),
                        new Posi((int)(width*0.65f),(int)(width*3/8),(int)(width*0.9),(int)(width*5/8)),
                        new Posi(width*3/4,width*3/4,width,width ),
                        new Posi(0,width*2/3,width/3,width),
//                new Posi(0,width*3/4,width/4,width)
                };
                iconPosi = new int[]{5,width*2 / 3-5, width / 3 + 5, width  - 5};
                break;
            case RIGHT_TOP:
                posis = new Posi[]{
                        new Posi(0, 0, width/4, width / 4),
                        new Posi((int)(width*0.1f),(int)(width*3/8),(int)(width*0.35f),(int)(width*5/8)),
                        new Posi((int)(width*3/8),(int)(width*0.65f),(int)(width*5/8),(int)(width*0.9f)),
                        new Posi(width * 3 / 4,width*3/4,width,width),
                        new Posi(width*2/3,0,width,width/3),
//                new Posi(0,width*3/4,width/4,width)
                };
                iconPosi = new int[]{width*2/3-5,5, width -5, width / 3 + 5};
                break;
            case RIGHT_BOTTOM:
                posis = new Posi[]{
                        new Posi(width*3/4, 0, width, width/4),
                        new Posi((int)(width*3/8),(int)(width*0.1),(int)(width*5/8),(int)(width*0.35)),
                        new Posi((int)(width*0.1f),(int)(width*3/8f),(int)(width*0.35f),(int)(width*5/8)),
                        new Posi(0,(int)(width*3/4),width/4,(int)(width)),
                        new Posi(width*2/3,width*2/3,width,width ),
//                new Posi(0,width*3/4,width/4,width)
                };
                iconPosi = new int[]{width*2/3-5,width*2/3-5,width-5,width-5 };
                break;
        }
    }


//    private MyFloatActionButton vx;
    private IconClickListener iconClickListener;
    public void setOnIconClickListener(final IconClickListener iconClickListener){
        this.iconClickListener = iconClickListener;

        for(int i=0;i<getChildCount();i++){
            MyFloatActionButton vx = (MyFloatActionButton) getChildAt(i);
            vx.setTag(i);
            vx.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    iconClickListener.IconCLick((MyFloatActionButton)v,(int)v.getTag());
                }
            });
        }
    }


}
