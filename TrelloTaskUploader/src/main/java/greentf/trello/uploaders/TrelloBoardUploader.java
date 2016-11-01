package greentf.trello.uploaders;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.impl.TrelloImpl;

import greentf.trello.loaders.CSVBoardsLoader;
import greentf.trello.loaders.TrelloKeyLoader;

/**
 * @author danielsantamariarod
 * Esta clase aun no funciona, está en espera de modificaciones del api de trello
 */
public class TrelloBoardUploader 
{
	private LinkedList<HashMap<String, String>> newBoards ;
	private Trello trello;
	private HashMap<String, Board> boards= new HashMap<String, Board>();
	
	public static void main(String[] args) 
	{
		LinkedList<HashMap<String, String>> boardsToAdd;
		try 
		{
			boardsToAdd = CSVBoardsLoader.load();
			Trello trelloApi = new TrelloImpl(TrelloKeyLoader.KEY, TrelloKeyLoader.TOKEN);
			TrelloBoardUploader boardUploader =new TrelloBoardUploader(boardsToAdd, trelloApi);
			boardUploader.execute();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public TrelloBoardUploader(LinkedList<HashMap<String, String>> boards, Trello t)
	{
		this.newBoards=boards;
		this.trello=t;
		this.loadTeams();
	}
	private void execute()
	{
		for (HashMap<String, String> board : newBoards) 
		{
			String teamname=board.get("team");
			String boardname=board.get("board");
			String listname=board.get("lists");
			
			

//			Team targetboard= this.findTeam(boardname);
//			if(targetboard!=null)
//			{
//				List<TList> lists=trello.getBoardLists(targetboard.getId());
//				
//				for (TList list : lists) 
//				{
//					if(listname.equals(list.getName()))
//					{
//						Card newcard= new Card();
//						newcard.setName(cardname);
//						newcard=trello.createCard(list.getId(), newcard);
//						//comments
//						String[] parts=comments.split("_");
//						for (String comment : parts) 
//						{
//							trello.addCommentToCard(newcard.getId(), comment);
//						}
//						
//					}
//				}
//				
//			}
		}
	}
	private void loadTeams()
	{
		Member men=this.trello.getMemberInformation("me");
		List<String> teams=men.getIdOrganizations();
		for (String team : teams) 
		{
			//this.trello.
			
//			this.boards.put(board, b);
			System.out.println(team);
		}
	}
	private Board findTeam(String name)
	{
		Set<String> keys=this.boards.keySet();
		Iterator<String> i= keys.iterator();
		while(i.hasNext())
		{
			Board b=this.boards.get(i.next());
			if(name.equals(b.getName()))
			{
				return b;
			}
		}
		return null;
	}

}
