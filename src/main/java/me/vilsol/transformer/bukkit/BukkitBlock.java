package me.vilsol.transformer.bukkit;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import me.vilsol.transformer.api.TBlock;
import me.vilsol.transformer.api.TItem;
import me.vilsol.transformer.api.TLocation;
import me.vilsol.transformer.bukkit.utils.BlockUtils;

/**
 * @author Nick Robson
 */
public class BukkitBlock implements TBlock {

    public static TBlock of(Block block) {
        return new BukkitBlock(block);
    }

    private int id;
    private byte data;
    private Map<Integer, TItem> items;
    private TLocation location;
    private BlockState state;

    @SuppressWarnings("deprecation")
    private BukkitBlock(Block block) {
        id = block.getTypeId();
        data = block.getData();
        location = BukkitUtils.toTransformer(block.getLocation());
        state = block.getState();
        items = new HashMap<>();
        if (block.getState() instanceof InventoryHolder) {
            InventoryHolder inv = (InventoryHolder) block.getState();
            ItemStack[] its = inv.getInventory().getContents();
            for (int i = 0; i < its.length; i++)
                items.put(i, BukkitUtils.toTransformer(its[i]));
        }
    }

    @Override
    public TLocation getLocation() {
        return location;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public byte getData() {
        return data;
    }

    @Override
    public Map<Integer, TItem> getItems() {
        return new HashMap<>(items);
    }

    @Override
    public TItem getItem(int slot) {
        Validate.isTrue(0 <= slot && slot < items.size(), "Slot (" + slot + ") must be in range 0.." + items.size());
        return items.get(slot);
    }

    @Override
    public void setItems(Map<Integer, TItem> items) {
        this.items = items == null ? new HashMap<>() : items;
    }

    @Override
    public void setItem(int slot, TItem item) {
        Validate.isTrue(0 <= slot && slot < items.size(), "Slot (" + slot + ") must be in range 0.." + items.size());
        items.put(slot, item == null ? new TItem() : item);
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public void setData(byte data) {
        this.data = data;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void apply() {
        Block b = toBukkit();
        b.setTypeIdAndData(id, data, true);
        if (items != null && b.getState() instanceof InventoryHolder) {
            InventoryHolder holder = (InventoryHolder) b;
            items.forEach((slot, item) -> holder.getInventory().setItem(slot, BukkitUtils.toBukkit(item)));
        }
        if (state != null)
            BlockUtils.setStates(state, b);
    }

    public Block toBukkit() {
        return BukkitUtils.toBukkit(location).getBlock();
    }

}
