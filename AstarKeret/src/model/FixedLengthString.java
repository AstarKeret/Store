package model;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FixedLengthString {

	//read fixed number of characters from a DataInput stream
	public static String readFixedLengthString(int size, DataInput in) throws IOException {
		char[] chars = new char[size];	//declare an array of characters
		//read fixed number of characters to the arry
		for(int i = 0 ; i < size ; i ++)
			chars[i] = in.readChar();
		return new String(chars);
	}
	//write fixed number of characters to a DataOutput stream
	public static void writeFixedLengthString(String str, int size, DataOutput out) throws IOException {
		int length;
		if(str.isEmpty())
			length = 0;
		else
			length = str.length();
		char[] chars = new char[size];
		//fill in string with characters
		str.getChars(0, Math.min(length, size), chars ,0);
		//fill in blank characters in the rest of the array
		for(int i = length ; i < size ; i++)
			chars[i] = ' ';
	    // Create and write a new string padded with blank characters
		out.writeBytes(new String(chars));
	}
}
