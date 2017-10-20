package com.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.barcode.GenerateBarcodeUtil;
import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class PdfUtils {

	/**
	 * 功能 : pdf文件上添加文字，图片等
	 * 开发：zwwang 2017-10-13 上午11:51:16
	 * @param args
	 * @throws IOException 
	 * @throws DocumentException 
	 * @throws WriterException 
	 */
	public static void main(String[] args) throws IOException, DocumentException, WriterException {
		
//		fileChannelCopy(new File("E:\\A.pdf"),new File("E:\\A" +"tmp.pdf"));
		File file = new File("E:\\B.pdf");
		if (file.exists()) {
			
			file.delete();
		}
		PdfReader reader = new PdfReader("E:\\A" + ".pdf");
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("E:\\B.pdf"));
		//pdf页数
		System.out.println(stamper.getReader().getNumberOfPages());
		//读取第n页的字节内容
		PdfContentByte overContent = stamper.getOverContent(1);
		
		Rectangle mediabox = reader.getPageSize(10);
		System.out.println(mediabox.getWidth());
		System.out.println(mediabox.getHeight());
		System.out.println(mediabox.getLeft());
		
		String idCode = "F003-00120170504121234";

		StringBuffer filePathBuffer = new StringBuffer("E:");
		filePathBuffer.append(File.separator + "barcode");
		
		/**
		 * 添加图片
		 */
		//生成二维码图片
		filePathBuffer.append(File.separator + "barcode");
		String qrCodeImageName = "qrCodeImage";
		String qrCodeImageFormat = "PNG";
		File qrCodeFile = GenerateBarcodeUtil.generateQRCodeByString(idCode, filePathBuffer.toString(), qrCodeImageName, qrCodeImageFormat);
		Image qrCodeImage = Image.getInstance(qrCodeFile.getAbsolutePath());
		//图片的放置的位置  横向坐标 纵向坐标 坐标原点在改页面左下角
		qrCodeImage.setAbsolutePosition(20, 630);
		overContent.addImage(qrCodeImage);
		
		/**
		 * 添加图片
		 */
		//生成条形码图片
		String barcodeImage = "barcodeImg";
		String format = "PNG";
		File barcodeFile = GenerateBarcodeUtil.generateBarcodeByString(idCode, filePathBuffer.toString(), barcodeImage, format);
		/*PdfDictionary pdfDictionary = reader.getPageN(1);
		PdfObject pdfObject = pdfDictionary.get(new PdfName("MediaBox"));
		PdfArray pdfArray = (PdfArray) pdfObject;*/
		Image image = Image.getInstance(barcodeFile.getAbsolutePath());
		//图片的放置的位置  横向坐标 纵向坐标 坐标原点在改页面左下角
		image.setAbsolutePosition(20, 520);
		overContent.addImage(image);
		
		/**
		 * 添加文字
		 */
		//设置字体
		BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		overContent.beginText();
		//设置字体大小
		overContent.setFontAndSize(font, 20);
//		overContent.setTextMatrix(200, 200);
		//设置文字对齐方式，需要添加的文字，添加文字的横向位置，纵向位置，旋转角度  坐标原点在改页面左下角
		overContent.showTextAligned(Element.ALIGN_CENTER, idCode, 130, 490,0);
		overContent.endText();
  
  
		/**
		 * 添加一个红圈
		 */
		/*overContent.setRGBColorStroke(0xFF, 0x00, 0x00);
		//圆圈内外圈之间的距离
		overContent.setLineWidth(20);
		overContent.ellipse(250, 450, 350, 550);
		overContent.stroke();*/
  
		//关闭流
		stamper.close();
		System.out.println("流程结束");

	}
	
	/**
	 * 功能 : 拷贝文件
	 * 开发：zwwang 2017-10-17 下午5:40:05
	 * @param sources
	 * @param dest
	 */
	public static void fileChannelCopy(File sources, File dest) {
		
		try {
			
			FileInputStream inputStream = new FileInputStream(sources);
			FileOutputStream outputStream = new FileOutputStream(dest);
			FileChannel fileChannepn = inputStream.getChannel();// 得到对应的文件通道
			FileChannel fileChannelout = outputStream.getChannel();// 得到对应的文件通道
			fileChannepn.transferTo(0, fileChannepn.size(), fileChannelout);// 连接两个通道，并且从in通道读取，然后写入out通道

			inputStream.close();
			fileChannepn.close();
			outputStream.close();
			fileChannelout.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
