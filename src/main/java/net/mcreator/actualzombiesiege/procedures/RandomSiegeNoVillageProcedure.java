package net.mcreator.actualzombiesiege.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;

import net.mcreator.actualzombiesiege.network.ActualZombieSiegeModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class RandomSiegeNoVillageProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != event.getPlayer().getUsedItemHand())
			return;
		execute(event, event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.BELL && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.ROTTEN_FLESH
				&& ActualZombieSiegeModVariables.MapVariables.get(world).ticksSinceSiege >= 2400) {
			UnnaturalSiegeProcedure.execute(world, x, y, z, entity);
		} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.BELL && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.ROTTEN_FLESH
				&& ActualZombieSiegeModVariables.MapVariables.get(world).ticksSinceSiege < 2400) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("A siege has been called to recently!"), (true));
		}
	}
}
