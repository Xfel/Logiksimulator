/**
 * 
 */
package de.goca.logicsim.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.goca.logicsim.model.PartAttribute;

/**
 * 
 * 
 * @author Joachim
 * 
 */
public class PartAttributeTest
{
	private PartAttribute<Integer> integerTest = new PartAttribute<Integer>("integerTest", Integer.class, 0);
	private PartAttribute<String> stringTest = new PartAttribute<String>("stringTest", String.class, "test");
	
	private PartAttribute rawTest = new PartAttribute("rawTest", Integer.class, 3, null, null);
	private PartAttribute<Integer> noBoundTest = new PartAttribute<Integer>("noBoundTest", Integer.class, 3, null, null);
	private PartAttribute<Integer> maxValidTest = new PartAttribute<Integer>("maxValidTest", Integer.class, 3, null, 5);
	private PartAttribute<Integer> minValidTest = new PartAttribute<Integer>("minValidTest", Integer.class, 3, 1, null);
	private PartAttribute<Integer> minMaxValidTest = new PartAttribute<Integer>("minMaxValidTest", Integer.class, 3, 1,
			5);
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}
	
	/**
	 * Test method for {@link de.goca.logicsim.model.PartAttribute#getDefaultValue()}.
	 */
	@Test
	public void testGetDefaultValue()
	{
		assertEquals(Integer.valueOf(0), integerTest.getDefaultValue());
		assertEquals("test", stringTest.getDefaultValue());
	}
	
	/**
	 * Test method for {@link de.goca.logicsim.model.PartAttribute#getId()}.
	 */
	@Test
	public void testGetId()
	{
		assertEquals("integerTest", integerTest.getId());
		assertEquals("stringTest", stringTest.getId());
	}
	
	/**
	 * Test method for {@link de.goca.logicsim.model.PartAttribute#getValueClass()}.
	 */
	@Test
	public void testGetValueClass()
	{
		assertEquals(Integer.class, integerTest.getValueClass());
		assertEquals(String.class, stringTest.getValueClass());
	}
	
	/**
	 * Test method for {@link de.goca.logicsim.model.PartAttribute#isValid(java.lang.Object)}.
	 */
	@Test
	public void testIsValid()
	{
//		assertFalse(rawTest.isValid(Integer.valueOf("Falsche Eingabe")));//in dieser Umgebung anscheinend nicht testbar
		
		assertTrue(noBoundTest.isValid(Integer.valueOf(0)));// keine Schranken vorhanden
		
		assertFalse(minValidTest.isValid(Integer.valueOf(-1))); // minVal = 1
		assertTrue(minValidTest.isValid(Integer.valueOf(3))); // minVal = 1
		
		assertFalse(maxValidTest.isValid(Integer.valueOf(7))); // maxVal = 5
		assertTrue(maxValidTest.isValid(Integer.valueOf(3))); // maxVal = 5
	}
}
