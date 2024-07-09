package trainingground.enemy;

import java.util.function.Consumer;

import trainingground.hero.Hero;

public class Slime extends Enemy {
	private Consumer<Enemy> addEnemies;
	private boolean isLarge;

	public Slime(int startingHealth, Consumer<Enemy> addEnemies) {
		this(startingHealth, true);
		this.addEnemies = addEnemies;
	}

	private Slime(int startingHealth, boolean large) {
		super("Слизь", startingHealth);
		this.isLarge = large;
	}
	
	@Override
	public void attack(Hero hero) {
		int damage;
		if (isLarge) {
			damage = 30;
		} else {
			damage = 10;
		}
		System.out.println(getName() + " атакует " + hero.getName());
		hero.takeDamage(damage);
	}
	
	@Override
	protected void die() {
		if (isLarge) {
			System.out.println(getName() + " разделяется надвое!");
			for (int i = 0; i < 2; i++) {
				Enemy smallSlime = new Slime(this.getStartingHealth() / 2, false);
				addEnemies.accept(smallSlime);
			}
		} else {
			super.die();
		}
	}

}
