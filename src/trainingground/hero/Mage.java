package trainingground.hero;

import trainingground.enemy.Enemy;

public class Mage extends Hero {

	public Mage(String name, int startingHealth) {
		super(name, startingHealth);
	}
	
	@Override
	public void attackEnemy(Enemy enemy) {
		System.out.println(getName() + " применяет заклинание к " + enemy.getName());
		enemy.takeDamage(35);
	}

	@Override
	public void specialAbility(Enemy target, Iterable<Hero> heroes, Iterable<Enemy> enemies) {
		System.out.println(getName() + " исцеляет союзников!");
		for (Hero hero : heroes) {
			if (hero.isAlive()) {
				hero.heal(250);
			}
		}
	}

}
