package defiantpvp.searlie.utilities;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class particleManager {
    public static void spawnParticle(Location location, String particleName, float offsetX, float offsetY, float offsetZ, float speed, int count) {
        System.out.println("started particles");
        // Create the packet for particles
        EnumParticle particle = EnumParticle.valueOf(particleName.toUpperCase());
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                particle,  // The particle type
                true,     // Long distance
                (float) location.getX(), (float) location.getY(), (float) location.getZ(),  // Location
                offsetX, offsetY, offsetZ,  // Offset
                speed,    // Speed
                count     // Number of particles
        );

        // Send the packet to all online players
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(packet);
        }
    }
}
