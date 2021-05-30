package com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.baseclasses;

import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.Cursed;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.Duel;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.Erasing;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.Gravity;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.Sacrificing;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.Switching;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.TierChange;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.limited.BleedingBlast;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.limited.Curing;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.limited.DarkFog;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.limited.GasVenting;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.limited.IceBreaking;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.limited.Imaging;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.limited.KillingWeak;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.limited.Meteor;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.limited.Overload;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.limited.Scattering;
import com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.wep.limited.Unholy;
import com.shatteredpixel.shatteredpixeldungeon.items.Ankh;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLevitation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfParalyticGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfPurity;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.BlizzardBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.InfernalBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfEarthenArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfShielding;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfShroudingFog;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.GooBlob;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.MetalShard;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAggression;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAugmentation;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfBlink;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfEnchantment;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfFlock;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredpixel.shatteredpixeldungeon.plants.Fadeleaf;
import com.shatteredpixel.shatteredpixeldungeon.plants.Icecap;
import com.shatteredpixel.shatteredpixeldungeon.plants.Sorrowmoss;
import com.shatteredpixel.shatteredpixeldungeon.plants.Stormvine;
import com.shatteredpixel.shatteredpixeldungeon.plants.Sungrass;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Arrays;

public enum EnchRecipe{
    METEOR_1(Meteor.class, 88, InfernalBrew.class, PotionOfLiquidFlame.class, PotionOfLevitation.class),
    ICE_BREAK(IceBreaking.class, 110, BlizzardBrew.class, PotionOfFrost.class, Icecap.Seed.class),
    UNHOLY(Unholy.class, 110, ScrollOfTerror.class, ScrollOfRage.class, ScrollOfRetribution.class),
    CURE(Curing.class, 150, PotionOfHealing.class, Sungrass.Seed.class, PotionOfHealing.class),
    LIGHT_SCATTERING(Scattering.LightScattering.class, 35, ScrollOfRecharging.class, StoneOfBlast.class, PotionOfInvisibility.class),
    FIERY_SCATTERING(Scattering.FireyScattering.class, 35, ScrollOfRecharging.class, StoneOfBlast.class, PotionOfLiquidFlame.class),
    ICEY_SCATTERING(Scattering.IceyScattering.class, 35, ScrollOfRecharging.class, StoneOfBlast.class, PotionOfFrost.class),
    OVERLOAD(Overload.class, 66, MetalShard.class, ScrollOfUpgrade.class, ScrollOfTransmutation.class),
    DARK_FOG(DarkFog.class, 55, PotionOfShroudingFog.class, PotionOfLevitation.class, StoneOfBlast.class),
    IMAGING(Imaging.class, 62, ScrollOfMirrorImage.class, ScrollOfMirrorImage.class, StoneOfFlock.class),
    BLEEDING_BLAST(BleedingBlast.class, 180, ScrollOfRage.class, ScrollOfRetribution.class, PotionOfHaste.class),
    GAS_VENTING(GasVenting.class, 70, PotionOfToxicGas.class, PotionOfToxicGas.class, PotionOfPurity.class),
    KILLING_WEAK(KillingWeak.class, 75, Sorrowmoss.Seed.class, ScrollOfTerror.class, PotionOfToxicGas.class),

    CURSED_ATTACK(Cursed.class, 1, MetalShard.class, GooBlob.class, Ankh.class),
    SWITCHING(Switching.class, 1, ScrollOfTeleportation.class, StoneOfBlink.class, PotionOfLevitation.class),
    SACRIFICING(Sacrificing.class, 1, MetalShard.class, PotionOfHealing.class, ScrollOfRetribution.class),
    GRAVITY(Gravity.class, 1, Earthroot.Seed.class, PotionOfParalyticGas.class, PotionOfEarthenArmor.class),
    DUEL(Duel.class, 1, PotionOfShielding.class, ScrollOfRetribution.class, StoneOfAggression.class),

    TIER_UP_ONE(TierChange.TierUpOne.class, 1, StoneOfAugmentation.class, PotionOfHealing.class),
    TIER_UP_TWO(TierChange.TierUpTwo.class, 1, ScrollOfTransmutation.class, StoneOfEnchantment.class),
    TIER_UP_THREE(TierChange.TierUpThree.class, 1, ScrollOfTransmutation.class, PotionOfStrength.class, ScrollOfUpgrade.class),
    TIER_DOWN_ONE(TierChange.TierDownOne.class, 1, GooBlob.class, StoneOfAugmentation.class, PotionOfHealing.class),
    TIER_DOWN_TWO(TierChange.TierDownTwo.class, 1,  GooBlob.class, StoneOfAugmentation.class, StoneOfEnchantment.class),
    TIER_DOWN_THREE(TierChange.TierDownThree.class, 1,  GooBlob.class, ScrollOfTransmutation.class, StoneOfAugmentation.class),

    ERASE(Erasing.class, 1, Fadeleaf.Seed.class, Stormvine.Seed.class);


    public ArrayList<Class<? extends Item>> input = new ArrayList<>();
    public Class<? extends Inscription> inscription;
    public int triggers;

    //the first boolean is never used, but it can distinguish the constructors because wild class is "the same".
    EnchRecipe(Class<? extends Inscription> ench, int triggers, Class<? extends Item>... in){
        this.inscription = ench;
        this.input.addAll(Arrays.asList(in));
        this.triggers = triggers;
    }

    public static final int last_enchant_index = 1;
    //find a corresponding recipe and return it, or return null.
    public static EnchRecipe searchForRecipe(ArrayList<Class<? extends Item>> in){
        for(EnchRecipe recipe: EnchRecipe.values()) {
            if(in.size()<recipe.input.size()) continue;
            boolean contain=true;
            for (Class<? extends Item> itemClass : in) {
                if(!recipe.input.contains(itemClass)){
                    contain=false;
                    break;
                }
            }
            if(contain){
                return recipe;
            }
        }
        return null;
    }
    //simply enchant
    //the return boolean indicates if ingredients should be consumed.
    public static boolean enchant(Weapon toEnchant, EnchRecipe recipe){
        if(recipe == null){
            return false;
        }
        Inscription inscription = Reflection.newInstance(recipe.inscription);
        if(inscription == null){
            return false;
        }
        if(inscription instanceof CountInscription){
            ((CountInscription) inscription).setTriggers(recipe.triggers);
        }
        if(toEnchant.inscription != null){
            toEnchant.inscription.detachFromWeapon();
        }
        inscription.attachToWeapon(toEnchant);
        return true;
    }

}
