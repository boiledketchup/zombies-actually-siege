package net.mcreator.actualzombiesiege.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.ModList;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import java.util.Random;

public class UnnaturalSiegeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		sx = -32;
		found = false;
		if ((world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD) == (Level.END) || (world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD) == (Level.NETHER)) {
			for (int index0 = 0; index0 < (int) (65); index0++) {
				sy = -32;
				for (int index1 = 0; index1 < (int) (65); index1++) {
					sz = -32;
					for (int index2 = 0; index2 < (int) (65); index2++) {
						if (world.getBlockState(new BlockPos(x + sx, (y + sy) - 1, z + sz)).canOcclude() && world.isEmptyBlock(new BlockPos(x + sx, y + sy, z + sz)) && world.isEmptyBlock(new BlockPos(x + sx, y + sy + 1, z + sz))
								&& world.isEmptyBlock(new BlockPos(x + sx, y + sy + 2, z + sz)) && world.isEmptyBlock(new BlockPos(x + sx + 1, y + sy, z + sz)) && world.isEmptyBlock(new BlockPos((x + sx) - 1, y + sy, z + sz))
								&& world.isEmptyBlock(new BlockPos(x + sx, y + sy, z + sz + 1)) && world.isEmptyBlock(new BlockPos(x + sx, y + sy, (z + sz) - 1)) && world.isEmptyBlock(new BlockPos(x + sx + 1, y + sy, z + sz + 1))
								&& world.isEmptyBlock(new BlockPos((x + sx) - 1, y + sy, (z + sz) - 1)) && world.isEmptyBlock(new BlockPos(x + sx + 1, y + sy, (z + sz) - 1)) && world.isEmptyBlock(new BlockPos((x + sx) - 1, y + sy, z + sz + 1))) {
							found = true;
							break;
						}
						sz = sz + 1;
					}
					if (found) {
						break;
					}
					sy = sy + 1;
				}
				if (found) {
					break;
				}
				sx = sx + 1;
			}
		} else {
			for (int index3 = 0; index3 < (int) (65); index3++) {
				sy = -32;
				for (int index4 = 0; index4 < (int) (65); index4++) {
					sz = -32;
					for (int index5 = 0; index5 < (int) (65); index5++) {
						if (world.canSeeSkyFromBelowWater(new BlockPos(x + sx, y + sy, z + sz)) && world.getBlockState(new BlockPos(x + sx, (y + sy) - 1, z + sz)).canOcclude()
								&& world.getBlockState(new BlockPos(x + sx, y + sy, z + sz)).getLightEmission(world, new BlockPos(x + sx, y + sy, z + sz)) == 0 && world.isEmptyBlock(new BlockPos(x + sx, y + sy, z + sz))) {
							found = true;
							break;
						}
						sz = sz + 1;
					}
					if (found) {
						break;
					}
					sy = sy + 1;
				}
				if (found) {
					break;
				}
				sx = sx + 1;
			}
		}
		if (found && !(world instanceof Level _lvl && _lvl.isDay())) {
			ZombieSiegeOccursProcedure.execute(world, (x + sx), (y + sy), (z + sz));
			for (int index6 = 0; index6 < (int) (Mth.nextInt(new Random(), 3, 12)); index6++) {
				if ((world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD) == (Level.END)) {
					if (ModList.get().isLoaded("varaventure")) {
						if (world instanceof ServerLevel _level) {
							Entity entityToSpawn = new WitherSkeleton(EntityType.WITHER_SKELETON, _level);
							entityToSpawn.moveTo((x + sx), (y + sy + 1), (z + sz), 0, 0);
							entityToSpawn.setYBodyRot(0);
							entityToSpawn.setYHeadRot(0);
							entityToSpawn.setDeltaMovement(0, 0, 0);
							if (entityToSpawn instanceof Mob _mobToSpawn)
								_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
							world.addFreshEntity(entityToSpawn);
						}
					} else {
						if (world instanceof ServerLevel _level) {
							Entity entityToSpawn = new EnderMan(EntityType.ENDERMAN, _level);
							entityToSpawn.moveTo((x + sx), (y + sy + 1), (z + sz), 0, 0);
							entityToSpawn.setYBodyRot(0);
							entityToSpawn.setYHeadRot(0);
							entityToSpawn.setDeltaMovement(0, 0, 0);
							if (entityToSpawn instanceof Mob _mobToSpawn)
								_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
							world.addFreshEntity(entityToSpawn);
						}
					}
				} else if ((world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD) == (Level.NETHER)) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = new ZombifiedPiglin(EntityType.ZOMBIFIED_PIGLIN, _level);
						entityToSpawn.moveTo((x + sx), (y + sy + 1), (z + sz), 0, 0);
						entityToSpawn.setYBodyRot(0);
						entityToSpawn.setYHeadRot(0);
						entityToSpawn.setDeltaMovement(0, 0, 0);
						if (entityToSpawn instanceof Mob _mobToSpawn)
							_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
						world.addFreshEntity(entityToSpawn);
					}
				} else {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = new Zombie(EntityType.ZOMBIE, _level);
						entityToSpawn.moveTo((x + sx), (y + sy + 1), (z + sz), 0, 0);
						entityToSpawn.setYBodyRot(0);
						entityToSpawn.setYHeadRot(0);
						entityToSpawn.setDeltaMovement(0, 0, 0);
						if (entityToSpawn instanceof Mob _mobToSpawn)
							_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
						world.addFreshEntity(entityToSpawn);
					}
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.cure")), SoundSource.BLOCKS, (float) 0.3, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.cure")), SoundSource.BLOCKS, (float) 0.3, 1, false);
				}
			}
			if (!(new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayer _serverPlayer) {
						return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
					} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
						return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
								&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
					}
					return false;
				}
			}.checkGamemode(entity))) {
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem(), 32);
				((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)).shrink(1);
			}
		} else {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.step")), SoundSource.BLOCKS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.step")), SoundSource.BLOCKS, 1, 1, false);
				}
			}
			if (!found) {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(new TextComponent("No valid location found!"), (true));
			} else {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(new TextComponent("Wait until nightfall!"), (true));
			}
		}
	}
}
