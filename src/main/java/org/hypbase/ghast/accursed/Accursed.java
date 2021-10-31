package org.hypbase.ghast.accursed;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hypbase.ghast.accursed.capabilities.EvilCapability;
import org.hypbase.ghast.accursed.capabilities.EvilCapabilityProvider;
import org.hypbase.ghast.accursed.capabilities.EvilCapabilityStorage;
import org.hypbase.ghast.accursed.capabilities.IEvilCapability;
import org.hypbase.ghast.accursed.client.ClientEvents;
import org.hypbase.ghast.accursed.events.BlockEvents;
import org.hypbase.ghast.accursed.events.DataEvents;
import org.hypbase.ghast.accursed.events.EvilUtils;
import org.hypbase.ghast.accursed.events.RenderEvents;

import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.config.ModConfig.ModConfigEvent;
import net.minecraftforge.fml.ModLoadingContext;

@Mod("accursed")
public class Accursed {
	public static final RenderEvents RENDER_EVENTS = new RenderEvents();
	public static final EvilUtils EVIL_UTILS = new EvilUtils();
	public static final ClientEvents CLIENT_EVENTS = new ClientEvents();
	public static final BlockEvents BLOCK_EVENTS = new BlockEvents();
	public static final CustomPumpkin CUSTOM_PUMPKIN = new CustomPumpkin();
	//public static final Accursed INSTANCE = new Accursed();
	public static final String MODID = "accursed";
	private static final Logger log = LogManager.getLogger();
	public static String configDirectory = "config/accursed";	
	
	
	public Accursed() {
		
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(BLOCK_EVENTS);
		MinecraftForge.EVENT_BUS.register(CUSTOM_PUMPKIN);
		MinecraftForge.EVENT_BUS.register(EVIL_UTILS);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(Accursed::onCommonInit);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(Accursed::onClientInit);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(Accursed::onModConfig);
		//FMLJavaModLoadingContext.get().getModEventBus().addListener(Accursed::configReloadListener);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigUtils.Common.COMMON_SPEC);
		AccursedRegistry.init(FMLJavaModLoadingContext.get().getModEventBus());
		FMLJavaModLoadingContext.get().getModEventBus().addListener(DataEvents::onGatherDataEvent);
		//File configLocation = new File(configDirectory);
		//configLocation.mkdir();
		
		
	}
	
	
	private static class Factory implements Callable<IEvilCapability> {
		public EvilCapability call() throws Exception {
			return new EvilCapability();
		}
	}
	
	@SubscribeEvent
	public static void onModConfig(ModConfigEvent event) {
		ConfigUtils.bakeCommon();
	}
	
	@SubscribeEvent
	public static void onCommonInit(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(IEvilCapability.class, new EvilCapabilityStorage(), EvilCapability::new);	
		//try {
			//System.out.println("We're trying.");
			//ConfigUtils.loadAssets(configDirectory);
		//} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
	}
	
	public static void onClientInit(FMLClientSetupEvent event) {
		
	}
	
	@SubscribeEvent
	public void onEntityKilled(LivingDeathEvent event) {
		if(event.getSource().getDirectEntity() instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity)event.getSource().getEntity();
			
			LazyOptional<IEvilCapability> l = player.getCapability(EvilCapabilityProvider.EVIL_CAPABILITY);
			if(l.isPresent()) {
				IEvilCapability cap = l.orElse(null);
				if(cap != null) {
					cap.addEvil((short)(1 * cap.getEvilConsistency()));
					cap.increaseEvilConsistency(0.1);
				}
				
				if(cap.getEvil() >= 40) {
					player.displayClientMessage(new StringTextComponent("You notice your appetite has been worryingly low recently."), false);
				}
				
				if(cap.getEvil() >= 70) {
					player.displayClientMessage(new StringTextComponent("You've been having an irrational craving for human blood."), false);
				}
				
				if(cap.getEvil() >= 100) {
					cap.curse();
				}
			}
		} else {
			
		}
	}
	
	@SubscribeEvent
	public void addCapability(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof PlayerEntity) {
			event.addCapability(new ResourceLocation(MODID + "accursed"), new EvilCapabilityProvider());
		}
	}
}
