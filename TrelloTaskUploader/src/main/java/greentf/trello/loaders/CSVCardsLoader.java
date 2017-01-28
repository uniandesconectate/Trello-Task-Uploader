package greentf.trello.loaders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import au.com.bytecode.opencsv.CSVReader;


public class CSVCardsLoader 
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
	            HashMap<String, String> card = new HashMap<String, String>();
	            card.put("team", nextLine[0]);
	            card.put("board", nextLine[1]);
	            card.put("card", nextLine[2]);
	            card.put("comments", nextLine[3]);
	            card.put("list", nextLine[4]);
	            elements.add(card);
	        	 //Verifying the read data here
	            System.out.println(Arrays.toString(nextLine));
	         }
	       }
	      return elements;
	}
	public static void main(String[] args) {
		try {
			CSVCardsLoader.load("./input/tarjetas.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
