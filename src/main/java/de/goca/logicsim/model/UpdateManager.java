package de.goca.logicsim.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Sorgt für die Verteilung der Update-Ticks.
 * 
 * Alle funktionen sind Thread-sicher. runUpdates iteriert über eine Kopie der Liste.
 * 
 * @author Felix Treede
 * 
 */
public class UpdateManager
{
	
	private List<Updatable> updatables = new LinkedList<Updatable>();
	
	/**
	 * Ruft auf jedes registrierte <code>Updatable</code>-Objekt die <code>update()</code>-Methode auf, wenn das
	 * jeweilige Dirty-Flag gesetzt ist.
	 * 
	 * Das Dirty-Flag wird automatisch gelöscht.
	 * 
	 * @see Updatable#update()
	 * @see Updatable#isDirty()
	 */
	public void runUpdates()
	{
		for (Updatable obj : getUpdatables())
		{
			if (obj.isDirty())
			{
				obj.clearDirty();
				obj.update();
			}
		}
	}
	
	/**
	 * Fügt ein Element zur Update-Liste hinzu.
	 * 
	 * @param e
	 *            das <code>Updatable</code>-Objekt
	 */
	public synchronized void addUpdatable(Updatable e)
	{
		updatables.add(e);
	}
	
	/**
	 * Entfernt ein Element aus der Update-Liste.
	 * 
	 * @param e
	 *            das <code>Updatable</code>-Objekt
	 */
	public synchronized void removeUpdatable(Updatable e)
	{
		updatables.remove(e);
	}
	
	public synchronized void addUpdatables(Collection<? extends Updatable> c)
	{
		updatables.addAll(c);
	}
	
	public synchronized void removeUpdatables(Collection<? extends Updatable> c)
	{
		updatables.removeAll(c);
	}
	
	/**
	 * Besorgt eine Kopie der aktuellen Elemente.
	 * 
	 * @return
	 */
	public synchronized List<Updatable> getUpdatables()
	{
		return new ArrayList<Updatable>(updatables);
	}
	
}
