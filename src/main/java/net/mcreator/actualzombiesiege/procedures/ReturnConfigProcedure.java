package net.mcreator.actualzombiesiege.procedures;

import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import net.mcreator.actualzombiesiege.network.ActualZombieSiegeModVariables;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class ReturnConfigProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		com.google.gson.JsonObject mainObj = new com.google.gson.JsonObject();
		double location = 0;
		double i = 0;
		String prefix = "";
		String export = "";
		File file = new File("");
		File lootfile = new File("");
		file = new File((FMLPaths.GAMEDIR.get().toString() + ""
				+ ("/saves/" + ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/"))),
				File.separator + "spawning.json");
		if ((world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD) == (Level.END)) {
			lootfile = new File(
					(FMLPaths.GAMEDIR.get().toString() + "" + ("/saves/"
							+ ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/"))),
					File.separator + "loot_end.json");
		} else if ((world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD) == (Level.NETHER)) {
			lootfile = new File(
					(FMLPaths.GAMEDIR.get().toString() + "" + ("/saves/"
							+ ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/"))),
					File.separator + "loot_nether.json");
		} else {
			lootfile = new File(
					(FMLPaths.GAMEDIR.get().toString() + "" + ("/saves/"
							+ ((world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + "/serverconfig/actualzombiesieges/"))),
					File.separator + "loot.json");
		}
		if (ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn.contains("item") || ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn.contains("chance")
				|| ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn.contains("rolls") || ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn.contains("total_items")) {
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(lootfile));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					mainObj = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					if (ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn.contains("item")) {
						export = mainObj.get(ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn).getAsString() + "";
					} else {
						export = mainObj.get(ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn).getAsDouble() + "";
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					mainObj = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					if (!((ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn).equals("soldier_spawn_cap") || (ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn).equals("siege_loot"))) {
						i = 0;
						location = 0;
						prefix = "0";
						for (int index0 = 0; index0 < (int) (mainObj.get("total_prefixes").getAsDouble()); index0++) {
							i = i + 1;
							if (world.getBiome(new BlockPos(x, y, z)).is(TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation((mainObj.get(("location" + i)).getAsString()).toLowerCase(java.util.Locale.ENGLISH))))) {
								location = i;
								break;
							} else {
								location = 0;
							}
						}
						if (location == 0) {
							prefix = mainObj.get("if_missing_default_to").getAsString();
						} else {
							prefix = mainObj.get(("prefixlocation" + location)).getAsString();
						}
						if (mainObj.has((prefix + "" + ("_" + ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn)))) {
							export = mainObj.get((prefix + "" + ("_" + ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn))).getAsString();
						} else {
							prefix = mainObj.get("if_missing_default_to").getAsString();
							export = mainObj.get((prefix + "" + ("_" + ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn))).getAsString();
						}
					} else {
						if ((ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn).equals("soldier_spawn_cap")) {
							export = mainObj.get("soldier_spawn_cap").getAsDouble() + "";
						} else {
							export = mainObj.get("siege_loot").getAsBoolean() + "";
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return export;
	}
}
