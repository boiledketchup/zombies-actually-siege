package net.mcreator.actualzombiesiege.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.actualzombiesiege.network.ActualZombieSiegeModVariables;

import javax.annotation.Nullable;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class SiegeMobDeathDetectProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean won = false;
		boolean nearPlayer = false;
		Entity playerIs = null;
		double i = 0;
		double rolls = 0;
		double items = 0;
		double chance = 0;
		if (new Object() {
			public int getScore(String score, Entity _ent) {
				Scoreboard _sc = _ent.getLevel().getScoreboard();
				Objective _so = _sc.getObjective(score);
				if (_so != null)
					return _sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).getScore();
				return 0;
			}
		}.getScore("isSiegeMob", entity) == 1) {
			won = false;
			nearPlayer = false;
			ActualZombieSiegeModVariables.MapVariables.get(world).activeSiegeCount = ActualZombieSiegeModVariables.MapVariables.get(world).activeSiegeCount - 1;
			ActualZombieSiegeModVariables.MapVariables.get(world).syncData(world);
			ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn = "total_items";
			ActualZombieSiegeModVariables.WorldVariables.get(world).syncData(world);
			i = 0;
			items = new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(ReturnConfigProcedure.execute(world, x, y, z));
			for (int index0 = 0; index0 < (int) (items); index0++) {
				i = i + 1;
				ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn = "rolls" + i;
				ActualZombieSiegeModVariables.WorldVariables.get(world).syncData(world);
				rolls = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(ReturnConfigProcedure.execute(world, x, y, z));
				for (int index1 = 0; index1 < (int) (rolls); index1++) {
					ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn = "chance" + i;
					ActualZombieSiegeModVariables.WorldVariables.get(world).syncData(world);
					chance = new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(ReturnConfigProcedure.execute(world, x, y, z)) / 100;
					if (Math.random() < chance) {
						ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn = "item" + i;
						ActualZombieSiegeModVariables.WorldVariables.get(world).syncData(world);
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
									("/summon item ~ ~ ~ {Item:{id:\"" + "" + (ReturnConfigProcedure.execute(world, x, y, z) + "" + "\",Count:1b}}")));
					}
				}
			}
			if (ActualZombieSiegeModVariables.MapVariables.get(world).activeSiegeCount == 0) {
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(32 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
							.collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if (entityiterator instanceof Player) {
							nearPlayer = true;
							won = true;
							playerIs = entityiterator;
							break;
						}
						nearPlayer = false;
						won = false;
					}
				}
				if (nearPlayer) {
					if (won) {
						if (!ModList.get().isLoaded("varaventure")) {
							if (playerIs instanceof LivingEntity _entity)
								_entity.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 3600, 0));
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.converted")), SoundSource.HOSTILE, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.converted")), SoundSource.HOSTILE, 1, 1, false);
							}
						}
						if (playerIs instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("actual_zombie_siege:complete_siege"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemainingCriteria().iterator();
								while (_iterator.hasNext())
									_player.getAdvancements().award(_adv, (String) _iterator.next());
							}
						}
						for (int index2 = 0; index2 < (int) (10); index2++) {
							if (world instanceof Level _level && !_level.isClientSide())
								_level.addFreshEntity(new ExperienceOrb(_level, x, y, z, 10));
						}
						{
							final Vec3 _center = new Vec3(x, y, z);
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
									.collect(Collectors.toList());
							for (Entity entityiterator : _entfound) {
								if (entityiterator instanceof IronGolem) {
									if (entityiterator instanceof LivingEntity _entity)
										_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3600, 1, (false), (false)));
								}
							}
						}
					}
				}
			}
		}
	}
}
