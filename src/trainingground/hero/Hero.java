package trainingground.hero;

import java.util.Random;

import trainingground.enemy.Enemy;
import trainingground.mortal.Living;

public abstract class Hero extends Living {
	protected static final Random RANDOM = new Random();
	private final String name;
	
	public Hero(String name, int startingHealth) {
		super(startingHealth);
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	protected void die() {
		System.out.println("<!> " + getName() + " умирает!");
	}
	
	public abstract void attackEnemy(Enemy enemy);
	public abstract void specialAbility(Enemy target, Iterable<Hero> heroes, Iterable<Enemy> enemies);
}
