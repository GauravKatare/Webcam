import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.util.jh.JHGrayFilter;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Videosender implements Runnable,WebcamImageTransformer
{
    private  WebcamPanel webcamPanel;
    private  Webcam webcam;
    private static boolean isRunning=false;
    private  ObjectOutputStream oos;
    private File saveFile;

    public Videosender(WebcamPanel webcamPanel, Webcam webcam) {
        this.webcam=webcam;
        this.webcamPanel=webcamPanel;
    }

    public static void setIsRunning(boolean isRunning) {
        Videosender.isRunning = isRunning;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    private static final JHGrayFilter GRAY = new JHGrayFilter();

    public void run(){
        saveFile=new File("saved.mp4");
        //Initialize media writer
        IMediaWriter writer = ToolFactory.makeWriter(saveFile.getName());
        //Set video recording size
        Dimension size = WebcamResolution.VGA.getSize();
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, size.width, size.height);
        long start = System.currentTimeMillis();
        int i=0;
        while(isRunning)
        {
            System.out.println(System.currentTimeMillis() - start);
            BufferedImage image = ConverterFactory.convertToType(webcamPanel.getImage(), BufferedImage.TYPE_3BYTE_BGR);
            IConverter converter = ConverterFactory.createConverter(image, IPixelFormat.Type.YUV420P);
            IVideoPicture frame = converter.toPicture(image, (System.currentTimeMillis() - start) * 1000);
            frame.setKeyFrame(i == 0);
            frame.setQuality(100);
            writer.encodeVideo(0, frame);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();

            try {
                ImageIO.write(webcam.getImage(),"jpg",baos);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] data = baos.toByteArray();
            Myvideo myvideo=new Myvideo(data,System.currentTimeMillis()-start);

            try {
                oos.writeObject(myvideo);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
        writer.close();
        System.out.println("Video recorded to the file: " + saveFile.getAbsolutePath());
    }

    @Override
    public BufferedImage transform(BufferedImage image) {
        return GRAY.filter(image, null);
    }
}
