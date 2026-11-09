package io.github.jadimatic.jadis_mended_progression.item.component;

import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;

public class JadisMendedDataComponents {
	protected static void initialize() {
		JadisMendedProgression.LOGGER.info("Registering {} components", JadisMendedProgression.MOD_ID);
	}

	/**
	 * Uses a fraction to assign the amount of durability restored per repair item used in the anvil with this item.<p>
	 * NOTE: This is similar but distinct from 0-100%, 0.5 being comparable to 50%.
	 */
	public static final DataComponentType<Float> REPAIR_AMOUNT = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		JadisMendedProgression.id("repair_amount"),
		DataComponentType.<Float>builder().persistent(ExtraCodecs.NON_NEGATIVE_FLOAT).networkSynchronized(ByteBufCodecs.FLOAT).build()
	);





}
