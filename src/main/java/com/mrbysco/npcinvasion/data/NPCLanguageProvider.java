package com.mrbysco.npcinvasion.data;

import com.mrbysco.npcinvasion.NPCInvasion;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class NPCLanguageProvider extends LanguageProvider {
	public NPCLanguageProvider(PackOutput packOutput) {
		super(packOutput, NPCInvasion.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		addSubtitle(NPCInvasion.NO_SPICY, "🌶 No Spicy!");
		addSubtitle(NPCInvasion.ICECREAM_SO_GOOD, "🍧 Ice cream so good!");
		addSubtitle(NPCInvasion.GANGGANG, "Gang Gang");
		addSubtitle(NPCInvasion.YIPPEE, "Yippeee!");

		addConfig("title", "NPC Invasion Config", null);
		addConfig("General", "General", "General settings");
		addConfig("spicyChance", "Spicy Chance", "The chance a 'No Spicy!' plays instead it's gets chosen (0.01 = 1%, 0.5 = 50%)[default: 0.01]");
		addConfig("noSpicy", "No Spicy", "What sound to replace with 'No Spicy!'");
		addConfig("icecreamChance", "Ice Cream Chance", "The chance a 'Ice Cream So Good' plays instead it's gets chosen (0.01 = 1%, 0.5 = 50%)[default: 0.01]");
		addConfig("icecream", "Ice Cream", "What sound to replace with 'Ice Cream So Good!'");
		addConfig("ganggangChance", "Gang Gang Chance", "The chance a 'Gang Gang' plays instead it's gets chosen (0.01 = 1%, 0.5 = 50%)[default: 0.01]");
		addConfig("ganggang", "Gang Gang", "What sound to replace with 'Gang Gang'");
		addConfig("yippeeChance", "Yippee Chance", "The chance a 'Yippee' plays instead it's gets chosen (0.01 = 1%, 0.5 = 50%)[default: 0.01]");
		addConfig("yippee", "Yippee", "What sound to replace with 'Yippeee'");
	}

	/**
	 * Add a subtitle to a sound event
	 *
	 * @param sound The sound event registry object
	 * @param text  The subtitle text
	 */
	public void addSubtitle(Supplier<SoundEvent> sound, String text) {
		this.addSubtitle(sound.get(), text);
	}

	/**
	 * Add a subtitle to a sound event
	 *
	 * @param sound The sound event
	 * @param text  The subtitle text
	 */
	public void addSubtitle(SoundEvent sound, String text) {
		String path = NPCInvasion.MOD_ID + ".subtitle." + sound.getLocation().getPath();
		this.add(path, text);
	}

	/**
	 * Add the translation for a config entry
	 *
	 * @param path        The path of the config entry
	 * @param name        The name of the config entry
	 * @param description The description of the config entry (optional in case of targeting "title" or similar entries that have no tooltip)
	 */
	private void addConfig(String path, String name, @Nullable String description) {
		this.add("npcinvasion.configuration." + path, name);
		if (description != null && !description.isEmpty())
			this.add("npcinvasion.configuration." + path + ".tooltip", description);
	}
}
