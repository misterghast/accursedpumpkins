package org.hypbase.ghast.accursed;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.resources.IResourcePack;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.resources.data.IMetadataSectionSerializer;
import net.minecraft.util.ResourceLocation;

public class PumpkinResource implements IResourcePack {

	@Override
	public InputStream getRootResource(String p_195763_1_) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getResource(ResourcePackType p_195761_1_, ResourceLocation p_195761_2_) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ResourceLocation> getResources(ResourcePackType p_225637_1_, String p_225637_2_,
			String p_225637_3_, int p_225637_4_, Predicate<String> p_225637_5_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasResource(ResourcePackType p_195764_1_, ResourceLocation p_195764_2_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> getNamespaces(ResourcePackType p_195759_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getMetadataSection(IMetadataSectionSerializer<T> p_195760_1_) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
