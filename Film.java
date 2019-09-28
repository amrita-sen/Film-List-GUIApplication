package filmapp;

/**
  *  This is a description of the Film class. 
  *  Three private variables, title, the year released and rating, define the film object. A list of film objects 
  *  will constitute the FilmList, the model of the film app.
  *  A constructor to intialise the film object is defined in this class.
  *  Each variable has a public get method for access outside of the Film class and a public set method to 
  *  allow values to be set from outside the Film class.
  *  A to string method to output a string representation of the Film object, is also defined in this class.
  * 
  * @author asen 18048443
 **/
public class Film 
{
	private String title;  		// Stores the film title.
	private int yearReleased;   // Stores the year film released.
	private String rating;      // Stores the film rating.

	// Constructor to initialise the film object
	/**
	  * The constructor initialises the title, the year released, and the rating of the film object.
	  * 
	  * @param title is a String representing the film title.
	  * @param yearReleased is an integer representing the year the film released.
	  * @param rating is a String representing the film rating.
	  * @author asen 18048443
	 **/
	public Film(String title, int yearReleased, String rating)
	{
		this.setTitle(title);
		this.setYearReleased(yearReleased);
		this.setRating(rating);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	// Get and set methods for title
	/**
	  * This public method will return the film title as a String. It doesn't take in any parameter.
	  * 
	  * @return title of type String representing the film title.
	  * @author asen 18048443
	 **/
	public String getTitle() 
	{
		return title;
	}
	
	/**
	  * This public method takes in title as a parameter of type String and saves it in variable this.title. 
	  * This method does not return any value.
	  * 
	  * @param title of type String representing the film title.
	  * @author asen 18048443
	 **/
	public void setTitle(String title) 
	{
		this.title = title;
	} 
	
	//---------------------------------------------------------------------------------------------------------------------
	// Get and set methods for yearReleased.
	/**
	  * This public method will return the year film released as an integer. It doesn't take in any parameter.
	  * 
	  * @return yearReleased of type integer representing the year the film released.
	  * @author asen 18048443
	 **/
	public int getYearReleased() 
	{
		return yearReleased;
	}
	
	/**
	  * This public method takes in yearReleased as a parameter of type integer and saves it in variable this.yearReleased. 
	  * This method does not return any value.
	  * 
	  * @param yearReleased if type integer representing the year the film released.
	  * @author asen 18048443
	 **/
	public void setYearReleased(int yearReleased) 
	{
		this.yearReleased = yearReleased;
	} 
	
	//---------------------------------------------------------------------------------------------------------------------
	// Get and set methods for rating.
	/**
	  * This public method will return the film rating as a String. It doesn't take in any parameter.
	  * 
	  * @return rating of type String representing the film rating.
	  * @author asen 18048443
	 **/
	public String getRating() 
	{
		return rating;
	}
	
	/**
	  * This public method takes in rating as a parameter of type String and saves it in variable this.rating. 
	  * This method does not return any value.
	  * 
	  * @param rating of type String representing the film rating.
	  * @author asen 18048443
	 **/
	public void setRating(String rating) 
	{
		this.rating = rating;
	} 
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * 
	  * This public method overrides the toString method and returns a String representation of the film object.
	  * 
	  * @return String representation of film object.
	  * @author asen 18048443
	 **/
	public String toString()
	{
		if (this.rating.isEmpty() || this.rating.contains("Click to enter Rating..."))
		{
			this.rating = "Rating: Not Available";
		}
		
		String year = Integer.toString(this.yearReleased);
		
		if (this.yearReleased == 0)
		{
			year = "Release year: Not Available";
		}
			
		return this.title + " (" + year + "); " + this.rating;
	}
}