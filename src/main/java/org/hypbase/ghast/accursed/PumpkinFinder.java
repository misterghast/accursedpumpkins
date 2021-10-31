package org.hypbase.ghast.accursed;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import org.hypbase.ghast.accursed.client.ImageUtils;
import org.hypbase.ghast.accursed.client.PumpkinPack;

import com.google.common.io.Files;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.fonts.FontTexture;
import net.minecraft.resources.FolderPack;
import net.minecraft.resources.IPackFinder;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.IResourcePack;
import net.minecraft.resources.PackCompatibility;
import net.minecraft.resources.ResourcePack;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackInfo.IFactory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class PumpkinFinder implements IPackFinder {

	private final File folder;
	private final File root;
	private final IPackNameDecorator packSource;
	
	public PumpkinFinder(File location, String id) {
		this.folder = location;
		System.out.println(new ResourceLocation(id).getPath());
		this.root = new File(location.getPath() + "/" + id);
		this.packSource = IPackNameDecorator.DEFAULT;
	}
	
	@Override
	public void loadPacks(Consumer<ResourcePackInfo> p_230230_1_, ResourcePackInfo.IFactory p_230230_2_) {
		File assetsDirectory = new File(this.root.getPath() + "/assets");
		assetsDirectory.mkdirs();
		File modDirectory = new File(assetsDirectory.getPath() + "/accursed");
		modDirectory.mkdirs();
		File texturesDirectory = new File(modDirectory.getPath() + "/textures");
		texturesDirectory.mkdirs();
		File blockDirectory = new File(texturesDirectory.getPath() + "/block");
		blockDirectory.mkdirs();
		
		if(!this.folder.isDirectory()) {
			this.folder.mkdirs();
		}
		
		if(!this.root.isDirectory()) {
			this.root.mkdirs();
			
		}
		
		int var = 0;

		try {
			//InputStream metaFile = Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(Accursed.MODID, "pack.mcmeta")).getInputStream();
			InputStream metaFile = getClass().getResourceAsStream("/assets/accursed/info/pack.mcmeta");
			byte[] byteArray = new byte[4000];
			//I'm offloading this to JSON because it's better not to hardcode things. Yeah. Totally. Not because I don't know how to use the ResourcePackInfo class.
			FileOutputStream s = new FileOutputStream(new File(this.root.getPath() + "/pack.mcmeta"));
			
			int num = 0;
			while(-1 != (num = metaFile.read(byteArray))) {
				s.write(byteArray, 0, num);
			}
			metaFile.close();
		} catch (IOException e1) {
			System.out.println("Copying Accursed Pumpkins! Pack Meta Error");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        BufferedImage pumpkinSide = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
        	//InputStream pumpkinSideStream = Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation("minecraft", "textures/block/pumpkin_side.png")).getInputStream();
			InputStream pumpkinSideStream = getClass().getResourceAsStream("/textures/block/pumpkin_side.png");
			pumpkinSide = ImageIO.read(pumpkinSideStream);
        	pumpkinSideStream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error loading vanilla pumpkin texture. - Accursed");
		}

		for(File f : folder.listFiles()) {
			if(!f.isDirectory() && f.getName().endsWith(".png")) {
				String texturePath = (blockDirectory.getAbsolutePath() + "/custompumpkin" + var + ".png");
				String textureLitPath = (blockDirectory.getAbsolutePath() + "/custompumpkinlit" + var + ".png");
				try(FileOutputStream pathOutStream = new FileOutputStream(texturePath);
						FileOutputStream pathOutputLitStream = new FileOutputStream(textureLitPath)) {
					//String oldPath = (this.folder.getAbsolutePath() + "/custompumpkin" + var + ".png");
					
					//
					FileInputStream fs = new FileInputStream(f.getAbsolutePath());
					BufferedImage oldTexture = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
					BufferedImage secondOldTexture = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
					oldTexture = ImageIO.read(fs);
					//secondOldTexture = ImageIO.read(fs);
					//BufferedImage newTexture = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
					//newTexture = ImageIO.read(new FileInputStream(texturePath));					
					ImageIO.write(ImageUtils.overlayImages(oldTexture, pumpkinSide), "png", pathOutStream);
					ImageIO.write(ImageUtils.overlayYellowImages(oldTexture, pumpkinSide), "png", pathOutputLitStream);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					
				}
				var++;
			}
			
			
		}
		
		
		
		String s = root.getName();
		//This is unbelievably hacky BUT better than using reflection on the entire resource priority system.
		ResourcePackInfo resourcePackInfo = new ResourcePackInfo(s, true, this.createSupplier(root), new StringTextComponent("Custom Pumpkin Faces"), new StringTextComponent("Kool Aid Man."), PackCompatibility.forFormat(6), ResourcePackInfo.Priority.TOP, false, packSource, false);
		System.out.println(resourcePackInfo.getId());
		if(resourcePackInfo != null) {
			p_230230_1_.accept(resourcePackInfo);
		}
	}
	
	private Supplier<IResourcePack> createSupplier(File file) {
		return () -> {
			PumpkinPack p = new PumpkinPack(file);
			
			return new PumpkinPack(file);
		};
	}
}
