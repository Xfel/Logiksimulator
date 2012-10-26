/**
 * 
 */
package de.goca.logicsim.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.goca.logicsim.model.AbstractPart;
import de.goca.logicsim.model.PartAttribute;
import de.goca.logicsim.model.PartType;
import de.goca.logicsim.model.Port;

/**
 * @author Felix Treede
 * 
 */
public class AbstractPartTest
{
	
	private TestPart part;// erbt von AbstractPart
	private static PartType partType;
	private Port testPort;
	private static PartAttribute<Integer> attributes0;
	private static PartAttribute<Integer> attributes1;
	private static PartAttribute<Integer> attributes2;
	private static PartAttribute<Integer> attributes3;
	private static PartAttribute<Integer> attributes4;
	
	// private static Set<PartAttribute<Integer>> attributes;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
//		attributes = new HashSet<PartAttribute<Integer>>(5);
		
//		attributes.add( new PartAttribute<Integer>("test_DefaultValue_0", Integer.class, 0));
//		attributes.add( new PartAttribute<Integer>("test_DefaultValue_1", Integer.class, 1));
//		attributes.add( new PartAttribute<Integer>("test_DefaultValue_2", Integer.class, 2));
//		attributes.add( new PartAttribute<Integer>("test_DefaultValue_3", Integer.class, 3, 0, 3));
//		attributes.add( new PartAttribute<Integer>("test_DefaultValue_4", Integer.class, 4, 0, 4));
		
		attributes0 = new PartAttribute<Integer>("test_DefaultValue_0", Integer.class, 0);
		attributes1 = new PartAttribute<Integer>("test_DefaultValue_1", Integer.class, 1);
		attributes2 = new PartAttribute<Integer>("test_DefaultValue_2", Integer.class, 2, 0, 2);
		attributes3 = new PartAttribute<Integer>("test_DefaultValue_3", Integer.class, 3, 0, 3);
		attributes4 = new PartAttribute<Integer>("test_DefaultValue_4", Integer.class, 4, 0, 4);
		
		partType = new TestPartType("tst", "TestPartType", attributes0, attributes1, attributes2);
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
	
	private static class TestPart extends AbstractPart
	{
		
		protected TestPart(PartType type)
		{
			super(type);
		}
		
		@Override
		public void update()
		{
			// empty
		}
		
		// Zugriff auf public gesetzt
		@Override
		public void addPort(Port port)
		{
			super.addPort(port);
		}
	}
	
	private static class TestPartType extends PartType
	{
		
		protected TestPartType(String id, String name, PartAttribute<?>... attributes)
		{
			super(id, name, attributes);
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
		
		assertEquals(length + 1, part.getPorts().size());
		
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
	
	@Test
	public void testGetAttribute()
	{
		
	}
	
	@Test
	public void testSetAttribute()
	{
		try
		{
			part.setAttribute(attributes2, Integer.valueOf(127));
			fail("Exception not thrown when expected to");
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid attribute value", e.getMessage());
		}
		
		part.setAttribute(attributes0, 1);
		assertEquals(Integer.valueOf(1), part.getAttribute(attributes0));
		
		part.setAttribute(attributes0, 0);
		assertEquals(Integer.valueOf(0), part.getAttribute(attributes0));
		
		try
		{
			part.setAttribute(attributes4, Integer.valueOf(0)); // Wert im Prinzip egal
			fail("Exception not thrown when expected to");
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
}
