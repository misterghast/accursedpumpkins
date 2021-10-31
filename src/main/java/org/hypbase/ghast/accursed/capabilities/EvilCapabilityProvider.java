package org.hypbase.ghast.accursed.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ShortNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;

public class EvilCapabilityProvider implements ICapabilitySerializable<INBT> {
	
	
	@CapabilityInject(IEvilCapability.class)
	public static final Capability<IEvilCapability> EVIL_CAPABILITY = null;
	
	private LazyOptional<IEvilCapability> inst = LazyOptional.of(EVIL_CAPABILITY::getDefaultInstance);
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == EVIL_CAPABILITY) {
			return inst.cast();
		}
		
		return LazyOptional.empty();
		
	}

	private void throwException() {
		new IllegalArgumentException("There was an error reading NBT.");	
	}
	
	@Override
	public INBT serializeNBT() {
		INBT nbt = null;
		if(inst.isPresent()) {
			nbt = EVIL_CAPABILITY.writeNBT(inst.orElse(null), null);
		}
		return nbt;
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		if(inst.isPresent()) {
			EVIL_CAPABILITY.readNBT(inst.orElse(null), null, nbt);
		}
	}

}
