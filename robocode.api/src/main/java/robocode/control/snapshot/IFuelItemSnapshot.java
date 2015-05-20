package robocode.control.snapshot;

public interface IFuelItemSnapshot {
	/**
	 * Returns the fuel amount
	 *
	 * @return the fuel amount.
	 */
	int getAmount();

	/**
	 * Returns the X position of the fuel item.
	 *
	 * @return the X position of the fuel item.
	 */
	double getX();

	/**
	 * Returns the Y position of the bullet.
	 *
	 * @return the Y position of the bullet.
	 */
	double getY();

	int getSize();
}
