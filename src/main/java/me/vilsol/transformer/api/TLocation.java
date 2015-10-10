package me.vilsol.transformer.api;

/**
 * @author Nick Robson
 */
public class TLocation implements Cloneable {

    private final TWorld world;
    private final double x, y, z;
    private final float yaw, pitch;

    public TLocation(TWorld world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    public TLocation(TWorld world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public TWorld getWorld() {
        return world;
    }

    public int getBlockX() {
        return (int) x;
    }

    public double getX() {
        return x;
    }

    public int getBlockY() {
        return (int) y;
    }

    public double getY() {
        return y;
    }

    public int getBlockZ() {
        return (int) z;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public TLocation clone() {
        return new TLocation(world, x, y, z, yaw, pitch);
    }

    public TLocation add(TVector vector) {
        if (vector == null)
            return null;
        return add(vector.getX(), vector.getY(), vector.getZ());
    }

    public TLocation add(double x, double y, double z) {
        return new TLocation(world, this.x + x, this.y + y, this.z + z);
    }

}
