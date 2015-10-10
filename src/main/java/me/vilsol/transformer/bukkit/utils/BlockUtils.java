package me.vilsol.transformer.bukkit.utils;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.BrewingStand;
import org.bukkit.block.CommandBlock;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.Furnace;
import org.bukkit.block.Jukebox;
import org.bukkit.block.NoteBlock;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;

public class BlockUtils {

    public static void setStates(BlockState s, Block b) {
        if (s instanceof BrewingStand && b.getState() instanceof BrewingStand) {
            BrewingStand d = (BrewingStand) b.getState();
            d.setBrewingTime(((BrewingStand) s).getBrewingTime());
            d.update(true);
        } else if (s instanceof CommandBlock && b.getState() instanceof CommandBlock) {
            CommandBlock d = (CommandBlock) b.getState();
            d.setCommand(((CommandBlock) s).getCommand());
            d.setName(((CommandBlock) s).getName());
            d.update(true);
        } else if (s instanceof CreatureSpawner && b.getState() instanceof CreatureSpawner) {
            CreatureSpawner d = (CreatureSpawner) b.getState();
            d.setSpawnedType(((CreatureSpawner) s).getSpawnedType());
            d.setDelay(((CreatureSpawner) s).getDelay());
            d.update(true);
        } else if (s instanceof Furnace && b.getState() instanceof Furnace) {
            Furnace d = (Furnace) b.getState();
            d.setBurnTime(((Furnace) s).getBurnTime());
            d.setCookTime(((Furnace) s).getCookTime());
            d.update(true);
        } else if (s instanceof Jukebox && b.getState() instanceof Jukebox) {
            Jukebox d = (Jukebox) b.getState();
            d.setPlaying(((Jukebox) s).getPlaying());
            d.update(true);
        } else if (s instanceof NoteBlock && b.getState() instanceof NoteBlock) {
            NoteBlock d = (NoteBlock) b.getState();
            d.setNote(((NoteBlock) s).getNote());
            d.update(true);
        } else if (s instanceof Sign && b.getState() instanceof Sign) {
            Sign d = (Sign) b.getState();
            int i = 0;
            for (String x : ((Sign) s).getLines())
                d.setLine(i++, x);
            d.update(true);
        } else if (s instanceof Skull && b.getState() instanceof Skull) {
            Skull d = (Skull) b.getState();
            d.setRotation(((Skull) s).getRotation());
            d.setSkullType(((Skull) s).getSkullType());
            if (((Skull) s).hasOwner())
                d.setOwner(((Skull) s).getOwner());
            d.update(true);
        }
    }

}
