package org.hypbase.ghast.accursed.events;

import org.hypbase.ghast.accursed.capabilities.EvilCapabilityProvider;
import org.hypbase.ghast.accursed.capabilities.IEvilCapability;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderEvents {
	public RenderEvents() {
		
	}
	
	@SubscribeEvent
	public void onPlayerRender(RenderGameOverlayEvent event) {
		if(event.getType() == ElementType.FOOD && event.isCancelable()) {
			LazyOptional<IEvilCapability> cap = Minecraft.getInstance().player.getCapability(EvilCapabilityProvider.EVIL_CAPABILITY);
			if(cap.isPresent()) {
				IEvilCapability evil = cap.orElse(null);
				if(evil.getCursed()) {
					event.setCanceled(true);
				}
			}
		}
	}
}
