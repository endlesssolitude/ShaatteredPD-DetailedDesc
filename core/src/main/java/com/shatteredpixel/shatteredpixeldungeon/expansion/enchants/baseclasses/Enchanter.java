 package com.shatteredpixel.shatteredpixeldungeon.expansion.enchants.baseclasses;

 import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.custom.messages.M;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.ItemSlot;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndInfoItem;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;

public class Enchanter extends Item {
    {
        unique = true;
        defaultAction = AC_ENCHANT;
        image = ItemSpriteSheet.KIT;
    }

    private static final String AC_ENCHANT = "enchant";

    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        actions.add(AC_ENCHANT);
        return actions;
    }

    @Override
    public void execute(Hero hero, String action) {
       if(action.equals(AC_ENCHANT)){
           GameScene.show(new WndEnchant());
       }else {
           super.execute(hero, action);
       }
    }

    public static class WndEnchant extends Window {
        private static final int BTN_SIZE = 24;
        private static final int WIDTH = 120;
        private static final int GAP = 2;

        private ItemButton[] inputs = new ItemButton[3];
        private ItemButton toEnchant;
        private RedButton execute;

        private EnchRecipe currentAvailableRecipe;

        public WndEnchant(){
            super();

            resize(WIDTH, 100);

            RenderedTextBlock title = PixelScene.renderTextBlock(M.L(Enchanter.class, "title"), 10);
            title.setPos(WIDTH/2f-title.width()/2, GAP);
            add(title);

            synchronized (inputs){
                float xpos = WIDTH/2f - inputs.length*BTN_SIZE/2f-GAP*3*((inputs.length-1)/2f);
                for(int i=0; i< inputs.length; ++i){
                    inputs[i]=new ItemButton(){
                        @Override
                        protected void onClick() {
                            super.onClick();
                            if(item!=null){
                                if (!item.collect()) {
                                    Dungeon.level.drop(item, Dungeon.hero.pos);
                                }
                                item = null;
                                slot.item(new WndBag.Placeholder(ItemSpriteSheet.SOMETHING));
                                updateState();
                            }else{
                                GameScene.show(WndBag.lastBag(inputSelector, WndBag.Mode.ALL, M.L(Enchanter.class, "select_ingredient")));
                            }
                        }

                        @Override
                        protected boolean onLongClick() {
                            if (item != null){
                                Game.scene().addToFront(new WndInfoItem(item));
                                return true;
                            }
                            return false;
                        }
                    };
                    inputs[i].setRect(xpos, 18, BTN_SIZE, BTN_SIZE);
                    xpos += BTN_SIZE + GAP*3;
                    add(inputs[i]);
                }
            }

            execute = new RedButton(""){
                Image arrow;

                @Override
                protected void createChildren() {
                    super.createChildren();

                    arrow = Icons.get(Icons.ARROW);
                    add(arrow);
                }

                @Override
                protected void layout() {
                    super.layout();
                    arrow.angle = 90;
                    arrow.x = x + (width + arrow.width)/2f;
                    arrow.y = y + (height - arrow.height)/2f;
                    PixelScene.align(arrow);
                }

                @Override
                public void enable(boolean value) {
                    super.enable(value);
                    if (value){
                        arrow.tint(1, 1, 0, 1);
                        arrow.alpha(1f);
                        bg.alpha(1f);
                    } else {
                        arrow.color(0, 0, 0);
                        arrow.alpha(0.6f);
                        bg.alpha(0.6f);
                    }
                }

                @Override
                protected void onClick() {
                    super.onClick();
                    if(currentAvailableRecipe != null){
                        doEnchant(currentAvailableRecipe);
                    }
                    Sample.INSTANCE.play(Assets.Sounds.EVOKE);
                }
            };
            execute.setRect(WIDTH/2f - 8, inputs[0].bottom() + GAP * 2, 16, 21);
            add(execute);

            toEnchant = new ItemButton(){
                @Override
                protected void onClick() {
                    super.onClick();
                    if(item!=null){
                            if (!item.collect()) {
                                Dungeon.level.drop(item, Dungeon.hero.pos);
                            }
                        item = null;
                        slot.item(new WndBag.Placeholder(ItemSpriteSheet.WEAPON_HOLDER));
                        updateState();
                    }else{
                        GameScene.show(WndBag.lastBag(new WndBag.Listener() {
                            @Override
                            public void onSelect(Item item) {
                                if(item != null) {
                                    if(item.isEquipped(Dungeon.hero)){
                                        GLog.w(M.L(Enchanter.class, "unequip_first"));
                                        return;
                                    }
                                    toEnchant.item(item.detach(Dungeon.hero.belongings.backpack));
                                    updateState();
                                }
                            }
                        }, WndBag.Mode.WEAPON, M.L(Enchanter.class, "select_weapon")));
                    }
                }

                @Override
                protected boolean onLongClick() {
                    if (item != null){
                        Game.scene().addToFront(new WndInfoItem(item));
                        return true;
                    }
                    return false;
                }
            };
            toEnchant.setRect(WIDTH/2f-BTN_SIZE/2f, execute.bottom() + GAP * 2, BTN_SIZE, BTN_SIZE);
            add(toEnchant);

            slotReset();
        }

        private void doEnchant(EnchRecipe recipe){
            if(!(toEnchant.item instanceof Weapon)){
                GLog.w(M.L(Enchanter.class, "illegal_receiver"));
                return;
            }
            if(!EnchRecipe.enchant((Weapon) toEnchant.item, recipe)){
                GLog.w(M.L(Enchanter.class, "enchant_fail_bug"));
                return;
            }
            synchronized (inputs) {
                for (int i = 0; i < inputs.length; i++) {
                    if (inputs[i] != null && inputs[i].item != null) {
                        inputs[i].item.quantity(inputs[i].item.quantity()-1);
                        if (inputs[i].item.quantity() <= 0) {
                            inputs[i].slot.item(new WndBag.Placeholder(ItemSpriteSheet.SOMETHING));
                            inputs[i].item = null;
                        } else {
                            inputs[i].slot.item(inputs[i].item);
                        }
                    }
                }
            }
            Item item = toEnchant.item;
            if(item!=null){
                if (!item.collect()) {
                    Dungeon.level.drop(item, Dungeon.hero.pos);
                }
            }
            toEnchant.slot.item(new WndBag.Placeholder(ItemSpriteSheet.WEAPON_HOLDER));
            updateState();
        }

        private void slotReset(){
            synchronized (inputs) {
                for (int i = 0; i < inputs.length; ++i) {
                    Item item = inputs[i].item;
                    if (item != null) {
                        if (!item.collect()) {
                            Dungeon.level.drop(item, Dungeon.hero.pos);
                        }
                    }
                    inputs[i].slot.item(new WndBag.Placeholder(ItemSpriteSheet.SOMETHING));
                }
            }
            Item item = toEnchant.item;
            if(item!=null){
                if (!item.collect()) {
                    Dungeon.level.drop(item, Dungeon.hero.pos);
                }
            }
            toEnchant.slot.item(new WndBag.Placeholder(ItemSpriteSheet.WEAPON_HOLDER));
        }

        private void updateState(){
            ArrayList<Class<? extends Item>> classes = new ArrayList<>();
            for(ItemButton itb: inputs){
                if(itb.item != null){
                    classes.add(itb.item.getClass());
                }
            }
            currentAvailableRecipe = EnchRecipe.searchForRecipe(classes);
            execute.enable(currentAvailableRecipe != null && toEnchant.item != null);
        }

        @Override
        public void onBackPressed() {
            slotReset();
            super.onBackPressed();
        }

        protected WndBag.Listener inputSelector = new WndBag.Listener() {
            @Override
            public void onSelect( Item item ) {
                synchronized (inputs) {
                    if (item != null && inputs[0] != null) {
                        if(item.isEquipped(Dungeon.hero)){
                            GLog.w(M.L(Enchanter.class, "unequip_first"));
                            return;
                        }
                        for (int i = 0; i < inputs.length; i++) {
                            if (inputs[i].item == null) {
                                inputs[i].item(item.detach(Dungeon.hero.belongings.backpack));
                                break;
                            }
                        }
                        updateState();
                    }
                }
            }
        };
    }

    /*
    public static class InscriptionInfo extends IconButton{
        public InscriptionInfo(){
            super();
            icon(new ItemSprite(new Sword().enchant(new Lucky())));
        }

        @Override
        protected void onClick() {
            super.onClick();
            GameScene.show();
        }
    }

    public static class WndInscriptionRecipe extends Window{
        public WndInscriptionRecipe(){

        }
    }
     */

    public static class ItemButton extends Component {

       protected NinePatch bg;
       protected ItemSlot slot;

       public Item item = null;

       @Override
       protected void createChildren() {
           super.createChildren();

           bg = Chrome.get( Chrome.Type.RED_BUTTON);
           add( bg );

           slot = new ItemSlot() {
               @Override
               protected void onPointerDown() {
                   bg.brightness( 1.2f );
                   Sample.INSTANCE.play( Assets.Sounds.CLICK );
               }
               @Override
               protected void onPointerUp() {
                   bg.resetColor();
               }
               @Override
               protected void onClick() {
                   ItemButton.this.onClick();
               }

               @Override
               protected boolean onLongClick() {
                   return ItemButton.this.onLongClick();
               }
           };
           slot.enable(true);
           add( slot );
       }

       protected void onClick() {}
       protected boolean onLongClick() {
           return false;
       }

       @Override
       protected void layout() {
           super.layout();

           bg.x = x;
           bg.y = y;
           bg.size( width, height );

           slot.setRect( x + 2, y + 2, width - 4, height - 4 );
       }

       public void item( Item item ) {
           slot.item( this.item = item );
       }
    }
}