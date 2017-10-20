package com.barcode;

import java.io.File;  
import java.util.HashMap;  

import org.apache.commons.lang.StringUtils;
  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;  
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
  
public class GenerateBarcodeUtil {  
	
    public static void main(String[] args) throws Exception {  
    	
//        int width = 300;  
//        int height = 300;  
         int width = 200;  
         int height = 200;  
        // 条形码的输入是13位的数字  
         String text = "F003-00120170504121234";
        // 二维码的输入是字符串  
//        String text = "testtesttest生成条形码图片";  
        String format = "png";  
        HashMap<EncodeHintType, String> hints = new HashMap<>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        // 条形码的格式是 BarcodeFormat.EAN_13  
        // 二维码的格式是BarcodeFormat.QR_CODE  
        BitMatrix bm = new MultiFormatWriter().encode(text,  
                BarcodeFormat.CODE_128, width, height, hints);  
//        File out = new File("E:\\new.png");  
        // 生成条形码图片  
         File out = new File("E:\\ean3.png");  
  
        WriteBitMatricToFile.writeBitMatricToFile(bm, format, out);  
        
        System.out.println("=============End===============");
    }
    
    /**
     * 功能 : 生成二维码图片
     * 开发：zwwang 2017-10-18 下午4:55:07
     * @param args
     * @param filePath
     * @param format
     * @return
     * @throws WriterException
     */
    public static File generateQRCodeByString(String args, String filePath, String fileName, String format) throws WriterException {
    	
    	if (StringUtils.isBlank(args)) {
    		
    		return null;
    	}
    	
    	if (StringUtils.isBlank(filePath)) {
    		
    		return null;
    	}
    	
    	if (StringUtils.isBlank(fileName)) {
    		
    		return null;
    	}
    	
    	if (StringUtils.isBlank(format)) {
    		
    		format = "PNG";
    	}
    	
    	int width = 220;  
        int height = 220;  
    	
    	HashMap<EncodeHintType, String> hints = new HashMap<>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        BitMatrix bm = new MultiFormatWriter().encode(args, BarcodeFormat.QR_CODE, width, height, hints);  
        //生成二维码图片  
        File out = new File(filePath + File.separator + fileName + "." + format);
        if (!out.getParentFile().exists()) {
        	
        	out.getParentFile().mkdirs();
        }
    	
    	WriteBitMatricToFile.writeBitMatricToFile(bm, format, out);
    	
    	return out;
    }
    
    /**
     * 功能 : 生成条形码图片
     * 开发：zwwang 2017-10-18 下午4:57:30
     * @param args
     * @param filePath
     * @param fileName
     * @param format
     * @return
     * @throws WriterException
     */
    public static File generateBarcodeByString(String args, String filePath, String fileName, String format) throws WriterException {
    	
    	if (StringUtils.isBlank(args)) {
    		
    		return null;
    	}
    	
    	if (StringUtils.isBlank(filePath)) {
    		
    		return null;
    	}
    	
    	if (StringUtils.isBlank(fileName)) {
    		
    		return null;
    	}
    	
    	if (StringUtils.isBlank(format)) {
    		
    		format = "PNG";
    	}
    	
    	int width = 10;  
        int height = 100;  
    	
    	HashMap<EncodeHintType, String> hints = new HashMap<>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        BitMatrix bm = new MultiFormatWriter().encode(args, BarcodeFormat.CODE_128, width, height, hints);  
        //生成条形码图片  
        File out = new File(filePath + File.separator + fileName + "." + format);
        if (!out.getParentFile().exists()) {
        	
        	out.getParentFile().mkdirs();
        }
    	
    	WriteBitMatricToFile.writeBitMatricToFile(bm, format, out);
    	
    	return out;
    }
  
}  