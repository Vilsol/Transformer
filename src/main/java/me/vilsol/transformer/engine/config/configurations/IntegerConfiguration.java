package me.vilsol.transformer.engine.config.configurations;

import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.config.ChatConfiguration;
import me.vilsol.transformer.utils.ActionAPI;
import me.vilsol.transformer.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class IntegerConfiguration extends ChatConfiguration<Integer> {

    public IntegerConfiguration(Integer defaultValue, Material itemMaterial, String name, List<String> description, String message, ParamCallback<Integer> callback) {
        super(defaultValue, itemMaterial, name, description, message, callback);
    }

    @Override
    public boolean parseInput(Player player, String input) {
        if(!Utils.isInteger(input)){
            return false;
        }

        setValue(Integer.parseInt(input));

        ActionAPI.sendAction(player, ChatColor.DARK_GREEN + "Success");

        return true;
    }

}
