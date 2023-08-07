package com.mrbysco.npcinvasion;

import com.mojang.logging.LogUtils;
import com.mrbysco.npcinvasion.client.ClientHandler;
import com.mrbysco.npcinvasion.config.NPCConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(NPCInvasion.MOD_ID)
public class NPCInvasion {
	public static final String MOD_ID = "npcinvasion";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);


	public static final RegistryObject<SoundEvent> NO_SPICY = registerSound("no_spicy");
	public static final RegistryObject<SoundEvent> ICECREAM_SO_GOOD = registerSound("icecream_so_good");
	public static final RegistryObject<SoundEvent> GANGGANG = registerSound("ganggang");
	public static final RegistryObject<SoundEvent> YIPPEE = registerSound("yippee");

	public NPCInvasion() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NPCConfig.commonSpec);
		eventBus.register(NPCConfig.class);

		SOUND_EVENTS.register(eventBus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			MinecraftForge.EVENT_BUS.addListener(ClientHandler::onSound);
		});
	}

	private static RegistryObject<SoundEvent> registerSound(String name) {
		return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(NPCInvasion.MOD_ID, name)));
	}
}
