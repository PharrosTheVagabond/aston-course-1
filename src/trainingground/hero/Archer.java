package trainingground.hero;

import trainingground.enemy.Enemy;

public class Archer extends Hero {

	public Archer(String name, int startingHealth) {
		super(name, startingHealth);
	}
	
	@Override
	public void attackEnemy(Enemy enemy) {
		System.out.println(getName() + " стреляет в " + enemy.getName());
		enemy.takeDamage(45);
	}
	
	@Override
	public void specialAbility(Enemy target, Iterable<Hero> heroes, Iterable<Enemy> enemies) {
		for (Enemy enemy : enemies) {
			if (enemy.getHealth() > target.getHealth()) {
				target = enemy;
			}
		}
		System.out.println(getName() + " прицеливается в " + target.getName() + "!");
		target.takeDamage(target.getStartingHealth());
	}

}
