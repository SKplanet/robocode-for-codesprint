package net.sf.robocode.battle.peer;

import java.util.List;

/**
 * Fuels
 */
public class FuelItem {
	public static int FUEL_ITEM_DEFAULT_SIZE = 40;
	private int amount;
	private double x;
	private double y;
	private boolean consumed;
	private int size;

	private FuelItem() {}

	public FuelItem(int amount, double x, double y) {
		this.amount = amount;
		this.x = x;
		this.y = y;
		this.consumed = false;
		this.size = FUEL_ITEM_DEFAULT_SIZE;
	}
	public FuelItem(int amount, double x, double y, int size) {
		this.amount = amount;
		this.x = x;
		this.y = y;
		this.consumed = false;
		this.size = size;
	}

	public int getAmount() {
		return amount;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void update(List<RobotPeer> robotsAtRandom) {
	}

	public boolean isConsumed() {
		return consumed;
	}

	public int getSize() {
		return size;
	}
}
