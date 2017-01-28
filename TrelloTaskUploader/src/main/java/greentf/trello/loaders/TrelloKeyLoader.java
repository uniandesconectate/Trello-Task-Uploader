package greentf.trello.loaders;

public class TrelloKeyLoader 
{
	private String key;
	private String token;
	
	public TrelloKeyLoader(String key, String token)
	{
		this.key=key;
		this.token=token;
	}
	public TrelloKeyLoader()
	{
		this.key="f0232b37cba133351f64c578935cfff";
		this.token = "bd4a9249044a0ea176b8315fdd6edc95bde3b191edf60547945aa8403457184";
	}
	public String getKey() {
		return key;
	}
	public void setKey(String kEY) {
		key = kEY;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String tOKEN) {
		token = tOKEN;
	}
	
}
