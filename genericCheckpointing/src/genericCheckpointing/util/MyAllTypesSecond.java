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
public class MyAllTypesSecond {

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
	
}
