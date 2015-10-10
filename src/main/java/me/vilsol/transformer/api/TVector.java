package me.vilsol.transformer.api;

/**
 * @author Nick Robson
 */
public class TVector implements Cloneable {

    private final double x, y, z;

    public TVector() {
        this(0, 0, 0);
    }

    public TVector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public TVector add(TVector vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public TVector add(double x, double y, double z) {
        return new TVector(this.x + x, this.y + y, this.z + z);
    }

    public double dot(TVector vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    public TVector cross(TVector vector) {
        return new TVector(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);
    }

    @Override
    public TVector clone() {
        return new TVector(x, y, z);
    }

    public TLocation toLocation(TWorld world) {
        return new TLocation(world, x, y, z);
    }

}
