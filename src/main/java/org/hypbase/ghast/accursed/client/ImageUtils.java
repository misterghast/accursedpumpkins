package org.hypbase.ghast.accursed.client;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import org.imgscalr.Scalr;
public class ImageUtils {
	public static BufferedImage overlayImages(BufferedImage imageOne, BufferedImage imageTwo) {
		//imageOne = clearWhite(imageOne, new Color(255, 255, 255));
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_IN, 1.0f);
		BufferedImage finalImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		//g2.setComposite(ac);
		Graphics2D g2 = finalImage.createGraphics();
		g2.drawImage(imageTwo, 0, 0, null);
		g2.drawImage(clearWhite(imageOne, new Color(255, 255, 255)), 0, 0, null);
		g2.dispose();
		
		
		return finalImage;
	}
	
	public static BufferedImage overlayYellowImages(BufferedImage imageOne, BufferedImage imageTwo) {
		BufferedImage finalImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = finalImage.createGraphics();
		//g2.drawImage(imageTwo, 0, 0, null);
		//g2.drawImage(replaceBlackToYellow(clearWhite(imageOne, new Color(255, 255, 255)), new Color(0, 0, 0)), 0, 0, null);
		g2.drawImage(replaceBlackToYellow(overlayImages(imageOne, imageTwo), new Color(0, 0, 0), new Color(60, 60, 60), new Color(120, 120, 120)), 0, 0, null);
		g2.dispose();
		
		return finalImage;
	}
	
	public static BufferedImage clearWhite(BufferedImage image, Color toClear) {
		int r1 = toClear.getRed();
		int g1 = toClear.getGreen();
		int b1 = toClear.getBlue();
		ImageFilter f = new RGBImageFilter() {
			public int markerRGB = toClear.getRGB() | 0xFF000000;
			public final int filterRGB(final int x, final int y, final int rgb) {
					/*int r = (rgb & 0xFF0000) >> 16;
					int g = (rgb & 0xFF00) >> 8;
					int b = (rgb & 0xFF);
					if(r == r1 && g == g1 && b == b1) {
						return rgb & 0xFFFFFF;
					} */
					if((rgb | 0xFF000000) == markerRGB) {
						return 0x00FFFFFF & rgb;
					}
					return rgb;
			}
		};
		
		ImageProducer ip = new FilteredImageSource(image.getSource(), f);
		BufferedImage b = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2b = b.createGraphics();
		g2b.drawImage(Toolkit.getDefaultToolkit().createImage(ip), 0, 0, null);
		g2b.dispose();
		return b;
	}
	
	public static BufferedImage replaceBlackToYellow(BufferedImage image, Color color, Color colorShadow, Color colorShadowMax) {
		int r1 = colorShadow.getRed();
		int g1 = colorShadow.getGreen();
		int b1 = colorShadow.getBlue();
		int r2 = colorShadowMax.getRed();
		int g2 = colorShadowMax.getGreen();
		int b2 = colorShadowMax.getBlue();
		ImageFilter f = new RGBImageFilter() {
			public int markerRGB = color.getRGB();
			public int shadowRGB = colorShadow.getRGB();
			public final int filterRGB(final int x, final int y, final int rgb) {
					int red = rgb & 0xFF0000;
					int green = rgb & 0xFF00;
					int blue = rgb & 0xFF;
					if((rgb) == markerRGB) {
						return Color.YELLOW.getRGB();
					} else if ((red) >= r1 && (red) <= r2 && (green) >= g1 && (green) <= g2 && (blue) >= b1 && (blue) <= b2) {
						return new Color(156, 156, 0).getRGB();
					}
					return rgb;
			}
		};
		
		ImageProducer ip = new FilteredImageSource(image.getSource(), f);
		BufferedImage b = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2b = b.createGraphics();
		g2b.drawImage(Toolkit.getDefaultToolkit().createImage(ip), 0, 0, null);
		g2b.dispose();
		return b;
	}
}
