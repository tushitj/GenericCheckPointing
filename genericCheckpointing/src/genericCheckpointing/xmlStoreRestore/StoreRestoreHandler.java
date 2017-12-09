package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler {

	public StoreRestoreHandler(FileProcessor outputFile) {
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	           //  call serializeData(args[0], new XMLSerializationStrategy());
	   
	    // if statements to check if it is the read method so that
	    // deserialization can be done ... 
		
		
		
		
		
		if(method.getName().equalsIgnoreCase("writeobj")) {
			String wireFormat = (String)args[1];
			 if(wireFormat.equalsIgnoreCase("XML")) {
				 serializeData((SerializableObject)args[0],new XMLSerializationStrategy());
			 }
			
		}
		if(method.getName().equalsIgnoreCase("read")){
			
		}
		
		return args;
	} 
	
	
	   public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
           sStrategy.processInput(sObject);
 }

}
