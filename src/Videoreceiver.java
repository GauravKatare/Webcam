import java.io.IOException;
import java.io.ObjectInputStream;

public class Videoreceiver implements Runnable
{
    public void setOoin(ObjectInputStream ooin) {
        this.ooin = ooin;
    }

    private ObjectInputStream ooin;


    public void run() throws NullPointerException
    {
        while(true)
        {
            Object obj = null;
            try {
                obj = ooin.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (obj instanceof Myvideo) {
                Myvideo myvideo = (Myvideo) obj;
                Bufferplay.addvideopacket(myvideo);
            }
            else
            {
                System.out.println("Error in Video");
            }
        }
    }

}