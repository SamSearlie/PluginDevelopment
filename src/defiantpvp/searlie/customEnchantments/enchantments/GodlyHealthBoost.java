package defiantpvp.searlie.customEnchantments.enchantments;

import defiantpvp.searlie.customEnchantments.enums.enchantmentApplyType;
import defiantpvp.searlie.customEnchantments.enums.enchantmentRarity;
import defiantpvp.searlie.customEnchantments.enums.enchantmentStacks;
import defiantpvp.searlie.customEnchantments.enums.enchantmentType;
import defiantpvp.searlie.customEnchantments.interfaces.enchantmentInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GodlyHealthBoost implements enchantmentInterface {


    @Override
    public String getName() {return "Godly Health Boost";}
    @Override
    public String getDescription(){return "Gives player godly amounts of health boost";}
    @Override
    public int maxLevel() {return 3;}

    @Override
    public enchantmentApplyType getApplyType() {return enchantmentApplyType.ARMOR;}

    @Override
    public enchantmentRarity getRarity() {return enchantmentRarity.LEGENDARY;}

    @Override
    public enchantmentStacks stacks() {return enchantmentStacks.FALSE;}

    @Override
    public enchantmentType enchantType() {return enchantmentType.PASSIVE;}

    @Override
    public void applyOffensive(int a, EntityDamageByEntityEvent event){

    }

    @Override
    public void applyPassiveEffect(Player player, int enchantmentLevel){
        System.out.println(enchantmentLevel);
        PotionEffect effect = new PotionEffect(PotionEffectType.HEALTH_BOOST, 9999999, enchantmentLevel -1);
        player.addPotionEffect(effect);
    }

    @Override
    public int applyDefensive(int enchantmentLevel, EntityDamageEvent event){return 0;}

    @Override
    public void removeEffect(Player player){
        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
    }

}
