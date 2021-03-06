package me.vilsol.transformer.engine;


import me.vilsol.transformer.utils.BlockUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class VirtualBlock {

    private Location loc;
    private Material mat;
    private Byte data;
    private ItemStack[] inv;
    private BlockState state;

    public VirtualBlock(VirtualBlock vb) {
        loc = vb.getLocation();
        mat = vb.getMaterial();
        data = vb.getData();
        inv = vb.getInventory();
        state = vb.getState();
    }

    public VirtualBlock(Location location){
        this(location.getBlock());
    }

    public VirtualBlock(Block block) {
        loc = block.getLocation();
        mat = block.getType();
        data = block.getData();
        if(block.getState() instanceof InventoryHolder) {
            inv = ((InventoryHolder) block.getState()).getInventory().getContents();
        }

        if(block.getState() != null) {
            state = block.getState();
        }
    }

    public VirtualBlock(Material material) {
        mat = material;
    }

    /**
     * Returns the virtual block location
     *
     * @return Virtual location
     */
    public Location getLocation() {
        return loc;
    }

    /**
     * Returns the virtual block material
     *
     * @return Blocks material
     */
    public Material getMaterial() {
        return mat;
    }

    /**
     * Returns the block data
     *
     * @return Blocks data
     */
    public Byte getData() {
        return data;
    }

    /**
     * Returns the block inventory
     *
     * @return Blocks Inventory
     */
    public ItemStack[] getInventory() {
        return inv;
    }

    public BlockState getState() {
        return state;
    }

    /**
     * Sets the block location
     *
     * @param location
     *            New location
     */
    public void setLocation(Location location) {
        loc = location;
    }

    /**
     * Sets the block material
     *
     * @param material
     *            New material
     */
    public void setMaterial(Material material) {
        mat = material;
    }

    /**
     * Sets the block data
     *
     * @param blockData
     *            New data
     */
    public void setData(Byte blockData) {
        data = blockData;
    }

    /**
     * Sets the block inventory
     *
     * @param inventory
     *            New inventory
     */
    public void setInventory(ItemStack[] inventory) {
        inv = inventory;
    }

    public void setState(BlockState blockState) {
        state = blockState;
    }


    /**
     * Places the block in the world
     */
    public boolean buildBlock(){
        return buildBlock(loc, true);
    }


    /**
     * Places the block in the world
     *
     * @param phys Whether to run physics or not
     */
    public boolean buildBlock(boolean phys){
        return buildBlock(loc, phys);
    }

    /**
     * Places the block in the world
     *
     * @param loc The location of the new block
     * @param phys Whether to run physics or not
     */
    public boolean buildBlock(Location loc, boolean phys) {
        if(loc != null) {
            final Block realBlock = loc.getBlock();
            if(realBlock.getState() instanceof InventoryHolder) {
                ((InventoryHolder) realBlock.getState()).getInventory().clear();
            }
            realBlock.setType(mat, phys);
            if(data != null) {
                realBlock.setData(data);
            } else {
                realBlock.setData((byte) 0);
            }
            if(inv != null) {
                if(realBlock.getState() instanceof InventoryHolder) {
                    if(inv.length > ((InventoryHolder) realBlock.getState()).getInventory().getSize()){
                        return true;
                    }else{
                        ((InventoryHolder) realBlock.getState()).getInventory().setContents(inv);
                    }
                }
            }
            if(state != null) {
                BlockUtils.setStates(state, realBlock);
            }
        }
        return false;
    }

    public void delayActions(){
        final Block realBlock = loc.getBlock();
        if(inv != null) {
            if(realBlock.getState() instanceof InventoryHolder) {
                ((InventoryHolder) realBlock.getState()).getInventory().setContents(inv);
            }
        }
    }

    /**
     * Returns the block at the current location
     *
     * @return The block
     */
    public Block getBlock() {
        return loc.getBlock();
    }

}