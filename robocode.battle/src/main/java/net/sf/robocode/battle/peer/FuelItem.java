package net.sf.robocode.battle.peer;

import net.sf.robocode.battle.BoundingRectangle;

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
	private final BoundingRectangle boundingBox = new BoundingRectangle();

	public FuelItem(double x, double y, int amount) {
		this.amount = amount;
		this.x = x;
		this.y = y;
		this.consumed = false;
		this.size = FUEL_ITEM_DEFAULT_SIZE;
		boundingBox.setRect(x - (size / 2), y - (size / 2), size, size);
	}
	public FuelItem(double x, double y, int size, int amount) {
		this.amount = amount;
		this.x = x;
		this.y = y;
		this.consumed = false;
		this.size = size;
		boundingBox.setRect(x - (size / 2), y - (size / 2), size, size);
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
		checkItemOccupied(robotsAtRandom);
	}

	private void checkItemOccupied(List<RobotPeer> robotsAtRandom){
		for (RobotPeer robot : robotsAtRandom) {
			if (!(robot == null || robot.isDead()) &&
					boundingBox.intersects(robot.getBoundingBox())) {
				consumed = true;
				robot.resetFuel(amount);
			}
		}
	}

	public boolean isConsumed() {
		return consumed;
	}

	public int getSize() {
		return size;
	}

	public BoundingRectangle getBoundingBox() {
		return boundingBox;
	}
}
