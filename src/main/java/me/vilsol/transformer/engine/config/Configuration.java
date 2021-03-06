package me.vilsol.transformer.engine.config;

import me.vilsol.menuengine.engine.DynamicMenuModel;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.engine.ParamCallback;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class Configuration<T> {

    private Player player;
    private Class<? extends DynamicMenuModel> menu;

    private ParamCallback<T> callback;
    private ItemStack identifierItem;

    protected T value;
    protected Material itemMaterial;
    protected String name;
    protected List<String> description;

    // TODO Is it possible to make this simpler?
    public Configuration(T defaultValue, Material itemMaterial, String name, List<String> description, ParamCallback<T> callback) {
        this.value = defaultValue;
        this.itemMaterial = itemMaterial;
        this.name = name;
        this.description = description;
        this.callback = callback;
        this.identifierItem = new Builder(itemMaterial).name(name).lore(description).item();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        callback.callback(value);
        if(menu != null){
            DynamicMenuModel.createMenu(player, menu).showToPlayer(player);
        }
    }

    public List<String> getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Material getItemMaterial() {
        return itemMaterial;
    }

    public void onClick(Player player, Class<? extends DynamicMenuModel> menu){
        this.player = player;
        this.menu = menu;
        onClick(player);
    }

    protected abstract void onClick(Player player);

    public ItemStack getIdentifierItem() {
        return identifierItem;
    }
}
