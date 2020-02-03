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

            }
            else {
                System.out.println("Error in Audio");
            }
        }
    }
}
