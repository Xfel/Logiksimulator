package de.goca.logicsim.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstrakte Oberklasse aller Bauteile.
 * 
 * @author Felix Treede
 * 
 */
public abstract class AbstractPart extends Updatable implements IPortListener
{
	
	private List<Port> ports;
	
	private PartType type;
	
	private Map<PartAttribute<?>, Object> attributes;
	
	/**
	 * Standard-Konstruktor
	 * 
	 * @param type
	 */
	protected AbstractPart(PartType type)
	{
		this.type = type;
		this.ports = new ArrayList<Port>();
		attributes = new HashMap<PartAttribute<?>, Object>();
	}
	
	/**
	 * Fügt einen Port hinzu. Sollte nur im Konstruktor aufgerufen werden.
	 * 
	 * @param port
	 * @throws IllegalArgumentException
	 *             wenn es bereits einen gleichnamigen Port gibt
	 */
	protected void addPort(Port port)
	{
		if (ports.contains(port))
		{
			throw new IllegalArgumentException("Duplicate port");
		}
		
		ports.add(port);
		port.owner = this;
		
		if (port.isInput())
		{
			port.addListener(this);
		}
	}
	
	/**
	 * Wird von Unterklassen überschrieben, um den Zustand des Bauteils entsprechend zu aktualisieren.
	 */
	@Override
	public abstract void update();
	
	/**
	 * Gibt den Typ des Bauteils zurück.
	 * 
	 * @return
	 */
	public PartType getType()
	{
		return type;
	}
	
	/**
	 * Gibt die Liste aller Bauteil-Ports zurück.
	 * 
	 * Die Liste ist nicht veränderbar.
	 * 
	 * @return
	 */
	public List<Port> getPorts()
	{
		return Collections.unmodifiableList(ports);
	}
	
	/**
	 * Findet einen Port anhand seiner ID.
	 * 
	 * @param id
	 *            die Port-ID
	 * @return den Port
	 */
	public Port getPort(String id)
	{
		for (Port port : ports)
		{
			if (port.getId().equals(id))
			{
				return port;
			}
		}
		return null;
	}
	
	/**
	 * Gibt den Wert der Eigenschaft zurück.
	 * 
	 * @param attribute Eigenschafts-ID
	 * @return den Eigenschaftswert
	 */
	public <T> T getAttribute(PartAttribute<T> attribute)
	{
		if (!type.isAttributeSupported(attribute))
		{
			throw new IllegalArgumentException("unsupported Attribute");
		}
		
		Object value = attributes.get(attribute);
		
		if (value == null)
		{
			return attribute.getDefaultValue();
		}
		
		return attribute.getValueClass().cast(value);
	}
	
	/**
	 * Setzt eine Eigenschaft.
	 * 
	 * @param attribute Eigenschafts-ID
	 * @param value der neue Wert
	 * @throws IllegalArgumentException
	 *             wenn die Eigenschaft nicht unterstützt wird oder wenn der neue Wert nicht erlaubt ist.
	 */
	public <T> void setAttribute(PartAttribute<T> attribute, T value)
	{
		if (!type.isAttributeSupported(attribute))
		{
			throw new IllegalArgumentException("unsupported Attribute");
		}
		if (!attribute.isValid(value))
		{
			throw new IllegalArgumentException("Invalid attribute value");
		}
		
		if (value.equals(attribute.getDefaultValue()))
		{
			attributes.remove(attribute);
		}
		else
		{
			attributes.put(attribute, value);
		}
	}
	
	@Override
	public void onInputChanged(Port port)
	{
		// markiern, um geänderte Eingabe zu verarbeiten.
		setDirty();
	}
	
	@Override
	public void onOutputChanged(Port port)
	{
		// no-op, we should have changed it ourselves...
	}
	
}
