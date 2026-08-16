package com.lastprayer;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Last Prayer",
	description = "Shows your last active overhead prayer",
	tags = {"prayer", "combat"}
)
public class LastPrayerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private LastPrayerConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private LastPrayerOverlay overlay;

	@Getter
	private OverheadPrayer lastOverheadPrayer;

	@Override
	protected void startUp()
	{
		lastOverheadPrayer = null;
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
		lastOverheadPrayer = null;
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		for (OverheadPrayer overheadPrayer : OverheadPrayer.values())
		{
			if (client.isPrayerActive(overheadPrayer.getPrayer()))
			{
				lastOverheadPrayer = overheadPrayer;
				return;
			}
		}
	}

	@Provides
	LastPrayerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LastPrayerConfig.class);
	}
}
