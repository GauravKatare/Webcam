import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

class Audioreceiver implements Runnable
{
    ObjectInputStream audioooin;

    @Override
    public void run() throws NullPointerException
    {
        while(true)
        {
            Object obj = null;
            try {
                obj = audioooin.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (obj instanceof Myaudio)
            {
                System.out.println("Audio is working");
                Myaudio myaudio=(Myaudio)obj;
                byte[] data=myaudio.getAudioData();
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                System.out.println(myaudio.getTimestamp());


                AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                SourceDataLine speakers = null;
                try {
                    speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                    speakers.open(format);
                    speakers.start();
                }
                catch (LineUnavailableException e) {
                    e.printStackTrace();
                }

                speakers.write(data,0,data.length);
            }
            else {
                System.out.println("Error in Audio");
            }
        }
    }
}
