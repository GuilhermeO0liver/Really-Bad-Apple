import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Frame extends JFrame{

    private static final Random random = new Random();

    private static double currentX = 700;
    private static double currentY = 400;
    private static double velocityX = 3.0;
    private static double velocityY = 2.0;

    private static final double BASE_SPEED = 3.0;
    private static final boolean WRAP_EDGES = false;

    public Frame(int frameCount) {

        this.setSize(480, 360);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("A certain Apple frame count: " + frameCount);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxX = screenSize.width - this.getWidth();
        int maxY = screenSize.height - this.getHeight();

        Point position = calculateTrajectory(frameCount, maxX, maxY);
        int x = position.x;
        int y = position.y;

        x = Math.max(0, Math.min(x, screenSize.width - this.getWidth()));
        y = Math.max(0, Math.min(y, screenSize.height - this.getHeight()));

        this.setLocation(x, y);
        this.setAlwaysOnTop(true);

        ImageIcon Icon = new ImageIcon("src\\video_frames\\"+ frameCount +".jpg");
        JLabel label = new JLabel(Icon);
        this.add(label);
    }

    private Point calculateTrajectory(int frameCount, int maxX, int maxY) {
        drunkWalkPath(frameCount, maxX, maxY);

        return new Point((int)currentX, (int)currentY);
    }

    private void drunkWalkPath(int frameCount, int maxX, int maxY) {
        if (frameCount % 15 == 0 || random.nextDouble() < 0.1) {
            double angle = random.nextDouble() * 2 * Math.PI;
            velocityX = BASE_SPEED * Math.cos(angle);
            velocityY = BASE_SPEED * Math.sin(angle);
        }

        currentX += velocityX;
        currentY += velocityY;

        handleBoundaries(maxX, maxY);
    }

    private void handleBoundaries(int maxX, int maxY) {
        if (WRAP_EDGES) {
            if (currentX < 0) currentX = maxX;
            if (currentX > maxX) currentX = 0;
            if (currentY < 0) currentY = maxY;
            if (currentY > maxY) currentY = 0;
        } else {
            if (currentX < 0 || currentX > maxX) {
                velocityX *= -1;
                currentX = clamp(currentX, 0, maxX);
            }
            if (currentY < 0 || currentY > maxY) {
                velocityY *= -1;
                currentY = clamp(currentY, 0, maxY);
            }
        }
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public static void FrameDisplay(int frameCount) throws InterruptedException {
        Frame frame1 = new Frame(frameCount);
        frame1.setVisible(true);
        try {
            Thread.sleep(19);
        } catch (InterruptedException e) {
            System.out.println("Ocorreu um erro");
        }
        frame1.dispose();
    }
}