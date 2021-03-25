package com.shatteredpixel.shatteredpixeldungeon.custom.ch.mob.city;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

public class AbsoluteBlindness extends Buff {
    {
        actPriority = VFX_PRIO;
        announced = true;
    }
    protected float left=0f;
    private int storedViewDistance;
    @Override
    public boolean act(){
        spend(TICK);
        if(target.viewDistance>0 && storedViewDistance != target.viewDistance){
            storedViewDistance = target.viewDistance;
        }
        target.viewDistance = 0;
        left-=1f;
        if(left<0) detach();
        return true;
    }

    @Override
    public void storeInBundle(Bundle b){
        super.storeInBundle(b);
        b.put("stroedVD", storedViewDistance);
        b.put("blindLeft", left);
    }

    @Override
    public void restoreFromBundle(Bundle b){
        super.restoreFromBundle(b);
        storedViewDistance = b.getInt("stroedVD");
        left = b.getFloat("blindLeft") + 1f;
    }

    public AbsoluteBlindness storeVD(int vd){
        storedViewDistance = vd;
        return this;
    }

    public AbsoluteBlindness addLeft(float left){
        this.left += left;
        return this;
    }

    @Override
    public void detach(){
        target.viewDistance = storedViewDistance;
        Dungeon.observe();
        super.detach();
    }
    @Override
    public int icon(){
        return BuffIndicator.BLINDNESS;
    }
    @Override
    public void tintIcon(Image icon){
        icon.hardlight(0x3366D4);
    }
}