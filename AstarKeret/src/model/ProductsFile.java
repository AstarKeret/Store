package model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProductsFile implements Iterable<Byte>{
	private File file;
	private RandomAccessFile raf;
	private long size;
	public ProductsFile(File file) throws IOException {
		this.file = file;
		raf = new RandomAccessFile(file, "rw");	//open file for write and read
		size = raf.length();
	}	
	
	@Override
	public Iterator<Byte> iterator() {
		return new FileIterator();	//returns the file iterator class
	}
	
	public File getFile() {
		return file;
	}

	public void closeFile() throws IOException {
		raf.close();	//close the file
	}
	
	public long getFileLength() {
		return size; 	//returns the file size in bytes
	}
	
	//reads from the file, byte by byte 
	private class FileIterator implements Iterator<Byte>{
		private long cur = 0; 		// the byte to be retrieved now with next
		private long last = -1;		// the byte to be removed

		@Override
		public boolean hasNext() {
			return cur < size;		//return true if there is more byte to read in the file
		}

		@Override
		public Byte next() {
			Byte tmp = null;
			if(!hasNext())				//exception when there is no more byte next to get
				throw new NoSuchElementException();
			 try {
				 raf.seek(cur);				//sets the iterator pointer to the current position
				 last++;					//promotes the last pointer in one
				 tmp =	raf.readByte();		//read one byte from the file
				 cur++;						//promotes the current pointer in one
			} catch (IOException e) {
				e.printStackTrace();
			}
			return tmp;						//return the byte where the iterator is
		}
		@Override
		public void remove() {
			if (last < 0)										//checks if there is more byte in the file
				throw new IllegalStateException();
			try {
				raf.seek(cur);									//sets the iterator pointer to the current position
				byte[] data = new byte[ (int) (size - cur)];	//creates array byte to seve in the nun deleted bytes
				raf.read(data);									//reads all the bytes after the one that need to be deleted 
				raf.setLength(last);							//set new length to the file- until the deleted one (not include)
				raf.write(data);								//writes beck to the file all the saves bytes
				cur = last;										//set new current 
				last--;											
				size--;								
			}catch (Exception e) {
				
			}
		}
		
	}


}
