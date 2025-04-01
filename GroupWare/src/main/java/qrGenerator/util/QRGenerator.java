package qrGenerator.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Component
public class QRGenerator {
	
	public String generateQRBase64(String text) throws IOException, WriterException{
		int width =250;
		int height =250;
		
		QRCodeWriter qrCodeWriter=new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int x=0;x<width;x++) {
			for(int y=0;y<height;y++) {
				int grayValue=(bitMatrix.get(x, y)?0:255);
				int rgb=(grayValue <<16)|(grayValue <<8)|grayValue;
				image.setRGB(x,y,rgb);
			}
		}
		
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		byte[] imageBytes=baos.toByteArray();
		
		String base64=Base64.encodeBase64String(imageBytes);
		
		
		
		return "data:image/png;base64,"+base64;
	}

}
