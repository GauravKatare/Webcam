import java.io.Serializable;

public class Myvideo implements Serializable
{
    public byte[] getFrameData() {
        return frameData;
    }

    public void setFrameData(byte[] frameData) {
        this.frameData = frameData;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private byte[] frameData;
    private long timestamp;

    public Myvideo(byte[] frameData,long timestamp)
    {
        this.frameData=frameData;
        this.timestamp=timestamp;
    }
}
