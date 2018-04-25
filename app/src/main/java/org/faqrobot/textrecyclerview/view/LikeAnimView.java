package org.faqrobot.textrecyclerview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import org.faqrobot.textrecyclerview.R;

/**
 * Created by zhubing on 2017/10/30.
 */

public class LikeAnimView extends View {
    public interface a {
    }

    private static float A;
    private ValueAnimator B;
    private ValueAnimator C;
    private ValueAnimator D;
    private ValueAnimator E;
    private boolean F;
    private ValueAnimator G;
    Paint a;
    int b;
    int c =1;
    int d;
    int e;
    int f;
    Rect g;
    Rect h;
    Rect i;
    Rect j;
    Rect k;
    Rect l;
    Rect m;
    Rect n;
    Handler o;
    int p;
    Paint q;
    Drawable r;
    a s;
    private static int t;
    private static int u;
    private static int v;
    private static int w;
    private static int x;
    private static int y;
    private static int z;

    static {
        LikeAnimView.t = 400;
        LikeAnimView.u = 400;
        LikeAnimView.v = LikeAnimView.t + LikeAnimView.u * 8 / 9;
        LikeAnimView.w = 400;
        LikeAnimView.x = LikeAnimView.t;
        LikeAnimView.y = LikeAnimView.t;
        LikeAnimView.z = LikeAnimView.t;
        LikeAnimView.A = 0.666667f;
    }

    public LikeAnimView(Context arg3) {
        super(arg3);
        this.c = 1;
        this.g = new Rect();
        this.h = new Rect();
        this.i = new Rect();
        this.j = new Rect();
        this.k = new Rect();
        this.l = new Rect();
        this.m = new Rect();
        this.n = new Rect();
        this.o = new Handler();
        this.F = false;
        this.c();
//        if(Boolean.FALSE.booleanValue()) {
//            System.out.println(Hack.class);
//        }
    }

    public LikeAnimView(Context arg2, AttributeSet arg3) {
        super(arg2, arg3);
        this.c = 1;
        this.g = new Rect();
        this.h = new Rect();
        this.i = new Rect();
        this.j = new Rect();
        this.k = new Rect();
        this.l = new Rect();
        this.m = new Rect();
        this.n = new Rect();
        this.o = new Handler();
        this.F = false;
        this.c();
    }

    public LikeAnimView(Context arg2, AttributeSet arg3, int arg4) {
        super(arg2, arg3, arg4);
        this.c = 1;
        this.g = new Rect();
        this.h = new Rect();
        this.i = new Rect();
        this.j = new Rect();
        this.k = new Rect();
        this.l = new Rect();
        this.m = new Rect();
        this.n = new Rect();
        this.o = new Handler();
        this.F = false;
        this.c();
    }

    public boolean a() {
        boolean v2 = false;
        String v3 = "LikeAnimView";
        StringBuilder v4 = new StringBuilder().append("isAnimating: (alphaAnim != null && alphaAnim.isRunning() = ");
        boolean v0 = this.G == null || !this.G.isRunning() ? false : true;
        Log.e(v3, v4.append(v0).toString());
        v3 = "LikeAnimView";
        v4 = new StringBuilder().append("isAnimating: (alphaAnim != null && alphaAnim.isRunning() = ");
        v0 = this.C == null || !this.C.isRunning() ? false : true;
        Log.e(v3, v4.append(v0).toString());
        v3 = "LikeAnimView";
        v4 = new StringBuilder().append("isAnimating: (strokeDecreaseAnim != null && strokeDecreaseAnim.isRunning())  = ");
        v0 = this.D == null || !this.D.isRunning() ? false : true;
        Log.e(v3, v4.append(v0).toString());
        v3 = "LikeAnimView";
        v4 = new StringBuilder().append("isAnimating: (likeDrawableAnim != null && likeDrawableAnim.isRunning()) = ");
        v0 = this.E == null || !this.E.isRunning() ? false : true;
        Log.e(v3, v4.append(v0).toString());
        if(this.G == null || !this.G.isRunning()) {
            if((this.C == null || !this.C.isRunning()) && (this.D == null || !this.D.isRunning()) && (this.E == null || !this.E.isRunning()) && !this.F) {
                return v2;
            }

//            label_80:
            v2 = true;
        }
        else {
            v2 = true;
        }

        return v2;
    }

    static boolean a(LikeAnimView arg0, boolean arg1) {
        arg0.F = arg1;
        return arg1;
    }

    public void a(int arg12) {
        int v10 = 2;
        int v1 = this.f / 6;
        int v2 = this.f / 2 - this.f / 6;
        int v0 = this.f / 2;
        int v3 = -this.f / 16;
        this.q.setAlpha(255);
        this.F = true;
        int[] v4 = new int[v10];
        v4[0] = v0;
        v4[1] = v1;
        ValueAnimator v4_1 = ValueAnimator.ofInt(v4);
        v4_1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator arg4) {
                int v0 = (int)arg4.getAnimatedValue();
                if(v0 == 0) {
                    LikeAnimView.this.g.top = v0;
                }
                else if(v0 == 1) {
                    LikeAnimView.this.i.left = v0;
                }
                else if(v0 == 2) {
                    LikeAnimView.this.k.right = f - v0;
                }
                else if(v0 == 3) {
                    LikeAnimView.this.m.bottom = f - v0;
                }

                LikeAnimView.this.postInvalidate();
            }
        });
        int[] v5 = new int[v10];
        v5[0] = v0;
        v5[1] = v2;
        ValueAnimator v5_1 = ValueAnimator.ofInt(v5);
        v5_1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator arg4) {
                int v0 = (int) arg4.getAnimatedValue();
                if(v0 == 0) {
                    g.bottom = v0;
                }
                else if(v0 == 1) {
                    i.right = v0;
                }
                else if(v0 == 2) {
                    k.left = f - v0;
                }
                else if(v0 == 3) {
                    m.top = f - v0;
                }

                postInvalidate();
            }
        });
        int[] v0_1 = new int[v10];
        v0_1[0] = 0;
        v0_1[1] = v3;
        ValueAnimator v6 = ValueAnimator.ofInt(v0_1);
        v6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator arg5) {
                int v0 = (int)arg5.getAnimatedValue();
                if(v0 == 0) {
                    g.top = b + v0;
                    g.bottom = v0 + c;
                }
                else if(v0 == 1) {
                    i.left = b + v0;
                    i.right = v0 + c;
                }
                else if(v0 == 2) {
                    k.right = f - (b + v0);
                    k.left = f - (v0 + c);
                }
                else if(v0 == 3) {
                    m.top = f - (c + v0);
                    m.bottom = f - (v0 + b);
                }

                postInvalidate();
            }
        });
        ValueAnimator v0_2 = null;
        if(arg12 == 0) {
            v0_1 = new int[v10];
            v0_1[0] = v2 + v3;
            v0_1[1] = v1;
            v0_2 = ValueAnimator.ofInt(v0_1);
        }
        else if(arg12 == 1) {
            v0_1 = new int[v10];
            v0_1[0] = v2 + v3;
            v0_1[1] = v1;
            v0_2 = ValueAnimator.ofInt(v0_1);
        }
        else if(arg12 == v10) {
            v0_1 = new int[v10];
            v0_1[0] = this.f - (v2 + v3);
            v0_1[1] = this.f - v1;
            v0_2 = ValueAnimator.ofInt(v0_1);
        }
        else if(arg12 == 3) {
            v0_1 = new int[v10];
            v0_1[0] = this.f - (v2 + v3);
            v0_1[1] = this.f - v1;
            v0_2 = ValueAnimator.ofInt(v0_1);
        }

        v0_2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator arg4) {
                int v0 = (int)arg4.getAnimatedValue();
                if(v0 == 0) {
                    g.bottom = v0;
                }
                else if(v0 == 1) {
                    i.right = v0;
                }
                else if(v0 == 2) {
                    k.left = v0;
                }
                else if(v0 == 3) {
                    m.top = v0;
                }

                postInvalidate();
            }
        });
        AnimatorSet v1_1 = new AnimatorSet();
        Animator[] v2_1 = new Animator[v10];
        v2_1[0] = v4_1;
        v2_1[1] = v5_1;
        v1_1.playTogether(v2_1);
        v1_1.setInterpolator(new AccelerateInterpolator());
        v1_1.setDuration(((long)LikeAnimView.x));
        v1_1.start();
        v1_1.setInterpolator(new AccelerateInterpolator());
        v6.setDuration(100);
        v6.setStartDelay(((long)LikeAnimView.x));
        v6.start();
        v0_2.setStartDelay(((long)(LikeAnimView.x + 100)));
        v0_2.setDuration(((long)LikeAnimView.y));
        v0_2.start();
        v0_2.setInterpolator(new AccelerateInterpolator());
    }

    public void b() {
        int v6 = 2;
        if(this.a()) {
            Log.e("LikeAnimView", "startAnim: isAnimating = ");
        }
        else {
            this.setAlpha(1f);
            this.setVisibility(VISIBLE);
            this.c = 1;
            this.b = 0;
            this.r.setBounds(0, 0, 0, 0);
            this.g.set(this.h);
            this.i.set(this.j);
            this.k.set(this.l);
            this.m.set(this.n);
            this.postInvalidate();
            int v0 = ((int)((((float)this.f)) * LikeAnimView.A));
            int[] v1 = new int[v6];
            v1[0] = 0;
            v1[1] = v0;
            this.B = ValueAnimator.ofInt(v1);
            this.B.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator arg3) {
                    b = (int)arg3.getAnimatedValue();
                    postInvalidate();
                }
            });
            v1 = new int[v6];
            v1[0] = 1;
            v1[1] = this.f / 2;
            this.C = ValueAnimator.ofInt(v1);
            this.C.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator arg3) {
                    c = (int)arg3.getAnimatedValue();
                    postInvalidate();
                }
            });
            this.C.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg3) {
                    super.onAnimationEnd(arg3);
                    c = 1;
                    b = 0;
                    postInvalidate();
                }
            });
            v1 = new int[v6];
            v1[0] = v0;
            v1[1] = 0;
            this.D = ValueAnimator.ofInt(v1);
            this.D.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator arg4) {
                    b = (int)arg4.getAnimatedValue();
                    if(c + b / 2 >= f / 2) {
                        b = (f / 2 - c) * 2;
                    }

                    postInvalidate();
                }
            });
            this.B.setDuration(((long)LikeAnimView.t));
            this.B.setInterpolator(new AccelerateInterpolator());
            this.C.setDuration(((long)LikeAnimView.t));
            this.C.setStartDelay(((long)LikeAnimView.u));
            this.C.setInterpolator(new DecelerateInterpolator());
            this.D.setDuration(((long)LikeAnimView.t));
            this.D.setStartDelay(((long)LikeAnimView.u));
            this.D.setInterpolator(new DecelerateInterpolator());
            this.B.start();
            this.D.start();
            this.C.start();
            int[] v0_1 = new int[v6];
            v0_1[0] = 0;
            v0_1[1] = this.r.getIntrinsicWidth();
            this.E = ValueAnimator.ofInt(v0_1);
            this.E.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator arg6) {
                    int v0 = (int)arg6.getAnimatedValue();
                    int v1 = (f - v0) / 2;
                    int v3 = (f - v0) / 2;
                    r.setBounds(v1, v3, v1 + v0, v0 + v3);
                    postInvalidate();
                }
            });
            this.E.setInterpolator(new OvershootInterpolator());
            this.E.setDuration(((long)LikeAnimView.w));
            this.E.setStartDelay(((long)LikeAnimView.v));
            this.E.start();
            this.a(0);
            this.a(1);
            this.a(v6);
            this.a(3);
            this.h();
        }
    }

    private void c() {
        this.a = new Paint();
        this.a.setColor(this.getResources().getColor(R.color.like_view_circle_red));
        this.a.setStyle(Paint.Style.STROKE);
        this.r = this.getResources().getDrawable(R.mipmap.like_red);
        this.q = new Paint();
        this.q.setColor(this.getResources().getColor(R.color.like_view_stick_red));
        this.q.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    private void d() {
        this.g.left = (this.f - this.p) / 2;
        this.g.right = this.g.left + this.p;
        this.g.bottom = this.f / 2;
        this.g.top = this.g.bottom;
        this.h.set(this.g);
    }

    private void e() {
        this.i.left = this.f / 2;
        this.i.right = this.f / 2;
        this.i.top = (this.f - this.p) / 2;
        this.i.bottom = this.i.top + this.p;
        this.j.set(this.i);
    }

    private void f() {
        this.k.left = this.f / 2;
        this.k.right = this.f / 2;
        this.k.top = (this.f - this.p) / 2;
        this.k.bottom = this.k.top + this.p;
        this.l.set(this.k);
    }

    private void g() {
        this.m.left = (this.f - this.p) / 2;
        this.m.right = this.m.left + this.p;
        this.m.bottom = this.f / 2;
        this.m.top = this.g.bottom;
        this.n.set(this.m);
    }

    private void h() {
        this.G = ValueAnimator.ofInt(new int[]{255, 0});
        this.G.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator arg3) {
                q.setAlpha((int)arg3.getAnimatedValue());
                postInvalidate();
            }
        });
        this.G.addListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator arg3) {
                super.onAnimationCancel(arg3);
                LikeAnimView.a(LikeAnimView.this, false);
            }

            public void onAnimationEnd(Animator arg5) {
                super.onAnimationEnd(arg5);
                o.postDelayed(new Runnable() {
                    public void run() {
                        LikeAnimView.this.animate().alpha(0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                            public void onAnimationEnd(Animator arg3) {
                                super.onAnimationEnd(arg3);
                                LikeAnimView.a(LikeAnimView.this, false);
                                LikeAnimView.this.setVisibility(INVISIBLE);
                            }
                        }).start();
                    }
                }, 900);
            }
        });
        this.G.setDuration(200);
        this.G.setStartDelay(200);
        this.G.setInterpolator(new DecelerateInterpolator());
        this.G.start();
    }

    protected void onDraw(Canvas arg6) {
        super.onDraw(arg6);
        this.a.setStrokeWidth(((float)this.b));
//        this.a.setStrokeWidth(200);
        arg6.drawCircle(((float)this.d), ((float)this.e), ((float)this.c), this.a);
//        arg6.drawCircle(200, 200, 200, this.a);
        this.r.draw(arg6);
        arg6.drawRoundRect(new RectF(this.g), 30f, 30f, this.q);
        arg6.drawRoundRect(new RectF(this.i), 30f, 30f, this.q);
        arg6.drawRoundRect(new RectF(this.k), 30f, 30f, this.q);
        arg6.drawRoundRect(new RectF(this.m), 30f, 30f, this.q);
    }

    protected void onLayout(boolean arg3, int arg4, int arg5, int arg6, int arg7) {
        super.onLayout(arg3, arg4, arg5, arg6, arg7);
        this.f = Math.min(this.getWidth(), this.getHeight());
        int v0 = this.f / 2;
        this.e = v0;
        this.d = v0;
        this.p = this.f / 24;
        this.d();
        this.e();
        this.f();
        this.g();
    }

    public void setAnimListener(a arg1) {
        this.s = arg1;
    }
}


