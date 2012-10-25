package de.goca.logicsim.model;

/**
 * Abstrakte Oberklasse für alle Objekte, die eine Update-Methode besitzen.
 * 
 * @author Felix Treede
 * 
 */
public abstract class Updatable {

	// "Update nötig"-Markierung.
	private boolean dirty;

	/**
	 * Bringt die berechneten Werte auf den neusten Stand.
	 * 
	 * @see #isDirty()
	 */
	public abstract void update();

	/**
	 * Überprüft die "Update nötig"-Markierung.
	 * 
	 * @return <code>true</code>, wenn {@code update()} aufgerufen werden
	 *         sollte.
	 * @see #update()
	 */
	public boolean isDirty() {
		return dirty;
	}

	/**
	 * Setzt die "Update nötig"-Markierung.
	 * 
	 * @see #isDirty()
	 */
	protected void setDirty() {
		dirty = true;
	}

	/**
	 * Entfernt die "Update nötig"-Markierung.
	 * 
	 * @see #isDirty()
	 */
	protected void clearDirty() {
		dirty = false;
	}
}
