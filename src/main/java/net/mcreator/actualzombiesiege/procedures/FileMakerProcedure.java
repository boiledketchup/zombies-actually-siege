package net.mcreator.actualzombiesiege.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FileMakerProcedure {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		com.google.gson.JsonObject mainObj = new com.google.gson.JsonObject();
		com.google.gson.JsonObject subObj = new com.google.gson.JsonObject();
		File file = new File("");
		File readmefile = new File("");
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/config/actualzombiesieges/"), File.separator + "spawning.json");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			mainObj.addProperty("siege_loot", (true));
			mainObj.addProperty("soldier_spawn_cap", 6);
			mainObj.addProperty("location1.0", "minecraft:has_structure/village_desert");
			mainObj.addProperty("location2.0", "minecraft:has_structure/village_savanna");
			mainObj.addProperty("location3.0", "minecraft:has_structure/village_snowy");
			mainObj.addProperty("location4.0", "minecraft:has_structure/village_taiga");
			mainObj.addProperty("location5.0", "minecraft:has_structure/village_plains");
			mainObj.addProperty("location6.0", "minecraft:has_structure/swamp_hut");
			mainObj.addProperty("location7.0", "minecraft:has_structure/jungle_temple");
			mainObj.addProperty("location8.0", "minecraft:has_structure/woodland_mansion");
			mainObj.addProperty("prefixlocation1.0", "desert");
			mainObj.addProperty("prefixlocation2.0", "savanna");
			mainObj.addProperty("prefixlocation3.0", "snowy");
			mainObj.addProperty("prefixlocation4.0", "taiga");
			mainObj.addProperty("prefixlocation5.0", "plains");
			mainObj.addProperty("prefixlocation6.0", "swamp");
			mainObj.addProperty("prefixlocation7.0", "jungle");
			mainObj.addProperty("prefixlocation8.0", "darkforest");
			mainObj.addProperty("total_prefixes", 8);
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
	}
}
