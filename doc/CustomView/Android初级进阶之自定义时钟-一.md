---
title: Android初级进阶之自定义时钟(一)
date: 2016-08-08 16:22:13
tags: Android初级进阶
categories: Android初级进阶
---
#### 前言
一直以为仪表盘，时钟之类的非常的难，不敢去触摸这个领域。真正接触了才发现不过如此。
#### 技术要点
	1. cancvas
			1. Cancvas.save()		保存画布
			2. Cancvas.restore()	合并画布
			3. Cancvas.translate()	切换x,y坐标点
			4. Cancvas.rotate()		旋转画布
	2. 自定义View相关的知识
		1. Measure
#### 目标
	实现静态的时钟UI

#### 效果图
![](http://obh9jd33g.bkt.clouddn.com/colick_img.png)

##### 1.测量
测量View的宽度与高度，因为是画一个圆形的时钟，所以View取得是高度与宽度的最小值，一下是测量方法

```java

    private int measureSize(int spec) {
        int result;
        int size = MeasureSpec.getSize(spec);//获取大小
        int mode = MeasureSpec.getMode(spec);//获取模式
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 100;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mCircleWidth = Math.min(measureSize(heightMeasureSpec), measureSize(widthMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

``` 







##### 2.绘制最外层的圆

```java

	//外部圆
    Paint paintCircle = new Paint();
    paintCircle.setStyle(Paint.Style.STROKE);//圆环
    paintCircle.setAntiAlias(true);//抗锯齿
    paintCircle.setStrokeWidth(5);//圆环宽度
    canvas.drawCircle(mCircleWidth / 2, mCircleWidth / 2, mCircleWidth / 2 - 20/*减去20的原因是中心点留白*/, paintCircle);
``` 

![](http://obh9jd33g.bkt.clouddn.com/%E6%97%B6%E9%92%9F-%E5%A4%96%E5%B1%82%E7%9A%84%E5%9C%86.png)

##### 3.绘制刻度线与刻度值

绘制刻度线与刻度值是最大的难点，在这里就是利用了Canvas为我们提供的rotate方法，顾名思义，就是旋转画布。我们以视图坐标系 x y 进行绘制，没绘制完成一次，旋转相应的角度就完成了刻度线的绘制。玩过PS的人应该好理解。

```java

    //刻度线
    Paint paintDegree = new Paint();
    paintDegree.setStrokeWidth(3);
    for (int i = 0; i < 60; i++) {
        if (i % 5 == 0) {
            paintDegree.setStrokeWidth(5);// 小时
            paintDegree.setTextSize(30);
            canvas.drawLine(mCircleWidth / 2, 20/*绘制外圆减去的值*/, mCircleWidth / 2, 60/*刻度线的长度*/, paintDegree);
            String degree = "12";
            if (i != 0)
                degree = String.valueOf(i / 5);
            canvas.drawText(degree, mCircleWidth / 2 - paintDegree.measureText(degree) / 2, 90/*字体的高度*/, paintDegree);
        } else {
            paintDegree.setStrokeWidth(3);//秒
            paintDegree.setTextSize(15);
            canvas.drawLine(mCircleWidth / 2, 20/*绘制外圆减去的值*/, mCircleWidth / 2, 30/*刻度线的长度*/, paintDegree);
            String degree = String.valueOf(i);
            canvas.drawText(degree, mCircleWidth / 2 - paintDegree.measureText(degree) / 2, 60/*字体的高度*/, paintDegree);
        }
        // 旋转六度，360度/60秒  以时钟的中心点进行旋转
        canvas.rotate(6, mCircleWidth / 2, mCircleWidth / 2);
    }
``` 

![](http://obh9jd33g.bkt.clouddn.com/%E6%97%B6%E9%92%9F-%E5%88%BB%E5%BA%A6%E5%80%BC.png)

##### 4. 绘制时分秒指针
```java

    //画针
    Paint paintHour = new Paint();
    paintHour.setStrokeWidth(20);
    Paint paintMinute = new Paint();
    paintMinute.setStrokeWidth(10);
    Paint paintSecond = new Paint();
    paintSecond.setStrokeWidth(7);
    canvas.save();
    // 将 x y 点移到 中心
    canvas.translate(mCircleWidth / 2, mCircleWidth / 2);
    //float startX, float startY, float stopX, float stopY
    canvas.drawLine(0, 0, 100, 100/*暂时写死*/, paintHour);
    canvas.drawLine(0, 0, 100, 280, paintSecond);
    canvas.drawLine(0, 0, 100, 200, paintMinute);
    //合并 画布
    canvas.restore();
```

![](http://obh9jd33g.bkt.clouddn.com/colick_img.png)

##### PS
Android初级进阶之自定义时钟(一)就到这里结束，原谅我的文学水平，没有过多的文字修饰。

Android初级进阶之自定义时钟(二)：
	
	1. 完成时分秒指针的计算。
	2. 与系统时间进行同步，完成真正的时钟。


[我的简书](http://www.jianshu.com/p/7df63512a1b0)

[我的博客](https://aohanyao.github.io/)