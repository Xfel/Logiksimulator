package de.goca.logicsim.model;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Eine allgemeine Verbindung zwischen einem Bauteil und einer Leitung.
 * 
 * @author Felix Treede
 * 
 */
public class Port {

	public static final int INPUT = 0x1;
	public static final int OUTPUT = 0x2;

	private int type;

	private int width;

	private BitSet data;

	private List<IPortListener> listeners = new ArrayList<IPortListener>();

	public int getType() {
		return type;
	}

	/**
	 * Prüft, ob dieser Port den Zustand der Leitung an das Bauteil weitergibt.
	 * 
	 * @return
	 */
	public boolean isInput() {
		return (type & INPUT) != 0;
	}

	/**
	 * Prüft, ob dieser Port den Zustand des Bauteils an die Leitung weitergibt.
	 * 
	 * @return
	 */
	public boolean isOutput() {
		return (type & OUTPUT) != 0;
	}

	/**
	 * Fügt einen Listener hinzu.
	 * 
	 * @param listener
	 */
	public void addListener(IPortListener listener) {
		listeners.add(listener);
	}

	/**
	 * Entfernt einen Listener.
	 * 
	 * @param listener
	 */
	public void removeListener(IPortListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Callback, wenn der Ausgang geändert wird (Änderung im Bauteil).
	 */
	protected void onOutputChanged() {
		for (IPortListener listener : listeners) {
			listener.onOutputChanged(this);
		}
	}

	/**
	 * Callback, wenn der Eingang geändert wird (Änderung in der Leitung).
	 */
	protected void onInputChanged() {
		for (IPortListener listener : listeners) {
			listener.onInputChanged(this);
		}
	}

}
