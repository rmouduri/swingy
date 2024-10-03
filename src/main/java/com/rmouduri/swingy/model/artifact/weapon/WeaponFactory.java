package com.rmouduri.swingy.model.artifact.weapon;

public class WeaponFactory {
    /**
     * Generate a fresh weapon of type @param weaponType
     * @param weaponType
     * @param tier
     * @return The Weapon
     */
    public static Weapon generateWeapon(final String weaponType, final int tier) {
        if (tier < 0) {
            return null;
        }

        if (Bow.DEFAULT_WEAPON_TYPE_NAME.equals(weaponType)) {
            return new Bow(tier);
        } else if (Staff.DEFAULT_WEAPON_TYPE_NAME.equals(weaponType)) {
            return new Staff(tier);
        } else if (Sword.DEFAULT_WEAPON_TYPE_NAME.equals(weaponType)) {
            return new Sword(tier);
        }

        return null;
    }
}
