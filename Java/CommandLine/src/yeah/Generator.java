package yeah;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.omg.CORBA.portable.InputStream;

public class Generator
{
	public static void main(String[] args) throws IOException
	{
		List<String> list = Arrays.asList(args);
		String library = "lorem";
		String filename = "";
		String output = "console";
		String mode = "paragraph";
		String count = "1";
		Boolean html = false;
		File file = null;
		boolean generatePresent = false;

		if (list.contains("--generate") || list.contains("-g"))
		{
			for (int i = 0; i < args.length; i++)
			{
				switch(args[i])
				{
				case "--generate":
				case "-g":
					if (generatePresent == false)
					{
						generatePresent = true;
					}
					else if (generatePresent == true)
					{
						System.out.println("\n\tMultiple --generate/-g  arguments present in command.");
						System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
						System.exit(0);
					}
					break;
				
				case "--help":
				case "-h":
					if (i + 1 < args.length)
					{
						System.out.println("\n\tHelp is only valid as a standalone option."
								+ "\n\tTo view help, only --help or -h should be present in command.\n");
						System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
						System.exit(0);
					}
					else
					{
						System.out.println("\n- - - Command Line Help Screen - - -");
						System.out.println("Command line mode runs one time and produces text as speciied by the user.");
						System.out.println("To get to Interactive Mode, run command without --generate/-g.\n");
						helpMessage(1);
						System.exit(0);
					}
					i = args.length;
					break;

				case "--version":
				case "-v":
					if (i + 1 < args.length)
					{
						System.out.println("\n\tVersion is only valid as a standalone option."
								+ "\n\tTo view version, only --version or -v should be present in command.\n");
						System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
						System.exit(0);
					}
					else
					{
						System.out.println("\n\tVersion 1.0.0\n");
						System.exit(0);
					}
					i = args.length;
					break;

				case "--library":
				case "-l":
					if (i + 1 <= args.length - 1)
					{
						if (args[i + 1].equals("lorem"))
						{
							System.out.println("\n\tLibrary set to lorem\n");
							library = "lorem";
						}
						else if (args[i + 1].equals("anguish"))
						{
							System.out.println("\n\tLibrary set to anguish\n");
							library = "anguish";
						}
						else
						{
							System.out.println("\n\tInvalid library.\n\tValid libraries are 'lorem' and 'anguish'.\n\t"
									+ "Press [up arrow key] to edit previous command, or run help command for more options.\n");
							i = args.length;
							System.exit(0);
						}
					}
					else
					{
						System.out.println("\n\tMust include library choice in command.\n\tValid libraries are 'lorem' and 'anguish'.\n\t"
								+ "Press [up arrow key] to edit previous command, or run help command for more options.\n");
						i = args.length;
						System.exit(0);
					}
					break;

				case "--outfile":
				case "-o":
					if (i + 1 <= args.length - 1)
					{
						filename = args[i + 1];
						String ext = "";
						if (filename.length() > 4)
						{
							ext = filename.substring(filename.length() - 4);
						}

						if (ext.equals(".txt"))
						{
							try
							{
								file = new File(filename);
								if (file.createNewFile()) { System.out.println("\n\tFile created successfully\n"); }
								else
								{
									System.out.println("\n\tFile already exists.");
									System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
									i = args.length;
									System.exit(0);
								}
								output = "file";
							}
							catch (IOException e) { e.printStackTrace(); }
						}
						else
						{
							System.out.println("\n\tInvalid file.");
							System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
							i = args.length;
							System.exit(0);
						}
					}
					else
					{
						System.out.println("\n\tNo file name specified. Please try again."
								+ "\n\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
						i = args.length;
						System.exit(0);
					}
					break;

				case "--mode":
				case "-m":
					if (i + 1 <= args.length - 1)
					{
						if (args[i + 1].equals("paragraph"))
						{
							System.out.println("\n\tMode set to paragraph\n");
							mode = "paragraph";
							count = "1";
						}
						else if (args[i + 1].equals("word"))
						{
							System.out.println("\n\tMode set to words\n");
							mode = "word";
							count = "3";
						}
						else if (args[i + 1].equals("bullet"))
						{
							System.out.println("\n\tMode set to bullets\n");
							mode = "bullet";
							count = "5";
						}
						else
						{
							System.out.println("\n\tInvalid mode. Valid modes include:");
							System.out.println("\t\tparagraph:  prints out however many paragraphs you want  (MAX OF 5)");
							System.out.println("\t\tword:       prints out however many words you want       (MAX OF 50)");
							System.out.println("\t\tbullet:     prints out however many bullets you want     (MAX OF 10)");
							System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
							i = args.length;
							System.exit(0);
						}
					}
					else
					{
						System.out.println("\n\tMust include mode choice in command. Valid modes include:");
						System.out.println("\t\tparagraph:  prints out however many paragraphs you want  (MAX OF 5)");
						System.out.println("\t\tword:       prints out however many words you want       (MAX OF 50)");
						System.out.println("\t\tbullet:     prints out however many bullets you want     (MAX OF 10)");
						System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
						i = args.length;
						System.exit(0);
					}
					break;

				case "--count":
				case "-c":
					if (i + 1 <= args.length - 1)
					{
						try
						{
							int intCount = Integer.valueOf(args[i + 1]);
							if (mode.equals("paragraph"))
							{
								if (intCount <= 5)
								{
									count = args[i + 1];
									System.out.println("\n\tCount set to " + intCount + "\n");
								}
								else
								{
									System.out.println("\n\tInvalid number for amount of paragraphs.");
									System.out.println("\tMax number of paragraphs allowed is 5.");
									System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
									i = args.length;
									System.exit(0);
								}
							}
							else if (mode.equals("word"))
							{
								if (intCount <= 50)
								{
									count = args[i + 1];
									System.out.println("\n\tCount set to " + intCount + "\n");
								}
								else
								{
									System.out.println("\n\tInvalid number for amount of words.");
									System.out.println("\tMax number of words allowed is 50.");
									System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
									i = args.length;
									System.exit(0);
								}
							}
							else if (mode.equals("bullet"))
							{
								if (intCount <= 10)
								{
									count = args[i + 1];
									System.out.println("\n\tCount set to " + intCount + "\n");
								}
								else
								{
									System.out.println("\n\tInvalid number for amount of bullets.");
									System.out.println("\tMax number of bullets allowed is 10.");
									System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
									i = args.length;
									System.exit(0);
								}
							}

						}
						catch (NumberFormatException e)
						{
							System.out.println("\n\tInvalid number for count. Maximums are:");
							System.out.println("\t\tparagraphs: max of 5");
							System.out.println("\t\twords:      max of 50");
							System.out.println("\t\tbullets:    max of 10");
							System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
							i = args.length;
							System.exit(0);
						}
					}
					else
					{
						System.out.println("\n\tMust include number for value of mode specified. Maximums are:");
						System.out.println("\t\tparagraphs: max of 5");
						System.out.println("\t\twords:      max of 50");
						System.out.println("\t\tbullets:    max of 10");
						System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
						i = args.length;
						System.exit(0);
					}
					break;

				case "--html":
				case "-t":
					System.out.println("\n\tHTML formatting is now ON\n");
					html = true;
					break;
				//default:
				//	System.out.println("\n\tInvalid option, please try again.");
				//	System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
				//	System.exit(0);
				//	break;
				}
			}

			// Call function to do the crap
			doIt(library, output, filename, mode, count, html);


		}

////////////////////////////// Interactive Mode Start		
		
		else
		{
			try
			{
				Terminal term = TerminalBuilder.terminal();    
				StringsCompleter completer = new StringsCompleter("generate", "help", "version", "set", "show", "reset",
						"exit", "quit", "mode", "count", "library", "html", "outfile", "paragraph", "word", "bullet",
						"lorem", "anguish");

				LineReader reader = LineReaderBuilder.builder()
						.terminal(term)
						.completer(completer)
						.build();

				while (true)
				{
					String input = reader.readLine(" > ");
					String[] inputArray = input.split(" ");
					int index = 0;

					if (input.trim().equals(""))
					{
						System.out.println("\n\tInvalid option, please try again.");
						System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
					}

					else
					{
/////////////////// easy interactive section
						switch(inputArray[index])
						{
						case "exit":
						case "quit":
							System.out.println("\n\tExiting Interactive Mode . . .\n");
							System.exit(0);
							break;

						case "version":
							System.out.println("\n\tVersion 1.0.0\n");
							break;

						case "help":
							System.out.println("\n- - - Interactive Mode Help Screen - - -");
							System.out.println("Interactive mode allows user to set/generate many different scenarios.");
							System.out.println("To end interactive mode, type exit, or type help for more options.\n");
							helpMessage(0);
							break;

						case "reset":
							System.out.println("\n\tReseting all values to default\n");
							library = "lorem";
							filename = "";
							output = "console";
							mode = "paragraph";
							count = "1";
							html = false;
							break;

						case "set":
							if (index + 1 <= inputArray.length - 1)
							{
								if (inputArray[index + 1].equals("mode"))
								{
									if (index + 2 <= inputArray.length - 1)
									{
										if (inputArray[index + 2].equals("paragraph"))
										{
											System.out.println("\n\tMode set to paragraph\n");
											mode = "paragraph";
											count = "1";
										}
										else if (inputArray[index + 2].equals("word"))
										{
											System.out.println("\n\tMode set to words\n");
											mode = "word";
											count = "3";
										}
										else if (inputArray[index + 2].equals("bullet"))
										{
											System.out.println("\n\tMode set to bullets\n");
											mode = "bullet";
											count = "5";
										}
										else
										{
											System.out.println("\n\tInvalid mode. Valid modes include:");
											System.out.println("\t\tparagraph:  prints out however many paragraphs you want  (MAX OF 5)");
											System.out.println("\t\tword:       prints out however many words you want       (MAX OF 50)");
											System.out.println("\t\tbullet:     prints out however many bullets you want     (MAX OF 10)");
											System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
										}
									}
									else
									{
										System.out.println("\n\tMust include mode choice in command. Valid modes include:");
										System.out.println("\t\tparagraph:  prints out however many paragraphs you want  (MAX OF 5)");
										System.out.println("\t\tword:       prints out however many words you want       (MAX OF 50)");
										System.out.println("\t\tbullet:     prints out however many bullets you want     (MAX OF 10)");
										System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
									}
								}
								else if (inputArray[index + 1].equals("count"))
								{
									if (index + 1 <= inputArray.length - 1)
									{
										try
										{
											int intCount = Integer.valueOf(inputArray[index + 2]);
											if (mode.equals("paragraph"))
											{
												if (intCount <= 5)
												{
													count = inputArray[index + 2];
													System.out.println("\n\tCount set to " + intCount + "\n");
												}
												else
												{
													System.out.println("\n\tInvalid number for amount of paragraphs.");
													System.out.println("\tMax number of paragraphs allowed is 5.");
													System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
												}
											}
											else if (mode.equals("word"))
											{
												if (intCount <= 50)
												{
													count = inputArray[index + 2];
													System.out.println("\n\tCount set to " + intCount + "\n");
												}
												else
												{
													System.out.println("\n\tInvalid number for amount of words.");
													System.out.println("\tMax number of words allowed is 50.");
													System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
												}
											}
											else if (mode.equals("bullet"))
											{
												if (intCount <= 10)
												{
													count = inputArray[index + 2];
													System.out.println("\n\tCount set to " + intCount + "\n");
												}
												else
												{
													System.out.println("\n\tInvalid number for amount of bullets.");
													System.out.println("\tMax number of bullets allowed is 10.");
													System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
												}
											}

										}
										catch (NumberFormatException e)
										{
											System.out.println("\n\tInvalid number for count. Maximums are:");
											System.out.println("\t\tparagraphs: max of 5");
											System.out.println("\t\twords:      max of 50");
											System.out.println("\t\tbullets:    max of 10");
											System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
										}
									}
									else
									{
										System.out.println("\n\tMust include number for value of mode specified. Maximums are:");
										System.out.println("\t\tparagraphs: max of 5");
										System.out.println("\t\twords:      max of 50");
										System.out.println("\t\tbullets:    max of 10");
										System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
									}
								}

								else if (inputArray[index + 1].equals("library"))
								{
									if (index + 2 <= inputArray.length - 1)
									{
										if (inputArray[index + 2].equals("lorem"))
										{
											System.out.println("\n\tLibrary set to lorem\n");
											library = "lorem";
										}
										else if (inputArray[index + 2].equals("anguish"))
										{
											System.out.println("\n\tLibrary set to anguish\n");
											library = "anguish";
										}
										else
										{
											System.out.println("\n\tInvalid library.\n\tValid libraries are 'lorem' and 'anguish'.");
											System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
										}
									}
									else
									{
										System.out.println("\n\tMust include library choice in command.\n\tValid libraries are 'lorem' and 'anguish'.\n\t"
												+ "Press [up arrow key] to edit previous command, or run help command for more options.\n");
									}
								}

								else if (inputArray[index + 1].equals("html"))
								{
									if (index + 2 <= inputArray.length - 1)
									{
										if (inputArray[index + 2].equals("on"))
										{
											System.out.println("\n\tHTML formatting is now ON\n");
											html = true;
										}
										else if (inputArray[index + 2].equals("off"))
										{
											System.out.println("\n\tHTML formatting is now OFF\n");
											html = false;
										}
										else
										{
											System.out.println("\n\tInvalid option.\n\tHTML options include 'on' or 'off'.");
											System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
										}
									}
									else
									{
										System.out.println("\n\tMust include HTML option in command.\n\tHTML options include 'on' or 'off'.\n\t"
												+ "Press [up arrow key] to edit previous command, or run help command for more options.\n");
									}
								}

								else if (inputArray[index + 1].equals("outfile"))
								{
									if (index + 2 <= inputArray.length - 1)
									{
										filename = inputArray[index + 2];
										String ext = "";
										if (filename.length() > 4)
										{
											ext = filename.substring(filename.length() - 4);
											System.out.println(ext);
										}

										if (ext.equals(".txt"))
										{
											try
											{
												file = new File(filename);
												if (file.createNewFile()) { System.out.println("\n\tFile created successfully\n"); }
												else
												{
													System.out.println("\n\tFile already exists.");
													System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
												}
												output = "file";
											}
											catch (IOException e) { e.printStackTrace(); }
										}
										else
										{
											System.out.println("\n\tInvalid file.");
											System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
										}
									}
									else
									{
										System.out.println("\n\tNo file name specified. Please try again.");
										System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
									}
								}
							}

							else
							{
								System.out.println("\n\tMust include options you'd like to set. Valid set options include:");
								System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
							}
							break;

//////////////////// Interactive generate							
							
						case "generate":
							String tempLibrary = library;
							String tempMode = mode;
							String tempCount = count;
							boolean tempHtml = html;
							String tempOutput = output;
							boolean nodoit = false;
							
							if (index + 1 <= inputArray.length - 1)
							{
								for (int i = 1; i < inputArray.length; i++)
								{
									switch(inputArray[i])
									{
									case "--help":
									case "-h":
									case "help":
										if (i + 1 < inputArray.length)
										{
											System.out.println("\n\tHelp is only valid as a standalone option."
													+ "\n\tTo view help, only --help or -h should be present in command.\n");
										}
										else
										{
											System.out.println("\n- - - Interactive Mode Help Screen - - -");
											System.out.println("Interactive mode allows user to set/generate many different scenarios.");
											System.out.println("To end interactive mode, type exit, or type help for more options.\n");
											helpMessage(0);
										}
										nodoit = true;
										i = inputArray.length;
										break;

									case "--version":
									case "-v":
									case "version":
										if (i + 1 < inputArray.length)
										{
											System.out.println("\n\tVersion is only valid as a standalone option."
													+ "\n\tTo view version, only --version or -v should be present in command.\n");
										}
										else
										{
											System.out.println("\n\tVersion 1.0.0\n");
										}
										nodoit = true;
										i = inputArray.length;
										break;

									case "--library":
									case "-l":
										if (i + 1 <= inputArray.length - 1)
										{
											if (inputArray[i + 1].equals("lorem"))
											{
												System.out.println("\n\tLibrary set to lorem\n");
												tempLibrary = "lorem";
											}
											else if (inputArray[i + 1].equals("anguish"))
											{
												System.out.println("\n\tLibrary set to anguish\n");
												tempLibrary = "anguish";
											}
											else
											{
												System.out.println("\n\tInvalid library.\n\tValid libraries are 'lorem' and 'anguish'.\n\t"
														+ "Press [up arrow key] to edit previous command, or run help command for more options.\n");
												nodoit = true;
												i = inputArray.length;
											}
										}
										else
										{
											System.out.println("\n\tMust include library choice in command.\n\tValid libraries are 'lorem' and 'anguish'.\n\t"
													+ "Press [up arrow key] to edit previous command, or run help command for more options.\n");
											nodoit = true;
											i = inputArray.length;
										}
										break;

									case "--outfile":
									case "-o":
										if (i + 1 <= inputArray.length - 1)
										{
											filename = inputArray[i + 1];
											String ext = "";
											if (filename.length() > 4)
											{
												ext = filename.substring(filename.length() - 4);
											}

											if (ext.equals(".txt"))
											{
												try
												{
													file = new File(filename);
													if (file.createNewFile()) { System.out.println("\n\tFile created successfully\n"); }
													else
													{
														System.out.println("\n\tFile already exists.");
														System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
														nodoit = true;
														i = inputArray.length;
													}
													tempOutput = "file";
												}
												catch (IOException e) { e.printStackTrace(); }
											}
											else
											{
												System.out.println("\n\tInvalid file.");
												System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
												nodoit = true;
												i = inputArray.length;
											}
										}
										else
										{
											System.out.println("\n\tNo file name specified. Please try again."
													+ "\n\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
											nodoit = true;
											i = inputArray.length;
										}
										break;

									case "--mode":
									case "-m":
										if (i + 1 <= inputArray.length - 1)
										{
											if (inputArray[i + 1].equals("paragraph"))
											{
												System.out.println("\n\tMode set to paragraph\n");
												tempMode = "paragraph";
												tempCount = "1";
											}
											else if (inputArray[i + 1].equals("word"))
											{
												System.out.println("\n\tMode set to words\n");
												tempMode = "word";
												tempCount = "3";
											}
											else if (inputArray[i + 1].equals("bullet"))
											{
												System.out.println("\n\tMode set to bullets\n");
												tempMode = "bullet";
												tempCount = "5";
											}
											else
											{
												System.out.println("\n\tInvalid mode. Valid modes include:");
												System.out.println("\t\tparagraph:  prints out however many paragraphs you want  (MAX OF 5)");
												System.out.println("\t\tword:       prints out however many words you want       (MAX OF 50)");
												System.out.println("\t\tbullet:     prints out however many bullets you want     (MAX OF 10)");
												System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
												nodoit = true;
												i = inputArray.length;
											}
										}
										else
										{
											System.out.println("\n\tMust include mode choice in command. Valid modes include:");
											System.out.println("\t\tparagraph:  prints out however many paragraphs you want  (MAX OF 5)");
											System.out.println("\t\tword:       prints out however many words you want       (MAX OF 50)");
											System.out.println("\t\tbullet:     prints out however many bullets you want     (MAX OF 10)");
											System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
											nodoit = true;
											i = inputArray.length;
										}
										break;

									case "--count":
									case "-c":
										if (i + 1 <= inputArray.length - 1)
										{
											try
											{
												int intCount = Integer.valueOf(inputArray[i + 1]);
												if (mode.equals("paragraph"))
												{
													if (intCount <= 5)
													{
														tempCount = inputArray[i + 1];
														System.out.println("\n\tCount set to " + intCount + "\n");
													}
													else
													{
														System.out.println("\n\tInvalid number for amount of paragraphs.");
														System.out.println("\tMax number of paragraphs allowed is 5.");
														System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
														nodoit = true;
														i = inputArray.length;
													}
												}
												else if (mode.equals("word"))
												{
													if (intCount <= 50)
													{
														tempCount = inputArray[i + 1];
														System.out.println("\n\tCount set to " + intCount + "\n");
													}
													else
													{
														System.out.println("\n\tInvalid number for amount of words.");
														System.out.println("\tMax number of words allowed is 50.");
														System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
														nodoit = true;
														i = inputArray.length;
													}
												}
												else if (mode.equals("bullet"))
												{
													if (intCount <= 10)
													{
														tempCount = inputArray[i + 1];
														System.out.println("\n\tCount set to " + intCount + "\n");
													}
													else
													{
														System.out.println("\n\tInvalid number for amount of bullets.");
														System.out.println("\tMax number of bullets allowed is 10.");
														System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
														nodoit = true;
														i = inputArray.length;
													}
												}

											}
											catch (NumberFormatException e)
											{
												System.out.println("\n\tInvalid number for count. Maximums are:");
												System.out.println("\t\tparagraphs: max of 5");
												System.out.println("\t\twords:      max of 50");
												System.out.println("\t\tbullets:    max of 10");
												System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
												nodoit = true;
												i = inputArray.length;
											}
										}
										else
										{
											System.out.println("\n\tMust include number for value of mode specified. Maximums are:");
											System.out.println("\t\tparagraphs: max of 5");
											System.out.println("\t\twords:      max of 50");
											System.out.println("\t\tbullets:    max of 10");
											System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
											nodoit = true;
											i = inputArray.length;
										}
										break;

									case "--html":
									case "-t":
										System.out.println("\n\tHTML formatting is now ON\n");
										tempHtml = true;
										break;
									}
								}
								if (nodoit == false)
								{
									doIt(tempLibrary, tempOutput, filename, tempMode, tempCount, tempHtml);
								}
								
							}
							else
							{
								doIt(tempLibrary, tempOutput, filename, tempMode, tempCount, tempHtml);
							}
							break;

						case "show":
							if (index + 1 <= inputArray.length - 1)
							{
								switch(inputArray[index + 1])
								{
								case "library":
									System.out.println("\t\tlibrary set: " + library);
									System.out.println("\n");
									break;
								case "outfile":
									if (filename.equals(""))
									{
										System.out.println("\t\toutfile set: NONE Specified");
										System.out.println("\n");
									}
									else
									{
										System.out.println("\t\toutfile set: " + filename);
										System.out.println("\n");
									}
									break;
								case "mode":
									System.out.println("\t\tmode set   : " + mode);
									System.out.println("\n");
									break;
								case "count":
									System.out.println("\t\tcount set  : " + count);
									System.out.println("\n");
									break;
								case "html":
									if(html == true)
									{
										System.out.println("\t\tHTML       : ON");
										System.out.println("\n");
									}
									else
									{
										System.out.println("\t\tHTML       : OFF");
										System.out.println("\n");
									}
									break;
								}
							}
							else
							{
								System.out.println("\n\t- - - Showing all values set - - -");
								System.out.println("\t\tlibrary: " + library);
								if (filename.equals(""))
								{
									System.out.println("\t\toutfile: NONE Specified");
								}
								else
								{
									System.out.println("\t\toutfile: " + filename);
								}
								System.out.println("\t\tmode   : " + mode);
								System.out.println("\t\tcount  : " + count);
								if(html == true)
								{
									System.out.println("\t\tHTML   : ON\n");
								}
								else
								{
									System.out.println("\t\tHTML   : OFF\n");
								}
							}
							break;

						default:
							System.out.println("\n\tInvalid option, please try again.");
							System.out.println("\tPress [up arrow key] to edit previous command, or run help command for more options.\n");
							break;
						}
					}
				}
			}

			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// takes in all values and produces weird language to either file or console
	public static void doIt(String LIBRARY, String OUTPUT, String FILENAME, String MODE, String COUNT, Boolean HTML) throws IOException
	{
		String LINE = null;
		int counter = 0;

		switch(LIBRARY)
		{
		case "lorem":
			if (MODE.equals("paragraph"))
			{
				java.io.InputStream in = Generator.class.getResourceAsStream("loremP.txt");
				InputStreamReader inReader = new InputStreamReader(in);
				BufferedReader bufferedReader = new BufferedReader(inReader);

				if (OUTPUT.equals("file"))
				{
					FileWriter fileWriter = new FileWriter(FILENAME);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { bufferedWriter.write("\t<p> " + LINE + " <p>\n"); }
						else { bufferedWriter.write("\t" + LINE + "\n"); }
						bufferedWriter.newLine();
						counter = counter + 1;
					}
					bufferedWriter.close();
					bufferedReader.close();
				}

				else if (OUTPUT.equals("console"))
				{
					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { System.out.println("\t<p> " + LINE + " <p>\n"); }
						else { System.out.println("\t" + LINE + "\n"); }
						counter = counter + 1;
					}
					bufferedReader.close();
				}
			}

			else if (MODE.equals("word"))
			{
				java.io.InputStream in = Generator.class.getResourceAsStream("loremW.txt");
				InputStreamReader inReader = new InputStreamReader(in);
				BufferedReader bufferedReader = new BufferedReader(inReader);

				if (OUTPUT.equals("file"))
				{
					FileWriter fileWriter = new FileWriter(FILENAME);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { bufferedWriter.write("<h1> " + LINE + " <h1>"); }
						else { bufferedWriter.write(LINE); }
						bufferedWriter.newLine();
						counter = counter + 1;
					}
					bufferedWriter.close();
					bufferedReader.close();
				}

				else if (OUTPUT.equals("console"))
				{
					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { System.out.println("<h1> " + LINE + " <h1>"); }
						else { System.out.println(LINE); }
						counter = counter + 1;
					}
					bufferedReader.close();
				}
			}

			else if (MODE.equals("bullet"))
			{
				java.io.InputStream in = Generator.class.getResourceAsStream("loremB.txt");
				InputStreamReader inReader = new InputStreamReader(in);
				BufferedReader bufferedReader = new BufferedReader(inReader);

				if (OUTPUT.equals("file"))
				{
					FileWriter fileWriter = new FileWriter(FILENAME);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { bufferedWriter.write("<ul> " + LINE + " <li>"); }
						else { bufferedWriter.write(LINE); }
						bufferedWriter.newLine();
						counter = counter + 1;
					}
					bufferedWriter.close();
					bufferedReader.close();
				}

				else if (OUTPUT.equals("console"))
				{
					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { System.out.println("<ul> " + LINE + " <li>"); }
						else { System.out.println(LINE); }
						counter = counter + 1;
					}
					bufferedReader.close();
				}
			}
			break;


		case "anguish":
			if (MODE.equals("paragraph"))
			{
				java.io.InputStream in = Generator.class.getResourceAsStream("anguishP.txt");
				InputStreamReader inReader = new InputStreamReader(in);
				BufferedReader bufferedReader = new BufferedReader(inReader);

				if (OUTPUT.equals("file"))
				{
					FileWriter fileWriter = new FileWriter(FILENAME);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { bufferedWriter.write("\t<p> " + LINE + " <p>\n"); }
						else { bufferedWriter.write("\t" + LINE + "\n"); }
						bufferedWriter.newLine();
						counter = counter + 1;
					}
					bufferedWriter.close();
					bufferedReader.close();
				}

				else if (OUTPUT.equals("console"))
				{
					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { System.out.println("\t<p> " + LINE + " <p>\n"); }
						else { System.out.println("\t" + LINE + "\n"); }
						counter = counter + 1;
					}
					bufferedReader.close();
				}
			}

			else if (MODE.equals("word"))
			{
				java.io.InputStream in = Generator.class.getResourceAsStream("anguishW.txt");
				InputStreamReader inReader = new InputStreamReader(in);
				BufferedReader bufferedReader = new BufferedReader(inReader);

				if (OUTPUT.equals("file"))
				{
					FileWriter fileWriter = new FileWriter(FILENAME);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { bufferedWriter.write("<h1> " + LINE + " <h1>"); }
						else { bufferedWriter.write(LINE); }
						bufferedWriter.newLine();
						counter = counter + 1;
					}
					bufferedWriter.close();
					bufferedReader.close();
				}

				else if (OUTPUT.equals("console"))
				{
					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { System.out.println("<h1> " + LINE + " <h1>"); }
						else { System.out.println(LINE); }
						counter = counter + 1;
					}
					bufferedReader.close();
				}
			}

			else if (MODE.equals("bullet"))
			{
				java.io.InputStream in = Generator.class.getResourceAsStream("anguishB.txt");
				InputStreamReader inReader = new InputStreamReader(in);
				BufferedReader bufferedReader = new BufferedReader(inReader);

				if (OUTPUT.equals("file"))
				{
					FileWriter fileWriter = new FileWriter(FILENAME);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { bufferedWriter.write("<ul> " + LINE + " <li>"); }
						else { bufferedWriter.write(LINE); }
						bufferedWriter.newLine();
						counter = counter + 1;
					}
					bufferedWriter.close();
					bufferedReader.close();
				}

				else if (OUTPUT.equals("console"))
				{
					while(Integer.valueOf(COUNT) != counter)
					{
						LINE = bufferedReader.readLine();
						if (HTML == true) { System.out.println("<ul> " + LINE + " <li>"); }
						else { System.out.println(LINE); }
						counter = counter + 1;
					}
					bufferedReader.close();
				}
			}
			break;
		}

	}

	public static void helpMessage(int messageType)
	{
		if (messageType == 1)
		{
			System.out.println("\t--help/-h");
			System.out.println("\t\tdisplays help screen (Standalone option only)");
			System.out.println("\t--version/-v");
			System.out.println("\t\tdisplays version of generator (Standalone option only)");
			System.out.println("\t--library/-l");
			System.out.println("\t\tlorem: sets library to lorem ipsum language (default)");
			System.out.println("\t\tanguish: sets library to anguish languish language");
			System.out.println("\t--outfile/-o");
			System.out.println("\t\tspecifies outfile name (default is to console)");
			System.out.println("\t--mode/-m");
			System.out.println("\t\tparagraph: sets mode to paragraph");
			System.out.println("\t\tword: sets mode to words");
			System.out.println("\t\tbullet: sets mode to bullets");
			System.out.println("\t--count/-c");
			System.out.println("\t\tmax count for paragraph = 5");
			System.out.println("\t\tmax count for words = 50");
			System.out.println("\t\tmax count for bullets = 10");
			System.out.println("\t--html/-t");
			System.out.println("\t\tsets html formatting to on (default is off)");
			System.out.println("\t--generate/-g");
			System.out.println("\t\tspecifies command line mode\n");
		}
		
		else
		{
			System.out.println("\thelp");
			System.out.println("\t\tdisplays help screen (Standalone option only)");
			System.out.println("\tversion");
			System.out.println("\t\tdisplays version of generator (Standalone option only)");
			System.out.println("\tset");
			System.out.println("\t\tlibrary");
			System.out.println("\t\t\tlorem: sets library to lorem ipsum language (default)");
			System.out.println("\t\t\tanguish: sets library to anguish languish language");
			System.out.println("\t\toutfile");
			System.out.println("\t\t\tspecifies outfile name (default is to console)");
			System.out.println("\t\tmode");
			System.out.println("\t\t\tparagraph: sets mode to paragraph");
			System.out.println("\t\t\tword: sets mode to words");
			System.out.println("\t\t\tbullet: sets mode to bullets");
			System.out.println("\t\tcount");
			System.out.println("\t\t\tmax count for paragraph = 5");
			System.out.println("\t\t\tmax count for words = 50");
			System.out.println("\t\t\tmax count for bullets = 10");
			System.out.println("\t\thtml");
			System.out.println("\t\t\ton: sets html formatting to on (default is off)");
			System.out.println("\t\t\toff: sets html formatting to off (default is off)");
			System.out.println("\tshow");
			System.out.println("\t\tlibrary: shows set library");
			System.out.println("\t\toutfile: shows set outfile");
			System.out.println("\t\tmode: shows set mode");
			System.out.println("\t\thtml: shows html status");
			System.out.println("\tgenerate");
			System.out.println("\t\tgenerates based on given (or default) values. Given values can include:\n");

			System.out.println("\t\t\t--help/-h");
			System.out.println("\t\t\t\tdisplays help screen (Standalone option only)");
			System.out.println("\t\t\t--version/-v");
			System.out.println("\t\t\t\tdisplays version of generator (Standalone option only)");
			System.out.println("\t\t\t--library/-l");
			System.out.println("\t\t\t\tlorem: sets library to lorem ipsum language (default)");
			System.out.println("\t\t\t\tanguish: sets library to anguish languish language");
			System.out.println("\t\t\t--outfile/-o");
			System.out.println("\t\t\t\tspecifies outfile name (default is to console)");
			System.out.println("\t\t\t--mode/-m");
			System.out.println("\t\t\t\tparagraph: sets mode to paragraph");
			System.out.println("\t\t\t\tword: sets mode to words");
			System.out.println("\t\t\t\tbullet: sets mode to bullets");
			System.out.println("\t\t\t--count/-c");
			System.out.println("\t\t\t\tmax count for paragraph = 5");
			System.out.println("\t\t\t\tmax count for words = 50");
			System.out.println("\t\t\t\tmax count for bullets = 10");
			System.out.println("\t\t\t--html/-t");
			System.out.println("\t\t\t\tsets html formatting to on (default is off)");
			
			System.out.println("\treset");
			System.out.println("\t\tresets session to all starting default values (defaults specified in each category above)\n");
			System.out.println("\texit/quit");
			System.out.println("\t\tends session (will not save any set values)\n");
		}
	}
}