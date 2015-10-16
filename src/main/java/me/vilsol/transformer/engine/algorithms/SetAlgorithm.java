package me.vilsol.transformer.engine.algorithms;

import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.config.Configuration;
import me.vilsol.transformer.engine.config.configurations.IntegerConfiguration;
import me.vilsol.transformer.handlers.TransformerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetAlgorithm extends TransformerAlgorithm {

    private VirtualBlock replacement;

    public SetAlgorithm(VirtualBlock replacement, TransformerHandler owner) {
        super(owner);
        this.replacement = replacement;
    }

    @Override
    public List<VirtualBlock> applyToBlock(Block b, Vector relativePosition) {
        VirtualBlock before = new VirtualBlock(b);
        replacement.buildBlock(b.getLocation(), false);
        return Collections.singletonList(before);
    }

    public VirtualBlock getReplacement() {
        return replacement;
    }

    public void setReplacement(VirtualBlock replacement) {
        this.replacement = replacement;
    }

    @Override
    public void newInstance(TransformerHandler handler, ParamCallback<TransformerAlgorithm> callback) {
        callback.callback(new SetAlgorithm(new VirtualBlock(Material.STONE), handler));
    }

    @Override
    public ItemStack getIdentifierItem() {
        return new Builder(Material.BED).name(ChatColor.GOLD + "Set").item();
    }

    @Override
    public List<Configuration> getConfigurations() {
        List<Configuration> configurations = new ArrayList<>();
        configurations.add(new IntegerConfiguration(1, Material.STONE, "Block", Collections.singletonList("Block to set to"), ChatColor.DARK_AQUA + "Enter new block ID", mat -> this.replacement.setMaterial(Material.getMaterial(mat))));
        return configurations;
    }

}
