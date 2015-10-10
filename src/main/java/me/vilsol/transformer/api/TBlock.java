package me.vilsol.transformer.api;

import java.util.Map;

/**
 * @author Nick Robson
 */
public interface TBlock {

    TLocation getLocation();

    int getID();

    byte getData();

    Map<Integer, TItem> getItems();

    TItem getItem(int slot);

    void setItems(Map<Integer, TItem> items);

    default void setItems(Map<Integer, TItem> items, boolean apply) {
        setItems(items);
        if (apply)
            apply();
    }

    void setItem(int slot, TItem item);

    default void setData(int slot, TItem item, boolean apply) {
        setItem(slot, item);
        if (apply)
            apply();
    }

    void setID(int id);

    default void setID(int id, boolean apply) {
        setID(id);
        if (apply)
            apply();
    }

    void setData(byte data);

    default void setData(byte data, boolean apply) {
        setData(data);
        if (apply)
            apply();
    }

    void apply();

}
