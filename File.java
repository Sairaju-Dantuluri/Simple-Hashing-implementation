import java.util.Scanner;
import java.util.*;
import java.io.*;
class File
{
	static void dirToFile(Directory dir) throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter("database.txt");
		writer.println(dir.d);
		
		int s=(int)Math.pow(2,dir.d);
		String arr[] = new String[s];
		for(int i=0;i<s;i++)
		{
			if(s>1)
				arr[i]=String.format("%"+dir.d+"s",Integer.toBinaryString(i)).replaceAll(" ","0");
		}
		// arr[i] has the binarystring of i;
		int visited[] = new int[s];
		for(int i=0;i<s;i++)
		{
			if(visited[i]==0)
			{
				writer.println(dir.directory.get(arr[i]).b);
				String data="";
				Iterator<String> itr = dir.directory.get(arr[i]).bucket.iterator();
				while(itr.hasNext())
					data = itr.next()+" "+data;
				writer.println(data);
				visited[i]=1;
				String hleads = arr[i];
				for(int j=i+1;j<s;j++)
				{
					if(dir.directory.get(arr[i]).bucket.equals(dir.directory.get(arr[j]).bucket) == true )
					{
						visited[j]=1;
						hleads=hleads+" "+arr[j];
					}
				}
				writer.println(hleads);
			}
		}
		writer.flush();
		writer.close();
	}

	
	static Directory fileToDir() throws FileNotFoundException
	{
		Scanner sc = new Scanner( new java.io.File("database.txt"));
		Directory dir = new Directory();
		if(sc.hasNextLine())
		{
			dir.d = Integer.parseInt(sc.nextLine());
			while(sc.hasNextLine())
			{
				Bucket temp = new Bucket(Integer.parseInt(sc.nextLine()));
				String data = sc.nextLine();
				String[] arr = data.split("\\s");
				for(int i=0;i<arr.length;i++)
					temp.bucket.add(arr[i]);
				String h = sc.nextLine();
				String heads[] = h.split("\\s");
				for(int i=0;i<heads.length;i++)
					dir.directory.put(heads[i],temp);
			}	
		}
		sc.close();
		return dir;
	}
}
