import java.io.IOException;
import java.io.ObjectInputStream;

class Audioreceiver implements Runnable
{
    public void setAudioooin(ObjectInputStream audioooin) {
        this.audioooin = audioooin;
    }

    private ObjectInputStream audioooin;

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
                Myaudio myaudio=(Myaudio)obj;
                Bufferplay.addaudiopacket(myaudio);
            }
            else {
                System.out.println("Error in Audio");
            }

        }
    }
}
