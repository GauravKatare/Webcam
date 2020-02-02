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
import java.net.Socket;
import java.sql.Timestamp;


public class VideoPlayerThread implements Runnable,WebcamImageTransformer
{
    WebcamPanel webcamPanel;
    Webcam webcam;
    static boolean isRunning=false;
    private File saveFile;
    ObjectOutputStream oos;
    ByteArrayOutputStream baos;

    private static final JHGrayFilter GRAY = new JHGrayFilter();

    public void run()
    {
        saveFile=new File("saved.mp4");
        IMediaWriter writer = ToolFactory.makeWriter(saveFile.getName()); //Initialize media write
        Dimension size = WebcamResolution.VGA.getSize(); //Set video recording size
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, size.width, size.height);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        long start = System.currentTimeMillis();
        int i=0;
        while(isRunning)
        {
            System.out.println("Image is Sending");
            BufferedImage image = ConverterFactory.convertToType(webcamPanel.getImage(), BufferedImage.TYPE_3BYTE_BGR);
            IConverter converter = ConverterFactory.createConverter(image, IPixelFormat.Type.YUV420P);
            IVideoPicture frame = converter.toPicture(image, (System.currentTimeMillis() - start) * 1000);
            try {
                ImageIO.write(webcam.getImage(),"jpg",baos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] data = baos.toByteArray();
            Myvideo myvideo=new Myvideo(data,System.currentTimeMillis()-start);
            try {
                oos.writeObject(myvideo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            frame.setKeyFrame(i == 0);
            frame.setQuality(100);
            writer.encodeVideo(0, frame);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        writer.close();
        System.out.println("Video recorded to the file: " + saveFile.getAbsolutePath());
    }

    public VideoPlayerThread(WebcamPanel  webcamPanel, Webcam webcam)
    {
        this.webcamPanel=webcamPanel;
        this.webcam=webcam;
    }

    @Override
    public BufferedImage transform(BufferedImage image) {
        return GRAY.filter(image, null);
    }
}
