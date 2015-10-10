package me.vilsol.transformer.api;

/**
 * @author Nick Robson
 */
public class TItem implements Cloneable {

    private int id, amount;
    private byte data;
    private short damage;

    public TItem() {
        this(0, 0, (byte) 0, (short) 0);
    }

    public TItem(int id, int amount, byte data, short damage) {
        this.id = id;
        this.amount = amount;
        this.data = data;
        this.damage = damage;
    }

    public int getID() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public byte getData() {
        return data;
    }

    public short getDamage() {
        return damage;
    }

    @Override
    public TItem clone() {
        return new TItem(id, amount, data, damage);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || !(o instanceof TItem))
            return false;
        TItem item = (TItem) o;
        return item.id == id && item.amount == amount && item.data == data && item.damage == damage;
    }

}
