package com.mrbysco.npcinvasion.util;

import com.mrbysco.npcinvasion.NPCInvasion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SoundHelper {
	private static final Random rand = new Random();


	public static final Map<ResourceLocation, List<SoundReplacement>> replacementMap = new HashMap<>();

	public static boolean containsSound(ResourceLocation originalSound) {
		return replacementMap.containsKey(originalSound);
	}

	public static SoundReplacement getRandomSound(ResourceLocation originalSound) {
		if (replacementMap.containsKey(originalSound)) {
			List<SoundReplacement> list = replacementMap.get(originalSound);
			Collections.shuffle(list);
			SoundReplacement replacement = list.get(0);
			if (rand.nextDouble() <= replacement.chance) {
				return replacement;
			}
		}
		return null;
	}

	public static void clearCache() {
		replacementMap.clear();
	}

	public static void refreshCache(List<? extends String> configValues, SoundEvent event, double chance) {
		if (!configValues.isEmpty()) {
			for (String configValue : configValues) {
				if (configValue.contains(":")) {
					ResourceLocation location = ResourceLocation.tryParse(configValue);
					if (location != null) {
						List<SoundReplacement> replacementList = replacementMap.getOrDefault(location, new ArrayList<>());
						replacementList.add(new SoundReplacement(event, chance));
						replacementMap.put(location, replacementList);
					} else {
						NPCInvasion.LOGGER.error(String.format("Invalid sound location used for NoSpicy: %s", configValue));
					}
				} else {
					NPCInvasion.LOGGER.error(String.format("Invalid sound location used for NoSpicy, could not find \":\" in %s", configValue));
				}
			}
		}
	}

	public static void sortCache() {
		if (!replacementMap.isEmpty()) {
			for (Map.Entry<ResourceLocation, List<SoundReplacement>> entry : replacementMap.entrySet()) {
				List<SoundReplacement> replacements = entry.getValue();
				replacements.sort(Comparator.comparingDouble(SoundReplacement::chance));
				entry.setValue(replacements);
			}
		}
	}

	public record SoundReplacement(SoundEvent soundEvent, double chance) {

	}
}
