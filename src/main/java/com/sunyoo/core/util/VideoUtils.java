package com.sunyoo.core.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

public class VideoUtils {

    /**
     * 获取指定视频的帧并保存为图片至指定目录
     *
     * @param videoFile 源视频文件路径
     * @param frameFile 截取帧的图片存放路径
     * @throws Exception
     * @throws IOException
     */
    public static void fetchFrame(String videoFile, String frameFile)
            throws Exception, IOException {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videoFile);
        File destFile = new File(frameFile);
        if (!destFile.getParentFile().exists()) {
            // 如果目录不存在，则创建目录
            destFile.getParentFile().mkdirs();
        }
        doFetchFrame(ff, frameFile);
    }

    /**
     * 获取指定视频的帧并保存为图片至指定目录
     *
     * @param fileInputStream 源视频文件路径
     * @param frameFile 截取帧的图片存放路径
     * @throws Exception
     * @throws IOException
     */
    public static void fetchFrameInputStream(InputStream fileInputStream, String frameFile)
            throws Exception, IOException {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(fileInputStream);
        File destFile = new File(frameFile);
        if (!destFile.getParentFile().exists()) {
            // 如果目录不存在，则创建目录
            destFile.getParentFile().mkdirs();
        }
        doFetchFrame(ff, frameFile);
    }


    public static void doFetchFrame(FFmpegFrameGrabber ff, String frameFile) throws Exception, IOException  {
        ff.start();
        ff.getAudioChannels();
        String rotate = ff.getVideoMetadata("rotate");//视频的旋转角度
        int length = ff.getLengthInFrames();
        int i = 0;
        Frame f = null;
        while (i < length) {
            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
            f = ff.grabFrame();
            if ((i > 5) && (f.image != null)) {
                break;
            }
            IplImage src = null;
            if (null != rotate && rotate.length() > 1) {
                OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
                src = converter.convert(f);
                f = converter.convert(rotate(src, Integer.valueOf(rotate)));
            }
            doExecuteFrame(f, frameFile);
            i++;
        }
    }


    public static IplImage rotate(IplImage src, int angle) {
        IplImage img = IplImage.create(src.height(), src.width(), src.depth(), src.nChannels());
        opencv_core.cvTranspose(src, img);
        opencv_core.cvFlip(img, img, angle);
        return img;
    }


    public static void doExecuteFrame(Frame f, String targetFileName) {
        if (null == f || null == f.image) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi = converter.getBufferedImage(f);
        File output = new File(targetFileName);
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        try {
//            VideoUtils.fetchFrame("http://192.168.1.166:9080/editor/ueditor/video/20190418/1555556812830034487.wmv", "D:/apache-tomcat-7.0.70/webapps/editor/test4.jpg");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
