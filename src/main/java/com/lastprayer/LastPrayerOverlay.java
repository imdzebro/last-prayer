package com.lastprayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import javax.inject.Inject;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ComponentConstants;

class LastPrayerOverlay extends Overlay
{
	private static final int ICON_SIZE = 24;
	private static final int ICON_TEXT_GAP = 4;
	private static final int BORDER = ComponentConstants.STANDARD_BORDER;

	private final LastPrayerPlugin plugin;
	private final LastPrayerConfig config;
	private final SpriteManager spriteManager;
	private final Map<OverheadPrayer, BooleanSupplier> prayerFilter;

	private OverheadPrayer cachedPrayer;
	private BufferedImage cachedSprite;

	@Inject
	private LastPrayerOverlay(LastPrayerPlugin plugin, LastPrayerConfig config, SpriteManager spriteManager)
	{
		super(plugin);
		this.plugin = plugin;
		this.config = config;
		this.spriteManager = spriteManager;
		setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
		setLayer(OverlayLayer.ABOVE_SCENE);

		prayerFilter = new EnumMap<>(OverheadPrayer.class);
		prayerFilter.put(OverheadPrayer.PROTECT_FROM_MELEE,    config::trackMelee);
		prayerFilter.put(OverheadPrayer.PROTECT_FROM_MISSILES, config::trackRange);
		prayerFilter.put(OverheadPrayer.PROTECT_FROM_MAGIC,    config::trackMagic);
		prayerFilter.put(OverheadPrayer.RETRIBUTION,           config::trackRetribution);
		prayerFilter.put(OverheadPrayer.REDEMPTION,            config::trackRedemption);
		prayerFilter.put(OverheadPrayer.SMITE,                 config::trackSmite);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!config.enableOverlay())
		{
			return null;
		}

		OverheadPrayer lastPrayer = plugin.getLastOverheadPrayer();
		if (lastPrayer == null)
		{
			if (config.hideWhenNone())
			{
				return null;
			}
			return drawText(graphics, "None", Color.GRAY);
		}

		updateCachedSprite(lastPrayer);

		boolean showIcon = config.showIcon();
		boolean showText = config.showText();

		if (!showIcon && !showText)
		{
			return null;
		}
		
		if (!shouldShowPrayer(lastPrayer))
		{
			return null;
		}

		if (showIcon && showText)
		{
			return drawIconAndText(graphics, cachedSprite, lastPrayer.getDisplayName());
		}
		else if (showIcon)
		{
			return drawIcon(graphics, cachedSprite);
		}
		else
		{
			return drawText(graphics, lastPrayer.getDisplayName(), Color.WHITE);
		}
	}

	private boolean shouldShowPrayer(OverheadPrayer prayer)
	{
		BooleanSupplier filter = prayerFilter.get(prayer);
		return filter != null && filter.getAsBoolean();
	}

	private Dimension drawIconAndText(Graphics2D graphics, BufferedImage icon, String text)
	{
		FontMetrics fm = graphics.getFontMetrics();
		int textW = fm.stringWidth(text);
		int iconW = icon != null ? ICON_SIZE : 0;
		int contentH = Math.max(iconW, fm.getHeight());

		int totalW = BORDER + iconW + (icon != null ? ICON_TEXT_GAP : 0) + textW + BORDER;
		int totalH = contentH + 2 * BORDER;

		graphics.setColor(ComponentConstants.STANDARD_BACKGROUND_COLOR);
		graphics.fillRect(0, 0, totalW, totalH);

		int x = BORDER;
		if (icon != null)
		{
			int iconY = BORDER + (contentH - ICON_SIZE) / 2;
			graphics.drawImage(icon, x, iconY, ICON_SIZE, ICON_SIZE, null);
			x += ICON_SIZE + ICON_TEXT_GAP;
		}

		int textY = BORDER + (contentH - fm.getHeight()) / 2 + fm.getAscent();
		graphics.setColor(Color.WHITE);
		graphics.drawString(text, x, textY);

		return new Dimension(totalW, totalH);
	}

	private Dimension drawIcon(Graphics2D graphics, BufferedImage icon)
	{
		if (icon == null)
		{
			return null;
		}
		int totalW = ICON_SIZE + 2 * BORDER;
		int totalH = ICON_SIZE + 2 * BORDER;
		graphics.setColor(ComponentConstants.STANDARD_BACKGROUND_COLOR);
		graphics.fillRect(0, 0, totalW, totalH);
		graphics.drawImage(icon, BORDER, BORDER, ICON_SIZE, ICON_SIZE, null);
		return new Dimension(totalW, totalH);
	}

	private Dimension drawText(Graphics2D graphics, String text, Color color)
	{
		FontMetrics fm = graphics.getFontMetrics();
		int totalW = fm.stringWidth(text) + 2 * BORDER;
		int totalH = fm.getHeight() + 2 * BORDER;
		graphics.setColor(ComponentConstants.STANDARD_BACKGROUND_COLOR);
		graphics.fillRect(0, 0, totalW, totalH);
		graphics.setColor(color);
		graphics.drawString(text, BORDER, BORDER + fm.getAscent());
		return new Dimension(totalW, totalH);
	}

	private void updateCachedSprite(OverheadPrayer prayer)
	{
		if (prayer == cachedPrayer)
		{
			return;
		}
		cachedPrayer = prayer;
		cachedSprite = spriteManager.getSprite(prayer.getSpriteId(), 0);
	}
}
