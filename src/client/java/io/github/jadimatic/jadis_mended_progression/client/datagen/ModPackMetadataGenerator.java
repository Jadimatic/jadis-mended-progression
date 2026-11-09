package io.github.jadimatic.jadis_mended_progression.client.datagen;

import io.github.jadimatic.jadis_mended_progression.client.mixin.IdentifierPatternAccessor;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.SharedConstants;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.resources.ResourceFilterSection;
import net.minecraft.util.IdentifierPattern;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ModPackMetadataGenerator extends PackMetadataGenerator {
	public ModPackMetadataGenerator(FabricPackOutput output) {
		super(output);



		final var packMetadata = new PackMetadataGenerator(output);

		this.add(
			PackMetadataSection.SERVER_TYPE,
			new PackMetadataSection(
				Component.translatable("jadis_mended_progression.pack.description"),
				SharedConstants.getCurrentVersion().packVersion(PackType.SERVER_DATA).minorRange()
			)
		);



		packMetadata.add(ResourceFilterSection.TYPE, new ResourceFilterSection(
			List.of(IdentifierPatternAccessor.jadis_mended_progression_datagen$construct(
					Optional.of(Pattern.compile("minecraft")),
					Optional.of(Pattern.compile("recipe/wooden_axe.json"))
				),
					IdentifierPatternAccessor.jadis_mended_progression_datagen$construct(
					Optional.of(Pattern.compile("minecraft")),
					Optional.of(Pattern.compile("advancement/recipes/tools/wooden_axe.json"))
				)
			)
		));


		// the contents of the output -> {...} lambda go here *except 'packMetadata' is 'this'
	}
}
