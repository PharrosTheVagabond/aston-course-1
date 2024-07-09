package trainingground.hero;

import trainingground.enemy.Enemy;

public class Warrior extends Hero {

	public Warrior(String name, int startingHealth) {
		super(name, startingHealth);
	}
	
	@Override
	public void attackEnemy(Enemy enemy) {
		System.out.println(getName() + " бьёт " + enemy.getName());
		enemy.takeDamage(40);
	}
	
	@Override
	public void specialAbility(Enemy target, Iterable<Hero> heroes, Iterable<Enemy> enemies) {
		System.out.println(getName() + " замахивается!");
		for (Enemy enemy : enemies) {
			enemy.takeDamage(35);
		}
	}

}
