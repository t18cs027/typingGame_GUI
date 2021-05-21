import static org.junit.Assert.*;
import java.awt.event.KeyEvent;
    
import org.junit.Test;

public class ModelTest {

    @Test
    public void モデルの管理する時刻データが時間経過とともに増えるかテスト() {
        Model model = new Model();
        int time = model.getTime();
        model.processTimeElapsed(100);
        assertEquals(time + 1, model.getTime());

        // その他のイベントでは増えない
        model.processKeyTyped("a");
        assertEquals(time + 1, model.getTime());
        assertNotEquals(time + 2, model.getTime());
    }

    @Test
    public void モデルがタイプされた文字を保持しているかテスト() {
        Model model = new Model();
        View view = model.getView();
        Controller controller = model.getController();
        // View オブジェクトが存在すること
        assertNotEquals(view, null);
        // Controler オブジェクトが存在すること
        assertNotEquals(controller, null);
        
        controller.keyTyped(new KeyEvent(view, 1, 1, 0, KeyEvent.VK_A, 'a'));
        assertEquals("a", model.getTypedChar());
       
    }
    
    @Test
    public void 正しく文字が入力された場合のみ文字を保持するかテスト() {
    	Model model = new Model();
    	model.processTimeElapsed(100);
    	model.processKeyTyped("d");
    	assertEquals("d", model.getTypedChar());
    	model.processKeyTyped("b");
    	assertEquals("d", model.getTypedChar());
    	model.processKeyTyped("n");
    	assertEquals("db", model.getTypedChar());    	
    }

}
