package genericCheckpointing.driver;

import java.util.Vector;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.RandomGenerator;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class Driver {

	public static void main(String args[]) {
		String mode = "serdeser";
		int NUM_OF_OBJECTS = 3;
		String outputFileName = "output.txt";
		Results result = new Results();
		Vector<SerializableObject> oldObjects = new Vector<SerializableObject>();
		Vector<SerializableObject> newObjects = new Vector<SerializableObject>();
		FileProcessor file = new FileProcessor("input.txt");
		ProxyCreator pc = new ProxyCreator();
		
		
		StoreRestoreHandler storeRestoreHandler = new StoreRestoreHandler(result,file);
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] { StoreI.class, RestoreI.class },
				storeRestoreHandler);
		if(mode.equals("serdeser")) {
			for(int i=1;i< NUM_OF_OBJECTS;i++) {
				MyAllTypesFirst myFirst;
				MyAllTypesSecond mySecond;
				myFirst = new MyAllTypesFirst(i+10, i+3+5, RandomGenerator.randomLong(i), RandomGenerator.randomLong(i*2), RandomGenerator.randomString(), RandomGenerator.randomBool());
				mySecond = new MyAllTypesSecond(RandomGenerator.randomDouble(), RandomGenerator.randomDouble(), RandomGenerator.randomFloat(), RandomGenerator.randomShort(i), RandomGenerator.randomShort(i*3), RandomGenerator.randomChar());
				oldObjects.add(myFirst);
				oldObjects.add(mySecond);				
				((StoreI) cpointRef).writeObj(myFirst,1, "XML");
				((StoreI) cpointRef).writeObj(mySecond,1, "XML");
			}

		}

		
		else if(mode.equals("deser")) {
			
			for(int i=0;i<NUM_OF_OBJECTS;i++) {
			SerializableObject obj = ((RestoreI) cpointRef).readObj("XML");
			newObjects.add(obj);
		}
		}
		
		for(int i=0;i<NUM_OF_OBJECTS;i++) {
			System.out.println("OLD OBJECTS  " +oldObjects.get(i).toString());
			
			System.out.println("NEW OBJECTS  " +newObjects.get(i).toString());
			
			}
		result.writeToFile(outputFileName);
	}
	}

