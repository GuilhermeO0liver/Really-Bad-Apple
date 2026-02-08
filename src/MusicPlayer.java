import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicPlayer {
    public static void main() {

        String filepath = "src/東方Bad Apple!! ＰＶ影絵.wav";
        File file = new File(filepath);

        try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)){

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();

            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-17.0f); // Reduce volume by 10 decibels

            int frameCount = 0;
            for (int i = 0; i <= 6571; i++){
                new Frame(frameCount);
                frameCount++;
            }
            Thread.sleep(220000); // the exact time of the music


        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("Something went wrong");
        }
        catch (UnsupportedAudioFileException e) {
            System.out.println("Audio file not supported");
        }
        catch (LineUnavailableException e) {
            System.out.println("Unable to access the audio resource");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
