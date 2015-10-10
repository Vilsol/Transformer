package me.vilsol.transformer.bukkit;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.World;

import me.vilsol.transformer.api.TBlock;
import me.vilsol.transformer.api.TLocation;
import me.vilsol.transformer.api.TWorld;

/**
 * @author Nick Robson
 */
public class BukkitWorld implements TWorld {

    private static final Map<String, BukkitWorld> cache = new HashMap<>();

    public static BukkitWorld of(World world) {
        return cache.computeIfAbsent(world.getName(), s -> new BukkitWorld(world));
    }

    private String world;

    private BukkitWorld(World world) {
        this.world = world.getName();
    }

    @Override
    public String getName() {
        return world;
    }

    @Override
    public TBlock getBlock(TLocation location) {
        return BukkitBlock.of(toBukkit().getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
    }

    @Override
    public TBlock getBlockAt(int x, int y, int z) {
        return BukkitBlock.of(toBukkit().getBlockAt(x, y, z));
    }

    public World toBukkit() {
        World world = Bukkit.getWorld(this.world);
        Validate.notNull(world, "World does not exist!");
        return world;
    }

}
