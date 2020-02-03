import sun.audio.AudioData;

import java.io.Serializable;

public class Myaudio implements Serializable
{
    private long timestamp;
    private byte[] AudioData;

    public Myaudio(byte[] AudioData, long timestamp)
    {
        this.AudioData= AudioData;
        this.timestamp=timestamp;
    }
}
