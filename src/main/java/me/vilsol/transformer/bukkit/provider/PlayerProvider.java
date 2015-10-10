package me.vilsol.transformer.bukkit.provider;

import org.bukkit.entity.Player;

import me.vilsol.transformer.api.AbstractTProvider;

public class PlayerProvider extends AbstractTProvider<Player> {

    public PlayerProvider(Player owner) {
        super(owner);
    }

    @Override
    public String getName() {
        return getOwner().getName();
    }

}
