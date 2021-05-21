import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void プレーヤーの遷移テスト() {
Player p=new Player(20,20,10,10);
String s="s";
assertEquals(20,p.getX());
assertEquals(20,p.getY());
assertEquals(50,p.getR());


p.update(s);
assertEquals(20,p.getX());
assertEquals(30,p.getY());
assertEquals(50,p.getR());


p.update("d");
assertEquals(30,p.getX());
assertEquals(30,p.getY());
assertEquals(50,p.getR());


p.update("a");
assertEquals(20,p.getX());
assertEquals(30,p.getY());
assertEquals(50,p.getR());


p.update("w");
assertEquals(20,p.getX());
assertEquals(20,p.getY());
assertEquals(50,p.getR());

	}

}
