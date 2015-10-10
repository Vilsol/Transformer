package me.vilsol.transformer.bukkit.utils;

import me.vilsol.transformer.api.TLocation;

public class LocationUtils {

    public static TLocation getLowestPosition(TLocation a, TLocation b) {
        double lowX = Math.min(a.getX(), b.getX());
        double lowY = Math.min(a.getY(), b.getY());
        double lowZ = Math.min(a.getZ(), b.getZ());
        return new TLocation(a.getWorld(), lowX, lowY, lowZ);
    }

    public static TLocation getHighestPosition(TLocation a, TLocation b) {
        double highX = Math.max(a.getX(), b.getX());
        double highY = Math.max(a.getY(), b.getY());
        double highZ = Math.max(a.getZ(), b.getZ());
        return new TLocation(a.getWorld(), highX, highY, highZ);
    }

}
