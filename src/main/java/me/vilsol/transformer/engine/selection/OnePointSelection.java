package me.vilsol.transformer.engine.selection;

import org.bukkit.Location;

public class OnePointSelection implements Selection {

    private Location point;

    public OnePointSelection(Location point) {
        this.point = point;
    }

    public Location getPoint() {
        return point;
    }

    public void setPoint(Location point) {
        this.point = point;
    }

    @Override
    public String getErrorMessage() {
        return "Please select a position!";
    }

    @Override
    public boolean isValid() {
        return point != null;
    }

}
