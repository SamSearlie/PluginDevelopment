package defiantpvp.searlie.customEnchantments.enchantments;

import defiantpvp.searlie.customEnchantments.enums.enchantmentApplyType;
import defiantpvp.searlie.customEnchantments.enums.enchantmentRarity;
import defiantpvp.searlie.customEnchantments.enums.enchantmentStacks;
import defiantpvp.searlie.customEnchantments.enums.enchantmentType;
import defiantpvp.searlie.customEnchantments.interfaces.enchantmentInterface;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Rage implements enchantmentInterface {

    private static final double MAX_MULTIPLIER = 2.0;
    private static final int MAX_COMBO = 5; // Max hits to reach the max multiplier
    private static final long COMBO_RESET_TIME = 40L; // 2 seconds in ticks (20 ticks per second)

    public HashMap<UUID, Integer> rageMap;
    @Override
    public String getDescription(){return "Enraged";}

    @Override
    public String getName() {return "Rage";}

    @Override
    public int maxLevel() {return 6;}

    @Override
    public enchantmentApplyType getApplyType() {return enchantmentApplyType.WEAPON;}

    @Override
    public enchantmentRarity getRarity() {return enchantmentRarity.MYTHIC;}

    @Override
    public enchantmentStacks stacks() {return enchantmentStacks.FALSE;}

    @Override
    public enchantmentType enchantType() {return enchantmentType.OFFENSIVE;}

    @Override
    public void applyOffensive(int enchantmentLevel, EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)){
            return;
        }
        Player player = (Player) event.getDamager();

        if(this.rageMap.containsKey(player.getUniqueId())){

        }

    }

    @Override
    public void applyPassiveEffect(Player player, int enchantmentLevel){}

    @Override
    public int applyDefensive(int enchantmentLevel, EntityDamageEvent event){return (int) 1.2;}

    @Override
    public void removeEffect(Player player){}



}
