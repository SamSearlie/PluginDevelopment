package defiantpvp.searlie.utilities;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class soundEffects {

    public static void playerSoundEffectPlay(Player player, Sound sound){
        if(player != null){
            player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
        }
    }
}
