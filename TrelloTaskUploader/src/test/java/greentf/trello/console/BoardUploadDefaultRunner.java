package greentf.trello.console;

import greentf.trello.loaders.TrelloKeyLoader;
import greentf.trello.services.UploaderServiceFacade;

public class BoardUploadDefaultRunner {

	public static void main(String[] args) 
	{
		UploaderServiceFacade fac= new UploaderServiceFacade();
		fac.uploadBoards(new TrelloKeyLoader(), "./input/tableros.csv");
	}

}
