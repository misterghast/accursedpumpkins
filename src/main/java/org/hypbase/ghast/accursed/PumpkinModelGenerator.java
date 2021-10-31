package org.hypbase.ghast.accursed;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;

public class PumpkinModelGenerator {
	public PumpkinModelGenerator() {
		
	}
	
	public static int getNumberOfDesigns() {
		return ConfigUtils.Common.COMMON_SPEC.getInt("designs");
	}
	
	public static void clientSideGenerateTextures(String blockId, int num, String configLocation, Block pumpkin, PumpkinProvider p) {
		
	}
	
	public static void generatePumpkinBlockstate(String blockId, boolean isClient, int num, String configLocation, Block pumpkin, PumpkinProvider p) {
		if(isClient) {
			clientSideGenerateTextures(blockId, num, configLocation, pumpkin, p);
		}
	}
}
