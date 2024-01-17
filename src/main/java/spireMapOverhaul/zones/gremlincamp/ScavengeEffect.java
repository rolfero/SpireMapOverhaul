package spireMapOverhaul.zones.gremlincamp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ScavengeEffect extends AbstractGameEffect {
    private static final float DURATION = 2.0f;
    private boolean playedSound;
    private Color screenColor;

    protected int goldToGain;

    public ScavengeEffect(int goldToGain) {
        this.playedSound = false;
        this.screenColor = AbstractDungeon.fadeColor.cpy();
        this.duration = DURATION;
        this.screenColor.a = 0.0f;
        this.goldToGain = goldToGain;
        ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.updateBlackScreenColor();
        if (!this.playedSound) {
            this.playedSound = true;
            CardCrawlGame.sound.play("GOLD_GAIN_3", 0.1F);
        }
        if (this.duration < 0.0f) {
            this.isDone = true;
            AbstractDungeon.player.gainGold(goldToGain);
            ((RestRoom) AbstractDungeon.getCurrRoom()).fadeIn();
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
        }
    }

    private void updateBlackScreenColor() {
        if (this.duration > 1.5f) {
            this.screenColor.a = Interpolation.fade.apply(1.0f, 0.0f, (this.duration - 1.5f) * 2.0f);
        }
        else if (this.duration < 1.0f) {
            this.screenColor.a = Interpolation.fade.apply(0.0f, 1.0f, this.duration);
        }
        else {
            this.screenColor.a = 1.0f;
        }
    }

    public void render(final SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0f, 0.0f, Settings.WIDTH, Settings.HEIGHT);
    }

    @Override
    public void dispose() {

    }
}
