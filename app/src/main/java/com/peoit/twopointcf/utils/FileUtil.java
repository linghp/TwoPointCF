package com.peoit.twopointcf.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;

/**
 * Created by ling on 2015/8/25.
 * description:一些文件的本地存储。
 */
public class FileUtil {
    /** 默认下载文件地址. */
    public static  String DOWNLOAD_ROOT_DIR = "download";

    /** 默认下载图片文件地址. */
    public static  String DOWNLOAD_IMAGE_DIR = "images";

    /**
     * 默认APP根目录.
     */
    private static String downloadRootDir = null;

    /**
     * 默认下载图片文件目录.
     */
    private static String imageDownloadDir = null;

    /**
     * 描述：初始化存储目录.
     *
     * @param context the context
     */
    public static void initFileDir(Context context) {

        PackageInfo info = getPackageInfo(context);

        //默认下载文件根目录.
        String downloadRootPath = File.separator + DOWNLOAD_ROOT_DIR + File.separator + info.packageName + File.separator;

        //默认下载图片文件目录.
        String imageDownloadPath = downloadRootPath + DOWNLOAD_IMAGE_DIR + File.separator;

//        //默认下载文件目录.
//        String fileDownloadPath = downloadRootPath + AbAppConfig.DOWNLOAD_FILE_DIR + File.separator;


        try {
            if (!isCanUseSD()) {
                MyLogger.e("SD卡不能用");
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
                imageDownloadDir = imageDownloadDirFile.getPath()+File.separator;

//                File fileDownloadDirFile = new File(root.getAbsolutePath() + fileDownloadPath);
//                if (!fileDownloadDirFile.exists()) {
//                    fileDownloadDirFile.mkdirs();
//                }
//                fileDownloadDir = fileDownloadDirFile.getPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * 获取包信息.
     *
     * @param context the context
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            String packageName = context.getPackageName();
            info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 删除所有缓存文件.
     *
     * @return true, if successful
     */
    public static boolean clearDownloadFile() {
        try {
            File fileDirectory = new File(downloadRootDir);
            deleteFile(fileDirectory);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除文件.
     *
     * @return true, if successful
     */
    public static boolean deleteFile(File file) {

        try {
            if(!isCanUseSD()){
                return false;
            }
            if (file == null) {
                return true;
            }
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }else{
                file.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Gets the image download dir.
     *
     * @param context the context
     * @return the image download dir
     */
    public static String getImageDownloadDir(Context context) {
        if(downloadRootDir == null){
            initFileDir(context);
        }
        return imageDownloadDir;
    }

    /**
     * 描述：获取src中的图片资源.
     *
     * @param src 图片的src路径，如（“image/arrow.png”）
     * @return Bitmap 图片
     */
    public static Bitmap getBitmapFromSrc(String src){
        Bitmap bit = null;
        try {
            bit = BitmapFactory.decodeStream(FileUtil.class.getResourceAsStream(src));
        } catch (Exception e) {
            MyLogger.e("获取图片异常："+e.getMessage());
        }
        return bit;
    }}
