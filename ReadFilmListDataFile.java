package filmapp;
/**
 * 
 * This class contains two static methods which read and write a database of films.
 * One saves the changes made to the film list. The second method reads the film text file and 
 * displays the list of films in the database.
 * 
 * @author asen 18048443
 *  
 * */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadFilmListDataFile 
{
	public static final String filmListDataFilePath = "filmlistdata/";
	public static final String filmListDataFileName = "films.txt";
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	 * Writes the text file filmListDataFileName of the films in supplied FilmList object
	 * 
	 * @param fl a film list to store in the database
	 * @author asen 18048443
	 * 
	 * */
	public static void saveFilmList(FilmList fl)
	{
		try {
			try (PrintWriter fileWriter = new PrintWriter(new File(filmListDataFilePath+filmListDataFileName)))
			{
				fileWriter.println(fl.getFilmList().length);
				
				for(Film film : fl.getFilmList())
				{
					fileWriter.println(film.getTitle());
					fileWriter.println(film.getYearReleased());
					fileWriter.println(film.getRating());
				}
				
				fileWriter.close();
			}		

		} catch (FileNotFoundException e) 
		{			
			e.printStackTrace();
		}
	}
	//---------------------------------------------------------------------------------------------------------------------
	/**
	 * Reads the text file filmListDataFileName of the films in supplied FilmList object
	 * 
	 * @param fl a film list which will be cleared of existing films to store the films read from the database.
	 * @author asen 18048443
	 * 
	 * */

	public static void viewAllFilmList(FilmList fl)
	{
		try 
		{
			Scanner fileScan = new Scanner(new File(filmListDataFilePath+filmListDataFileName));

			int nFilms = fileScan.nextInt();
			fileScan.nextLine();
			fl.clear();

			for(int i = 1; i <= nFilms; i++)
			{			
				String name = fileScan.nextLine();
				String year = fileScan.nextLine();
				String rating = fileScan.nextLine();

				int yearx = Integer.parseInt(year);

				Film aFilm = new Film(name, yearx, rating);
				fl.addFilm(aFilm);
			}

			fileScan.close();
			
			if(nFilms >= 1)
			{
				System.out.print("Database currently has "+ nFilms); 
		
				if (nFilms == 1)
				{
					System.out.println(" film title");
				}
				else if (nFilms > 1)
				{
					System.out.println(" film titles");
				}
			}
			else if (nFilms == 0)
			{
				System.out.println("The film database is empty!"); 
			} 

		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}