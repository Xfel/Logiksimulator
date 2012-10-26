package de.goca.logicsim.model;

/**
 * Eine Eigenschaft eines Part-Objektes.
 * 
 * @author Felix Treede
 * @param <T>
 *            the attribute type
 * 
 */
public class PartAttribute<T>
{
	
	/**
	 * Das Standard-Attribut "Breite", das, sofern unterstützt, die Verwendung einzelner Gatter ohne Aufwand mehrmals
	 * nebeneinander ermöglicht.
	 */
	public static final PartAttribute<Integer> WIDTH = new PartAttribute<Integer>("width", Integer.class,
			Integer.valueOf(1), Integer.valueOf(1), null);
	
	private String id;
	
	private Class<T> valueClass;
	
	private T defaultValue;
	
	private Comparable<? super T> minimum, maximum;
	
	/**
	 * 
	 * @param name
	 *            the attribute id, which should be unique for the part.
	 * @param valueClass
	 *            the value type
	 * @param defaultValue
	 *            standard-Wert (nicht <code>null</code>)
	 */
	public PartAttribute(String name, Class<T> valueClass, T defaultValue)
	{
		this.id = name;
		this.valueClass = valueClass;
		this.defaultValue = defaultValue;
	}
	
	/**
	 * Erlaubt die Spezifikation von Minimal- und Maximalwerten.
	 * 
	 * @param id
	 * @param valueClass
	 * @param defaultValue
	 *            standard-Wert (nicht <code>null</code>)
	 * @param minimum
	 * @param maximum
	 */
	public PartAttribute(String id, Class<T> valueClass, T defaultValue, Comparable<? super T> minimum,
			Comparable<? super T> maximum)
	{
		this.id = id;
		this.valueClass = valueClass;
		this.defaultValue = defaultValue;
		this.minimum = minimum;
		this.maximum = maximum;
	}
	
	/**
	 * 
	 * @return die Eigenschafts-ID
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * 
	 * @return den Datentyp des Attributs
	 */
	public Class<T> getValueClass()
	{
		return valueClass;
	}
	
	/**
	 * Gibt den standard-wert der Eigenschaft zurück.
	 * 
	 * @return
	 */
	public T getDefaultValue()
	{
		return defaultValue;
	}
	
	/**
	 * Überprüft, ob der gegebene Wert für diese Eigenschaft erlaubt ist.
	 * 
	 * @param value
	 * @return
	 */
	public boolean isValid(T value)
	{
		if (!valueClass.isInstance(value))
		{
			return false;
		}
		
		if (minimum != null && minimum.compareTo(value) < 0)
		{
			return false;
		}
		
		if (maximum != null && maximum.compareTo(value) > 0)
		{
			return false;
		}
		
		return true;
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
		PartAttribute<?> other = (PartAttribute<?>) obj;
		return this.id.equals(other.id);
	}
	
	@Override
	public String toString()
	{
		return id;
	}
}
