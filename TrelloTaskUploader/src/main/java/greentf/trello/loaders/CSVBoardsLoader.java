package greentf.trello.loaders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import au.com.bytecode.opencsv.CSVReader;


public class CSVBoardsLoader 
{
	public static LinkedList<HashMap<String, String>> elements = new LinkedList<HashMap<String, String>>();
	public static LinkedList<HashMap<String, String>> load(String path) throws Exception
	{
		CSVReader reader = new CSVReader(new FileReader(path), ',' , '"' , 1);
		elements = new LinkedList<HashMap<String, String>>();
		//Read CSV line by line and use the string array as you want
	      String[] nextLine;
	      while ((nextLine = reader.readNext()) != null) 
	      {
	         if (nextLine != null) 
	         {
	            HashMap<String, String> board = new HashMap<String, String>();
	            board.put("team", nextLine[0]);
	            board.put("board", nextLine[1]);
	            board.put("lists", nextLine[2]);
	            
	            elements.add(board);
	        	 //Verifying the read data here
	            System.out.println(Arrays.toString(nextLine));
	         }
	       }
	      return elements;
	}
	public static void main(String[] args) {
		try {
			CSVBoardsLoader.load("./input/tableros.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
