package me.vilsol.transformer.api.regions;

import java.util.ArrayList;
import java.util.List;

import me.vilsol.transformer.api.TBlock;
import me.vilsol.transformer.api.TLocation;
import me.vilsol.transformer.bukkit.utils.LocationUtils;

public class CuboidRegion implements TransformerRegion {

    private TLocation a, b;

    public CuboidRegion(TLocation positionOne, TLocation positionTwo) {
        a = LocationUtils.getLowestPosition(positionOne, positionTwo);
        b = LocationUtils.getHighestPosition(positionOne, positionTwo);
    }

    public TLocation getA() {
        return a;
    }

    public void setA(TLocation a) {
        this.a = LocationUtils.getLowestPosition(a, b);
        b = LocationUtils.getHighestPosition(a, b);
    }

    public TLocation getB() {
        return b;
    }

    public void setB(TLocation b) {
        a = LocationUtils.getLowestPosition(a, b);
        this.b = LocationUtils.getHighestPosition(a, b);
    }

    @Override
    public long getBlockCount() {
        return (b.getBlockX() - a.getBlockX() + 1) * (b.getBlockY() - a.getBlockY() + 1) * (b.getBlockZ() - a.getBlockZ() + 1);
    }

    @Override
    public List<TBlock> getEnclosedBlocks() {
        List<TBlock> blocks = new ArrayList<>();
        for (int x = a.getBlockX(); x <= b.getBlockX(); x++)
            for (int y = a.getBlockY(); y <= b.getBlockY(); y++)
                for (int z = a.getBlockZ(); z <= b.getBlockZ(); z++)
                    blocks.add(a.getWorld().getBlockAt(x, y, z));
        return blocks;
    }

    @Override
    public boolean isInside(TLocation loc) {
        if (loc == null)
            return false;
        return a.getX() <= loc.getX() && loc.getX() <= b.getX() && a.getY() <= loc.getY() && loc.getY() <= b.getY()
                && a.getZ() <= loc.getZ() && loc.getZ() <= b.getZ();
    }

}
