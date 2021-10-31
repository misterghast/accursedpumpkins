package org.hypbase.ghast.accursed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.data.DirectoryCache;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/*class PumpkinSupplier implements Supplier<Block> {
	
	@Override
	public Block get() {
		Properties properties = Properties.of(Material.VEGETABLE);
		/*properties.harvestLevel(1);
		properties.harvestTool(ToolType.AXE);
		properties.strength(1);
		return new CustomPumpkin();
	}	
}*/

/*class PumpkinItemSupplier implements Supplier<BlockItem> {
	
	@Override
	public BlockItem get() {
		PumpkinSupplier ps = new PumpkinSupplier();
		Block p = ps.get();
		return new BlockItem(p, new Item.Properties().tab(ItemGroup.TAB_DECORATIONS));
	}
}*/

public class AccursedRegistry {
	//private static final PumpkinModelGenerator GENERATOR = new PumpkinModelGenerator();
	public static final DeferredRegister<Block> PUMPKIN_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Accursed.MODID);
	public static final DeferredRegister<Item> PUMPKIN_ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Accursed.MODID);
	public static final RegistryObject<Block> PUMPKIN = PUMPKIN_REGISTER.register("custompumpkin", () -> new CustomPumpkin());
	public static final RegistryObject<Item> PUMPKIN_ITEM = PUMPKIN_ITEM_REGISTER.register("custompumpkin",() -> new BlockItem(PUMPKIN.get(), new Item.Properties()));
	public AccursedRegistry() {
		
		
	}
	
	public static void init(final IEventBus context) {
		System.out.println("test");
		PUMPKIN_REGISTER.register(context);
		PUMPKIN_ITEM_REGISTER.register(context);
		
	}
	
	/*public static <T extends Item.Properties>  void registerVariantItem(Item item, String name, T variant) {
		
	}*/
	
}
