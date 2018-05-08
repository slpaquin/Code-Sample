package pangram;

import java.util.*;
import java.io.*;

public class pangram
{
	public static void pangram(String inFile, String outFile) throws IOException
	{
		FileReader fRead = new FileReader(inFile);
		BufferedReader bRead = new BufferedReader(fRead);
		
		PrintWriter pWrite = new PrintWriter(outFile);
		
		String str = bRead.readLine();
		
		while ((str) != null)
		{	
			int count = 0;
			char array[] = new char[26];
			
			for (int i = 0; i < str.length(); i++)
			{
				if(str.charAt(i) >= 97 && str.charAt(i) <= 122)
				{
					char character = str.charAt(i);
					int ascii = (int) character;
					if (array[character - 97] == 0)
					{
						array[character - 97] = 1;
						count++;
					}	
				}
			}
			if (count == 26)
			{
				String isPangram = new String("true\n");
				pWrite.write(isPangram);
			}
			else
			{
				String notPangram = new String("false\n");
				pWrite.write(notPangram);
			}
			str = bRead.readLine();
		}
		pWrite.close();
	}
}
