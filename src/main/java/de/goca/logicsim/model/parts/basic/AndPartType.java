package de.goca.logicsim.model.parts.basic;

import de.goca.logicsim.model.AbstractPart;
import de.goca.logicsim.model.PartAttribute;
import de.goca.logicsim.model.PartType;

/**
 * a basic and gate
 * @author Felix Treede
 *
 */
public class AndPartType extends PartType
{

	/**
	 * Default constructor
	 */
	public AndPartType()
	{
		super("basic.and", "And gate", PartAttribute.WIDTH);
	}

	@Override
	public AbstractPart createPart()
	{
		return new AndPart(this);
	}
	
}
