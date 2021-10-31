package org.hypbase.ghast.accursed;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.google.common.io.Files;

import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class PumpkinProvider extends BlockStateProvider {

	public static final ResourceLocation PUMPKIN_SIDE = new ResourceLocation("block/pumpkin_side");
	public static final ResourceLocation PUMPKIN_TOP = new ResourceLocation("block/pumpkin_top");
	
	public PumpkinProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
		super(gen, modid, exFileHelper);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void registerStatesAndModels() {
		
		ArrayList<ModelFile> b = new ArrayList<ModelFile>();
		ArrayList<ModelFile> bLit = new ArrayList<ModelFile>();
		CustomPumpkin p = new CustomPumpkin();
		VariantBlockStateBuilder builder = getVariantBuilder(AccursedRegistry.PUMPKIN.get());
		File default_pumpkin_front = new File("C:\\Users\\flash\\Desktop\\bruh\\src\\main\\resources\\textures\\block\\carved_pumpkin.png");
    	File default_pumpkin_front_lit = new File("C:\\Users\\flash\\Desktop\\bruh\\src\\main\\resources\\textures\\block\\jack_o_lantern.png");
		for(Integer value : CustomPumpkin.DESIGN.getPossibleValues()) {		
			ResourceLocation pumpkin_front = new ResourceLocation("accursed", "block/custompumpkin" + value.toString());
			System.out.println(pumpkin_front.getPath());
			ResourceLocation pumpkin_front_lit = new ResourceLocation("accursed", "block/custompumpkinlit" + value.toString());
			System.out.println(pumpkin_front_lit.getPath());
			
			//Toggleable default texture generator.
			if(true) {
				try {
					Files.copy(default_pumpkin_front, new File("C:\\Users\\flash\\Desktop\\bruh\\src\\main\\resources\\assets\\accursed\\textures\\block\\custompumpkin" + value.toString() + ".png"));
					Files.copy(default_pumpkin_front_lit, new File("C:\\Users\\flash\\Desktop\\bruh\\src\\main\\resources\\assets\\accursed\\textures\\block\\custompumpkinlit" + value.toString() + ".png"));
				} catch (IOException e) {
				    //TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Files Registered." + value);
			b.add(models().orientable("custompumpkin" + value.toString(), PUMPKIN_SIDE, pumpkin_front, PUMPKIN_TOP));
			builder.partialState().with(CustomPumpkin.DESIGN, value).with(CustomPumpkin.LIT, false).modelForState().modelFile(b.get(value)).addModel();
			bLit.add(models().orientable("custompumpkinlit" + value.toString(), PUMPKIN_SIDE, pumpkin_front_lit, PUMPKIN_TOP));
			builder.partialState().with(CustomPumpkin.DESIGN, value).with(CustomPumpkin.LIT, true).modelForState().modelFile(bLit.get(value)).addModel();
		}
		this.itemModels().getBuilder("custompumpkin").parent(models().cubeAll("custompumpkin", PUMPKIN_SIDE));
		
		
		
		
			
				
				/*try {
					ResourceLocation r = new ResourceLocation("accursed:blockstates" + i);
					String contents = b.get(i).toString();
					FileWriter writer = new FileWriter("accursed:.json");
					writer.append(contents);
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	}

}
