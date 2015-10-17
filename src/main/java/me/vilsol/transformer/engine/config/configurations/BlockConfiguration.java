package me.vilsol.transformer.engine.config.configurations;

import me.vilsol.menuengine.engine.ChatCallback;
import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.config.Configuration;
import me.vilsol.transformer.listeners.PickBlockListener;
import me.vilsol.transformer.listeners.WandListener;
import me.vilsol.transformer.utils.ActionAPI;
import me.vilsol.transformer.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class BlockConfiguration extends Configuration<VirtualBlock> implements ChatCallback {

    public BlockConfiguration(String name, List<String> description, ParamCallback<VirtualBlock> callback) {
        super(new VirtualBlock(Material.STONE), Material.STONE, name, description, callback);
    }

    public void changeBlock(VirtualBlock block, Player player){
        ChatCallback.locked_players.remove(player);
        PickBlockListener.locked_players.remove(player);
        WandListener.locked_players.remove(player);
        getIdentifierItem().setType(block.getMaterial());
        setValue(block);
    }

    @Override
    protected void onClick(Player player) {
        player.closeInventory();
        ActionAPI.sendAction(player, ChatColor.DARK_AQUA + "Enter new block ID or Pick Block or click with Wand");
        ChatCallback.locked_players.put(player, this);
        PickBlockListener.locked_players.put(player, this);
        WandListener.locked_players.put(player, this);
    }

    @Override
    public void onChatMessage(AsyncPlayerChatEvent e) {
        if(Utils.isInteger(e.getMessage())){
            if(Material.getMaterial(Integer.parseInt(e.getMessage())) != null){
                changeBlock(new VirtualBlock(Material.getMaterial(Integer.parseInt(e.getMessage()))), e.getPlayer());
            }else{
                ActionAPI.sendAction(e.getPlayer(), ChatColor.RED + "Material Not Found!");
            }
        }else{
            ActionAPI.sendAction(e.getPlayer(), ChatColor.RED + "Material Not Found!");
        }
    }

}

