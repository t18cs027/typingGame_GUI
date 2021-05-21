import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;
import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.util.LinkedList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {

    private Model model;

    // Sample instance variables:
  //  private Image image;
    private AudioClip sound;

    public View(Model model) {
        this.model = model;
        

        // 画像を読み込む．画像ファイルは src においておくと bin に自動コピーされる
//        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("robot.png"));
        // サウンドを読み込む
        sound = Applet.newAudioClip(getClass().getResource("bomb.wav"));
    }

    /*
      画面を描画する
      @param g  描画用のグラフィックスオブジェクト
     */
    
    public void paintComponent(Graphics g) {
        // 画面をいったんクリア
        clear(g);
        State state = model.getState();
        state.paintComponent(g);
    }
    
    public void clear(Graphics g) { 
        Dimension size = getSize();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size.width, size.height);
    }

    public void playBombSound() {
        sound.stop(); // まず音を停めてから
        sound.play(); // 再生する
    }
    
    public Model getModel() {return model;}

}
