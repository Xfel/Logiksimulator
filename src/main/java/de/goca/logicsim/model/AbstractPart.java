package de.goca.logicsim.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstrakte Oberklasse aller Bauteile.
 * 
 * @author Felix Treede
 * 
 */
public abstract class AbstractPart extends Updatable implements IPortListener{
	
	private List<Port> ports;

	private PartType type;

	/**
	 * Standard-Konstruktor
	 * 
	 * @param type
	 */
	protected AbstractPart(PartType type) {
		this.type = type;
		this.ports = new ArrayList<Port>();
	}

	/**
	 * Fügt einen Port hinzu. Sollte nur im Konstruktor aufgerufen werden.
	 * 
	 * @param port
	 * @throws IllegalArgumentException
	 *             wenn es bereits einen gleichnamigen Port gibt
	 */
	protected void addPort(Port port) {
		if (ports.contains(port)) {
			throw new IllegalArgumentException("Duplicate port");
		}

		ports.add(port);
		
		port.addListener(this);
	}

	/**
	 * Wird von Unterklassen überschrieben, um den Zustand des Bauteils
	 * entsprechend zu aktualisieren.
	 */
	public abstract void update();

	/**
	 * Gibt den Typ des Bauteils zurück.
	 * 
	 * @return
	 */
	public PartType getType() {
		return type;
	}

	/**
	 * Gibt die Liste aller Bauteil-Ports zurück.
	 * 
	 * Die Liste ist nicht veränderbar.
	 * 
	 * @return
	 */
	public List<Port> getPorts() {
		return Collections.unmodifiableList(ports);
	}
	
	/**
	 * Findet einen Port anhand seiner ID.
	 * @param id die Port-ID
	 * @return den Port
	 */
	public Port getPort(String id){
		for (Port port:ports) {
			if(port.getId().equals(id)){
				return port;
			}
		}
		return null;
	}
	
	@Override
	public void onInputChanged(Port port) {
		// markiern, um geänderte Eingabe zu verarbeiten.
		setDirty();
	}
	
	@Override
	public void onOutputChanged(Port port) {
		// no-op, we should have changed it ourselves...
	}
	
}
