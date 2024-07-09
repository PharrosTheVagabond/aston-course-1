package trainingground.enemy;

import trainingground.hero.Hero;

public class Mummy extends Enemy {
	private Hero cursedHero = null;
	private int curseTimer = 4;

	public Mummy(int startingHealth) {
		super("Мумия", startingHealth);
	}
	
	@Override
	public void attack(Hero hero) {
		if (this.cursedHero == null || !this.cursedHero.isAlive()) {
			System.out.println(getName() + " проклинает " + hero.getName() + "!");
			this.cursedHero = hero;
			System.out.println("Проклятие " + cursedHero.getName() + ": осталось " + curseTimer + " хода");
		} else {
			curseTimer--;
			if (curseTimer > 0) {
				System.out.println("Проклятие " + cursedHero.getName() + ": осталось " + curseTimer + " хода");
			} else {
				System.out.println("Проклятие " + cursedHero.getName() + " наступило!");
				cursedHero.takeDamage(cursedHero.getStartingHealth());
			}
		}
	}

}
