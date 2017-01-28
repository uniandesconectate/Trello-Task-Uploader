package greentf.trello.console;

import greentf.trello.loaders.TrelloKeyLoader;
import greentf.trello.services.UploaderServiceFacade;

public class TaskUploadDefaultRunner {

	public static void main(String[] args) 
	{
		UploaderServiceFacade fac= new UploaderServiceFacade();
		fac.uploadTasks(new TrelloKeyLoader(), "./input/tarjetas.csv");
	}

}
