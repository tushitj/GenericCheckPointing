package genericCheckpointing.xmlStoreRestore;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.reflect.Constructor;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class XMLDeSerializationStrategy implements SerStrategy {

	public SerializableObject deSerializeObject(FileProcessor file) {
		SerializableObject record = new SerializableObject();
		String line = file.readLine();
		Class<?> cls;
		String[] myArray;
		String className = null;
		String fieldName;
		String dataType;
		String fieldValue = null;
		ArrayList<String> parameters = new ArrayList<String>();
		while (line!=null && (!line.equalsIgnoreCase("</DPSerialization>"))) {
			line = line.trim();
			if (line.equalsIgnoreCase("<DPSerialization>") || (line.equalsIgnoreCase("</complexType>"))) {
				line = file.readLine();
			}
			if (line.equalsIgnoreCase("</DPSerialization>")) {
				break;
			}
			line = line.trim();
			myArray = line.split(" ");
			if (myArray[0].substring(1).equalsIgnoreCase("complexType")) {
				myArray = myArray[1].split("\"");
				className = myArray[1];
			} else {
				fieldName = myArray[0].substring(1);
				Pattern type = Pattern.compile("xsd:(.*?)\">");
				Matcher matcher = type.matcher(line);
				if (matcher.find()) {
					dataType = (matcher.group(1));
				}
				Pattern value = Pattern.compile(">(.*?)<");
				matcher = value.matcher(line);
				if (matcher.find()) {
					fieldValue = (matcher.group(1));
				}
				parameters.add(fieldValue);
			}
			line = file.readLine();
		}

		Object[] argsC = new Object[parameters.size()];
		try {
			cls = Class.forName(className);
			for (Constructor<?> ctor : cls.getConstructors()) {
				Class<?>[] paramTypes = ctor.getParameterTypes();
				if (parameters.size() == paramTypes.length) {
					for (int i = 0; i < argsC.length; i++) {
						argsC[i] = convert(paramTypes[i], parameters.get(i));
					}
				}
				return (SerializableObject) ctor.newInstance(argsC);
			}
		} catch (Exception e) {
		}

		return record;
	}

	static Object convert(Class<?> target, String s) {
		try {
			if (target == Object.class || target == String.class || s == null) {
				return s;
			}
			if (target == Character.class || target == char.class) {
				return s.charAt(0);
			}
			if (target == Byte.class || target == byte.class) {
				return Byte.parseByte(s);
			}
			if (target == Short.class || target == short.class) {
				return Short.parseShort(s);
			}
			if (target == Integer.class || target == int.class) {
				return Integer.parseInt(s);
			}
			if (target == Long.class || target == long.class) {
				return Long.parseLong(s);
			}
			if (target == Float.class || target == float.class) {
				return Float.parseFloat(s);
			}
			if (target == Double.class || target == double.class) {
				return Double.parseDouble(s);
			}
			if (target == Boolean.class || target == boolean.class) {
				return Boolean.parseBoolean(s);
			}
		} catch (Exception e) {
			System.out.println("Can't Convert " + s + " to " + target);
			System.exit(0);
		}
		return s;
	}

	@Override
	public void processInput(SerializableObject sObject) {
	}

}
