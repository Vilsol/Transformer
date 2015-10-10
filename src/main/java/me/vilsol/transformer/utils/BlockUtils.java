package me.vilsol.transformer.utils;

import org.bukkit.Material;
import org.bukkit.block.*;

import java.util.Arrays;
import java.util.List;

public class BlockUtils {

    private static List<Material> transparentBlocks = Arrays.asList(Material.ACTIVATOR_RAIL, Material.ANVIL, Material.BED, Material.BED_BLOCK, Material.BROWN_MUSHROOM, Material.CACTUS, Material.CAKE_BLOCK, Material.CROPS, Material.DEAD_BUSH, Material.DETECTOR_RAIL, Material.DIODE, Material.DIODE_BLOCK_OFF, Material.DIODE_BLOCK_OFF, Material.DRAGON_EGG, Material.ITEM_FRAME, Material.LADDER, Material.LAVA, Material.NETHER_WARTS, Material.PAINTING, Material.POWERED_RAIL, Material.RAILS, Material.REDSTONE_COMPARATOR, Material.REDSTONE_COMPARATOR_OFF, Material.REDSTONE_COMPARATOR_ON, Material.REDSTONE_TORCH_OFF, Material.REDSTONE_TORCH_ON, Material.REDSTONE_WIRE, Material.SAND, Material.SAPLING, Material.SEEDS, Material.SIGN, Material.SIGN_POST, Material.STATIONARY_LAVA, Material.STATIONARY_WATER, Material.SUGAR_CANE_BLOCK, Material.TORCH, Material.TRAP_DOOR, Material.TRIPWIRE, Material.TRIPWIRE_HOOK, Material.VINE, Material.WALL_SIGN, Material.WATER, Material.WATER_LILY, Material.WOOD_DOOR, Material.IRON_DOOR, Material.WOOD_PLATE, Material.STONE_PLATE, Material.GOLD_PLATE, Material.IRON_PLATE, Material.WOOD_BUTTON, Material.STONE_BUTTON, Material.LEVER, Material.GRAVEL, Material.CARROT, Material.POTATO);

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
            for (String x : ((Sign) s).getLines()) d.setLine(i++, x);
            d.update(true);
        } else if (s instanceof Skull && b.getState() instanceof Skull) {
            Skull d = (Skull) b.getState();
            d.setRotation(((Skull) s).getRotation());
            d.setSkullType(((Skull) s).getSkullType());
            if (((Skull) s).hasOwner()) d.setOwner(((Skull) s).getOwner());
            d.update(true);
        }
    }

    /**
     * Returns whether or not the provided block is transparent
     * @param material The material to check
     * @return Is the material transparent
     */
    public static boolean isTransparent(Material material) {
        return transparentBlocks.contains(material);
    }

}
