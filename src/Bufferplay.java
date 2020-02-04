import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.sound.sampled.SourceDataLine;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Queue;

class Bufferplay implements Runnable
{

    static Queue<Myaudio> audioQueue;
    static Queue<Myvideo> videoQueue;
    static private SourceDataLine speakers;
    static private ImageView imageView;

    public static void setImageView(ImageView imageView) {
        Bufferplay.imageView = imageView;
    }

    public static void setSpeakers(SourceDataLine speakers) {
        Bufferplay.speakers = speakers;
    }

    public static void addaudiopacket(Myaudio myaudio){
        audioQueue.add(myaudio);
    }

    public static void  addvideopacket(Myvideo myvideo){
        videoQueue.add(myvideo);
    }

    public void playaudio(byte[] data,long timestamp) {
        System.out.println("audio");
        speakers.write(data,0,data.length);
    }

    public void playimage(byte[] data,long timestamp) throws IOException {
        System.out.println("video");
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bufferedImage = null;
        bufferedImage = ImageIO.read(bis);
        Image myimage = SwingFXUtils.toFXImage(bufferedImage, null);
        imageView.setImage(myimage);
    }

    @Override
    public void run() {
        System.out.println("Hello i m Synchonizer");
        while(true) {
            Myaudio myaudio = audioQueue.peek();
            System.out.println(videoQueue.isEmpty());
            Myvideo myvideo = videoQueue.peek();
            if(myaudio!=null) {
                playaudio(myaudio.getAudioData(), myaudio.getTimestamp());
                audioQueue.remove();
            }
            if (myvideo != null) {
                try {
                    playimage(myvideo.getFrameData(), myvideo.getTimestamp());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                videoQueue.remove();
            }

        }
    }
}
