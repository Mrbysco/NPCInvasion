package com.mrbysco.npcinvasion.data;

import com.mrbysco.npcinvasion.NPCInvasion;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class NPCSoundProvider extends SoundDefinitionsProvider {

	public NPCSoundProvider(PackOutput packOutput, ExistingFileHelper helper) {
		super(packOutput, NPCInvasion.MOD_ID, helper);
	}

	@Override
	public void registerSounds() {
		this.add(NPCInvasion.NO_SPICY, definition()
				.subtitle(modSubtitle(NPCInvasion.NO_SPICY.getId()))
				.with(sound(modLoc("no_spicy"))));
		this.add(NPCInvasion.ICECREAM_SO_GOOD, definition()
				.subtitle(modSubtitle(NPCInvasion.ICECREAM_SO_GOOD.getId()))
				.with(sound(modLoc("icecream_so_good"))));
		this.add(NPCInvasion.GANGGANG, definition()
				.subtitle(modSubtitle(NPCInvasion.GANGGANG.getId()))
				.with(sound(modLoc("gg"))));
		this.add(NPCInvasion.YIPPEE, definition()
				.subtitle(modSubtitle(NPCInvasion.YIPPEE.getId()))
				.with(sound(modLoc("yippee"))));
	}

	public String modSubtitle(ResourceLocation id) {
		return NPCInvasion.MOD_ID + ".subtitle." + id.getPath();
	}

	public ResourceLocation modLoc(String name) {
		return new ResourceLocation(NPCInvasion.MOD_ID, name);
	}
}
