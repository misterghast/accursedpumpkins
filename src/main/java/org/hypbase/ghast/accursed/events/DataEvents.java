package org.hypbase.ghast.accursed.events;

import org.hypbase.ghast.accursed.Accursed;
import org.hypbase.ghast.accursed.PumpkinProvider;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class DataEvents {
	@SubscribeEvent
	public static void onGatherDataEvent(GatherDataEvent event) {
		if(event.includeClient()) {
			//event.getGenerator().addInput(new File("config/accursed/pumpkin_faces").toPath());
			PumpkinProvider p = new PumpkinProvider(event.getGenerator(), Accursed.MODID, event.getExistingFileHelper());
			//PumpkinSupplier supplier = new PumpkinSupplier();
			//PROVIDER.registerStatesAndModels();
			
			System.out.println("Pumpkin provider created.");
			event.getGenerator().addProvider(p);		
		}
	}
}
