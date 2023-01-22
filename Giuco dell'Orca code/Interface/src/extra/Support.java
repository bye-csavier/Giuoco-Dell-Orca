package extra;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;
import java.time.LocalDate;
import java.time.LocalTime;

public class Support{
	
	// ENUM ================================================
		
	// VARIABLES(CONST/FINAL) ================================================

		// String ------------------------------------------
		
		public final static String nL= System.getProperty("line.separator");
	
	// FUNCTIONS ================================================
	
		// Numbers ------------------------------------------
	
			// Mapping set 
		
			public static long map(long x, long in_min, long in_max, long out_min, long out_max)
			{
			  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
			}
			
			public static double map(double x, double in_min, double in_max, double out_min, double out_max)
			{
			  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
			}
			
			public static int map(int x, int in_min, int in_max, int out_min, int out_max)
			{
			  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
			}
				
	
	public static int clamp(int min, int value, int max)
	{
		if(value < min)
		{
			return min;
		}
		
		if(value > max)
		{
			return max;
		}
		return value;
	}
	
	public static double clamp(double min, double value, double max)
	{
		if(value < min)
		{
			return min;
		}
		
		if(value > max)
		{
			return max;
		}
		return value;
	}
			
	public static int randInt(int min, int max) // int random con limiti facili
	{
		return (int) ((Math.random() * ((max+1)-min)) + min);
	}
			
	
	public static double truncate(double value, int decimalpoint) // troncaggio dei double
	{
	  
		boolean negative = false;
	        
		if(value < 0)
		{
			value = Math.abs(value);
			negative = true;
		}
	        
		value = value * Math.pow(10, decimalpoint);
		value = Math.floor(value);
		value = value / Math.pow(10, decimalpoint);
	  
		if(negative == true)
		{
			return (-value);
		}
		else
		{
			return value;
		}
	        
	}

		// Strings e Char ------------------------------------------
	
	public static String newLines(int newLinesAmt)
	{
		String str = nL;
		
		for(int i = 1; i < newLinesAmt; i++)
		{
			str += nL;
		}
		
		return str;
	}
	
	public static String stringSetChar(String str, char input, int index)
	{
		char[] temp = str.toCharArray();
		temp[index] = input;
		
		return  String.valueOf(temp);
	}
	
	public static String deleteLastStringChar(String str)
	{
		return str.substring(0, str.length() - 1);
	}
	
	public static char charToUpper(char chr) // due funzioni per trasformare i charatteri singoli
	{
	    if(chr >= 'a'&& chr <= 'z')
	    {
	        int temp = (int) chr;
	        temp -= 32;
	        
	        return ( (char) temp ); 
	    }
	    
	    return chr;
	}

	public static char charToLower(char chr)
	{
	    if(chr >= 'A'&& chr <= 'Z')
	    {
	        int temp = (int) chr;
	        temp += 32;
	        
	        return ( (char) temp ); 
	    }
	    
	    return chr;
	}
	
	public static char randAlphaChar(int upperChance) // funzione che ritorna una lettera a caso con una percentuale "upperChance" di essere maiuscola
	{
	    char chr = (char) randInt(65,90);
	    int whatCase = randInt(0,100);
	    upperChance /= 2;    

	    if(whatCase > (50-upperChance) && whatCase < (50+upperChance))
	    {
	        return chr;
	    }
	    else
	    {
	        return charToLower(chr);
	    }
	}
	
		// Time ------------------------------------------
	
	public static void wait(int ms, boolean inSeconds) // funzione di sleep/wait
	{
	    
	    if(inSeconds == true)
	    {
	        ms = ms * 1000;
	    }
	    
	    try
	    {
	        Thread.sleep(ms);
	    }
	    catch(InterruptedException ex)
	    {
	        Thread.currentThread().interrupt();
	    }
	    
	}
	
	public static double convertTime(double fromTime, String fromTimeFormat, String toTimeFormat)
	{
		/*
			a = anno
			m = mesi
			week = settimane
			d = giorni
			h = ore
			min = minuti
			s = secondi
			ms = millisecondi
			ns = nanosecondi
		 */
		
		fromTimeFormat = fromTimeFormat.toLowerCase();
		
		if(fromTimeFormat.equals(toTimeFormat))
		{
			return (double) fromTime;
		}
		
		
		double convTime = 0;
		
		// Conversion to seconds cases
		
		if(fromTimeFormat.equals("s") == true)
		{
			convTime = fromTime;
		}
		else if(fromTimeFormat.equals("a") == true)
		{
			convTime = fromTime * 31557600;
		}
		else if(fromTimeFormat.equals("m") == true)
		{
			convTime = fromTime * 2629800;
		}
		else if(fromTimeFormat.equals("week") == true)
		{
			convTime = fromTime * 604800.02;
		}
		else if(fromTimeFormat.equals("d") == true)
		{
			convTime = fromTime * 86400;
		}
		else if(fromTimeFormat.equals("h") == true)
		{
			convTime = fromTime * 3600;
		}
		else if(fromTimeFormat.equals("min") == true)
		{
			convTime = fromTime * 60;
		}
		else if(fromTimeFormat.equals("ms") == true)
		{
			convTime = fromTime * 0.001;
		}
		else if(fromTimeFormat.equals("ns") == true)
		{
			convTime = fromTime * 0.000000001;
		}
		
		// Conversion to "toTimeFormat" cases
		
		if(toTimeFormat.equals("a") == true)
		{
			convTime /= 31557600;
		}
		else if(toTimeFormat.equals("m") == true)
		{
			convTime /= 2629800;
		}
		else if(toTimeFormat.equals("week") == true)
		{
			convTime /= 604800.02;
		}
		else if(toTimeFormat.equals("d") == true)
		{
			convTime /= 86400;
		}
		else if(toTimeFormat.equals("h") == true)
		{
			convTime /= 3600;
		}
		else if(toTimeFormat.equals("min") == true)
		{
			convTime /= 60;
		}
		else if(toTimeFormat.equals("ms") == true)
		{
			convTime /= 0.001;
		}
		else if(toTimeFormat.equals("ns") == true)
		{
			convTime /= 0.000000001;
		}
		
		// return
		
		return convTime;
	}
	
	public static String getCurrentDate() // ritorna una stringa con la data corrente
	{
	    LocalDate temp = java.time.LocalDate.now();
	    return temp.toString();
	}

	public static String getCurrentTime() // ritorna una stringa con l'ora corrente
	{
	    LocalTime temp = java.time.LocalTime.now();
	    String str = temp.getHour() + ":" + temp.getMinute() + ":" + String.valueOf(truncate(temp.getSecond(),0));
	    
	    return str;
	}
	
		// Copia classe leggi del Vassena ------------------------------------------
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static String s;

	public static boolean unBoolean() {
	  input();
	  if (s != null && !s.equals("true") 
	                && !s.equals("false"))
	    System.err.println("Errore: "+s+" non e' un boolean");
	  return s!=null && s.equals("true");
	}
	public static byte unByte() {
	  try {
	    return Byte.parseByte(input());
	  } catch (NumberFormatException e) {
	    if (s!=null) System.err.println("Errore: "+s+
	                                    " non e' un byte");
	    return 0;
	  }
	}
	public static short unoShort() {
	  try {
	    return Short.parseShort(input());
	  } catch (NumberFormatException e) {
	    if (s!=null) System.err.println("Errore: "+s+
	                                    " non e' uno short");
	    return 0;
	  }
	}
	public static int unInt() {
	    return Integer.parseInt(input());
	}
	public static long unLong() {
	  try {
	    return Long.parseLong(input());
	  } catch (NumberFormatException e) {
	    if (s!=null) System.err.println("Errore: "+s+
	                                    " non e' un long");
	    return 0;
	  }
	}
	public static float unFloat() {
	  try {
	    return Float.parseFloat(input());
	  } catch (NumberFormatException e) {
	    if (s!=null) System.err.println("Errore: "+s+
	                                    " non e' un float");
	  } catch (NullPointerException e) {}
	  return 0;
	}
	public static double unDouble() {
	  try {
	    return Double.parseDouble(input());
	  } catch (NumberFormatException e) {
	    if (s!=null) System.err.println("Errore: "+s+
	                                    " non e' un double");
	  } catch (NullPointerException e) {}
	  return 0;
	}
	public static char unChar() {
	  try {
	    return input().charAt(0);
	  } catch (IndexOutOfBoundsException e) {
	    // non dovrebbe mai accadere 
	    // se non quando c'e' un IOException
	  } catch (NullPointerException e) {}
	  return ' ';
	}
	public static String unoString() {
	  try {
	    return br.readLine();
	  } catch (IOException e) {
	    System.err.println("Errore durante la "+
	                        "lettura dell'input!");
	    return null;
	  }
	}
	private static String input() {
	  try {
	    s = br.readLine().trim()+" ";
	    s = s.substring(0,s.indexOf(" "));
	    if (s.length() < 1) {
	      System.err.println("Errore: inserire almeno un "+
	                          "carattere o uno spazio");
	      s = null;
	    }
	  } catch (IOException e) {
	    System.err.println("Errore durante la "+
	                        "lettura dell'input!");
	    return null;
	  }
	  return s;
	}
	
//?!?
}