package de.goca.logicsim.model;

/**
 * Listener für Port-Änderungen
 * 
 * @author Felix Treede
 * 
 */
public interface IPortListener
{
	
	/**
	 * Callback, wenn der Ausgang geändert wird (Änderung im Bauteil).
	 * 
	 * @param port
	 *            der geänderte Port
	 */
	void onOutputChanged(Port port);
	
	/**
	 * Callback, wenn der Eingang geändert wird (Änderung in der Leitung).
	 * 
	 * @param port
	 *            der geänderte Port
	 */
	void onInputChanged(Port port);
	
}
