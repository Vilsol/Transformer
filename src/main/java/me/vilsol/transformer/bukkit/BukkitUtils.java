package me.vilsol.transformer.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import me.vilsol.transformer.api.TItem;
import me.vilsol.transformer.api.TLocation;

/**
 * @author Nick Robson
 */
public class BukkitUtils {

    public static Location toBukkit(TLocation loc) {
        return new Location(Bukkit.getWorld(loc.getWorld().getName()), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(),
                loc.getPitch());
    }

    public static TLocation toTransformer(Location loc) {
        return new TLocation(BukkitWorld.of(loc.getWorld()), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(),
                loc.getPitch());
    }

    @SuppressWarnings("deprecation")
    public static ItemStack toBukkit(TItem item) {
        if (item == null)
            return new ItemStack(0);
        return new ItemStack(item.getID(), item.getAmount(), item.getDamage(), item.getData());
    }

    @SuppressWarnings("deprecation")
    public static TItem toTransformer(ItemStack item) {
        if (item == null)
            return new TItem();
        return new TItem(item.getTypeId(), item.getAmount(), item.getData().getData(), item.getDurability());
    }

}
