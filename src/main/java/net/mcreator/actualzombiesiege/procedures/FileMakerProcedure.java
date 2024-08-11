package net.mcreator.actualzombiesiege.procedures;

import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.world.WorldEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.client.Minecraft;

import net.mcreator.actualzombiesiege.ActualZombieSiegeMod;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@Mod.EventBusSubscriber
public class FileMakerProcedure {
	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event) {
		execute(event, event.getWorld());
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		com.google.gson.JsonObject mainObj = new com.google.gson.JsonObject();
		com.google.gson.JsonObject subObj = new com.google.gson.JsonObject();
		File file = new File("");
		File readmefile = new File("");
		File lootfile = new File("");
		File lootreadmefile = new File("");
		File lootnetherfile = new File("");
		File lootnethereadmefile = new File("");
		File lootendfile = new File("");
		File lootendreadmefile = new File("");
		file = new File((FMLPaths.GAMEDIR.get().toString() + ""
				+ ("/saves/" + ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/"))),
				File.separator + "spawning.json");
		readmefile = new File((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs/actualzombiesieges/"), File.separator + "spawning.json");
		lootfile = new File((FMLPaths.GAMEDIR.get().toString() + ""
				+ ("/saves/" + ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/"))),
				File.separator + "loot.json");
		lootreadmefile = new File((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs/actualzombiesieges/"), File.separator + "loot.json");
		lootnetherfile = new File((FMLPaths.GAMEDIR.get().toString() + ""
				+ ("/saves/" + ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/"))),
				File.separator + "loot_nether.json");
		lootnethereadmefile = new File((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs/actualzombiesieges/"), File.separator + "loot_nether.json");
		lootendfile = new File((FMLPaths.GAMEDIR.get().toString() + ""
				+ ("/saves/" + ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/"))),
				File.separator + "loot_end.json");
		lootendreadmefile = new File((FMLPaths.GAMEDIR.get().toString() + "/defaultconfigs/actualzombiesieges/"), File.separator + "loot_end.json");
		if (!readmefile.exists() && !file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			mainObj.addProperty("soldier_spawn_cap", 6);
			mainObj.addProperty("location1.0", "minecraft:has_structure/village_desert");
			mainObj.addProperty("location2.0", "minecraft:has_structure/village_savanna");
			mainObj.addProperty("location3.0", "minecraft:has_structure/village_snowy");
			mainObj.addProperty("location4.0", "minecraft:has_structure/village_taiga");
			mainObj.addProperty("location5.0", "minecraft:has_structure/village_plains");
			mainObj.addProperty("location6.0", "minecraft:has_structure/swamp_hut");
			mainObj.addProperty("location7.0", "minecraft:has_structure/jungle_temple");
			mainObj.addProperty("location8.0", "minecraft:has_structure/woodland_mansion");
			mainObj.addProperty("location9.0", "minecraft:is_nether");
			mainObj.addProperty("location10.0", "minecraft:is_end");
			mainObj.addProperty("prefixlocation1.0", "desert");
			mainObj.addProperty("prefixlocation2.0", "savanna");
			mainObj.addProperty("prefixlocation3.0", "snowy");
			mainObj.addProperty("prefixlocation4.0", "taiga");
			mainObj.addProperty("prefixlocation5.0", "plains");
			mainObj.addProperty("prefixlocation6.0", "swamp");
			mainObj.addProperty("prefixlocation7.0", "jungle");
			mainObj.addProperty("prefixlocation8.0", "darkforest");
			mainObj.addProperty("prefixlocation9.0", "nether");
			mainObj.addProperty("prefixlocation10.0", "end");
			mainObj.addProperty("total_prefixes", 10);
			mainObj.addProperty("if_missing_default_to", "plains");
			mainObj.addProperty("desert_soldier",
					"minecraft:husk ~ ~ ~ {PersistenceRequired:1b,CanPickUpLoot:1b,IsBaby:0,CanBreakDoors:1,HandItems:[{id:\"minecraft:iron_shovel\",Count:1},{}],HandDropChances:[0.1F,0F],ArmorItems:[{},{},{Count:1,id:\"minecraft:iron_chestplate\"},{id:\"minecraft:iron_helmet\",Count:1}],ArmorDropChances:[0F,0F,0.1F,0.1F]}");
			mainObj.addProperty("desert_soldier_variant",
					"minecraft:husk ~ ~ ~ {PersistenceRequired:1b,CanPickUpLoot:1b,IsBaby:0,CanBreakDoors:1,HandItems:[{id:\"minecraft:stone_axe\",Count:1},{}],HandDropChances:[0.1F,0F],ArmorItems:[{},{},{Count:1,id:\"minecraft:chainmail_chestplate\"},{id:\"minecraft:chainmail_helmet\",Count:1}],ArmorDropChances:[0F,0F,0.1F,0.1F]}");
			mainObj.addProperty("swamp_soldier",
					"minecraft:drowned ~ ~ ~ {PersistenceRequired:1b,CanPickUpLoot:1b,IsBaby:0,CanBreakDoors:1,HandItems:[{id:\"minecraft:iron_shovel\",Count:1},{}],HandDropChances:[0.1F,0F],ArmorItems:[{},{},{Count:1,id:\"minecraft:iron_chestplate\"},{id:\"minecraft:iron_helmet\",Count:1}],ArmorDropChances:[0F,0F,0.1F,0.1F]}");
			mainObj.addProperty("swamp_soldier_variant",
					"minecraft:drowned ~ ~ ~ {PersistenceRequired:1b,CanPickUpLoot:1b,IsBaby:0,CanBreakDoors:1,HandItems:[{id:\"minecraft:stone_axe\",Count:1},{}],HandDropChances:[0.1F,0F],ArmorItems:[{},{},{Count:1,id:\"minecraft:chainmail_chestplate\"},{id:\"minecraft:chainmail_helmet\",Count:1}],ArmorDropChances:[0F,0F,0.1F,0.1F]}");
			mainObj.addProperty("darkforest_captain_variant",
					"minecraft:skeleton_horse ~ ~ ~ {Passengers:[{id:\"minecraft:zombie_villager\",PersistenceRequired:1b,HandItems:[{id:\"minecraft:crossbow\",Count:1,tag:{Enchantments:[{id:quick_charge,lvl:3}]}},{}],HandDropChances:[0.1F,0F]}]}");
			mainObj.addProperty("plains_soldier",
					"minecraft:zombie ~ ~ ~ {PersistenceRequired:1b,CanPickUpLoot:1b,IsBaby:0,CanBreakDoors:1,HandItems:[{id:\"minecraft:iron_shovel\",Count:1},{}],HandDropChances:[0.1F,0F],ArmorItems:[{},{},{Count:1,id:\"minecraft:iron_chestplate\"},{id:\"minecraft:iron_helmet\",Count:1}],ArmorDropChances:[0F,0F,0.1F,0.1F]}");
			mainObj.addProperty("plains_soldier_variant",
					"minecraft:zombie ~ ~ ~ {PersistenceRequired:1b,CanPickUpLoot:1b,IsBaby:0,CanBreakDoors:1,HandItems:[{id:\"minecraft:iron_axe\",Count:1},{}],HandDropChances:[0.1F,0F],ArmorItems:[{},{},{Count:1,id:\"minecraft:chainmail_chestplate\"},{id:\"minecraft:chainmail_helmet\",Count:1}],ArmorDropChances:[0F,0F,0.1F,0.1F]}");
			mainObj.addProperty("plains_deputy",
					"minecraft:zombie ~ ~ ~ {PersistenceRequired:1b,CanPickUpLoot:1b,IsBaby:0,CanBreakDoors:1,HandItems:[{id:\"minecraft:iron_sword\",Count:1},{}],ArmorItems:[{Count:1,id:\"minecraft:iron_boots\"},{Count:1,id:\"minecraft:iron_leggings\"},{Count:1,id:\"minecraft:iron_chestplate\"},{id:\"minecraft:iron_helmet\",Count:1}],ArmorDropChances:[0.1F,0.1F,0.1F,0.1F]}");
			mainObj.addProperty("plains_deputy_variant",
					"minecraft:zombie ~ ~ ~ {PersistenceRequired:1b,CanPickUpLoot:1b,IsBaby:0,CanBreakDoors:1,HandItems:[{id:\"minecraft:iron_sword\",Count:1},{}],ArmorItems:[{Count:1,id:\"minecraft:iron_boots\"},{Count:1,id:\"minecraft:iron_leggings\"},{Count:1,id:\"minecraft:iron_chestplate\"},{id:\"minecraft:iron_helmet\",Count:1}],ArmorDropChances:[0.1F,0.1F,0.1F,0.1F]}");
			mainObj.addProperty("plains_captain",
					"minecraft:skeleton_horse ~ ~ ~ {Passengers:[{id:\"minecraft:zombie\",PersistenceRequired:1b,CanPickUpLoot:1b,IsBaby:0,CanBreakDoors:1,HandItems:[{id:\"minecraft:iron_sword\",Count:1},{}],HandDropChances:[0.1F,0F],ArmorItems:[{Count:1,tag:{Enchantments:[{id:feather_falling,lvl:4}]},id:\"minecraft:iron_boots\"},{Count:1,tag:{Enchantments:[{id:mending,lvl:1}]},id:\"minecraft:iron_leggings\"},{Count:1,tag:{Enchantments:[{id:unbreaking,lvl:3}]},id:\"minecraft:iron_chestplate\"},{id:\"minecraft:iron_helmet\",Count:1,tag:{Enchantments:[{id:fire_protection,lvl:4}]}}],ArmorDropChances:[0.05F,0.05F,0.05F,0.05F]}]}");
			mainObj.addProperty("plains_captain_variant",
					"minecraft:skeleton_horse ~ ~ ~ {Passengers:[{id:\"minecraft:zombie\",PersistenceRequired:1b,CanPickUpLoot:1b,IsBaby:0,CanBreakDoors:1,HandItems:[{id:\"minecraft:iron_sword\",Count:1},{}],HandDropChances:[0.1F,0F],ArmorItems:[{Count:1,tag:{Enchantments:[{id:feather_falling,lvl:4}]},id:\"minecraft:iron_boots\"},{Count:1,tag:{Enchantments:[{id:mending,lvl:1}]},id:\"minecraft:iron_leggings\"},{Count:1,tag:{Enchantments:[{id:unbreaking,lvl:3}]},id:\"minecraft:iron_chestplate\"},{id:\"minecraft:iron_helmet\",Count:1,tag:{Enchantments:[{id:fire_protection,lvl:4}]}}],ArmorDropChances:[0.05F,0.05F,0.05F,0.05F]}]}");
			mainObj.addProperty("nether_soldier", "minecraft:piglin ~ ~ ~ {PersistenceRequired:1b,Age:0,HandItems:[{id:\"minecraft:golden_sword\",Count:1},{}],ArmorItems:[{},{},{},{id:\"minecraft:golden_helmet\",Count:1}]}");
			mainObj.addProperty("nether_soldier_variant", "minecraft:piglin ~ ~ ~ {PersistenceRequired:1b,Age:0,HandItems:[{id:\"minecraft:crossbow\",Count:1},{}],ArmorItems:[{},{},{},{id:\"minecraft:golden_helmet\",Count:1}]}");
			mainObj.addProperty("nether_deputy", "minecraft:piglin_brute ~ ~ ~ {PersistenceRequired:1b,HandItems:[{id:\"minecraft:golden_axe\",Count:1},{}]}");
			mainObj.addProperty("nether_deputy_variant", "minecraft:piglin_brute ~ ~ ~ {PersistenceRequired:1b,HandItems:[{id:\"minecraft:golden_axe\",Count:1},{}]}");
			mainObj.addProperty("nether_captain",
					"minecraft:piglin ~ ~ ~ {PersistenceRequired:1b,Age:0,HandItems:[{id:\"minecraft:crossbow\",tag:{Enchantments:[{id:quick_charge,lvl:3}]},Count:1},{id:\"minecraft:warped_fungus\",Count:1}],ArmorItems:[{tag:{Enchantments:[{id:soul_speed,lvl:3}]},id:\"minecraft:golden_boots\",Count:1},{tag:{Enchantments:[{id:projectile_protection,lvl:4}]},id:\"minecraft:golden_leggings\",Count:1},{tag:{Enchantments:[{id:blast_protection,lvl:4}]},id:\"minecraft:golden_chestplate\",Count:1},{id:\"minecraft:golden_helmet\",Count:1,tag:{Enchantments:[{id:fire_protection,lvl:4}]}}]}");
			mainObj.addProperty("nether_captain_variant",
					"minecraft:piglin ~ ~ ~ {PersistenceRequired:1b,Age:0,HandItems:[{id:\"minecraft:crossbow\",tag:{Enchantments:[{id:quick_charge,lvl:3}]},Count:1},{id:\"minecraft:warped_fungus\",Count:1}],ArmorItems:[{tag:{Enchantments:[{id:soul_speed,lvl:3}]},id:\"minecraft:golden_boots\",Count:1},{tag:{Enchantments:[{id:projectile_protection,lvl:4}]},id:\"minecraft:golden_leggings\",Count:1},{tag:{Enchantments:[{id:blast_protection,lvl:4}]},id:\"minecraft:golden_chestplate\",Count:1},{id:\"minecraft:golden_helmet\",Count:1,tag:{Enchantments:[{id:fire_protection,lvl:4}]}}]}");
			mainObj.addProperty("end_soldier", "minecraft:enderman ~ ~ ~ {PersistenceRequired:1b}");
			mainObj.addProperty("end_soldier_variant", "minecraft:enderman ~ ~ ~ {PersistenceRequired:1b}");
			mainObj.addProperty("end_deputy", "minecraft:shulker ~ ~ ~ {PersistenceRequired:1b}");
			mainObj.addProperty("end_deputy_variant", "minecraft:shulker ~ ~ ~ {PersistenceRequired:1b}");
			mainObj.addProperty("end_captain", "minecraft:shulker ~ ~ ~ {PersistenceRequired:1b}");
			mainObj.addProperty("end_captain_variant", "minecraft:shulker ~ ~ ~ {PersistenceRequired:1b}");
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(mainGSONBuilderVariable.toJson(mainObj));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		if (readmefile.exists() && !file.exists()) {
			try {
				org.apache.commons.io.FileUtils.copyFile(readmefile, new File((FMLPaths.GAMEDIR.get().toString() + "" + ("/saves/"
						+ ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/")))));
			} catch (IOException e) {
				ActualZombieSiegeMod.LOGGER.error(e);
			}
		}
		if (!lootreadmefile.exists() && !lootfile.exists()) {
			try {
				lootfile.getParentFile().mkdirs();
				lootfile.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			subObj.addProperty("total_items", 4);
			subObj.addProperty("item1.0", "minecraft:diamond");
			subObj.addProperty("chance1.0", 5);
			subObj.addProperty("rolls1.0", 1);
			subObj.addProperty("item2.0", "minecraft:iron_ingot");
			subObj.addProperty("chance2.0", 30);
			subObj.addProperty("rolls2.0", 3);
			subObj.addProperty("item3.0", "minecraft:emerald");
			subObj.addProperty("chance3.0", 10);
			subObj.addProperty("rolls3.0", 3);
			subObj.addProperty("item4.0", "minecraft:iron_nugget");
			subObj.addProperty("chance4.0", 50);
			subObj.addProperty("rolls4.0", 8);
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(lootfile);
					fileWriter.write(mainGSONBuilderVariable.toJson(subObj));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		if (lootreadmefile.exists() && !lootfile.exists()) {
			try {
				org.apache.commons.io.FileUtils.copyFile(readmefile, new File((FMLPaths.GAMEDIR.get().toString() + "" + ("/saves/"
						+ ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/")))));
			} catch (IOException e) {
				ActualZombieSiegeMod.LOGGER.error(e);
			}
		}
		if (!lootnethereadmefile.exists() && !lootnetherfile.exists()) {
			try {
				lootnetherfile.getParentFile().mkdirs();
				lootnetherfile.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			subObj.addProperty("total_items", 4);
			subObj.addProperty("item1.0", "minecraft:netherite_scrap");
			subObj.addProperty("chance1.0", 5);
			subObj.addProperty("rolls1.0", 1);
			subObj.addProperty("item2.0", "minecraft:gold_ingot");
			subObj.addProperty("chance2.0", 30);
			subObj.addProperty("rolls2.0", 3);
			subObj.addProperty("item3.0", "minecraft:leather");
			subObj.addProperty("chance3.0", 10);
			subObj.addProperty("rolls3.0", 3);
			subObj.addProperty("item4.0", "minecraft:gold_nugget");
			subObj.addProperty("chance4.0", 50);
			subObj.addProperty("rolls4.0", 8);
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(lootnetherfile);
					fileWriter.write(mainGSONBuilderVariable.toJson(subObj));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		if (lootnethereadmefile.exists() && !lootnetherfile.exists()) {
			try {
				org.apache.commons.io.FileUtils.copyFile(lootnethereadmefile, new File((FMLPaths.GAMEDIR.get().toString() + "" + ("/saves/"
						+ ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/")))));
			} catch (IOException e) {
				ActualZombieSiegeMod.LOGGER.error(e);
			}
		}
		if (!lootendreadmefile.exists() && !lootendfile.exists()) {
			try {
				lootendfile.getParentFile().mkdirs();
				lootendfile.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			subObj.addProperty("total_items", 4);
			subObj.addProperty("item1.0", "minecraft:shulker_shell");
			subObj.addProperty("chance1.0", 5);
			subObj.addProperty("rolls1.0", 1);
			subObj.addProperty("item2.0", "minecraft:diamond");
			subObj.addProperty("chance2.0", 30);
			subObj.addProperty("rolls2.0", 3);
			subObj.addProperty("item3.0", "minecraft:bone");
			subObj.addProperty("chance3.0", 10);
			subObj.addProperty("rolls3.0", 3);
			subObj.addProperty("item4.0", "minecraft:iron_nugget");
			subObj.addProperty("chance4.0", 50);
			subObj.addProperty("rolls4.0", 8);
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(lootendfile);
					fileWriter.write(mainGSONBuilderVariable.toJson(subObj));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		if (lootendreadmefile.exists() && !lootendfile.exists()) {
			try {
				org.apache.commons.io.FileUtils.copyFile(lootendreadmefile, new File((FMLPaths.GAMEDIR.get().toString() + "" + ("/saves/"
						+ ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/")))));
			} catch (IOException e) {
				ActualZombieSiegeMod.LOGGER.error(e);
			}
		}
	}
}
