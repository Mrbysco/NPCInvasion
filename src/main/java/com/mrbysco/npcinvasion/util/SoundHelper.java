package com.mrbysco.npcinvasion.util;

import com.mrbysco.npcinvasion.NPCInvasion;
import net.minecraft.resources.Identifier;
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


	public static final Map<Identifier, List<SoundReplacement>> replacementMap = new HashMap<>();

	public static boolean containsSound(Identifier originalSound) {
		return replacementMap.containsKey(originalSound);
	}

	public static SoundReplacement getRandomSound(Identifier originalSound) {
		if (replacementMap.containsKey(originalSound)) {
			List<SoundReplacement> list = replacementMap.get(originalSound);
			Collections.shuffle(list);
			SoundReplacement replacement = list.getFirst();
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
					Identifier location = Identifier.tryParse(configValue);
					if (location != null) {
						List<SoundReplacement> replacementList = replacementMap.getOrDefault(location, new ArrayList<>());
						replacementList.add(new SoundReplacement(event, chance));
						replacementMap.put(location, replacementList);
					} else {
						NPCInvasion.LOGGER.error("Invalid sound location used for NoSpicy: {}", configValue);
					}
				} else {
					NPCInvasion.LOGGER.error("Invalid sound location used for NoSpicy, could not find \":\" in {}", configValue);
				}
			}
		}
	}

	public static void sortCache() {
		if (!replacementMap.isEmpty()) {
			replacementMap.forEach((key, value) -> value.sort(Comparator.comparingDouble(SoundReplacement::chance)));
		}
	}

	public record SoundReplacement(SoundEvent soundEvent, double chance) {

	}
}
