package org.hypbase.ghast.accursed.client;

import java.util.function.Supplier;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.DistExecutor.SafeRunnable;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class ClientEvents {
	
	@SubscribeEvent
	public void configReloadListener(AddReloadListenerEvent event) {
		//TODO: I know this isn't what I'm supposed to do but I'll fix this late.
		/*System.out.println("configReloadListener being called.");
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, new Supplier<Runnable>() {

			@Override
			public Runnable get() {
				// TODO Auto-generated method stub
				return new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						event.addListener(new AccursedReloadListener());
					}
					
				};
			}
			
		});*/
	}
}
