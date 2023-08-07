package com.mrbysco.npcinvasion.config;

import com.mrbysco.npcinvasion.NPCInvasion;
import com.mrbysco.npcinvasion.util.SoundHelper;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class NPCConfig {
	public static class Common {
		public final ForgeConfigSpec.DoubleValue spicyChance;
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> noSpicy;
		public final ForgeConfigSpec.DoubleValue icecreamChance;
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> icecream;
		public final ForgeConfigSpec.DoubleValue ganggangChance;
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> ganggang;
		public final ForgeConfigSpec.DoubleValue yippeeChance;
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> yippee;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("General");

			spicyChance = builder
					.comment("The chance a 'No Spicy!' plays instead it's gets chosen (0.01 = 1%, 0.5 = 50%)[default: 0.01]")
					.defineInRange("spicyChance", 0.02D, 0.001D, 1.0D);

			noSpicy = builder
					.comment("What sound to replace with 'No Spicy!'")
					.defineListAllowEmpty(List.of("noSpicy"), () -> List.of("minecraft:entity.creeper.primed"),
							o -> (o instanceof String));

			icecreamChance = builder
					.comment("The chance a 'Ice Cream So Good' plays instead it's gets chosen (0.01 = 1%, 0.5 = 50%)[default: 0.01]")
					.defineInRange("icecreamChance", 0.05D, 0.001D, 1.0D);

			icecream = builder
					.comment("What sound to replace with 'Ice Cream So Good!'")
					.defineListAllowEmpty(List.of("icecream"), () -> List.of("minecraft:entity.creeper.primed"),
							o -> (o instanceof String));

			ganggangChance = builder
					.comment("The chance a 'Gang Gang' plays instead it's gets chosen (0.01 = 1%, 0.5 = 50%)[default: 0.01]")
					.defineInRange("ganggangChance", 0.03D, 0.001D, 1.0D);

			ganggang = builder
					.comment("What sound to replace with 'Gang Gang'")
					.defineListAllowEmpty(List.of("ganggang"), () -> List.of("minecraft:entity.creeper.primed"),
							o -> (o instanceof String));

			yippeeChance = builder
					.comment("The chance a 'Yippee' plays instead it's gets chosen (0.01 = 1%, 0.5 = 50%)[default: 0.01]")
					.defineInRange("yippeeChance", 0.04D, 0.001D, 1.0D);

			yippee = builder
					.comment("What sound to replace with 'Yippeee'")
					.defineListAllowEmpty(List.of("yippee"), () -> List.of("minecraft:entity.generic.explode"),
							o -> (o instanceof String));

			builder.pop();
		}
	}

	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent configEvent) {
		ModConfig config = configEvent.getConfig();
		NPCInvasion.LOGGER.debug("Loaded No Spicy's config file {}", config.getFileName());
		refreshCache(config.getType());
	}

	private static void refreshCache(ModConfig.Type type) {
		if (type == ModConfig.Type.COMMON) {
			SoundHelper.clearCache();
			SoundHelper.refreshCache(NPCConfig.COMMON.noSpicy.get(), NPCInvasion.NO_SPICY.get(), NPCConfig.COMMON.spicyChance.get());
			SoundHelper.refreshCache(NPCConfig.COMMON.icecream.get(), NPCInvasion.ICECREAM_SO_GOOD.get(), NPCConfig.COMMON.icecreamChance.get());
			SoundHelper.refreshCache(NPCConfig.COMMON.ganggang.get(), NPCInvasion.GANGGANG.get(), NPCConfig.COMMON.ganggangChance.get());
			SoundHelper.refreshCache(NPCConfig.COMMON.yippee.get(), NPCInvasion.YIPPEE.get(), NPCConfig.COMMON.yippeeChance.get());
			SoundHelper.sortCache();
		}
	}
}
