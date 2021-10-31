package org.hypbase.ghast.accursed.mixin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.hypbase.ghast.accursed.ConfigUtils;
import org.lwjgl.system.CallbackI.V;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.points.BeforeNew;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackList;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import net.minecraftforge.fml.packs.ModFileResourcePack;
import net.minecraftforge.fml.packs.ResourcePackLoader;
import net.minecraftforge.fml.packs.ResourcePackLoader.IPackInfoFinder;

@Mixin(ResourcePackLoader.class)
public class MixinResourcePacks {
	
	
	@Inject(method = "loadResourcePacks(Lnet/minecraft/resources/ResourcePackList;Ljava/util/function/BiFunction;)V", at = @At("RETURN"), remap = false)
	private static <T extends ResourcePackInfo> void injectPacks(ResourcePackList resourcePacks, BiFunction<Map<ModFile, ? extends ModFileResourcePack>, BiConsumer<? super ModFileResourcePack, T>, IPackInfoFinder> packFinder, CallbackInfo callback) {
		try {
			System.out.println("Loading resource packs.");
			ConfigUtils.loadAssets(resourcePacks);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			System.out.println("Accursed: Error injecting code into resource packs to load pumpkin faces.");
			e.printStackTrace();
		}
	}
	
	@Inject(method = "getSorter", at = @At(value = "INVOKE", target = "add", ordinal = 1), remap = false, locals = LocalCapture.CAPTURE_FAILHARD)
	private static void injectSorter(CallbackInfoReturnable<Comparator<Map.Entry<String, V>>> cr, List<String> order) {
		order.add("custompumpkinfaces");
		System.out.println(order);
		System.out.println("Order injection working.");
	}
	
	/*@Redirect(method = "getSorter",
			at = @At(value = "NEW", target = "java/util/ArrayList"))
	public ArrayList arrayListRedirect() {	
		ArrayList<String> newList = new ArrayList<String>();
		newList.add("thisisaresourcepackid");
		return newList;
	}*/
}
