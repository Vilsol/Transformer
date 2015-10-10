package me.vilsol.transformer.handlers;

import org.bukkit.Location;

public abstract class TransformerHandler<T> {

    private Location positionOne;
    private Location positionTwo;

    private T owner;

    public TransformerHandler(T owner) {
        this.owner = owner;
    }

    public T getOwner() {
        return owner;
    }

    public Location getPositionOne() {
        return positionOne;
    }

    public void setPositionOne(Location positionOne) {
        this.positionOne = positionOne;
    }

    public Location getPositionTwo() {
        return positionTwo;
    }

    public void setPositionTwo(Location positionTwo) {
        this.positionTwo = positionTwo;
    }
}
