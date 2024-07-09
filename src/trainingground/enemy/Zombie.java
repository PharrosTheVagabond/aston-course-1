package trainingground.enemy;

import trainingground.hero.Hero;

public class Zombie extends Enemy {
	private boolean resurrected = false;

	public Zombie(int startingHealth) {
		super("Зомби", startingHealth);
	}

	@Override
	public void attack(Hero hero) {
		System.out.println(getName() + " атакует " + hero.getName());
		hero.takeDamage(20);
	}
	
	@Override
	protected void die() {
		if (!resurrected && RANDOM.nextBoolean()) {
			System.out.println(getName() + " воскрешается!");
			setHealth(getStartingHealth());
			resurrected = true;
		} else {
			super.die();
		}
	}

}
