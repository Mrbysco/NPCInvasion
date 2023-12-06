package com.mrbysco.npcinvasion;

import com.mojang.logging.LogUtils;
import com.mrbysco.npcinvasion.client.ClientHandler;
import com.mrbysco.npcinvasion.config.NPCConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(NPCInvasion.MOD_ID)
public class NPCInvasion {
	public static final String MOD_ID = "npcinvasion";
	public static final Logger LOGGER = LogUtils.getLogger();

	private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, MOD_ID);

	public static final DeferredHolder<SoundEvent, SoundEvent> NO_SPICY = registerSound("no_spicy");
	public static final DeferredHolder<SoundEvent, SoundEvent> ICECREAM_SO_GOOD = registerSound("icecream_so_good");
	public static final DeferredHolder<SoundEvent, SoundEvent> GANGGANG = registerSound("ganggang");
	public static final DeferredHolder<SoundEvent, SoundEvent> YIPPEE = registerSound("yippee");

	public NPCInvasion() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NPCConfig.commonSpec);
		eventBus.register(NPCConfig.class);

		SOUND_EVENTS.register(eventBus);

		if (FMLEnvironment.dist.isClient()) {
			NeoForge.EVENT_BUS.addListener(ClientHandler::onSound);
		}
	}

	private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
		return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(NPCInvasion.MOD_ID, name)));
	}
}
