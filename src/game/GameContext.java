package game;

public class GameContext {
	GameFrame gameframe;

	private static GameContext gamecontext;
	
	public static GameContext getInstance() {
		if(gamecontext == null)
			gamecontext = new GameContext();
		return gamecontext;
	}

	private GameContext() {
		gameframe = GameFrame.getInstance();
	}
	
	public void test() {
		System.out.println("test");
	}
}
