import java.util.concurrent.*;
public class Directory
{
	public int d;
	public ConcurrentHashMap<String,Bucket> directory= new ConcurrentHashMap<String,Bucket>();
}
