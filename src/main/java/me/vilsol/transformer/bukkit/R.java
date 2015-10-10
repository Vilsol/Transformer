package me.vilsol.transformer.bukkit;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.vilsol.menuengine.utils.Builder;

public class R {

    public static final ItemStack WAND = new Builder(Material.BLAZE_ROD).name(ChatColor.AQUA + "Transformer Wand")
            .lore(Arrays.asList(ChatColor.DARK_AQUA + "Left-Click set position 1", ChatColor.DARK_AQUA + "Right-Click set position 2"))
            .item();

}
