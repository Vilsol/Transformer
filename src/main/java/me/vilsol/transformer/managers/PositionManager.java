package me.vilsol.transformer.managers;

import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.regions.TransformerRegion;
import me.vilsol.transformer.engine.selection.Selection;
import me.vilsol.transformer.engine.selection.SelectionType;
import me.vilsol.transformer.handlers.PlayerHandler;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;

public class PositionManager {

    private static PositionManager ourInstance = new PositionManager();

    public static PositionManager getInstance() {
        return ourInstance;
    }

    private PositionManager() {
    }

    public void getRegion(Object owner, ParamCallback<TransformerRegion> callback){
        TransformerHandler handler = HandlerManager.getInstance().getHandler(owner);
        SelectionType selectionType = handler.getRegionType().getSelection();
        Selection selection = handler.getSelection();

        if(!selectionType.equalClass(selection)){
            handler.setSelection(selectionType.newInstance());

            if(handler instanceof PlayerHandler){
                ActionAPI.sendAction(((PlayerHandler) handler).getOwner(), ChatColor.DARK_RED + handler.getSelection().getErrorMessage());
            }

            callback.callback(null);
            return;
        }

        if(!selection.isValid()){
            if(handler instanceof PlayerHandler){
                ActionAPI.sendAction(((PlayerHandler) handler).getOwner(), ChatColor.DARK_RED + handler.getSelection().getErrorMessage());
            }

            callback.callback(null);
            return;
        }

        handler.getRegionType().newInstance(handler, callback);
    }

}
