package genericCheckpointing.util;
//
//<DPSerialization>
//<complexType xsi:type="genericCheckpointing.util.MyAllTypesSecond">

// <myDoubleT xsi:type="xsd:double">3.1459</myDoubleT>

//   <myOtherDoubleT xsi:type="xsd:double">3.145</myOtherDoubleT>

// <myFloatT xsi:type="xsd:float">3145.9</myFloatT>

// <myShortT xsi:type="xsd:short">314</myShortT>

// <myOtherShortT xsi:type="xsd:short">314</myOtherShortT>

// <myCharT xsi:type="xsd:char">P</myCharT>

//</complexType>
//</DPSerialization>
public class MyAllTypesSecond extends SerializableObject{

	private double myDoubleT;
	private double myOtherDoubleT;
	private float myFloatT;
	private short myShortT;
	private short myOtherShortT;
	private char myCharT;
	
	public MyAllTypesSecond(double myDoubleTIn, double myOtherDoubleTIn, float myFloatTIn, short myShortTIn, short myOtherShortTIn, char myCharTIn) {
		this.myDoubleT = myDoubleTIn;
		this.myOtherDoubleT = myOtherDoubleTIn;
		this.myFloatT = myFloatTIn;
		this.myShortT = myShortTIn;
		this.myOtherShortT = myOtherShortTIn;
		this.myCharT = myCharTIn;
	}
	
	public double getMyDoubleT() {
		return myDoubleT;
	}
	public void setMyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}
	public double getMyOtherDoubleT() {
		return myOtherDoubleT;
	}
	public void setMyOtherDoubleT(double myOtherDoubleT) {
		this.myOtherDoubleT = myOtherDoubleT;
	}
	public float getMyFloatT() {
		return myFloatT;
	}
	public void setMyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}
	public short getMyShortT() {
		return myShortT;
	}
	public void setMyShortT(short myShortT) {
		this.myShortT = myShortT;
	}
	public short getMyOtherShortT() {
		return myOtherShortT;
	}
	public void setMyOtherShortT(short myOtherShortT) {
		this.myOtherShortT = myOtherShortT;
	}
	public char getMyCharT() {
		return myCharT;
	}
	public void setMyCharT(char myCharT) {
		this.myCharT = myCharT;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + myCharT;
		long temp;
		temp = Double.doubleToLongBits(myDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(myFloatT);
		temp = Double.doubleToLongBits(myOtherDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + myOtherShortT;
		result = prime * result + myShortT;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyAllTypesSecond other = (MyAllTypesSecond) obj;
		if (myCharT != other.myCharT)
			return false;
		if (Double.doubleToLongBits(myDoubleT) != Double.doubleToLongBits(other.myDoubleT))
			return false;
		if (Float.floatToIntBits(myFloatT) != Float.floatToIntBits(other.myFloatT))
			return false;
		if (Double.doubleToLongBits(myOtherDoubleT) != Double.doubleToLongBits(other.myOtherDoubleT))
			return false;
		if (myOtherShortT != other.myOtherShortT)
			return false;
		if (myShortT != other.myShortT)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "MyAllTypesSecond [myDoubleT=" + myDoubleT + ", myOtherDoubleT=" + myOtherDoubleT + ", myFloatT="
				+ myFloatT + ", myShortT=" + myShortT + ", myOtherShortT=" + myOtherShortT + ", myCharT=" + myCharT
				+ "]";
	}
	
}
