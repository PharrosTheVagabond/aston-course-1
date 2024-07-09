package trainingground;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import trainingground.enemy.Enemy;
import trainingground.enemy.Ghoul;
import trainingground.enemy.LivingArmor;
import trainingground.enemy.Mummy;
import trainingground.enemy.Slime;
import trainingground.enemy.Zombie;
import trainingground.hero.Archer;
import trainingground.hero.Hero;
import trainingground.hero.Mage;
import trainingground.hero.Warrior;
import trainingground.mortal.Mortal;

public class BattleGround {

	public static void main(String[] args) throws InterruptedException {
		Hero warrior = new Warrior("Олег", 600);
		Hero archer = new Archer("Леголас", 450);
		Hero mage = new Mage("Антонидас", 500);
		List<Hero> aliveHeroes = new ArrayList<>();
		aliveHeroes.add(warrior);
		aliveHeroes.add(archer);
		aliveHeroes.add(mage);
		
		int waves = gameLoop(aliveHeroes);
		
		System.out.println("GAME OVER");
		System.out.println("Зачищено волн: " + waves);
	}
	
	private static int gameLoop(List<Hero> aliveHeroes) throws InterruptedException {
		boolean gameOver = false;
		int wave = 1;
		
		Random random = new Random();
		List<Enemy> enemies = new ArrayList<>();
		List<Enemy> enemiesToAdd = new ArrayList<>();
		
		while (!gameOver) {
			if (enemies.isEmpty()) {
				System.out.println("=============== Волна " + wave + " ===============");
				for (Hero hero : aliveHeroes) {
					System.out.println(hero.getName() + ": " + hero.getHealth() + "/" + hero.getStartingHealth() + " здоровья");
				}
				System.out.println("=======================================\n");
				Thread.sleep(1500);
				fillWave(enemies, enemiesToAdd::add, wave, random);
			}
			
			for (Hero hero : aliveHeroes) {
				if (enemies.isEmpty()) {
					break;
				}
				
				if (hero.isAlive()) {
					Enemy enemy = enemies.get(random.nextInt(enemies.size()));
					if (random.nextDouble() < 0.1) {
						hero.specialAbility(enemy, aliveHeroes, enemies);
					} else {
						hero.attackEnemy(enemy);
					}
					System.out.println();
					Thread.sleep(500);
				}
				
				clearDeadMortals(enemies);
				if (!enemiesToAdd.isEmpty()) {
					enemies.addAll(enemiesToAdd);
					enemiesToAdd.clear();
				}
			}
			System.out.println();
			Thread.sleep(1000);
			
			if (enemies.isEmpty()) {
				System.out.println("Волна " + wave + " зачищена!\n");
				wave++;
			}
			else {
				for (Enemy enemy : enemies) {
					if (aliveHeroes.isEmpty()) {
						break;
					}
					
					if (enemy.isAlive()) {
						Hero targetHero = aliveHeroes.get(random.nextInt(aliveHeroes.size()));
						enemy.attack(targetHero);
						System.out.println();
						Thread.sleep(500);
					}
					
					clearDeadMortals(aliveHeroes);
				}
				System.out.println();
			}
			Thread.sleep(1000);
			
			if (aliveHeroes.isEmpty()) {
				gameOver = true;
			}
		}
		
		return wave - 1;
	}
	
	private static void fillWave(List<Enemy> enemies, Consumer<Enemy> addEnemy, int enemiesCount, Random random) {
		for (int i = 0; i < enemiesCount; i++) {
			Enemy enemy = makeRandomEnemy(random, addEnemy);
			enemies.add(enemy);
		}
	}
	
	private static Enemy makeRandomEnemy(Random random, Consumer<Enemy> addEnemy) {
		switch (random.nextInt(5)) {
		case 0:
			return new Ghoul(100);
		case 1:
			return new LivingArmor(150);
		case 2:
			return new Mummy(25);
		case 3:
			return new Slime(80, addEnemy);
		default:
			return new Zombie(100);
		}
	}
	
	private static void clearDeadMortals(List<? extends Mortal> enemies) {
		Iterator<? extends Mortal> iter = enemies.iterator();
		while (iter.hasNext()) {
			Mortal enemy = iter.next();
			if (!enemy.isAlive()) {
				iter.remove();
			}
		}
	}
}
