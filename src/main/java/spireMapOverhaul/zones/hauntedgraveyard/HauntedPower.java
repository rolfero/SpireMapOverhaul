package spireMapOverhaul.zones.hauntedgraveyard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.vfx.combat.HbBlockBrokenEffect;
import spireMapOverhaul.SpireAnniversary6Mod;
import spireMapOverhaul.abstracts.AbstractSMOPower;
import spireMapOverhaul.zones.beastslair.BeastsLairZone;

public class HauntedPower extends AbstractSMOPower {

    public static final String POWER_ID = SpireAnniversary6Mod.makeID("HauntedPower");; //SHOULD NOT BE ABLE TO STACK.

    private static PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("");
    private static String[] DESC = powerStrings.DESCRIPTIONS;
    public static String ZONE_ID = HauntedGraveyardZone.ID;

    public boolean hasTriggered;

    public HauntedPower(AbstractCreature owner) {
        super(POWER_ID, powerStrings.NAME, ZONE_ID, PowerType.BUFF, false, owner, -1);
        this.hasTriggered = false;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (owner.currentHealth <= damageAmount) {
            hasTriggered = true; //You will die! So... don't!
            return owner.currentHealth-1;
        }
        return damageAmount;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && hasTriggered) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractDungeon.player.currentHealth = 0;
                    AbstractDungeon.player.healthBarUpdatedEvent();
                    AbstractDungeon.player.isDead = true;
                    AbstractDungeon.deathScreen = new DeathScreen(AbstractDungeon.getMonsters());
                    if (AbstractDungeon.player.currentBlock > 0) {
                        AbstractDungeon.player.loseBlock();
                    }
                    this.isDone = true;
                }
            });
        }

        if (hasTriggered)
            addToBot(new InstantKillAction(this.owner));
    }
}
