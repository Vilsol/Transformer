package me.vilsol.transformer.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public class ActionAPI {

    public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
    }

    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }

    public static void sendAction(Player p, String msg) {
        try {
            JSONObject message = new JSONObject();
            message.put("text", msg);

            String version = getServerVersion();
            String nmsClass = (version.startsWith("v1_8_R") ? "IChatBaseComponent$" : "") + "ChatSerializer";
            Object serializer = getNmsClass(nmsClass).getMethod("a", new Class[]{String.class}).invoke(null, message.toJSONString());
            Object packet = getNmsClass("PacketPlayOutChat").getConstructor(new Class[]{getNmsClass("IChatBaseComponent"), Byte.TYPE}).newInstance(serializer, (byte) 2);
            Object handle = p.getClass().getMethod("getHandle", new Class[0]).invoke(p);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", new Class[]{getNmsClass("Packet")}).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
