import sun.audio.AudioData;

import java.io.Serializable;

public class Myaudio implements Serializable
{
    private long timestamp;
    private byte[] AudioData;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getAudioData() {
        return AudioData;
    }

    public void setAudioData(byte[] audioData) {
        AudioData = audioData;
    }

    public Myaudio(byte[] AudioData, long timestamp)
    {
        this.AudioData= AudioData;
        this.timestamp=timestamp;
    }
}
