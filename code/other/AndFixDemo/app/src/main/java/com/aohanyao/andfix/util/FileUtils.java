package com.aohanyao.andfix.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/1/13.
 */
public class FileUtils {

    /**
     * 默认APP根目录.
     */
    private static String downloadRootDir = null;
    /**
     * 默认下载图片文件目录.
     */
    private static String imageDownloadDir = null;

    /**
     * Gets the image download dir.
     *
     * @param context the context
     * @return the image download dir
     */
    public static String getImageDownloadDir() {
        if (downloadRootDir == null) {
            initFileDir();
        }
        return imageDownloadDir;
    }

    /**
     * 获取内置SD卡路径
     *
     * @return
     */
    public static String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 描述：SD卡是否能用.
     *
     * @return true 可用,false不可用
     */
    public static boolean isCanUseSD() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Gets the file download dir.
     *
     * @return the file download dir
     */
    public static String getFileDownloadDir() {
        if(downloadRootDir == null){
            initFileDir();
        }
        return downloadRootDir;
    }

    /**
     * 描述：初始化存储目录.
     *
     */
    public static void initFileDir() {
//        PackageInfo info = AppUtils.getPackageInfo(context);

        //默认下载文件根目录.
        String downloadRootPath = File.separator + "AndFix" + File.separator + "cache" + File.separator;

        //默认下载图片文件目录.
        String imageDownloadPath = downloadRootPath + "cutImage" + File.separator;
        try {
            if (!isCanUseSD()) {
                return;
            } else {
                File root = Environment.getExternalStorageDirectory();
                File downloadDir = new File(root.getAbsolutePath() + downloadRootPath);
                if (!downloadDir.exists()) {
                    downloadDir.mkdirs();
                }
                downloadRootDir = downloadDir.getPath();


                File imageDownloadDirFile = new File(root.getAbsolutePath() + imageDownloadPath);
                if (!imageDownloadDirFile.exists()) {
                    imageDownloadDirFile.mkdirs();
                }
                imageDownloadDir = imageDownloadDirFile.getPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 描述：获取src中的图片资源.
     *
     * @return Bitmap 图片
     */
    public static Bitmap getBitmapFromSrc(Context mContext, int id) {
        InputStream is = mContext.getResources().openRawResource(id);
        Bitmap bit = null;
        try {
            bit = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
//            L.d("FileUtils", "获取图片异常：" + e.getMessage());
        }
        return bit;
    }

    /**
     * 获取文件夹大小
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(java.io.File file){

        long size = 0;
        try {
            java.io.File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++)
            {
                if (fileList[i].isDirectory())
                {
                    size = size + getFolderSize(fileList[i]);

                }else{
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return size/1048576;
        return size;
    }

    /**
     * 格式化单位
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size/1024;
        if(kiloByte < 1) {
            return size + "Byte(s)";
        }

        double megaByte = kiloByte/1024;
        if(megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte/1024;
        if(gigaByte < 1) {
            BigDecimal result2  = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte/1024;
        if(teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}
