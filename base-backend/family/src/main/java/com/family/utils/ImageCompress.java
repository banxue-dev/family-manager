package com.family.utils;



import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.io.FileUtil;
import net.coobird.thumbnailator.Thumbnails;

/**
 * 图片压缩
 * @author tWX932595
 *
 */

public class ImageCompress {
    /**
     * 指定图片宽度和高度和压缩比例对图片进行压缩
     * 
     * @param imgsrc
     *            源图片流
     * @param imgdist
     *            目标图片地址
     * @param widthdist
     *            压缩后图片的宽度
     * @param heightdist
     *            压缩后图片的高度
     * @param rate
     *            压缩的比例
     */

	private static Logger logger = LoggerFactory.getLogger(ImageCompress.class);
    public static void reduceImg(InputStream is,String imgdist,String hz, int widthdist, int heightdist, Float rate) {
        try {
            // 如果比例不为空则说明是按比例压缩
            if (rate != null && rate > 0) {
                //获得源图片的宽高存入数组中
                int[] results = getImgWidthHeight(is);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return ;
                } else {
                    //按比例缩放或扩大图片大小，将浮点型转为整型
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
            }
            // 开始读取文件并进行压缩
            Image src = ImageIO.read(is);

            // 构造一个类型为预定义图像类型之一的 BufferedImage
            BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);

            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

            //创建文件输出流
            FileOutputStream out = new FileOutputStream(imgdist);
            //将图片按JPEG压缩，保存到out中
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
            //关闭文件输出流
            ImageIO.write(tag,hz,out);
            out.close();

        } catch (Exception ef) {
            ef.printStackTrace();
            logger.error("压缩图片出错"+ef);
        }
    }
    /**
     * 写入压缩图片
     * @param absolutePath
     * @param file
     */
    public static void WriteComparessImg(String absolutePath,MultipartFile file,Float scale) {
    	//记录原MultipartFile，如果压缩异常就用原来的MultipartFile
        MultipartFile oldMultipartFile = file;
        try {
//            Thumbnails.of(file.getInputStream()).scale(0.25f).outputQuality(0.99f).toFile(absolutePath);
            Thumbnails.of(file.getInputStream()).size(120, 60).keepAspectRatio(false).toFile(absolutePath);
        } catch (IOException e) {
            logger.error("压缩图片失败{}", e);
            file = oldMultipartFile;
        }
		//上传图片压缩end
    }

    
    /**
     * 获取图片宽度和高度
     * 
     * @param 图片路径
     * @return 返回图片的宽度
     */
    public static int[] getImgWidthHeight(InputStream is) {
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            // 从流里将图片写入缓冲图片区
            src = ImageIO.read(is);
            result[0] =src.getWidth(null); // 得到源图片宽
            result[1] =src.getHeight(null);// 得到源图片高
            is.close();  //关闭输入流
        } catch (Exception ef) {
            ef.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        
        File srcfile = new File("d://bb//3.jpg"); 
        File distfile = new File("d://bb//31.jpg");
        
        System.out.println("压缩前图片大小：" + srcfile.length());
       // reduceImg("d://bb//3.jpg", "d://bb//31.jpg", 500, 500, null);
        System.out.println("压缩后图片大小：" + distfile.length());

    }
}
