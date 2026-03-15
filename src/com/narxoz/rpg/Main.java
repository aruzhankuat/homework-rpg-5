package com.narxoz.rpg;

import com.narxoz.rpg.decorator.*;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("Homework 5 Demo: Decorator + Facade");

        HeroProfile hero = new HeroProfile("Warrior", 100);
        BossEnemy boss = new BossEnemy("Dragon", 120, 15);

        AttackAction basic = new BasicAttack("Sword Strike", 15);
        AttackAction enhanced = new CriticalFocusDecorator(
                new FireRuneDecorator(
                        new PoisonCoatingDecorator(basic)
                )
        );

        System.out.println("Action: " + enhanced.getActionName());
        System.out.println("Damage: " + enhanced.getDamage());
        System.out.println("Effects: " + enhanced.getEffectSummary());

        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, enhanced);

        System.out.println("\n Battle Log ");
        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\nFinal Result:");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
    }
}