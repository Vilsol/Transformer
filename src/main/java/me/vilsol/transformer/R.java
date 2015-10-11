package me.vilsol.transformer;

import me.vilsol.menuengine.utils.Builder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class R {

    public static final ItemStack wand = new Builder(Material.BLAZE_ROD).name(ChatColor.AQUA + "Transformer Wand").lore(Arrays.asList(ChatColor.DARK_AQUA + "Left-Click set position 1", ChatColor.DARK_AQUA + "Right-Click set position 2")).item();

    public static int globalLimit = 1000;

}
