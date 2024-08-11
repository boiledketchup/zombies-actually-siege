package net.mcreator.actualzombiesiege.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.actualzombiesiege.network.ActualZombieSiegeModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class UpdateTicksSinceSiegeProcedure {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.WorldTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.world);
		}
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		ActualZombieSiegeModVariables.MapVariables.get(world).ticksSinceSiege = ActualZombieSiegeModVariables.MapVariables.get(world).ticksSinceSiege + 1;
		ActualZombieSiegeModVariables.MapVariables.get(world).syncData(world);
	}
}
