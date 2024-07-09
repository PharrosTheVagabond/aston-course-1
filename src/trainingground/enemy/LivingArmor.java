package trainingground.enemy;

import trainingground.hero.Hero;

public class LivingArmor extends Enemy {

	public LivingArmor(int startingHealth) {
		super("Живой доспех", startingHealth);
	}

	@Override
	public void attack(Hero hero) {
		System.out.println(getName() + " атакует " + hero.getName());
		hero.takeDamage(15);
	}

	@Override
	public void takeDamage(int damage) {
		damage -= 10;
		super.takeDamage(damage);
	}

}
