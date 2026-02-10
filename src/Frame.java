import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

    private static final int CENTER_X = 700;
    private static final int CENTER_Y = 400;
    private static final double SPIRAL_GROWTH = 0.1;
    private static final double ANGLE_INCREMENT = 0.15;

    public Frame(int frameCount) {

        this.setSize(480, 360);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("A certain Apple frame count: " + frameCount);

        double angle = frameCount * ANGLE_INCREMENT;
        double radius = frameCount * SPIRAL_GROWTH;

        int x = CENTER_X + (int)(radius * Math.cos(angle));
        int y = CENTER_Y + (int)(radius * Math.sin(angle));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x = Math.max(0, Math.min(x, screenSize.width - this.getWidth()));
        y = Math.max(0, Math.min(y, screenSize.height - this.getHeight()));

        this.setLocation(x, y);
        this.setAlwaysOnTop(true);

        ImageIcon Icon = new ImageIcon("src\\video_frames\\"+ frameCount +".jpg");
        JLabel label = new JLabel(Icon);
        this.add(label);
    }

    public static void FrameDisplay(int frameCount) throws InterruptedException {
        Frame frame1 = new Frame(frameCount);
        frame1.setVisible(true);
        try {
            Thread.sleep(19);
        } catch (InterruptedException e) {
            System.out.println("Ocorrreu um erro");
        }
        frame1.dispose();
    }
}