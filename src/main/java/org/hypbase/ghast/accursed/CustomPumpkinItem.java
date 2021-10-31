package org.hypbase.ghast.accursed;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ResourceLocation;

public class CustomPumpkinItem extends BlockItem {

	public static int getNBTDesign(ItemStack item) {
		CompoundNBT nbt = item.getOrCreateTag();
		return nbt.getInt("design");	
	}
	
	public CustomPumpkinItem(Block block, Properties properties) {
		super(block, properties);
		ItemModelsProperties.register(this, new ResourceLocation(Accursed.MODID, "design"), (stack, world, living) -> {return (float)((CustomPumpkinItem.getNBTDesign(stack) - 1))/100;});
		
		// TODO Auto-generated constructor stub
	}

}
