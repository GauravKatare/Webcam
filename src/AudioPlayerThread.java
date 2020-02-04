import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AudioPlayerThread implements Runnable
{
    private ObjectOutputStream audiooos;
    private static boolean isRunning;

    public static void setIsRunning(boolean isRunning) {
        AudioPlayerThread.isRunning = isRunning;
    }

    public void setOos(ObjectOutputStream audiooos) {
        this.audiooos = audiooos;
    }

    @Override
    public void run()
    {
        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
        TargetDataLine microphone;
        SourceDataLine speakers;
        try {
            microphone = AudioSystem.getTargetDataLine(format);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);

            int numBytesRead;
            int CHUNK_SIZE = 10000;
            byte[] data = new byte[microphone.getBufferSize() / 5];
            microphone.start();
            long start = System.currentTimeMillis();
            int bytesRead = 0;
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
            speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            speakers.open(format);
            speakers.start();
            long i=0;// adjust condition of loop for extent of microphone use
            while(isRunning)
            {
                i++;
                System.out.println(System.currentTimeMillis()-start);
                numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
                bytesRead += numBytesRead;
                // write the mic data to a stream for use later
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                out.write(data, 0, numBytesRead);
                byte[] Audiodata = out.toByteArray();
                Myaudio myaudio=new Myaudio(Audiodata,System.currentTimeMillis()-start);
                try {
                    audiooos.writeObject(myaudio);
                    audiooos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // write mic data to stream for immediate playback
                //speakers.write(data, 0, numBytesRead);
            }
            speakers.drain();
            speakers.close();
            microphone.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
