package io.github.jadimatic.jadis_mended_progression.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.lib.config.MidnightConfig;
import io.github.jadimatic.jadis_mended_progression.JadisMendedProgression;
import org.jspecify.annotations.NonNull;

public class JadisMendedProgressionModMenu implements ModMenuApi {

	@Override
	public @NonNull ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> MidnightConfig.getScreen(parent, JadisMendedProgression.MOD_ID);
	}
}
