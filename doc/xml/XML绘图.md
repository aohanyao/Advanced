#XML绘图

##一：shape

	``` xml

	<?xml version="1.0" encoding="utf-8"?>

	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	android:shape="rectangle|oval|ring|line"><!--形状-->

	<solid /><!--背景颜色-->
	<corners /><!--角度，圆角-->
	<gradient /><!--线性变化-->
	<size /><!--大小-->
	<stroke/><!--边框-->
	</shape>
	```

###rectangle
####圆角矩形
图片

![](http://obh9jd33g.bkt.clouddn.com/20160806094622.png)

xml

	```xml
	<?xml version="1.0" encoding="utf-8"?>
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
	    android:shape="rectangle">
	    <corners android:radius="8dp" />
	    <padding
	        android:bottom="7dp"
	        android:left="7dp"
	        android:right="7dp"
	        android:top="7dp" />
	    <solid android:color="#f60" />
	</shape>

注
	使用bottomLeftRadius等属性，可以做出不同的效果，如左两边圆角，右两边不圆角等。

![](http://obh9jd33g.bkt.clouddn.com/20160806100626.png)  ![](http://obh9jd33g.bkt.clouddn.com/20160806100746.png)

####圆角矩形-边框
图片

![](http://obh9jd33g.bkt.clouddn.com/20160806101759.png)

	``` xml 
	<?xml version="1.0" encoding="utf-8"?>
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/colorPrimaryDark" />
    <corners android:radius="10dp" />
    <stroke
        android:width="10dp"
        android:color="#f60" />
	</shape>
	
注
	虚线：

	<stroke
        android:width="10dp"
        android:dashGap="3dp"<!--虚线间隔-->
        android:dashWidth="13dp"<!--虚线宽度-->
        android:color="#f60" />

图

![](http://obh9jd33g.bkt.clouddn.com/20160806102143.png)
###oval
####实心圆
图片

![](http://obh9jd33g.bkt.clouddn.com/20160806095710.png)

xml

	```xml 
	<?xml version="1.0" encoding="utf-8"?>
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval"
    android:useLevel="false"
    >

    <solid android:color="#f60" />
    <size
        android:width="50dp"
        android:height="50dp" />

	</shape>

####实心圆-边框
图片

![](http://obh9jd33g.bkt.clouddn.com/20160806101127.png)

xml

	```xml
	<?xml version="1.0" encoding="utf-8"?>
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval"
    android:useLevel="false">
    <solid android:color="#03a9f4" />
    <size
        android:width="20dp"
        android:height="20dp" />
    <stroke
        android:width="5dp"
        android:color="#f60" />
</shape>	
注：
	虚线等与矩形相同
###ring
图片

![](http://obh9jd33g.bkt.clouddn.com/20160806100007.png)

xml

	```xml
	<?xml version="1.0" encoding="utf-8"?>
	<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="ring"
    android:useLevel="false"
    android:innerRadiusRatio="4"
    android:thicknessRatio="8">
    <solid android:color="#f00" />

    <size
        android:width="100dp"
        android:height="100dp" />
	</shape>



