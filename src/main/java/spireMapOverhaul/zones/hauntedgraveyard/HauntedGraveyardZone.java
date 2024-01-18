package spireMapOverhaul.zones.hauntedgraveyard;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.Byrd;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import spireMapOverhaul.SpireAnniversary6Mod;
import spireMapOverhaul.abstracts.AbstractZone;
import spireMapOverhaul.util.Wiz;
import spireMapOverhaul.zoneInterfaces.CombatModifyingZone;
import spireMapOverhaul.zoneInterfaces.RewardModifyingZone;
import spireMapOverhaul.zones.heavenlyClouds.FlightMod;
import spireMapOverhaul.zones.heavenlyClouds.HeavenlyFlightPower;

import java.util.ArrayList;

import static spireMapOverhaul.util.Wiz.atb;

public class HauntedGraveyardZone extends AbstractZone implements CombatModifyingZone, RewardModifyingZone {

    public static final String ID = "HauntedGraveyard";

    public HauntedGraveyardZone() {
        super(ID, Icons.MONSTER);
        this.width = 2;
        this.maxWidth = 4;
        this.height = 2;
        this.maxHeight = 4;
    }

    @Override
    public AbstractZone copy() {
        return new HauntedGraveyardZone();
    }

    @Override
    public Color getColor() {
        return Color.GRAY.cpy();
    }

    @Override
    public void atBattleStart() {
        for (AbstractMonster m : Wiz.getEnemies()) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new HauntedPower(m)));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HauntedPower(AbstractDungeon.player)));
    }

    @Override
    public void modifyRewardCards(ArrayList<AbstractCard> cards) {
        for (AbstractCard card : cards) {
            if (!card.isEthereal)
                CardModifierManager.addModifier(card, new EtherealMod());
        }
    }

}
