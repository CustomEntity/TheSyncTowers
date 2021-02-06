package fr.customentity.thesynctowers.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class ColorUtils {

    public static List<String> translateAlternateColorCodes(List<String> list) {
        return list.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList());
    }
}
