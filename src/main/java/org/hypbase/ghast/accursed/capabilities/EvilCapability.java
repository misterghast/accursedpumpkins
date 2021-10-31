package org.hypbase.ghast.accursed.capabilities;

import javax.annotation.Nonnull;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class EvilCapability implements IEvilCapability {

	private short evil;
	private short minimumEvil;
	private double evilConsistency;
	private double minimumConsistency;
	private boolean cursed;
	private short sustenance;
	private short maxSustenance;
	private CurseType curseType;
	
	public EvilCapability() {
		this.evil = 0;
		this.minimumEvil = 0;
		this.evilConsistency = 1.0;
		this.minimumConsistency = 1.0;
		this.sustenance = 50;
		this.cursed = false;
		this.curseType = CurseType.NONE;
	}
	
	@Override
	public short getEvil() {
		// TODO Auto-generated method stub
		return evil;
	}

	@Override
	public double getEvilConsistency() {
		// TODO Auto-generated method stub
		return evilConsistency;
	}

	@Override
	public boolean getCursed() {
		// TODO Auto-generated method stub
		return cursed;
	}

	@Override
	public void setEvil(short amount) {
		this.evil = amount;
	}

	@Override
	public void setEvilConsistency(double amount) {
		this.evilConsistency = amount;
	}

	@Override
	public void setCursed(boolean cursed) {
		this.cursed = cursed;
	}

	@Override
	public void addEvil(short amount) {
		this.evil += amount * this.evilConsistency;
	}

	@Override
	public void increaseEvilConsistency(double amount) {
		this.evilConsistency += amount;
	}
	
	@Override
	public void resetEvilConsistency() {
		this.evilConsistency = this.minimumConsistency;
	}

	@Override
	public void curse() {
		if(!this.cursed) {
			this.cursed = true;
			int temp = (int)(Math.random() * 2 + 1);
			switch(temp) {
				case 1: 
					this.curseType = CurseType.VAMPIRE;
				case 2:
					this.curseType = CurseType.GHOUL;
				case 3:
					this.curseType = CurseType.WRAITH;
			}
		}
	}

	@Override
	public short getSustenance() {
		return this.sustenance;
	}

	@Override
	public void setSustenance(short amount) {
		this.sustenance = amount;
		
	}

	@Override
	public void lowerSustenance(short amount) {
		this.sustenance = (short) (this.sustenance - amount);
		
	}

	@Override
	public void addSustenance(short amount) {
		this.sustenance = (short) (this.sustenance + amount);
		
	}

	@Override
	public short getMaxSustenance() {
		return this.maxSustenance;
	}

	@Override
	public CurseType getCurseType() {
		return this.curseType;
	}

}
