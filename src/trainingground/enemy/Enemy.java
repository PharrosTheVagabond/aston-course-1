package trainingground.enemy;

import java.util.Random;

import trainingground.hero.Hero;
import trainingground.mortal.Living;

public abstract class Enemy extends Living {
	protected static final Random RANDOM = new Random();
	private final String name;
	
	public Enemy(String name, int startingHealth) {
		super(startingHealth);
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public abstract void attack(Hero hero);
}
