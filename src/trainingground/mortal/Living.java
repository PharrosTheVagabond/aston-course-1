package trainingground.mortal;

public abstract class Living implements Mortal, HasName {
	private final int startingHealth;
	protected int health;
	
	public Living(int startingHealth) {
		this.startingHealth = startingHealth;
		this.health = startingHealth;
	}
	
	@Override
	public boolean isAlive() {
		return getHealth() > 0;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getStartingHealth() {
		return startingHealth;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}

	public void takeDamage(int damage) {
		if (isAlive()) {
			this.health -= damage;
			System.out.println(getName() + " получает " + damage + " урона");
			if (!isAlive()) {
				die();
			}
		}
	}
	
	protected void die() {
		System.out.println(getName() + " умирает!");
	}
	
	public void heal(int health) {
		if (this.getHealth() < getStartingHealth()) {
			health = Math.min(getStartingHealth() - this.getHealth(), health);
			System.out.println(getName() + " исцеляется на " + health + " единиц здоровья");
			this.health += health;
		}
	}
}
