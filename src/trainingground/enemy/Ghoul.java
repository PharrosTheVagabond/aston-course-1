package trainingground.enemy;

import trainingground.hero.Hero;

public class Ghoul extends Enemy {
	
	public Ghoul(int startingHealth) {
		super("Вурдалак", startingHealth);
	}
	
	@Override
	public void attack(Hero hero) {
		System.out.println(getName() + " атакует " + hero.getName());
		hero.takeDamage(20);
		this.heal(10);
	}

}
