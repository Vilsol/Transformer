package me.vilsol.transformer.engine.selection;

import org.bukkit.Location;

public class TwoPointSelection implements Selection {

    private Location positionOne;
    private Location positionTwo;

    public TwoPointSelection(Location positionOne, Location positionTwo) {
        this.positionOne = positionOne;
        this.positionTwo = positionTwo;
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

    @Override
    public String getErrorMessage() {
        return "Please select both positions!";
    }

    @Override
    public boolean isValid() {
        return positionOne != null && positionTwo != null;
    }

}
