package greentf.trello.uploaders;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;

import greentf.trello.loaders.CSVCardsLoader;
import greentf.trello.loaders.TrelloKeyLoader;

public class TrelloTaskUploader 
{
	private LinkedList<HashMap<String, String>> newCards ;
	private Trello trello;
	private HashMap<String, Board> boards= new HashMap<String, Board>();
	
	
	public TrelloTaskUploader(LinkedList<HashMap<String, String>> cards, Trello t)
	{
		this.newCards=cards;
		this.trello=t;
		this.loadBoards();
	}
	public void execute()
	{
		for (HashMap<String, String> card : newCards) 
		{

			String boardname=card.get("board");
			String cardname=card.get("card");
			String listname = card.get("list");
			String comments= card.get("comments");

			Board targetboard= this.findBoard(boardname);
			if(targetboard!=null)
			{
				List<TList> lists=trello.getBoardLists(targetboard.getId());
				
				for (TList list : lists) 
				{
					if(listname.equals(list.getName()))
					{
						Card newcard= new Card();
						newcard.setName(cardname);
						newcard=trello.createCard(list.getId(), newcard);
						//comments
						String[] parts=comments.split("_");
						for (String comment : parts) 
						{
							if(!comment.equals(""))
								trello.addCommentToCard(newcard.getId(), comment);
						}
						
					}
				}
				
			}
		}
	}
	private void loadBoards()
	{
		Member men=this.trello.getMemberInformation("me");
		List<String> boards=men.getIdBoards();
		for (String board : boards) 
		{
			Board b =this.trello.getBoard(board);
			this.boards.put(board, b);
		}
	}
	private Board findBoard(String name)
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
