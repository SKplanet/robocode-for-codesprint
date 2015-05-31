/**
 * Copyright (c) 2001-2014 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 */
package robocode;


import net.sf.robocode.peer.IRobotStatics;
import net.sf.robocode.serialization.ISerializableHelper;
import net.sf.robocode.serialization.RbSerializer;
import robocode.robotinterfaces.IBasicEvents;
import robocode.robotinterfaces.IBasicEvents4;
import robocode.robotinterfaces.IBasicRobot;

import java.awt.*;
import java.nio.ByteBuffer;


/**
 * A ScannedFuelItemEvent is sent to {@link Robot#onScannedFuelItem(ScannedFuelItemEvent)
 * onScannedFuel()} when you scan a robot.
 * You can use the information contained in this event to determine what to do.
 *
 */
public class ScannedFuelItemEvent extends Event {
	private static final long serialVersionUID = 1L;
	private final static int DEFAULT_PRIORITY = 10;

	private final double amount;
	private final double bearing;
	private final double distance;

	/**
	 * Called by the game to create a new ScannedFuelItemEvent.
	 *
	 * @param amountOfFuel  the amount of the scanned fuelItem
	 * @param bearing  the bearing of the scanned fuelItem, in radians
	 * @param distance the distance from your robot to the scanned fuelItem
	 *
	 */
	public ScannedFuelItemEvent(double amountOfFuel, double bearing, double distance) {
		super();
		this.amount = amountOfFuel;
		this.bearing = bearing;
		this.distance = distance;
	}

	/**
	 * Returns the bearing to the fuelItem you scanned, relative to your robot's
	 * heading, in degrees (-180 <= getBearing() < 180)
	 *
	 * @return the bearing to the fuelItem you scanned, in degrees
	 */
	public double getBearing() {
		return bearing * 180.0 / Math.PI;
	}

	/**
	 * Returns the bearing to the fuelItem you scanned, relative to your robot's
	 * heading, in radians (-PI <= getBearingRadians() < PI)
	 *
	 * @return the bearing to the fuelItem you scanned, in radians
	 */
	public double getBearingRadians() {
		return bearing;
	}

	/**
	 * Returns the distance to the fuelItem (your center to its center).
	 *
	 * @return the distance to the fuelItem.
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Returns the amount of the fuelItem.
	 *
	 * @return the amount of the fuelItem
	 */
	public double getAmountOfFuel() {
		return amount;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(Event event) {
		final int res = super.compareTo(event);
		if (res != 0) {
			return res;
		}
		// Compare the distance, if the events are ScannedRobotEvents
		// The shorter distance to the robot, the higher priority
		if (event instanceof ScannedFuelItemEvent) {
			return (int) (this.getDistance() - ((ScannedFuelItemEvent) event).getDistance());
		}
		// No difference found
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	final int getDefaultPriority() {
		return DEFAULT_PRIORITY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	final void dispatch(IBasicRobot robot, IRobotStatics statics, Graphics2D graphics) {
		IBasicEvents listener = robot.getBasicEventListener();
		if (listener != null && IBasicEvents4.class.isAssignableFrom(listener.getClass())) {
			((IBasicEvents4) listener).onScannedFuelItem(this);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	byte getSerializationType() {
		return RbSerializer.ScannedFuelItemEvent_TYPE;
	}

	static ISerializableHelper createHiddenSerializer() {
		return new SerializableHelper();
	}

	private static class SerializableHelper implements ISerializableHelper {
		public int sizeOf(RbSerializer serializer, Object object) {
			ScannedFuelItemEvent obj = (ScannedFuelItemEvent) object;
			return RbSerializer.SIZEOF_TYPEINFO + 3 * RbSerializer.SIZEOF_DOUBLE;
		}

		public void serialize(RbSerializer serializer, ByteBuffer buffer, Object object) {
			ScannedFuelItemEvent obj = (ScannedFuelItemEvent) object;
			serializer.serialize(buffer, obj.amount);
			serializer.serialize(buffer, obj.bearing);
			serializer.serialize(buffer, obj.distance);
		}

		public Object deserialize(RbSerializer serializer, ByteBuffer buffer) {
			double amount = buffer.getDouble();
			double bearing = buffer.getDouble();
			double distance = buffer.getDouble();

			return new ScannedFuelItemEvent(amount, bearing, distance);
		}
	}
}
