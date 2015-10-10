package me.vilsol.transformer.utils;

import org.bukkit.Location;

public class LocationUtils {

    public static Location getLowestPosition(Location positionOne, Location positionTwo){
        double lowX = Math.min(positionOne.getX(), positionTwo.getX());
        double lowY = Math.min(positionOne.getY(), positionTwo.getY());
        double lowZ = Math.min(positionOne.getZ(), positionTwo.getZ());
        return new Location(positionOne.getWorld(), lowX, lowY, lowZ);
    }

    public static Location getHighestPosition(Location positionOne, Location positionTwo){
        double lowX = Math.max(positionOne.getX(), positionTwo.getX());
        double lowY = Math.max(positionOne.getY(), positionTwo.getY());
        double lowZ = Math.max(positionOne.getZ(), positionTwo.getZ());
        return new Location(positionOne.getWorld(), lowX, lowY, lowZ);
    }

}
