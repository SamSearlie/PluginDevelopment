package defiantpvp.searlie.eventHandler;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import defiantpvp.searlie.customEnchantments.enchantmentManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class equipArmor implements Listener {

    @EventHandler
    public void onEquipArmor(ArmorEquipEvent event){
        System.out.println("Event happened: " + event.getPlayer());
        if(event.getPlayer() instanceof Player){
            System.out.println("Instance of Player");
            enchantmentManager.passiveEnchantment(event.getPlayer(), event.getOldArmorPiece(), event.getNewArmorPiece());
        }
    }
}
