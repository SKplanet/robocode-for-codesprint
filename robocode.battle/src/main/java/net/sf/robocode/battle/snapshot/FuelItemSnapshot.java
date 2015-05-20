package net.sf.robocode.battle.snapshot;

import net.sf.robocode.battle.peer.FuelItem;
import robocode.control.snapshot.IFuelItemSnapshot;

public class FuelItemSnapshot implements IFuelItemSnapshot {

	private int amount;
	private double x;
	private double y;
	private boolean consumed;
	private int size;

	public FuelItemSnapshot(FuelItem fuelItem) {
		this.amount = fuelItem.getAmount();
		this.x = fuelItem.getX();
		this.y = fuelItem.getY();
		this.consumed = fuelItem.isConsumed();
		this.size = fuelItem.getSize();
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

	public int getSize() {
		return size;
	}
}
