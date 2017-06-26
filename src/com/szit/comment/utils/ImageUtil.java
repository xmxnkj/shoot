package com.szit.comment.utils;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import com.hsit.common.kfbase.service.BaseSetting;

public class ImageUtil {
	public static void resizeImage(String filePath, int width, String descPath) throws Exception{  
		//filePath：原图路径，width：宽度比例，descPath：目标图片路径
		
	    File f = new File(filePath);   
	      
	    BufferedImage bi = ImageIO.read(f);  
	    
	    if (bi.getWidth()<=width) {//如果原图宽度小于目标宽度
	    	
	    	fileChannelCopy(f, new File(descPath));
			return;
		}
	    
	    double wRatio = (new Integer(width)).doubleValue() / bi.getWidth(); //宽度的比例  
	      
	    int height = (int)(wRatio * bi.getHeight());  //图片转换后的高度  
	      
	    Image image = bi.getScaledInstance(width,height,Image.SCALE_SMOOTH); //设置图像的缩放大小  
	      
	    AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(wRatio,wRatio),null);   //设置图像的缩放比例  
	      
	    image = op.filter(bi,null);  
	      
	    File zoomFile = new File(descPath);  
	      
	    try{  
	    	ImageIO.write((BufferedImage)image, "jpg", zoomFile);   
	    }  
	    catch (Exception e)   {  
	    	e.printStackTrace();  
	    }   
	}  
	
	
	public static void fileChannelCopy(File s, File t) {

        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	
	public static String getSmallFilePath(String fileId){
		return new StringBuilder(BaseSetting.getFileBasePath()).append(File.separator).append("small").append(File.separator).append(fileId).toString();
	}
	
	public static String getMiddleFilePath(String fileId){
		return new StringBuilder(BaseSetting.getFileBasePath()).append(File.separator).append("middle").append(File.separator).append(fileId).toString();
	}
}
