package filmapp;

import java.util.ArrayList;
import filmapp.Film;

/**
  * The Model of the film app. This class maintains a list of Film objects with functionality to view the films stored in 
  * the database, add new films to the list, remove existing films from the list, search the list according to a search 
  * term, and empty the list.
  * Two constructors to initialise the FilmList object and a a public get method for access to the list of films, are defined 
  * in this class.
  * A to string method to output a string representation of the FilmList object, is also defined in this class.
  * 
  * @author asen 18048443
 **/
public class FilmList 
{
	private ArrayList<Film> filmTitles; // Array list of film objects.

	//---------------------------------------------------------------------------------------------------------------------
	// Constructors to initialise the film list object.
	/**
	  * The default constructor initialises filmTitles, the ArrayList of Film objects with a new Array List of Film objects.
	  * 
	  * @author asen 18048443
	 **/
	public FilmList()
	{
		this.filmTitles = new ArrayList<Film>();
	}
	
	/**
	  * This constructor takes in filmTitles, an Array List of Film objects, as a parameter and initialises filmTitles.
	  * 
	  * @param  filmTitles, an Array List of film objects.
	  * @author asen 18048443
	 **/
	public FilmList(ArrayList<Film> filmTitles)
	{
		this.filmTitles = filmTitles;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method goes through the filmTitles array list and returns the a new array of Film objects. This array
	  * is of the same size as the filmTitles ArrayList.
	  * It does not take in any parameters.
	  * 
	  * @return array of film objects from the filmTitles arrayList.
	  * @author asen 18048443
	 **/
	public Film[] getFilmList() 
	{
		Film[] array = new Film[this.filmTitles.size()];  

		for(int i = 0; i < array.length; i++)            
		{
			array[i] = this.filmTitles.get(i);
		}

		return array;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method takes in aFilm, a Film object and adds it to end of the filmTitles array list. 
	  * This method does not return any value.
	  * 
	  * @param aFilm is a Film object to be added to the filmTitles array list.
	  * @author asen 18048443
	 **/
	public void addFilm(Film aFilm)
	{
		this.filmTitles.add(aFilm);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method takes in a Film object and removes it from the filmTitles array list. 
	  * This method does not return any value.
	  * 
	  * @param myFilmTitle is a Film object to be removed from the filmTitles array list.
	  * @author asen 18048443
	 **/
	public void removeFilmTitle(Film myFilmTitle)
	{
		this.filmTitles.remove(myFilmTitle);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method removes all elements from the filmTitles array list. 
	  * It does not take in any parameters nor does it return any value.
	  * 
	  * @author asen 18048443
	 **/
	public void clear() 
	{
		this.filmTitles.clear();
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method takes in afilm, a Film object and returns true if the filmTitles array list contains this object. 
	  * 
	  * @param  aFilm is a Film object.
	  * @return true if the filmTitles array list contains this object.
	  * @author asen 18048443
	 **/
	public boolean hasFilmTitles(Film aFilm) 
	{
		return this.filmTitles.contains(aFilm);					
	}	
		
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method takes in aFilm, a Film object, searches the filmTitles ArrayList.
	  * It then returns a new array list of film objects from the the filmTitles ArrayList, that have the search keyword in 
	  * their title.
	  * 
	  * @param  aFilm is a film object.
	  * @return filmSearchList, an ArrayList of film objects from the filmTitles ArrayList that contain the search keyword in their title.
	  * @author asen 18048443
	 **/
	public ArrayList<Film> searchFilm(Film aFilm)
	{
		ArrayList<Film> filmSearchList= new ArrayList<Film>();
		
		for(Film film: this.filmTitles)
		{
			String filmTitle = film.getTitle().toLowerCase();
			String titleKeyword = aFilm.getTitle().toLowerCase();		
			
			if(filmTitle.contains(titleKeyword))
			{
				filmSearchList.add(film);
			}
		}
		
		return filmSearchList;
	}
		
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * 
	  * This public method overrides the toString method and returns a String representation of the film list object.
	  * 
	  * @return String representation of film list object.
	  * @author asen 18048443
	 **/
	public String toString()
	{	
		String output = "";	
		
		for(Film film : filmTitles)
		{
			if(film!=null)
			{
				output += film;
			}
		}
		
		return output;
	}
}


