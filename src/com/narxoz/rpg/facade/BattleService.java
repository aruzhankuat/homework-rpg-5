package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;
import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        int rounds = 0;

        while (hero.isAlive() && boss.isAlive() && rounds < 20) {
            rounds++;
            int hDmg = action.getDamage();
            boss.takeDamage(hDmg);
            result.addLine("Раунд " + rounds + ": Герой нанес " + hDmg + " урона (" + action.getEffectSummary() + ")");

            if (!boss.isAlive()) break;

            int bDmg = boss.getAttackPower() + random.nextInt(5);
            hero.takeDamage(bDmg);
            result.addLine("Раунд " + rounds + ": Босс нанес " + bDmg + " урона");
        }

        result.setWinner(hero.isAlive() ? hero.getName() : boss.getName());
        result.setRounds(rounds);
        return result;
    }
}