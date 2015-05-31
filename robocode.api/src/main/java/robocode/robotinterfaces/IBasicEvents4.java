package robocode.robotinterfaces;

import robocode.ScannedFuelItemEvent;

/**
 * Third extended version of the {@link IBasicEvents} interface.
 *
 */
public interface IBasicEvents4 extends IBasicEvents3 {

	/**
	 * This method is called when your robot sees fuelItems, i.e. when the
	 * robot's radar scan "hits" the fuelItem.
	 * You should override it in your robot if you want to be informed of this
	 * event. (if not your robot will stop moving!)
	 * <p/>
	 * This event is automatically called if there is a fuelItem in range of your
	 * radar.
	 * <p/>
	 * Note that the robot's radar can only see fuelItem within the range defined
	 * by {@link robocode.Rules#RADAR_SCAN_RADIUS} (1200 pixels).
	 * <p/>
	 * Also not that the bearing of the scanned fuelItem is relative to your
	 * robot's heading.
	 *
	 * @param event the scanned-robot event set by the game
	 * @see robocode.ScannedRobotEvent
	 * @see robocode.Event
	 * @see robocode.Rules#RADAR_SCAN_RADIUS
	 */
	void onScannedFuelItem(ScannedFuelItemEvent event);
}
