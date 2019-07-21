package com.wkk.demo.imagehandle;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;


/**
 * @Description 图片处理
 * @Author wkk
 * @Date 2019-04-27 9:34
 **/
public class ImageUtils {

    public static void main(String[] args) throws Exception {
        Thread.sleep(15000);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread() {
                @Override
                public void run() {
                    Long start = System.currentTimeMillis();
                    try {
                        test4("C:\\Users\\Administrator\\Desktop\\王坤坤专属\\image\\test\\IMG_4919-" + finalI + ".tif",
                                "C:\\Users\\Administrator\\Desktop\\王坤坤专属\\image\\test\\IMG_test4-" + finalI + ".tif",
                                800, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Long end = System.currentTimeMillis();
                        System.out.println("第" + finalI + "张图片处理完成，耗时：" + (end - start));
                    }
                }
            }.start();
        }
    }

    /**
     * @param strPath
     * @param targetPath
     * @param width
     * @param height
     * @throws IOException
     * @description thumbnailator方式缩放图片，同时指定高和宽只会等比例缩放
     */
    public static void test1(String strPath, String targetPath, Integer width, Integer height) throws IOException {
        Thumbnails.Builder<File> of = Thumbnails.of(strPath);
        if (width != null) {
            of.width(width);
        }
        if (height != null) {
            of.height(height);
        }
        of.toFile(targetPath);
    }


    /**
     * @param strPath
     * @param targetPath
     * @param width
     * @param height
     * @description jdk api方式1
     */
    private static void test3(String strPath, String targetPath, Integer width, Integer height) throws Exception {
        OutputStream out = null;
        BufferedImage read = null;
        try {
            // 读到缓存中占空间
            read = ImageIO.read(new File(strPath));
            int imageWidth = read.getWidth();
            int imageHeight = read.getHeight();
            double imageRatio = (double) imageWidth / (double) imageHeight;
            int thumbHeight = 0;
            if (width != null) {
                thumbHeight = (int) (width / imageRatio);
            }
            int thumbWidth = 0;
            if (height != null) {
                thumbWidth = (int) (height * imageRatio);
            }
            Image scaledInstance = null;
            BufferedImage newImage = null;
            if (width != null) {
                scaledInstance = read.getScaledInstance(width, thumbHeight, Image.SCALE_DEFAULT);
                newImage = new BufferedImage(width, thumbHeight, BufferedImage.TYPE_INT_RGB);
            } else {
                scaledInstance = read.getScaledInstance(thumbWidth, height, Image.SCALE_DEFAULT);
                newImage = new BufferedImage(thumbWidth, height, BufferedImage.TYPE_INT_RGB);
            }

            if (height != null && width != null && thumbHeight > height) {
                scaledInstance = read.getScaledInstance(thumbWidth, height, Image.SCALE_DEFAULT);
                newImage = new BufferedImage(thumbWidth, height, BufferedImage.TYPE_INT_RGB);
            }
            Graphics2D graphics = newImage.createGraphics();
            graphics.drawImage(scaledInstance, 0, 0, null);
            graphics.dispose();
            out = new FileOutputStream(targetPath);
            String prefix = strPath.substring(strPath.lastIndexOf(".") + 1);
            ImageIO.write(newImage, prefix, out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    /**
     * @param strPath
     * @param targetPath
     * @param width
     * @param height
     * @description jdk api方式2
     */
    private static void test4(String strPath, String targetPath, Integer width, Integer height) throws Exception {
        OutputStream out = null;
        try {
            /*
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
             * 参数：formatName - 包含非正式格式名称 . （例如 "jpeg" 或 "tiff"）等 。
             */
            String substring = strPath.substring(strPath.lastIndexOf(".") + 1);
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(substring);
            ImageReader reader = readers.next();
            // 获取图片流
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(new File(strPath));
            reader.setInput(imageInputStream);
            int imageWidth = reader.getWidth(0);
            int imageHeight = reader.getHeight(0);
            double imageRatio = (double) imageWidth / (double) imageHeight;
            int thumbHeight = 0;
            if (width != null) {
                thumbHeight = (int) (width / imageRatio);
            }
            int thumbWidth = 0;
            if (height != null) {
                thumbWidth = (int) (height * imageRatio);
            }
            BufferedImage source = reader.read(0);
            BufferedImage tag = null;
            if (width != null && height == null) {
                tag = new BufferedImage(width, thumbHeight, BufferedImage.TYPE_3BYTE_BGR);
                tag.getGraphics().drawImage(source, 0, 0, width, thumbHeight, null);
            } else if (width == null && height != null) {
                tag = new BufferedImage(thumbWidth, height, BufferedImage.TYPE_3BYTE_BGR);
                tag.getGraphics().drawImage(source, 0, 0, thumbWidth, height, null);
            } else if (width != null && height != null) {
                if (thumbHeight > height) {
                    tag = new BufferedImage(thumbWidth, height, BufferedImage.TYPE_3BYTE_BGR);
                    tag.getGraphics().drawImage(source, 0, 0, thumbWidth, height, null);
                } else {
                    tag = new BufferedImage(width, thumbHeight, BufferedImage.TYPE_3BYTE_BGR);
                    tag.getGraphics().drawImage(source, 0, 0, width, thumbHeight, null);
                }
            }
            out = new FileOutputStream(targetPath);
            ImageIO.write(tag, substring, out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
