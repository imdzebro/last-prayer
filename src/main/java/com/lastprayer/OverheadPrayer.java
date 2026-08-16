package com.lastprayer;

import lombok.Getter;
import net.runelite.api.Prayer;
import net.runelite.api.gameval.SpriteID;

@Getter
enum OverheadPrayer
{
	PROTECT_FROM_MAGIC(Prayer.PROTECT_FROM_MAGIC, "Protect from Magic", SpriteID.Prayeron.PROTECT_FROM_MAGIC),
	PROTECT_FROM_MISSILES(Prayer.PROTECT_FROM_MISSILES, "Protect from Missiles", SpriteID.Prayeron.PROTECT_FROM_MISSILES),
	PROTECT_FROM_MELEE(Prayer.PROTECT_FROM_MELEE, "Protect from Melee", SpriteID.Prayeron.PROTECT_FROM_MELEE),
	RETRIBUTION(Prayer.RETRIBUTION, "Retribution", SpriteID.Prayeron.RETRIBUTION),
	REDEMPTION(Prayer.REDEMPTION, "Redemption", SpriteID.Prayeron.REDEMPTION),
	SMITE(Prayer.SMITE, "Smite", SpriteID.Prayeron.SMITE);

	private final Prayer prayer;
	private final String displayName;
	private final int spriteId;

	OverheadPrayer(Prayer prayer, String displayName, int spriteId)
	{
		this.prayer = prayer;
		this.displayName = displayName;
		this.spriteId = spriteId;
	}

	static OverheadPrayer fromPrayer(Prayer prayer)
	{
		for (OverheadPrayer overheadPrayer : values())
		{
			if (overheadPrayer.prayer == prayer)
			{
				return overheadPrayer;
			}
		}
		return null;
	}
}
