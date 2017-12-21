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

	public static boolean checkArgs(String... args) {
		boolean flag = true;
		for (String str : args)
			flag = flag && str != null && str.trim().length() > 0 && !str.contains("${arg");
			return flag;
	}

	public static void main(String args[]) {
		Results resultOut = new Results();
		int NUM_OF_OBJECTS=0;
		if(args.length !=3 ) {
			System.err.println("Incorrect Arguments. Check the arguments format");
			System.exit(1);
		}
		String mode = args[0];
		try {
			NUM_OF_OBJECTS = Integer.parseInt(args[1]);
		}catch(Exception e) {
			System.out.println("Number of Objects should be an integer.");
			System.exit(1);
		}
		String fileName = args[2];
		Results result = new Results();
		Vector<SerializableObject> oldObjects = new Vector<SerializableObject>();
		Vector<SerializableObject> newObjects = new Vector<SerializableObject>();
		FileProcessor file = new FileProcessor(fileName);
		ProxyCreator pc = new ProxyCreator();
		StoreRestoreHandler storeRestoreHandler = new StoreRestoreHandler(result,file);
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] { StoreI.class, RestoreI.class },storeRestoreHandler);
		//Ser DeSer
		if(mode.equals("serdeser")) {
			System.out.println("SERIALIZING");
			for(int i=0;i< NUM_OF_OBJECTS;i++){
				MyAllTypesFirst myFirst;
				MyAllTypesSecond mySecond;
				myFirst = new MyAllTypesFirst(i+10, i*3+5, RandomGenerator.randomLong(i)%(i+3), RandomGenerator.randomLong(i*2)%(i+1), RandomGenerator.randomString(), RandomGenerator.randomBool());
				mySecond = new MyAllTypesSecond((RandomGenerator.randomDouble() + 100)%5 , (RandomGenerator.randomDouble()+55)%9, (RandomGenerator.randomFloat()+99)%34, RandomGenerator.randomShort(i), RandomGenerator.randomShort(i*3), RandomGenerator.randomChar());
				oldObjects.add(myFirst);
				oldObjects.add(mySecond);				
				((StoreI) cpointRef).writeObj(myFirst,1, "XML");
				((StoreI) cpointRef).writeObj(mySecond,1, "XML");
			}
			result.writeToStdout(null);
			result.writeToFile("output.txt");
			System.out.println("DESRIALIZING AFTER SERIALIZING");
			for(int i=0;i<2*NUM_OF_OBJECTS;i++) {

				SerializableObject obj = ((RestoreI) cpointRef).readObj("XML");
				System.out.println(obj);
				newObjects.add(obj);
			}
			for(int i=0;i<2*NUM_OF_OBJECTS;i++){
				System.out.println(newObjects.get(i).toString());
			}
			int mismatch=0;
			for( int i=0;i< oldObjects.size();i++){
				if(!(oldObjects.get(i).equals(newObjects.get(i))))
					mismatch++;
			}
			if(mismatch==0)
				System.out.print("\n\n0 mismatched objects");
			else
				System.out.print("\n\nTotal mismatched objects:"+ mismatch);
		}
		//deser
		else if(mode.equals("deser")){ 
			System.out.println("DESERIALIZED MODE");
			for(int i=0;i<NUM_OF_OBJECTS;i++) {
				SerializableObject obj =((RestoreI) cpointRef).readObj("XML");
				newObjects.add(obj);
			}
			for(int i=0;i< newObjects.size();i++){
				resultOut.storeNewResult(newObjects.get(i).toString());
				resultOut.storeNewResult("\n");
				
			}
			resultOut.writeToStdout(null);
		}
	}
}

