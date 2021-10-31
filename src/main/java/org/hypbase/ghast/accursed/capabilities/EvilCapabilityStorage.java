package org.hypbase.ghast.accursed.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class EvilCapabilityStorage implements IStorage<IEvilCapability>{
	
	@Override
	public INBT writeNBT(Capability<IEvilCapability> capability, IEvilCapability instance, Direction side) {
		// TODO Auto-generated method stub
		CompoundNBT tag = new CompoundNBT();
		tag.putShort("sustenance", instance.getSustenance());
		tag.putShort("evil", instance.getEvil());
		tag.putDouble("evilConstant", instance.getEvilConsistency());
		tag.putBoolean("cursed", instance.getCursed());
		return tag;
	}

	@Override
	public void readNBT(Capability<IEvilCapability> capability, IEvilCapability instance, Direction side, INBT nbt) {
		CompoundNBT tag = (CompoundNBT) nbt;
		instance.setSustenance(tag.getShort("sustenance"));
		instance.setEvil(tag.getShort("evil"));
		instance.setEvilConsistency(tag.getDouble("evilConstant"));
		instance.setCursed(tag.getBoolean("cursed"));
		
	}

}
