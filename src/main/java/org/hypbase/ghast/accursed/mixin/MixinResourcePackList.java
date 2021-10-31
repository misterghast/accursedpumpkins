package org.hypbase.ghast.accursed.mixin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hypbase.ghast.accursed.ConfigUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import com.google.common.collect.ImmutableList;

import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mixin(ResourcePackList.class)
public class MixinResourcePackList {
	
	@Shadow
	private Map<String, ResourcePackInfo> available;
	
	@Shadow 
	private List<ResourcePackInfo> selected;
	
	@Inject(method = "setSelected(Ljava/util/Collection;)V", at = @At("RETURN"))
	public void setSelected (Collection<String> packs, CallbackInfo callback) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			System.out.println("Selecting resource packs.");
			final List<ResourcePackInfo> toEnable = this.selected.stream().collect(Collectors.toCollection(ArrayList::new));
			
			for(final ResourcePackInfo packInfo : this.available.values()) {
				if(packInfo.getId().equals("custompumpkinfaces")) {
					ConfigUtils.forceLoad(packInfo, toEnable);
				}
			}
			
			this.selected = ImmutableList.copyOf(toEnable);
		}
	}
	
}
