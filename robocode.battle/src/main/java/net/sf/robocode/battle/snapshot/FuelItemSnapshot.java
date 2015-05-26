package net.sf.robocode.battle.snapshot;

import net.sf.robocode.battle.peer.FuelItem;
import net.sf.robocode.serialization.IXmlSerializable;
import net.sf.robocode.serialization.SerializableOptions;
import net.sf.robocode.serialization.XmlReader;
import net.sf.robocode.serialization.XmlWriter;
import robocode.control.snapshot.IFuelItemSnapshot;

import java.io.IOException;
import java.io.Serializable;

public class FuelItemSnapshot implements Serializable, IXmlSerializable, IFuelItemSnapshot {

	private int amount;
	private double x;
	private double y;
	private boolean consumed;
	private int size;

	public FuelItemSnapshot() {
		this.consumed = true;
	}

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

	public boolean isConsumed() { return consumed; }

	public void writeXml(XmlWriter writer, SerializableOptions options) throws IOException {
		writer.startElement(options.shortAttributes ? "f" : "fuelItem"); {
			writer.writeAttribute(options.shortAttributes ? "a" : "amount", amount);
			writer.writeAttribute(options.shortAttributes ? "s" : "size", size);
			writer.writeAttribute(options.shortAttributes ? "c" : "consumed", consumed);
			writer.writeAttribute("x", x, options.trimPrecision);
			writer.writeAttribute("y", y, options.trimPrecision);
		}
		writer.endElement();
	}

	@Override
	public XmlReader.Element readXml(XmlReader reader) {
		return reader.expect("fuelItem", "f", new XmlReader.Element() {
			public IXmlSerializable read(XmlReader reader) {
				final FuelItemSnapshot snapshot = new FuelItemSnapshot();

				reader.expect("amount", "a", new XmlReader.Attribute() {
					public void read(String value) {
						snapshot.amount = Integer.parseInt(value);
					}
				});

				reader.expect("size", "s", new XmlReader.Attribute() {
					public void read(String value) {
						snapshot.size = Integer.parseInt(value);
					}
				});

				reader.expect("consumed", "c", new XmlReader.Attribute() {
					public void read(String value) {
						snapshot.consumed = Boolean.valueOf(value);
					}
				});

				reader.expect("x", new XmlReader.Attribute() {
					public void read(String value) {
						snapshot.x = Double.parseDouble(value);
					}
				});

				reader.expect("y", new XmlReader.Attribute() {
					public void read(String value) {
						snapshot.y = Double.parseDouble(value);
					}
				});


				return snapshot;
			}
		});	}
}
