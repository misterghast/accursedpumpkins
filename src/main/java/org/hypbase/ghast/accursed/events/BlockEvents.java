package org.hypbase.ghast.accursed.events;

import java.util.Collection;

import org.hypbase.ghast.accursed.Accursed;
import org.hypbase.ghast.accursed.AccursedRegistry;
import org.hypbase.ghast.accursed.CustomPumpkin;

import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootContext.Builder;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;

public class BlockEvents {
	@SubscribeEvent
	public static void onBlockBreak(BreakEvent event) {
		if(event.getState().getBlock() instanceof CustomPumpkin) {
			System.out.println("Working!");
			//event.getState().getDrops(null);
			//event.getState().getDrops(null);
		}
	}
	
	@SubscribeEvent
	public void OnBlockUsage(PlayerInteractEvent.RightClickBlock event) {
		if (event.getPlayer().getItemInHand(event.getHand()).getToolTypes().contains(ToolType.HOE)) {
			if(event.getWorld().getBlockState(event.getHitVec().getBlockPos()).is(Blocks.PUMPKIN)) {
				event.getWorld().setBlock(event.getHitVec().getBlockPos(), AccursedRegistry.PUMPKIN.get().defaultBlockState().setValue(CustomPumpkin.LIT, false), 0);
			}
		}
	}
	
	public static void OnBlockPunched(PlayerInteractEvent.LeftClickBlock event) {
		
	}
}
