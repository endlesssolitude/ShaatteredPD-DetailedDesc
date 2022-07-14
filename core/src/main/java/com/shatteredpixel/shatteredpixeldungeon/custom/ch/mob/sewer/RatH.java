package com.shatteredpixel.shatteredpixeldungeon.custom.ch.mob.sewer;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rat;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class RatH extends Rat {
    {
        immunities.add(Corruption.class);
    }

    @Override
    public void die(Object cause) {

        int toSummon = Random.chances(new float[]{0.85f, 0.15f}) + 1;

        while (toSummon-- > 0) {
            AlbinoH white = new AlbinoH();
            white.pos = Dungeon.level.randomRespawnCell(white);
            white.setModifier(Dungeon.hero.lvl / 2 , true);
            GameScene.add(white, 1f);
            white.beckon(pos);

            MagicMissile.boltFromChar(sprite.parent,
                    MagicMissile.MAGIC_MISSILE,
                    sprite,
                    new Ballistica(pos, white.pos, Ballistica.STOP_TARGET).collisionPos,
                    new Callback() {
                        @Override
                        public void call() {
                            new Flare(5, 25).color(0xFF4488, true).show(white.sprite, 2f);
                        }
                    });
        }

        super.die(cause);
    }
}
