* The above code is not actually up to date, I've been having difficulties uploading it. The below wiki is up to date with the latest version.

# Zombies Actually Siege Forge 1.18.2
Zombies Actually Siege is a simple mod that improves the experience of Zombie Sieges, by adding some extra normal zombies with armor and a few special attributes.
When a Zombie Siege occurs, a random configurable number of "soldiers" will spawn (depending on difficulty) as well as a "captain" riding a skeleton horse, and occasionally a "deputy" for the captain as well.
All special siege mobs have a special loot table which includes iron and emeralds. Once all siege mobs have been killed, if there is still a villager alive in the village, you will gain Hero Of The Village for three minutes and quite a bit of XP. If there is no villager present, you will just get a little more XP than you usually do for killing normal zombies, and no Hero Of The Village.
## Config Quick Wiki
### Configuring Spawning

"soldier_spawn_cap": a number, the highest number of soldiers that can spawn per siege

"locationY.0": "modid:biome_tag"

"prefixlocationY.0": a string, anything you want

"total_prefixes": a number, the amount of prefixes you have, total

"if_missing_default_to": a string, one of the prefixes you have set that you want to be the default

#### Above is most of the required variables, you will also need a value for each siege mob type for your "if_missing_default_to" (X in this example) value.

Siege Mobs = X_soldier, X_solider_variant, X_deputy, X_deputy_variant, X_captain, X_captian_variant

### Configuring Loot

"total_items": a number, the total number of items to be picked from

"itemY.0": "modid:item"

"chanceY.0": a number, the chance, as a percent, that the associated item is dropped

"rollsY.0": a number, the number of times that the chance associated with the item will be repeated

### General Explanation

When a siege happens, it iterates through the list of all locations, and when it finds a matching one, it jumps to the prefix for the same value. (locationY.Y == prefixY.Y)
It does run in order, so a lower value location tag will overwrite a higher value location tag if a biome has both of them.
When it finds the prefix, it will spawn the appropriate siege mobs (defined above) based on it. If a siege mob for a prefix is not present, it will set to the "if_missing_default_to" value.
It is REQUIRED to have every siege mob type for the "if_missing_default_to" value.
It is also required to have a prefix for every location, even if you set the prefixes to be the same.

The spawn cap is for hard mode, in normal mode it is multiplied by 0.75x and in easy mode by 0.5x.

For loot: Each time a siege mob dies, it will run through each of the items on this file. For each item, it will try at the chance dictated the number of times dictated by the rolls. For example, if an item as 8 rolls and a 50% chance, then it will repeat the 50% chance 8 times. If an item has 1 roll and a 100% chance, then that item will drop from every single siege mob, exactly once.
