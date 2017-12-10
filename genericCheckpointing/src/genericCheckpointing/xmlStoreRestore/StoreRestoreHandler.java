package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler {
	Results result;
	 FileProcessor file;
	public StoreRestoreHandler(Results result, FileProcessor file) {
		this.result = result;
		this.file = file;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		if(method.getName().equalsIgnoreCase("writeobj")) {
			String wireFormat = (String)args[2];
			if(wireFormat.equalsIgnoreCase("XML")) {
				serializeData((SerializableObject)args[0],new XMLSerializationStrategy(result));
			}

		}
		//  call serializeData(args[0], new XMLSerializationStrategy());

		// if statements to check if it is the read method so that
		// deserialization can be done ... 
		if(method.getName().equalsIgnoreCase("readObj")){
			String wireFormat = (String) args[0];
			if(wireFormat.equalsIgnoreCase("XML")){
				return deSerializeData(new XMLDeSerializationStrategy());

			}

		}

		return args;
	} 


	private SerializableObject deSerializeData(XMLDeSerializationStrategy xmlDeSerializationStrategy) {
		return xmlDeSerializationStrategy.deSerializeObject(file);
		
	}


	public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
		sStrategy.processInput(sObject);
	}


}
