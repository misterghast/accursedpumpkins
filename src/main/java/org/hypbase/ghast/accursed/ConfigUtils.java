package org.hypbase.ghast.accursed;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Functions;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class ConfigUtils {
	@SuppressWarnings("deprecation")
	public static void loadAssets(ResourcePackList resourcePacks) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		
		/*File f = new File(configDirectory + "/pumpkin_faces");
		ArrayList<ResourceLocation> r = new ArrayList<ResourceLocation>();
		int i = 0;
		for(File texture : f.listFiles()) {
			if(texture.getName().contains(".png")) {
				r.add(new ResourceLocation("accursed:textures/pumpkin" + i));
				if(texture.exists()) {
					try {
						Files.createTempFile(Paths.get(r.get(i).getPath()));
						Files.copy(Paths.get(texture.getPath()), Paths.get(r.get(i).getPath()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			i++;
		
		}*/
		/*System.out.println("Loaded resources.");
		PumpkinFinder p = new PumpkinFinder(new File(configDirectory + "/pumpkin_faces"), "custompumpkinfaces");
		Field f = Minecraft.class.getDeclaredField("field_110448_aq");*/
		//Field f = Minecraft.class.getDeclaredField("resourcePackRepository");
		/*f.setAccessible(true);
		ResourcePackList resources = (ResourcePackList) f.get(Minecraft.getInstance());
		resources.addPackFinder(p);
		System.out.println(resources.getAvailablePacks());
		Collection<String> selectedIDS = new ArrayList<String>(); selectedIDS.addAll(resources.getAvailableIds());
		selectedIDS.add("custompumpkinfaces");
		resources.setSelected(resources.getSelectedIds());
		final List<ResourcePackInfo> enabledPacks = resources.getSelectedPacks().stream().collect(Collectors.toCollection(ArrayList::new));*/
		/*for(ResourcePackInfo pack : enabledPacks) {
			if(pack.getId().equals("custompumpkinfaces")) {
				forceLoad(pack, enabledPacks);
			}
		}*/		
		PumpkinFinder p = new PumpkinFinder(new File(Accursed.configDirectory + "/pumpkin_faces"), "custompumpkinfaces");
		resourcePacks.addPackFinder(p);
	}
	
	public static void forceLoad(ResourcePackInfo packInfo, List<ResourcePackInfo> enabled) {
		if(FMLEnvironment.dist.isClient()) {
			if(!enabled.contains(packInfo)) {
				packInfo.getDefaultPosition().insert(enabled, packInfo, Functions.identity(), false);
			}
		}
	}
	
	public static boolean curses;
	public static int designs;
	
	public static void bakeCommon() {
		designs = Common.COMMON.numberOfDesigns.get();
	}
	
	public static class Common {
		public final IntValue numberOfDesigns;
		
		public Common(ForgeConfigSpec.Builder builder) {
			builder.push("core");
			numberOfDesigns = builder.comment("The number of jack'o'lantern faces you put in the pumpkin_faces directory. PLEASE ENTER THE EXACT NUMBER.").defineInRange("designs", 0, 0, 25);
			builder.pop();
		}
		
		public static final Common COMMON;
		public static final ForgeConfigSpec COMMON_SPEC;
		static {
			Builder builder = new ForgeConfigSpec.Builder();
			final Pair<Common, ForgeConfigSpec> specPair = builder.configure(Common::new);
			COMMON_SPEC = specPair.getRight();
			COMMON = specPair.getLeft();
		}
		
		
		public static String getFileLocation() {
			return null;
		}
	}
}
