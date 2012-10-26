/**
 * 
 */
package de.goca.logicsim.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.goca.logicsim.model.Updatable;
import de.goca.logicsim.model.UpdateManager;

/**
 * @author Felix Treede
 *
 */
public class UpdatableTest
{
	// stub class
	static class TestUpdatable extends Updatable{

		private boolean updated;
		
		@Override
		public void update()
		{
			updated=true;
		}
		
		@Override
		public void setDirty()
		{
			super.setDirty();
			updated=false;
		}
		
		@Override
		public void clearDirty()
		{
			super.clearDirty();
			updated=false;
		}
		
		/**
		 * Testet, ob die update-Methode aufgerufen wurde.
		 * @return
		 */
		public boolean wasUpdated()
		{
			return updated;
		}
	}
	
	private UpdateManager updateManager;
	private TestUpdatable testUpdatable;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		testUpdatable=new TestUpdatable();
		
		updateManager=new UpdateManager();
		updateManager.addUpdatable(testUpdatable);
	}
	
	/**
	 * Testet die Funktion des dirty-Flags.
	 */
	@Test
	public void testDirty()
	{
		assertFalse("dirty flag was not initialized to false", testUpdatable.isDirty());
		
		testUpdatable.setDirty();
		
		assertTrue("dirty flag was not set", testUpdatable.isDirty());
		
		testUpdatable.clearDirty();
		
		assertFalse("dirty flag was not cleared", testUpdatable.isDirty());
	}
	
	/**
	 * Testet die Funktion des Update Managers.
	 */
	@Test
	public void testUpdateManager()
	{
		assertFalse("precondition: dirty flag was not initialized to false", testUpdatable.isDirty());
		
		assertFalse("precondition: updated is not false", testUpdatable.wasUpdated());
		
		updateManager.runUpdates();
		
		assertFalse("updated although dirty was false", testUpdatable.wasUpdated());
		
		testUpdatable.setDirty();
		updateManager.runUpdates();
		
		assertTrue("not updated although dirty was true", testUpdatable.wasUpdated());
		assertFalse("dirty flag wasn't cleared by update", testUpdatable.isDirty());
		
	}
	
	
}
