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
	private Port testPortIn;
	private Port testPortOut;
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
		testPortIn = new Port("testPortIn", Port.INPUT, 32);
		testPortOut = new Port("testPortOut", Port.OUTPUT, 32);
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
		
		part.addPort(testPortIn);
		
		assertEquals(length + 1, part.getPorts().size());
		
		part.addPort(testPortOut);
		
		assertEquals(length + 2, part.getPorts().size());
		
		try
		{
			part.addPort(testPortIn);
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
		part.addPort(testPortIn);
		
		assertEquals(null, part.getPort("Dieser Port existiert nicht"));
		assertEquals(testPortIn, part.getPort("testPortIn"));
	}
	
	@Test
	public void testGetAttribute()
	{
		try
		{
			part.getAttribute(attributes4); // Wert im Prinzip egal
			fail("Exception not thrown when expected to");
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("unsupported Attribute", e.getMessage());
		}
		
		assertEquals(Integer.valueOf(1), part.getAttribute(attributes1));
		
		part.setAttribute(attributes1, 2);
		assertEquals(Integer.valueOf(2), part.getAttribute(attributes1));
	}
	
	@Test
	public void testSetAttribute()
	{
		try
		{
			part.setAttribute(attributes4, Integer.valueOf(0)); // Wert im Prinzip egal
			fail("Exception not thrown when expected to");
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("unsupported Attribute", e.getMessage());
		}
		
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
	}
}
