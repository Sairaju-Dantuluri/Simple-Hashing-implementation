import java.util.*;
import java.util.concurrent.*;
import java.lang.Math;
class Hashing
{
	public int bfr=5;
	public Directory dir=new Directory();
	public void initialize()
	{
		dir.d = 1; // GD of directory. 
		Bucket temp=new Bucket(0);
		dir.directory.put("0",temp);
		dir.directory.put("1",temp);
	}
	public String hashFunction(String data)
	{
		String binary=convert.strToBinary(data);
		int length=binary.length();
		length=length-dir.d;
		return binary.substring(length);
	}	
	public void insert(String data)	
	{
		String keystring=hashFunction(data);
		Bucket temp1=dir.directory.get(keystring);
		if(temp1.bucket.size() < bfr)
		{
			dir.directory.get(keystring).bucket.add(data);
			//System.out.println("Normal insertion "+data ); //prints what is happening
			//System.out.println("key : "+keystring);  // prints key of the entering data
		}
		else if((temp1.bucket.size() == bfr) && (temp1.b < dir.d))
		{
			//System.out.println("Bucket insertion " +data); // prints what is happening
			//System.out.println("key : "+keystring);  // prints key of entering data.
			Bucket temp = new Bucket(temp1.b+1);
			temp1.b++;
			Iterator<String> i = temp1.bucket.iterator();
			while(i.hasNext())
			{
				String str=(String)i.next();
				String ptr=hashFunction(str);
				//System.out.println(ptr);
				if(ptr.equals(keystring)==true)
					temp.bucket.add(str);
			}
			Iterator<String> r= temp.bucket.iterator();
			while(r.hasNext())
			{
				String str=r.next();
				temp1.bucket.remove(str);
			}
			dir.directory.put(keystring,temp);
			temp.bucket.add(data);
		}
		
		else if((temp1.bucket.size() == bfr) && (temp1.b == dir.d))
		{
			//System.out.println("directory expansion "+data); // prints whats happening 
			Enumeration enu = dir.directory.keys();
			HashSet<String> hs=new HashSet<String>();
			while(enu.hasMoreElements())
			{
				String str = (String)enu.nextElement();
				hs.add(str);
			}
			Iterator<String> x = hs.iterator();
			while(x.hasNext())
			{
				String str = x.next();
				Bucket temp = dir.directory.get(str);
				dir.directory.remove(str,temp);
				dir.directory.put("1"+str,temp);
				dir.directory.put("0"+str,temp);
			}
			dir.d++;
			keystring=hashFunction(data);
			//System.out.println("key : "+keystring); // this prints key.
			temp1=dir.directory.get(keystring);
			Bucket temp = new Bucket(temp1.b+1);
			temp1.b++;
			Iterator<String> i = temp1.bucket.iterator();
			while(i.hasNext())
			{
				String str=(String)i.next();
				String ptr=hashFunction(str);
				//System.out.println(ptr);	// this prints key of each element of the bucket having bucket expansion.
				if(ptr.equals(keystring)==true)
					temp.bucket.add(str);
			}
			Iterator<String> r= temp.bucket.iterator();
			while(r.hasNext())
			{
				String str=r.next();
				temp1.bucket.remove(str);
			}
			dir.directory.put(keystring,temp);
			temp.bucket.add(data);
		}
	}	
	public boolean search(String data)
	{
		String keystring=hashFunction(data);
		return dir.directory.get(keystring).bucket.contains(data);
	}
	public void printer()
	{
		int s=(int)Math.pow(2,dir.d);
		String arr[] = new String[s];
		for(int i=0;i<s;i++)
		{
			if(s>1)
				arr[i]=String.format("%"+dir.d+"s",Integer.toBinaryString(i)).replaceAll(" ","0");
		}
		int visited[] = new int[s];
		String heads = new String();
		for(int i=0;i<s;i++)
		{
			
			if(visited[i]==0)
			{
				System.out.println(arr[i]);
				//System.out.println("if entered");
				if(heads.length()==0)
					heads=arr[i];
				else
					heads=heads+arr[i];
				int count=1;
				for(int j=i+1;j<s;j++)
				{
					if(dir.directory.get(arr[i]).bucket.equals(dir.directory.get(arr[j]).bucket) == true )
					{
						count++;
						visited[j]=1;
						System.out.println(arr[j]);
					}
				}
				visited[i]=count;
				System.out.println(dir.directory.get(arr[i]).bucket);
			}
		}
	}				
}
