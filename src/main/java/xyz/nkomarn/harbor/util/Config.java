package xyz.nkomarn.harbor.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.harbor.Harbor;

import java.util.List;
import java.util.stream.Collectors;

public class Config {
    private final Harbor harbor;
    private final MiniMessage mm = MiniMessage.miniMessage();

    public Config(@NotNull Harbor harbor) {
        this.harbor = harbor;
        harbor.saveDefaultConfig();
    }

    /**
     * Fetches an instance of the FileConfiguration.
     *
     * @return The configuration for this server.
     */
    @NotNull
    public FileConfiguration getConfig() {
        return harbor.getConfig();
    }

    /**
     * Reloads the configuration, loading any new changes.
     */
    public void reload() {
        harbor.reloadConfig();
    }

    /**
     * Returns the prefix for Harbor messages.
     * @return Harbor message prefix.
     */
    @NotNull
    public Component getPrefix() {
        return mm.deserialize(getString("messages.miscellaneous.chat-prefix"));
    }

    /**
     * Fetches a boolean from the configuration
     * if location is not found, false is returned
     *
     * @param location Configuration location of the boolean
     */
    public boolean getBoolean(@NotNull String location) {
        return getConfig().getBoolean(location, false);
    }

    /**
     * Fetches a string from the configuration
     * if location is not found, empty string is returned
     *
     * @param location Configuration location of the string
     */
    @NotNull
    public String getString(@NotNull String location) {
        return getConfig().getString(location, "").replaceAll("<prefix>", getConfig().getString("messages.miscellaneous.chat-prefix"));
    }

    /**
     * Fetches an integer from the configuration
     * if location is not found, 0 is returned
     *
     * @param location Configuration location of the integer
     */
    public int getInteger(@NotNull String location) {
        return getConfig().getInt(location, 0);
    }

    /**
     * Fetches a double from the configuration
     * if location is not found, 0.0 is returned
     *
     * @param location Configuration location of the double
     */
    public double getDouble(@NotNull String location) {
        return getConfig().getDouble(location, 0.0);
    }

    @NotNull
    public List<String> getStringList(@NotNull String location) {
        return getConfig().getStringList(location).stream().map(t->t.replaceAll("<prefix>", getConfig().getString("messages.miscellaneous.chat-prefix"))).collect(Collectors.toList());
    }
}
