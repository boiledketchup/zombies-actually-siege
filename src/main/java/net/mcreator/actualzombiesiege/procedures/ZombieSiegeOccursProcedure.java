package net.mcreator.actualzombiesiege.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.village.VillageSiegeEvent;

import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.actualzombiesiege.network.ActualZombieSiegeModVariables;

import javax.annotation.Nullable;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class ZombieSiegeOccursProcedure {
	@SubscribeEvent
	public static void onVillageSiege(VillageSiegeEvent event) {
		execute(event, event.getWorld(), event.getAttemptedSpawnPos().x, event.getAttemptedSpawnPos().y, event.getAttemptedSpawnPos().z);
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double repeat = 0;
		double returner = 0;
		ActualZombieSiegeModVariables.MapVariables.get(world).activeSiegeCount = 0;
		ActualZombieSiegeModVariables.MapVariables.get(world).syncData(world);
		repeat = Math.random() * 100;
		returner = 0;
		ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn = "soldier_spawn_cap";
		ActualZombieSiegeModVariables.WorldVariables.get(world).syncData(world);
		returner = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(ReturnConfigProcedure.execute(world, x, y, z));
		if (repeat < 1) {
			repeat = 1;
		}
		if (repeat > returner) {
			repeat = returner;
		}
		if (repeat < returner * 0.5 && world.getDifficulty() == Difficulty.HARD) {
			repeat = returner * 0.5;
		}
		if (repeat > returner * 0.75 && world.getDifficulty() == Difficulty.NORMAL) {
			repeat = returner * 0.75;
		}
		if (repeat < returner * 0.25 && world.getDifficulty() == Difficulty.NORMAL) {
			repeat = returner * 0.25;
		}
		if (repeat > returner * 0.5 && world.getDifficulty() == Difficulty.EASY) {
			repeat = returner * 0.5;
		}
		for (int index0 = 0; index0 < (int) (repeat); index0++) {
			if (Math.random() < 0.5) {
				ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn = "soldier_variant";
				ActualZombieSiegeModVariables.WorldVariables.get(world).syncData(world);
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands()
							.performCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
											_level.getServer(), null).withSuppressedOutput(),
									("summon " + ReturnConfigProcedure.execute(world, x, y, z)));
			} else {
				ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn = "soldier";
				ActualZombieSiegeModVariables.WorldVariables.get(world).syncData(world);
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands()
							.performCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
											_level.getServer(), null).withSuppressedOutput(),
									("summon " + ReturnConfigProcedure.execute(world, x, y, z)));
			}
		}
		if (Math.random() < 0.5) {
			ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn = "captain_variant";
			ActualZombieSiegeModVariables.WorldVariables.get(world).syncData(world);
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands()
						.performCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
										_level.getServer(), null).withSuppressedOutput(),
								("summon " + ReturnConfigProcedure.execute(world, x, y, z)));
		} else {
			ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn = "captain";
			ActualZombieSiegeModVariables.WorldVariables.get(world).syncData(world);
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands()
						.performCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
										_level.getServer(), null).withSuppressedOutput(),
								("summon " + ReturnConfigProcedure.execute(world, x, y, z)));
		}
		if (Math.random() < 0.5 && world.getDifficulty() == Difficulty.NORMAL || world.getDifficulty() == Difficulty.HARD) {
			if (Math.random() < 0.5) {
				ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn = "deputy_variant";
				ActualZombieSiegeModVariables.WorldVariables.get(world).syncData(world);
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands()
							.performCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
											_level.getServer(), null).withSuppressedOutput(),
									("summon " + ReturnConfigProcedure.execute(world, x, y, z)));
			} else {
				ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn = "deputy";
				ActualZombieSiegeModVariables.WorldVariables.get(world).syncData(world);
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands()
							.performCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
											_level.getServer(), null).withSuppressedOutput(),
									("summon " + ReturnConfigProcedure.execute(world, x, y, z)));
			}
		}
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1 / 2d), e -> true).stream()
					.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (entityiterator instanceof LivingEntity _livEnt ? _livEnt.getMobType() == MobType.UNDEAD : false) {
					if (!(entityiterator instanceof SkeletonHorse || entityiterator instanceof ZombieHorse)) {
						{
							Entity _ent = entityiterator;
							Scoreboard _sc = _ent.getLevel().getScoreboard();
							Objective _so = _sc.getObjective("isSiegeMob");
							if (_so == null)
								_so = _sc.addObjective("isSiegeMob", ObjectiveCriteria.DUMMY, new TextComponent("isSiegeMob"),
										ObjectiveCriteria.RenderType.INTEGER);
							_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(1);
						}
						ActualZombieSiegeModVariables.MapVariables
								.get(world).activeSiegeCount = ActualZombieSiegeModVariables.MapVariables.get(world).activeSiegeCount + 1;
						ActualZombieSiegeModVariables.MapVariables.get(world).syncData(world);
					}
				}
			}
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.ambient")),
						SoundSource.HOSTILE, 8, (float) 0.2);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.ambient")),
						SoundSource.HOSTILE, 8, (float) 0.2, false);
			}
		}
	}
}
