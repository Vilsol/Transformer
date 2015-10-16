package me.vilsol.transformer.engine.algorithms;

import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.config.Configuration;
import me.vilsol.transformer.engine.config.configurations.IntegerConfiguration;
import me.vilsol.transformer.handlers.TransformerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OverlayAlgorithm extends TransformerAlgorithm {

    private VirtualBlock overlay;

    public OverlayAlgorithm(VirtualBlock overlay, TransformerHandler owner) {
        super(owner);
        this.overlay = overlay;
    }

    @Override
    public List<VirtualBlock> applyToBlock(Block b, Vector relativePosition) {
        Location location = b.getLocation().clone();
        int highest = location.getWorld().getHighestBlockYAt(location.getBlockX(), location.getBlockZ()) - 1;
        location.setY(highest);
        if(location.getBlock().getType().equals(overlay.getMaterial())){
            return null;
        }
        location.add(0, 1, 0);
        VirtualBlock before = new VirtualBlock(location);
        overlay.buildBlock(location, false);
        return Collections.singletonList(before);
    }

    public VirtualBlock getOverlay() {
        return overlay;
    }

    public void setOverlay(VirtualBlock overlay) {
        this.overlay = overlay;
    }

    @Override
    public void newInstance(TransformerHandler handler, ParamCallback<TransformerAlgorithm> callback) {
        callback.callback(new OverlayAlgorithm(new VirtualBlock(Material.STONE), handler));
    }

    @Override
    public ItemStack getIdentifierItem() {
        return new Builder(Material.SNOW).name(ChatColor.GOLD + "Overlay").item();
    }

    @Override
    public boolean singleY() {
        return true;
    }

    @Override
    public List<Configuration> getConfigurations() {
        List<Configuration> configurations = new ArrayList<>();
        configurations.add(new IntegerConfiguration(1, Material.STONE, "Block", Collections.singletonList("Block to overlay with"), ChatColor.DARK_AQUA + "Enter new block ID", mat -> this.overlay.setMaterial(Material.getMaterial(mat))));
        return configurations;
    }

}
