package de.goca.logicsim.model;

import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Eine Leitung
 * 
 * @author Felix Treede
 * 
 */
public class Wire extends Updatable implements IPortListener
{
	private Set<Port> sources = new HashSet<Port>();
	
	private Set<Port> connections = new HashSet<Port>();
	
	private int width;
	
	private BitSet state;
	
	/**
	 * 
	 * @param width
	 *            anzahl paralleler Leitungen
	 */
	public Wire(int width)
	{
		this.width = width;
		state = new BitSet(width);
	}
	
	@Override
	public void update()
	{
		state.clear();
		for (Port port : sources)
		{
			state.or(port.getOutput());
		}
		
		for (Port port : connections)
		{
			if (port.isInput())
			{
				port.setInput(state);
			}
		}
	}
	
	/**
	 * Verbindet einen port mit diesem Wire
	 * 
	 * @param port
	 */
	public void connect(Port port)
	{
		if (connections.contains(port))
		{
			return;
		}
		
		connections.add(port);
		
		if (port.isOutput())
		{
			sources.add(port);
			port.addListener(this);
			setDirty();
		}
		
		if(port.isInput()){
			port.setInput(state);
		}
	}
	
	/**
	 * Löst eine Verbindung
	 * 
	 * @param port
	 */
	public void disconnect(Port port)
	{
		if (!connections.contains(port))
		{
			return;
		}
		
		if (port.isOutput())
		{
			port.removeListener(this);
			sources.remove(port);
			setDirty();
		}
		
		connections.remove(port);
	}
	
	/**
	 * 
	 * @return eine Liste aller verbundenen Ports
	 */
	public Set<Port> getConnections()
	{
		return Collections.unmodifiableSet(connections);
	}
	
	@Override
	public void onOutputChanged(Port port)
	{
		setDirty();
	}
	
	@Override
	public void onInputChanged(Port port)
	{
		// sollten wir ja selbst gewesen sein...
	}
}
