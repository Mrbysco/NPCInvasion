package com.mrbysco.npcinvasion.client;

import com.mrbysco.npcinvasion.util.SoundHelper;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.neoforged.neoforge.client.event.sound.PlaySoundEvent;

public class ClientHandler {
	public static void onSound(PlaySoundEvent event) {
		SoundInstance original = event.getOriginalSound();
		if (original == null || !SoundHelper.containsSound(original.getIdentifier()))
			return;

		SoundHelper.SoundReplacement replacement = SoundHelper.getRandomSound(original.getIdentifier());
		if (replacement != null) {
			SoundInstance soundInstance = original;
			float volume = soundInstance.getSound() != null ? soundInstance.getVolume() : 1.0F;
			SoundInstance replacedInstance = new SimpleSoundInstance(replacement.soundEvent(), soundInstance.getSource(),
					volume, 1.0F, SoundInstance.createUnseededRandom(),
					soundInstance.getX(), soundInstance.getY(), soundInstance.getZ());
			event.setSound(replacedInstance);
		}
	}
}
