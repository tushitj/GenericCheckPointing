package genericCheckpointing.util;
//
//
//<DPSerialization>
//<complexType xsi:type="genericCheckpointing.util.MyAllTypesFirst">
// <myInt xsi:type="xsd:int">314</myInt>
// <myOtherInt xsi:type="xsd:int">3141</myOtherInt>

// <myLong xsi:type="xsd:long">314159</myLong>

//  <myOtherLong xsi:type="xsd:long">31415</myOtherLong>

// <myString xsi:type="xsd:string">Design Patterns</myString>

// <myBool xsi:type="xsd:boolean">false</myBool>

//</complexType>
//</DPSerialization>
public class MyAllTypesFirst {
	
	private int myInt;
	private int myOtherInt;
	private long myLong;
	private long myOtherLong;
	private String myString;
	private boolean myBool;
	
	public MyAllTypesFirst(int myIntIn, int myOtherIntIn, long myLongIn, long myOtherLongIn, String myStringIn, boolean myBoolIn) {
		this.myInt = myIntIn;
		this.myOtherInt = myOtherIntIn;
		this.myLong = myLongIn;
		this.myOtherLong = myLongIn;
		this.myString = myStringIn;
		this.myBool = myBoolIn;
		
	}
	
	public int getMyInt() {
		return myInt;
	}
	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}
	public int getMyOtherInt() {
		return myOtherInt;
	}
	public void setMyOtherInt(int myOtherInt) {
		this.myOtherInt = myOtherInt;
	}
	public long getMyLong() {
		return myLong;
	}
	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}
	public long getMyOtherLong() {
		return myOtherLong;
	}
	public void setMyOtherLong(long myOtherLong) {
		this.myOtherLong = myOtherLong;
	}
	public String getMyString() {
		return myString;
	}
	public void setMyString(String myString) {
		this.myString = myString;
	}
	public boolean isMyBool() {
		return myBool;
	}
	public void setMyBool(boolean myBool) {
		this.myBool = myBool;
	}
	

}
