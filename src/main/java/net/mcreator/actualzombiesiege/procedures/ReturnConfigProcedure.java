package net.mcreator.actualzombiesiege.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;

import net.mcreator.actualzombiesiege.network.ActualZombieSiegeModVariables;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class ReturnConfigProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		File file = new File("");
		com.google.gson.JsonObject mainObj = new com.google.gson.JsonObject();
		double location = 0;
		double i = 0;
		String prefix = "";
		String export = "";
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/config/actualzombiesieges/"), File.separator + "spawning.json");
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
				if (!((ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn).equals("soldier_spawn_cap")
						|| (ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn).equals("siege_loot"))) {
					i = 0;
					location = 0;
					prefix = "0";
					for (int index0 = 0; index0 < (int) (mainObj.get("total_prefixes").getAsDouble()); index0++) {
						i = i + 1;
						if (world.getBiome(new BlockPos(x, y, z)).is(TagKey.create(Registry.BIOME_REGISTRY,
								new ResourceLocation((mainObj.get(("location" + i)).getAsString()).toLowerCase(java.util.Locale.ENGLISH))))) {
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
					if (mainObj.get((prefix + "" + ("_" + ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn))) != null) {
						export = mainObj.get((prefix + "" + ("_" + ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn)))
								.getAsString();
					} else {
						prefix = mainObj.get("if_missing_default_to").getAsString();
						export = mainObj.get((prefix + "" + ("_" + ActualZombieSiegeModVariables.WorldVariables.get(world).configReturn)))
								.getAsString();
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
		return export;
	}
}
