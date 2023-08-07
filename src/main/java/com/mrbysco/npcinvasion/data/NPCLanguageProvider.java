package com.mrbysco.npcinvasion.data;

import com.mrbysco.npcinvasion.NPCInvasion;
import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class NPCLanguageProvider extends LanguageProvider {
	public NPCLanguageProvider(DataGenerator generator) {
		super(generator, NPCInvasion.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		addSubtitle(NPCInvasion.NO_SPICY, "🌶 No Spicy!");
		addSubtitle(NPCInvasion.ICECREAM_SO_GOOD, "🍧 Ice cream so good!");
		addSubtitle(NPCInvasion.GANGGANG, "Gang Gang");
		addSubtitle(NPCInvasion.YIPPEE, "Yippeee!");
	}

	/**
	 * Add a subtitle to a sound event
	 *
	 * @param sound The sound event registry object
	 * @param text  The subtitle text
	 */
	public void addSubtitle(RegistryObject<SoundEvent> sound, String text) {
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
}
