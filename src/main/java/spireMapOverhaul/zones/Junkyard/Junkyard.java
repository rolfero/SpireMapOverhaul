package spireMapOverhaul.zones.Junkyard;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.rewards.RewardItem;
import spireMapOverhaul.SpireAnniversary6Mod;
import spireMapOverhaul.abstracts.AbstractZone;
import spireMapOverhaul.zoneInterfaces.EncounterModifyingZone;
import spireMapOverhaul.zoneInterfaces.ModifiedEventRateZone;
import spireMapOverhaul.zoneInterfaces.RewardModifyingZone;
import spireMapOverhaul.zones.Junkyard.monsters.Junkbot;
import spireMapOverhaul.zones.Junkyard.monsters.Peddler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Junkyard extends AbstractZone implements RewardModifyingZone, EncounterModifyingZone, ModifiedEventRateZone {
    public static final String ID = "Junkyard";
    private static final String JUNKBOTS = SpireAnniversary6Mod.makeID("Junkbots");
    private static final String CULTIST_JUNKBOT = SpireAnniversary6Mod.makeID("Cultist_Junkbot");
    private static final String MUGGER_JUNKBOT = SpireAnniversary6Mod.makeID("Mugger_Junkbot");
    private static final String PEDDLER = SpireAnniversary6Mod.makeID("Peddler");

    public Junkyard() {
        super(ID, Icons.MONSTER, Icons.EVENT);
        this.width = 3;
        this.height = 3;
    }

    @Override
    public AbstractZone copy() {
        return new Junkyard();
    }

    @Override
    public Color getColor() {
        return Color.LIGHT_GRAY.cpy();
    }

    @Override
    public boolean cannotSkipCardRewards() {
        return true;
    }

    @Override
    public ArrayList<RewardItem> getAdditionalRewards() {
        ArrayList<RewardItem> newRewards = new ArrayList<RewardItem>();
        newRewards.add(new RewardItem());
        return newRewards;
    }

    @Override
    public List<EncounterModifyingZone.ZoneEncounter> getNormalEncounters() {
        return Arrays.asList(
                new EncounterModifyingZone.ZoneEncounter(JUNKBOTS, 1, () -> new MonsterGroup(
                        new AbstractMonster[] {
                                new Junkbot(-400.0F, 0.0F),
                                new Junkbot(-200.0F, 0.0F),
                        })),
                new EncounterModifyingZone.ZoneEncounter(CULTIST_JUNKBOT, 1, () -> new MonsterGroup(
                        new AbstractMonster[] {
                                new FungiBeast(-300.0F, 0.0F),
                                new Junkbot(0.0F, 0.0F),
                        })),
                new EncounterModifyingZone.ZoneEncounter(MUGGER_JUNKBOT, 1, () -> new MonsterGroup(
                        new AbstractMonster[] {
                                new AcidSlime_S(-300.0F, 0.0F, 0),
                                new SpikeSlime_S(-300.0F, 0.0F, 0),
                                new Junkbot(0.0F, 0.0F),
                        })),
                new EncounterModifyingZone.ZoneEncounter(PEDDLER, 1, () -> new MonsterGroup(
                new AbstractMonster[] {
                        new Peddler(0.0f, 0.0F)
                }))
        );
    }

    @Override
    public float zoneSpecificEventRate() {
        return 0.4f;
    }


}
