package me.vilsol.transformer.bukkit.provider;

import org.bukkit.command.ConsoleCommandSender;

import me.vilsol.transformer.api.AbstractTProvider;

public class ConsoleProvider extends AbstractTProvider<ConsoleCommandSender> {

    public ConsoleProvider(ConsoleCommandSender owner) {
        super(owner);
    }

    @Override
    public String getName() {
        return getOwner().getName();
    }

}
