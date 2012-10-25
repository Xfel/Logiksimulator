package de.goca.logicsim.model;

/**
 * Ein Bauteiltyp. Dient als Factory für Einzelne {@link AbstractPart}-Objekte.
 * 
 * @author Felix Treede
 * 
 */
public abstract class PartType
{
	
	private String id;
	
	private String name;
	
	/**
	 * Standard-Konstruktor
	 * 
	 * @param id
	 * @param name
	 */
	protected PartType(String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Erzeugt ein neues Part-Objekt.
	 * 
	 * @return
	 */
	public abstract AbstractPart createPart();
	
	/**
	 * Gibt die eindeutige Bauteils-ID zurück.
	 * 
	 * @return
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * Gibt den Anzeigenamen des Bauteils zurück.
	 * 
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PartType other = (PartType) obj;
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
		return true;
	}
	
}
