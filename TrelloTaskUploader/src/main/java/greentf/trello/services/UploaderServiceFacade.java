package greentf.trello.services;

import java.util.HashMap;
import java.util.LinkedList;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.impl.TrelloImpl;

import greentf.trello.loaders.CSVBoardsLoader;
import greentf.trello.loaders.CSVCardsLoader;
import greentf.trello.loaders.TrelloKeyLoader;
import greentf.trello.uploaders.TrelloBoardUploader;
import greentf.trello.uploaders.TrelloTaskUploader;

public class UploaderServiceFacade 
{
	public boolean uploadTasks(TrelloKeyLoader keyLoader, String filepath )
	{
		LinkedList<HashMap<String, String>> cardsToAdd;
		try 
		{
			cardsToAdd = CSVCardsLoader.load(filepath);
			Trello trelloApi = new TrelloImpl(keyLoader.getKey(), keyLoader.getToken());
			TrelloTaskUploader taskUploader =new TrelloTaskUploader(cardsToAdd, trelloApi);
			taskUploader.execute();
			System.out.println("Finished");
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	public boolean uploadBoards(TrelloKeyLoader keyLoader, String filepath)
	{	
		LinkedList<HashMap<String, String>> boardsToAdd;
		try 
		{
			boardsToAdd = CSVBoardsLoader.load(filepath);
			Trello trelloApi = new TrelloImpl(keyLoader.getKey(), keyLoader.getToken());
			TrelloBoardUploader boardUploader =new TrelloBoardUploader(boardsToAdd, trelloApi);
			boardUploader.execute();
			System.out.println("Finished");
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean testAuth(TrelloKeyLoader keyLoader) 
	{
		try 
		{
			Trello trelloApi = new TrelloImpl(keyLoader.getKey(), keyLoader.getToken());
			Member men=trelloApi.getMemberInformation("me");
			System.out.println("Everything OK "+men.getUsername());
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
	public LinkedList<HashMap<String, String>> getTaskList(String absolutePath) 
	{
		LinkedList<HashMap<String, String>> cardsToAdd = new LinkedList<HashMap<String,String>>();
		try 
		{
			cardsToAdd = CSVCardsLoader.load(absolutePath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cardsToAdd;
	}
}
