package com.lastprayer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("lastprayer")
public interface LastPrayerConfig extends Config
{
	@ConfigSection(
		name = "General",
		description = "General plugin settings",
		position = 0
	)
	String generalSection = "general";

	@ConfigItem(
		keyName = "enableOverlay",
		name = "Enable Overlay",
		description = "Show the last overhead prayer overlay on screen",
		section = generalSection,
		position = 1
	)
	default boolean enableOverlay()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showIcon",
		name = "Show Icon",
		description = "Show the prayer icon in the overlay",
		section = generalSection,
		position = 2
	)
	default boolean showIcon()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showText",
		name = "Show Text",
		description = "Show the prayer name in the overlay (disable for icon-only mode)",
		section = generalSection,
		position = 3
	)
	default boolean showText()
	{
		return true;
	}

	@ConfigItem(
		keyName = "hideWhenNone",
		name = "Hide When None",
		description = "Hide the overlay until an overhead prayer has been active",
		section = generalSection,
		position = 4
	)
	default boolean hideWhenNone()
	{
		return true;
	}

	@ConfigItem(
		keyName = "trackMelee",
		name = "Track Melee",
		description = "Enables tracking of protect from melee prayer",
		section = generalSection,
		position = 5
	)
	default boolean trackMelee()
	{
		return true;
	}

	@ConfigItem(
		keyName = "trackRange",
		name = "Track Range",
		description = "Enables tracking of protect from range prayer",
		section = generalSection,
		position = 6
	)
	default boolean trackRange()
	{
		return true;
	}

	@ConfigItem(
		keyName = "trackMagic",
		name = "Track Magic",
		description = "Enables tracking of protect from magic prayer",
		section = generalSection,
		position = 7
	)
	default boolean trackMagic()
	{
		return true;
	}

	@ConfigItem(
		keyName = "trackRetribution",
		name = "Track Retribution",
		description = "Enables tracking of retribution prayer",
		section = generalSection,
		position = 8
	)
	default boolean trackRetribution()
	{
		return true;
	}

	@ConfigItem(
		keyName = "trackRedemption",
		name = "Track Redemption",
		description = "Enables tracking of redemption prayer",
		section = generalSection,
		position = 9
	)
	default boolean trackRedemption()
	{
		return true;
	}

	@ConfigItem(
		keyName = "trackSmite",
		name = "Track Smite",
		description = "Enables tracking of smite prayer",
		section = generalSection,
		position = 10
	)
	default boolean trackSmite()
	{
		return true;
	}
}
