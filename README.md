# Last Prayer

A RuneLite plugin that shows your last active overhead prayer in a small on-screen overlay — even after you turn it off.

## Features

- Tracks your last active overhead prayer (Protect, Smite, Redemption, etc.)
- Small overlay panel with optional prayer icon and name
- Configurable: enable/disable overlay, show/hide icon, hide until first prayer detected

## Building

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Build

```bash
mvn clean package
```

This creates `target/lastprayer-1.0.0.jar`.

## Installing

### Production (regular RuneLite client)

1. Copy `target/lastprayer-1.0.0.jar` to your RuneLite plugins folder:
   - **Windows**: `%USERPROFILE%\.runelite\plugins\`
   - **macOS**: `~/Library/Application Support/RuneLite/plugins/`
   - **Linux**: `~/.runelite/plugins/`
2. Restart RuneLite
3. Enable "Last Prayer" in the plugin list

### Development client

1. Build the plugin: `mvn clean package`
2. Copy the JAR to `runelite/runelite-client/src/main/resources/plugins/`
3. Run RuneLite from source

## Configuration

- **Enable Overlay** — master toggle for the on-screen display
- **Show Icon** — show the prayer sprite alongside the name
- **Hide When None** — hide the overlay until you've activated an overhead prayer this session

## Plugin Structure

```
src/main/java/com/lastprayer/
├── LastPrayerPlugin.java    # Main plugin, game tick detection
├── LastPrayerConfig.java    # Configuration interface
├── LastPrayerOverlay.java   # Overlay rendering
└── OverheadPrayer.java      # Overhead prayer enum with display names and sprites
```

## Resources

- [RuneLite Wiki](https://github.com/runelite/runelite/wiki)
- [RuneLite API Documentation](https://static.runelite.net/runelite-api/apidocs/)
