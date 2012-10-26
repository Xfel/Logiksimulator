/**
 * 
 */
package de.goca.logicsim.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.goca.logicsim.model.AbstractPart;
import de.goca.logicsim.model.PartType;
import de.goca.logicsim.model.Port;

/**
 * @author Felix Treede
 *
 */
public class AbstractPartTest
{
	
	private TestPart part;//erbt von AbstractPart
	private static PartType partType;
	private Port testPort;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		partType = new TestPartType("tst","TestPartType");
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		part = (TestPart) partType.createPart();
		testPort = new Port("tstPort", Port.INPUT, 32);
	}
	
	private static class TestPart extends AbstractPart{

		protected TestPart(PartType type)
		{
			super(type);
		}

		@Override
		public void update()
		{
			//empty
		}
		
		//Zugriff auf public gesetzt
		@Override
		public void addPort(Port port)
		{
			super.addPort(port);
		}
	}
	private static class TestPartType extends PartType{

		protected TestPartType(String id, String name)
		{
			super(id, name);
		}

		@Override
		public AbstractPart createPart()
		{
			return new TestPart(this);
		}
		
	}
	
	
	/**
	 * Test method for {@link de.goca.logicsim.model.AbstractPart#addPort(de.goca.logicsim.model.Port)}.
	 */
	@Test
	public void testAddPort()
	{
		int length = part.getPorts().size();
		
		part.addPort(testPort);
		
		assertEquals(length+1, part.getPorts().size());
		
		try
		{
			part.addPort(testPort);
			fail("Exception not thrown when expected to");
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Duplicate port", e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link de.goca.logicsim.model.AbstractPart#getType()}.
	 */
	@Test
	public void testGetType()
	{
		assertEquals(partType, part.getType());
	}
	
	/**
	 * Test method for {@link de.goca.logicsim.model.AbstractPart#getPort(java.lang.String)}.
	 */
	@Test
	public void testGetPort()
	{
		part.addPort(testPort);
		
		assertEquals(null, part.getPort("Dieser Port existiert nicht"));
		assertEquals(testPort, part.getPort("tstPort"));
	}
}
