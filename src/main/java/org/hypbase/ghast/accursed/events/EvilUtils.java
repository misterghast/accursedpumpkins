package org.hypbase.ghast.accursed.events;

import org.hypbase.ghast.accursed.capabilities.CurseType;
import org.hypbase.ghast.accursed.capabilities.EvilCapabilityProvider;
import org.hypbase.ghast.accursed.capabilities.IEvilCapability;
import org.hypbase.ghast.accursed.events.VampireUtils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.FoodStats;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EvilUtils {
	
	private VampireUtils vampireUtils = new VampireUtils();
	private GhoulUtils ghoulUtils = new GhoulUtils();
	private WraithUtils wraithUtils = new WraithUtils();
	
	@SubscribeEvent
	public void onPlayerHungerTick(PlayerTickEvent event) {
		if(event.type.equals(TickEvent.Type.PLAYER)) {
			LazyOptional<IEvilCapability> l = event.player.getCapability(EvilCapabilityProvider.EVIL_CAPABILITY);
			if(l.isPresent()) {
				IEvilCapability cap = l.orElse(null);
				if(cap.getCursed()) {
					PlayerEntity player = event.player;
					FoodStats stats = event.player.getFoodData();
					stats.setFoodLevel(5);
					stats.setSaturation(5);
					switch (cap.getCurseType()) {
						case VAMPIRE:
							vampireUtils.tickEvent(player);
							vampireUtils.hungerEvent(event.player);
						case GHOUL:
							ghoulUtils.tickEvent(player);
							ghoulUtils.hungerEvent(event.player);
						case WRAITH:
							wraithUtils.tickEvent(player);
							wraithUtils.hungerEvent(event.player);		
					}
				} 
				if(cap.getEvil() > 0) {
					int possibleLowering = (int)(Math.random() * 999 + 1);
					
					if(possibleLowering >= 999) {
						cap.setEvil((short)(cap.getEvil() - 1));
					}
				}
			}
		}
	}
}
