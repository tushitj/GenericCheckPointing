package genericCheckpointing.driver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.RandomGenerator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class Driver {


	//	ProxyCreator pc = new ProxyCreator();

	// create an instance of StoreRestoreHandler (which implements
	// the InvocationHandler

	// create a proxy
	//		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
	//									 new Class[] {
	//									     StoreI.class, RestoreI.class
	//									 }, 
	//									 new StoreRestoreHandler()
	//									 );

	// FIXME: invoke a method on the handler instance to set the file name for checkpointFile and open the file

	//	MyAllTypesFirst myFirst;
	//MyAllTypesSecond  mySecond;

	// Use an if/switch to proceed according to the command line argument
	// For deser, just deserliaze the input file into the data structure and then print the objects
	// The code below is for "serdeser" mode
	// For "serdeser" mode, both the serialize and deserialize functionality should be called.

	// create a data structure to store the objects being serialized
	// NUM_OF_OBJECTS refers to the count for each of MyAllTypesFirst and MyAllTypesSecond
	//		for (int i=0; i<NUM_OF_OBJECTS; i++) {
	//
	//		    // FIXME: create these object instances correctly using an explicit value constructor
	//		    // use the index variable of this loop to change the values of the arguments to these constructors
	//		    myFirst = new MyAllTypesFirst(i, i, i, i, null, false...);
	//		    mySecond = new MyAllTypesSecond(i, i, i, 0, 0, 0..);
	//
	//		    // FIXME: store myFirst and mySecond in the data structure
	//		    ((StoreI) cpointRef).writeObj(myFirst, "XML");
	//		    ((StoreI) cpointRef).writeObj(mySecond, "XML");
	//
	//		}
	//
	//		SerializableObject myRecordRet;
	//
	//		// create a data structure to store the returned ojects
	//		for (int j=0; j<2*NUM_OF_OBJECTS; j++) {
	//
	//		    myRecordRet = ((RestoreI) cpointRef).readObj("XML");
	//		    // FIXME: store myRecordRet in the vector
	//		}
	//
	//		// FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)
	//
	//		// FIXME: compare and confirm that the serialized and deserialzed objects are equal. 
	//		// The comparison should use the equals and hashCode methods. Note that hashCode 
	//		// is used for key-value based data structures
	//	    
	//	    }
	public static void main(String args[]) {
		String mode = "serdeser";
		int NUM_OF_OBJECTS = 3;
		String outputFileName = "output.txt";

		Vector<SerializableObject> oldObjects = new Vector<SerializableObject>();
		Vector<SerializableObject> newObjects = new Vector<SerializableObject>();

		ProxyCreator pc = new ProxyCreator();
		FileProcessor outputFile = new FileProcessor(outputFileName);
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] { StoreI.class, RestoreI.class },
				new StoreRestoreHandler(outputFile));

		StoreRestoreHandler storeRestoreHandler = new StoreRestoreHandler(outputFile);

		if(mode.equals("serdeser")) {
			for(int i=0;i< NUM_OF_OBJECTS;i++) {
				MyAllTypesFirst myFirst;
				MyAllTypesSecond mySecond;
				myFirst = new MyAllTypesFirst(RandomGenerator.randomInt(i), RandomGenerator.randomInt(i), RandomGenerator.randomLong(), RandomGenerator.randomLong(), RandomGenerator.randomString(), RandomGenerator.randomBool());
				mySecond = new MyAllTypesSecond(RandomGenerator.randomDouble(), RandomGenerator.randomDouble(), RandomGenerator.randomFloat(), RandomGenerator.randomShort(), RandomGenerator.randomShort(), RandomGenerator.randomChar());
				oldObjects.add(myFirst);
				oldObjects.add(mySecond);
				
				((StoreI) cpointRef).writeObj(myFirst,1, "XML");
				((StoreI) cpointRef).writeObj(mySecond,1, "XML");



			}

		}

		Iterator<SerializableObject> it = oldObjects.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}

	}







}

