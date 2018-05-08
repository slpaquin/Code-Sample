package pangram;

import java.io.IOException;

public class Tester
{
	public static void main(String[] args) throws IOException
	{
		// Changed contents of input.txt for each sample to see if output was correct
		String input = new String("input.txt");
		String output = new String("output.txt");
		pangram.pangram(input, output);
		System.out.println("- - - Checking Complete - Results in output.txt - - -");
	}
}
