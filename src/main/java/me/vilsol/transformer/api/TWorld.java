package me.vilsol.transformer.api;

/**
 * @author Nick Robson
 */
public interface TWorld {

    String getName();

    TBlock getBlockAt(int x, int y, int z);

    TBlock getBlock(TLocation location);

}
