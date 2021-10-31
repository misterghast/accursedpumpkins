package org.hypbase.ghast.accursed.capabilities;

public interface IEvilCapability {
	short getEvil();
	double getEvilConsistency();
	short getSustenance();
	boolean getCursed();
	
	void setEvil(short amount);
	void setEvilConsistency(double amount);
	void setSustenance(short amount);
	short getMaxSustenance();
	void setCursed(boolean cursed);
	
	void addEvil(short amount);
	void lowerSustenance(short amount);
	void addSustenance(short amount);
	void increaseEvilConsistency(double amount);
	void resetEvilConsistency();
	void curse();
	
	CurseType getCurseType();
}
