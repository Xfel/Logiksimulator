package de.goca.logicsim.model.parts.basic;

import java.util.BitSet;

import de.goca.logicsim.model.AbstractPart;
import de.goca.logicsim.model.PartAttribute;
import de.goca.logicsim.model.PartType;
import de.goca.logicsim.model.Port;

/**
 * a basic and gate
 * @author Felix Treede
 *
 */
class AndPart extends AbstractPart
{

	private Port portA;
	private Port portB;
	private Port portOut;

	AndPart(PartType type)
	{
		super(type);
		
		portA=new Port("A", Port.INPUT, 1);
		
		portB=new Port("B", Port.INPUT, 1);

		portOut=new Port("X", Port.INPUT, 1);
	}

	@Override
	public void update()
	{
		BitSet bs=(BitSet) portA.getInput().clone();
		bs.and(portB.getInput());
		
		portOut.setOutput(bs);
	}
	
	@Override
	protected <T> void onAttributeChanged(PartAttribute<T> attribute, T value)
	{
		if(attribute==PartAttribute.WIDTH){
			int newWidth=((Integer)value).intValue();
			
			portA.setWidth(newWidth);
			portB.setWidth(newWidth);
			portOut.setWidth(newWidth);
		}
	}
	
}
