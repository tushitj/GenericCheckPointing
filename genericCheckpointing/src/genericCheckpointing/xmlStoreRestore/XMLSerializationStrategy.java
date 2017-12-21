package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;
import java.util.HashMap;

import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;

public class XMLSerializationStrategy implements SerStrategy {
	
	Results result;
	public XMLSerializationStrategy(Results result) {
		this.result = result;
		
	}
	
	public HashMap<String, String> mapTypes(HashMap<String, String> map)
	{
		
		  map.put("class java.lang.String", "string");
		  map.put("int", "int");
		  map.put("double", "double");
		  map.put("char", "char");
		  map.put("short", "short");
		  map.put("float", "float");
		  map.put("long", "long");
		  map.put("boolean", "boolean");
		  
		  return map;
	}

	HashMap<String, String> map = new HashMap <String, String>();
	
	Class<?> cls;
	StringBuilder sb = new StringBuilder();
	@Override
	public void processInput(SerializableObject sObject) {
		  map = mapTypes(map);
		  try{
			  result.storeNewResult("<DPSerialization>\n");
			  result.storeNewResult("  <complexType xsi:type=\""+sObject.getClass().getName()+"\">\n");

			for (Field field : sObject.getClass().getDeclaredFields())
			{
				field.setAccessible(true);
				String type = field.getType().toString();
				type= map.get(type);
				Object value = field.get(sObject);
				//System.out.println(value.toString());
				if(type.equals("int") && Integer.parseInt(value.toString()) < 10) {
					continue;
				}	
				else if(type.equals("long") && Long.parseLong((value.toString()))<10) {
					continue;
				}
				else if(type.equals("float") && Float.parseFloat((value.toString()))<10) {
					continue;
				}
				else if(type.equals("short") && Short.parseShort((value.toString()))<10) {
					continue;
				}
				else if(type.equals("double") && Double.parseDouble((value.toString()))<10) {
					continue;
				}
				result.storeNewResult("    <"+field.getName()+" xsi:type=\"xsd:"+type+"\">"+value.toString()+"</"+field.getName()+">\n");
			}
			result.storeNewResult("  </complexType>\n");
			result.storeNewResult("</DPSerialization>\n\n");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
