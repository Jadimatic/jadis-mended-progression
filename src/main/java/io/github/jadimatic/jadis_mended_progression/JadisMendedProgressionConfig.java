package io.github.jadimatic.jadis_mended_progression;


import eu.midnightdust.lib.config.MidnightConfig;

public class JadisMendedProgressionConfig extends MidnightConfig {
	public static final String CONDITIONS = "conditions";




	@Entry(category = CONDITIONS, name = "Fall out of trees")
	public static boolean fallOutOfTrees = true;
}
