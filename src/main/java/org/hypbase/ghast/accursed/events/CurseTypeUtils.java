package org.hypbase.ghast.accursed.events;

import net.minecraft.entity.player.PlayerEntity;

public interface CurseTypeUtils {
	public void hungerEvent(PlayerEntity entity);
	public void abilityEvent(PlayerEntity entity);
	public void eatEvent(PlayerEntity entity);
	public void tickEvent(PlayerEntity entity);
}
