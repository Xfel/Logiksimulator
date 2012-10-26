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
public class Port
{
	
	public static final int INPUT = 0x1;
	public static final int OUTPUT = 0x2;
	
	private String id;
	
	private int type;
	
	private int width;
	
	private BitSet input, output;
	
	AbstractPart owner;
	
	private List<IPortListener> listeners = new ArrayList<IPortListener>();
	
	/**
	 * Standard-Konstruktor
	 * 
	 * @param id
	 * @param type
	 * @param width
	 */
	public Port(String id, int type, int width)
	{
		this.id = id;
		this.type = type;
		this.width = width;
		
		input = new BitSet(width);
		output = new BitSet(width);
	}
	
	public int getType()
	{
		return type;
	}
	
	/**
	 * Prüft, ob dieser Port den Zustand der Leitung an das Bauteil weitergibt.
	 * 
	 * @return
	 */
	public boolean isInput()
	{
		return (type & INPUT) != 0;
	}
	
	/**
	 * Prüft, ob dieser Port den Zustand des Bauteils an die Leitung weitergibt.
	 * 
	 * @return
	 */
	public boolean isOutput()
	{
		return (type & OUTPUT) != 0;
	}
	
	/**
	 * Fügt einen Listener hinzu.
	 * 
	 * @param listener
	 */
	public void addListener(IPortListener listener)
	{
		listeners.add(listener);
	}
	
	/**
	 * Entfernt einen Listener.
	 * 
	 * @param listener
	 */
	public void removeListener(IPortListener listener)
	{
		listeners.remove(listener);
	}
	
	/**
	 * Callback, wenn der Ausgang geändert wird (Änderung im Bauteil).
	 */
	protected void onOutputChanged()
	{
		for (IPortListener listener : listeners)
		{
			listener.onOutputChanged(this);
		}
	}
	
	/**
	 * Callback, wenn der Eingang geändert wird (Änderung in der Leitung).
	 */
	protected void onInputChanged()
	{
		for (IPortListener listener : listeners)
		{
			listener.onInputChanged(this);
		}
	}
	
	/**
	 * Gibt die Port-ID zurück.
	 * 
	 * @return
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * 
	 * @return das Bauteil, dem dr Port gehört.
	 */
	public AbstractPart getOwner()
	{
		return owner;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Port other = (Port) obj;
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}
		}
		else if (!id.equals(other.id))
		{
			return false;
		}
		if (owner == null)
		{
			if (other.owner != null)
			{
				return false;
			}
		}
		else if (!owner.equals(other.owner))
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @return Eingehendes Signal
	 */
	public BitSet getInput()
	{
		return input;
	}
	
	/**
	 * 
	 * @return Ausgehendes Signal
	 */
	public BitSet getOutput()
	{
		return output;
	}
	
	/**
	 * Ändert das eingehende Signal
	 * 
	 * @param input
	 */
	public void setInput(BitSet input)
	{
		if (input.equals(this.input))
		{
			return;
		}
		this.input.clear();
		this.input.or(input);
		onInputChanged();
	}
	
	/**
	 * Ändert das ausgehende Signal
	 * 
	 * @param input
	 */
	public void setOutput(BitSet output)
	{
		if (output.equals(this.output))
		{
			return;
		}
		this.output.clear();
		this.output.or(output);
		onInputChanged();
	}
	
}
