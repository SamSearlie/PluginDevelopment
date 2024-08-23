package defiantpvp.searlie.customEnchantments.enums;

public enum enchantmentRarity {
    COMMON("&7", "Common enchantment"),
    UNCOMMON("&a", "Uncommon enchantment"),
    RARE("&b", "Rare enchantment"),
    MYTHIC("&e", "Mythic enchantment"),
    LEGENDARY("&6", "Legendary enchantment"),
    SOUL("&4", "Soul enchantment");

    private final String colorCode;
    private final String description;

    enchantmentRarity(String colorCode, String description) {
        this.colorCode = colorCode;
        this.description = description;
    }

    //Get for color code
    public String getColorCode(){
        return colorCode;
    }

    public String getDescription() {
        return description;
    }

    public String getFormattedName(){
        return colorCode + name();
    }
}
