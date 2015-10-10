package me.vilsol.transformer.managers;

import me.vilsol.transformer.handlers.ConsoleHandler;
import me.vilsol.transformer.handlers.PlayerHandler;
import me.vilsol.transformer.handlers.PluginHandler;
import me.vilsol.transformer.handlers.TransformerHandler;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class HandlerManager {

    private static HandlerManager ourInstance = new HandlerManager();

    public static HandlerManager getInstance() {
        return ourInstance;
    }

    private HashMap<Object, TransformerHandler> handlers = new HashMap<>();

    private HandlerManager() {
    }

    public <T> TransformerHandler<T> getHandler(T owner){
        if(!handlers.containsKey(owner)){
            TransformerHandler handler = null;

            if(owner instanceof Player){
                handler = new PlayerHandler((Player) owner);
            }else if(owner instanceof Plugin){
                handler = new PluginHandler((Plugin) owner);
            }else if(owner instanceof ConsoleCommandSender){
                handler = new ConsoleHandler((ConsoleCommandSender) owner);
            }

            handlers.put(owner, handler);
        }

        return handlers.get(owner);
    }

}
