package com.rmouduri.swingy.model.entity.hero;

import com.rmouduri.swingy.model.artifact.armor.*;
import com.rmouduri.swingy.model.artifact.helmet.*;
import com.rmouduri.swingy.model.artifact.weapon.*;

public class HeroFactory {
    /**
     * Generate a fresh hero
     * @param heroClass
     * @param nameParam
     * @return The fresh Hero
     */
    public static AbstractHero generateHero(final String heroClass, final String nameParam) {
        if (Archer.DEFAULT_HERO_CLASS_NAME.equals(heroClass)) {
            return new Archer(nameParam);
        } else if (Warrior.DEFAULT_HERO_CLASS_NAME.equals(heroClass)) {
            return new Warrior(nameParam);
        } else if (Wizard.DEFAULT_HERO_CLASS_NAME.equals(heroClass)) {
            return new Wizard(nameParam);
        }
        return null;
    }

    /**
     * Generate an already existing hero
     * @param heroClass
     * @param nameParam
     * @param experienceParam
     * @param helmetParam
     * @param armorParam
     * @param weaponParam
     * @return The already existing Hero
     */
    public static AbstractHero generateHero(final String heroClass, final String nameParam, final long experienceParam,
            final Helmet helmetParam, final Armor armorParam, final Weapon weaponParam) {
        if (Archer.DEFAULT_HERO_CLASS_NAME.equals(heroClass)) {
            return new Archer(nameParam, experienceParam,
                (Bycocket) helmetParam, (LeatherArmor) armorParam, (Bow) weaponParam);
        } else if (Warrior.DEFAULT_HERO_CLASS_NAME.equals(heroClass)) {
            return new Warrior(nameParam, experienceParam,
                    (HeavyHelmet) helmetParam, (HeavyArmor) armorParam, (Sword) weaponParam);
        } else if (Wizard.DEFAULT_HERO_CLASS_NAME.equals(heroClass)) {
            return new Wizard(nameParam, experienceParam,
                    (MagicHat) helmetParam, (Robe) armorParam, (Staff) weaponParam);
        }

        return null;
    }

    /**
     * Generate an already existing hero
     * @param heroClass
     * @param nameParam
     * @param experienceParam
     * @param helmetTierParam
     * @param armorTierParam
     * @param weaponTierParam
     * @return The already existing Hero
     */
    public static AbstractHero generateHero(final String heroClass, final String nameParam, final long experienceParam,
            final int helmetTierParam, final int armorTierParam, final int weaponTierParam) {
        if (Archer.DEFAULT_HERO_CLASS_NAME.equals(heroClass)) {
            return new Archer(nameParam, experienceParam,
                (Bycocket) HelmetFactory.generateHelmet(Bycocket.DEFAULT_HELMET_TYPE_NAME, helmetTierParam),
                (LeatherArmor) ArmorFactory.generateArmor(LeatherArmor.DEFAULT_ARMOR_TYPE_NAME, armorTierParam),
                (Bow) WeaponFactory.generateWeapon(Bow.DEFAULT_WEAPON_TYPE_NAME, weaponTierParam));
        } else if (Warrior.DEFAULT_HERO_CLASS_NAME.equals(heroClass)) {
            return new Warrior(nameParam, experienceParam,
                (HeavyHelmet) HelmetFactory.generateHelmet(HeavyHelmet.DEFAULT_HELMET_TYPE_NAME, helmetTierParam),
                (HeavyArmor) ArmorFactory.generateArmor(HeavyArmor.DEFAULT_ARMOR_TYPE_NAME, armorTierParam),
                (Sword) WeaponFactory.generateWeapon(Sword.DEFAULT_WEAPON_TYPE_NAME, weaponTierParam));
        } else if (Wizard.DEFAULT_HERO_CLASS_NAME.equals(heroClass)) {
            return new Wizard(nameParam, experienceParam,
                (MagicHat) HelmetFactory.generateHelmet(MagicHat.DEFAULT_HELMET_TYPE_NAME, helmetTierParam),
                (Robe) ArmorFactory.generateArmor(Robe.DEFAULT_ARMOR_TYPE_NAME, armorTierParam),
                (Staff) WeaponFactory.generateWeapon(Staff.DEFAULT_WEAPON_TYPE_NAME, weaponTierParam));
        }

        return null;
    }
}
