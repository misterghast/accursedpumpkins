package org.hypbase.ghast.accursed.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.resources.FolderPack;
import net.minecraft.resources.data.IMetadataSectionSerializer;
import net.minecraft.resources.data.PackMetadataSection;
import net.minecraft.util.text.StringTextComponent;

public class PumpkinPack extends FolderPack {

	public PumpkinPack(File p_i47914_1_) {
		super(p_i47914_1_);
		
		// TODO Auto-generated constructor stub
	}
	
	/*@Override
	public <T> T getMetadataSection(IMetadataSectionSerializer<T> serializer) throws IOException {
		if (serializer == LanguageMetadataSection.SERIALIZER) {
			return (T) new LanguageMetadataSection(new ArrayList<Language>());
		} else {
			return (T) new PackMetadataSection(new StringTextComponent("custompumpkins"), 6);
		}
	}*/
	
	@Override
	public boolean isHidden() {
		return false;
	
	}

}
