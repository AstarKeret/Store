package model;

public class ConvertByteArray {

	//converte array of byets to an int veribale (the first 4 indexes)
	public static int toInt(byte[] bytes) {	
		return ((bytes[0] & 0xFF) << 24) |
				((bytes[1] & 0xFF) << 16) |
				((bytes[2] & 0xFF) << 8) |
				((bytes[3] & 0xFF) << 0);
	}
	
	//converte array of byets to a long veribale (the first 4 indexes)
	public static long toLong(byte[] bytes) {
		long l = 0;
		for (int i=0; i<8; i++) {
			l <<= 8;
			l ^= (long) bytes[i] & 0xff;
			}
		return l;
		}

	}
