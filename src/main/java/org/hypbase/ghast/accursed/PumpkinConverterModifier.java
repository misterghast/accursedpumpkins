package org.hypbase.ghast.accursed;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.LootModifier;

public class PumpkinConverterModifier extends LootModifier {
	//private final Item check;
	//private final int design;
	//private final Item finalItem;
	
	protected PumpkinConverterModifier(ILootCondition[] conditionsIn) {
		super(conditionsIn);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		context.getLootTable(new ResourceLocation("accursed:custompumpkin"));
		return null;
	}
	
}
