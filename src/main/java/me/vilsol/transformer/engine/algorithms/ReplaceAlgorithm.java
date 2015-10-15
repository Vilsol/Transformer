package me.vilsol.transformer.engine.algorithms;

import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.handlers.TransformerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.List;

public class ReplaceAlgorithm implements TransformerAlgorithm {

    private VirtualBlock replacement;

    public ReplaceAlgorithm(VirtualBlock replacement) {
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
        callback.callback(new ReplaceAlgorithm(new VirtualBlock(Material.STONE)));
    }

    @Override
    public ItemStack getIdentifierItem() {
        return new Builder(Material.BED).name(ChatColor.GOLD + "Replace").item();
    }

}
