package defiantpvp.searlie.customEnchantments.interfaces;

import defiantpvp.searlie.customEnchantments.enums.enchantmentApplyType;
import defiantpvp.searlie.customEnchantments.enums.enchantmentRarity;
import defiantpvp.searlie.customEnchantments.enums.enchantmentStacks;
import defiantpvp.searlie.customEnchantments.enums.enchantmentType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public interface enchantmentInterface {
    int maxLevel();
    void applyPassiveEffect(Player player, int enchantmentLevel);
    void applyOffensive(int a, EntityDamageByEntityEvent event);
    int applyDefensive(int enchantmentLevel, EntityDamageEvent event);
    void removeEffect(Player player);
    String getName();
    String getDescription();
    enchantmentApplyType getApplyType();
    enchantmentRarity getRarity();
    enchantmentStacks stacks();
    enchantmentType enchantType();
}
