package filmapp;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import filmapp.Film;
import filmapp.FilmList;

/**
  * The View of the film app. Maintains all JComponents to display the FilmList model.
  * Each button/field etc has a get method for access outside of the FilmListPanel class.
  * 
  * @author asen 18048443
 **/
public class FilmListPanel extends JPanel
{
	private FilmList model; // Stores a list of film objects.
	
	// All JComponents to display the FilmList model.
	private JList<Film> filmList;		
	
	private JTextField filmNameInput;			 
	private JTextField yearFilmReleasedInput;   
	private JTextField filmRatingInput;          
	private JTextField filmSearchInput;	       
	
	private JButton addButton;                  
	private JButton removeButton;               
	private JButton searchButton;               
	private JButton clearButton;                 

	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method returns the filmList, a JList of Film objects. It doesn't take in any parameter.
	  * 
	  * @return this.filmList, a JList of Film objects that displays a list of films and allows the user to select a film from the list.
	  * @author asen 18048443
	 **/
	public JList<Film> getFilmList()
	{
		return this.filmList;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method returns the text field for the film title text input. It doesn't take in any parameter.
	  * 
	  * @return this.filmNameInput, a JTextField that allows the user to type in and edit a single line of text representing the film title.
	  * @author asen 18048443
	 **/
	public JTextField getFilmNameTextField()
	{
		return this.filmNameInput;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method returns the text field for the year the film released text input. It doesn't take in any parameter.
	  * 
	  * @return this.yearFilmReleasedInput, a JTextField that allows the user to type in and edit a year, in a single line, representing the film release year.
	  * @author asen 18048443
	 **/
	public JTextField getYearFilmReleasedTextField()
	{
		return this.yearFilmReleasedInput;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * his public method returns the text field for film rating text input. It doesn't take in any parameter.
	  * 
	  * @return this.filmRatingInput, a JTextField that allows the user to type in and edit a single line of text representing the film rating.
	  * @author asen 18048443
	 **/
	public JTextField getFilmRatingTextField()
	{
		return this.filmRatingInput;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method returns the text field for search keyword input. It doesn't take in any parameter.
	  * 
	  * @return this.filmSearchInput, a JTextField that allows the user to type in and edit a single line of text representing the search keyword. 
	  * @author asen 18048443
	 **/
	public JTextField getFilmSearchTextField()
	{
		return this.filmSearchInput;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method returns a JButton, an implementation of a push button that will be configured to add a film object 
	  * to the existing list of Film objects, when clicked (pushed). It doesn't take in any parameter.
	  * 
	  * @return this.addButton is a JButton, an implementation of a push button that will be configured to add a film object to the existing list when clicked (pushed).
	  * @author asen 18048443
	 **/
	public JButton getAddButton()
	{
		return this.addButton;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method returns a JButton, an implementation of a push button that will be configured to remove a film object 
	  * from the existing list of Film objects, when clicked (pushed). It doesn't take in any parameter.
	  * 
	  * @return this.removeButton is a JButton, an implementation of a push button that will be configured to remove a film object from the existing list when clicked (pushed).
	  * @author asen 18048443
	 **/
	public JButton getRemoveButton()
	{
		return this.removeButton;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method returns a JButton, an implementation of a push button that will be configured to search the list of films and 
	  * display the search results, based on a search keyword, when clicked (pushed). It doesn't take in any parameter.
	  * 
	  * @return this.searchButton is a JButton, an implementation of a push button that will be configured to search the film list and display search results, when clicked (pushed).
	  * @author asen 18048443
	 **/
	public JButton getSearchButton()
	{
		return this.searchButton;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This public method returns a JButton, an implementation of a push button that will be configured to clear the search text field and 
	  * display the list of films in the database, when clicked (pushed). It doesn't take in any parameter.
	  * 
	  * @return this.clearButton is a JButton, an implementation of a push button that will be configured to clear the search text field and display list of films in the database, when clicked (pushed). 
	  * @author asen 18048443
	 **/
	public JButton getClearButton()
	{
		return this.clearButton;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	// Constructor of FilmListPanel object.
	/**
	  * This constructor takes in a model, a FilmList object and sets values for the components of the Film List Panel.
	  * It also sets the values for the Scroll Panel, JTextFields and JButtons.
	  * 
	  * @param model is a FilmList, and maintains a list of Film objects. 
	  * @author asen 18048443
	 **/
	public FilmListPanel(FilmList model)
	{
		this.model = model; // set the model.

		setLayout(null); // Choose to lay out components manually

		this.filmList = new JList(this.model.getFilmList());

		this.filmList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(filmList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setLocation(10, 175);
		scrollPane.setSize(290, 120);

		this.add(scrollPane);

		// Add the film name input text field...
		this.filmNameInput = new JTextField("Click to enter Film Title...");		
		this.filmNameInput.setLocation(10, 10);
		this.filmNameInput.setSize(290, 25);
		this.add(filmNameInput);

		// Add the year film released input text field...
		this.yearFilmReleasedInput = new JTextField("Click to enter Release Year...");
		this.yearFilmReleasedInput.setLocation(10, 40);
		this.yearFilmReleasedInput.setSize(160, 25);
		this.add(yearFilmReleasedInput);
		
		// Add the film rating input text field...
		this.filmRatingInput = new JTextField("Click to enter Rating...");
		this.filmRatingInput.setLocation(175, 40);
		this.filmRatingInput.setSize(125, 25);
		this.add(filmRatingInput);
		
		// Add the film search input text field...
		this.filmSearchInput = new JTextField("Click and enter Film Title to search Films...");
		this.filmSearchInput.setLocation(10, 108);
		this.filmSearchInput.setSize(290, 25);
		this.add(filmSearchInput);

		// Add the ADD button
		this.addButton = new JButton("Add"); 
		addButton.setLocation(10, 70);
		addButton.setSize(90, 25);
		this.add(addButton);

		// Add the REMOVE button
		removeButton = new JButton("Remove");   
		removeButton.setLocation(110, 70);
		removeButton.setSize(90, 25);
		this.add(removeButton);

		// Add the SEARCH button
		this.searchButton = new JButton("Search"); 
		searchButton.setLocation(10, 47 + 37 * 2 + 18);
		searchButton.setSize(90, 25);
		this.add(searchButton);
		
		// Add the CLEAR button.
		this.clearButton = new JButton("Clear Search");
		clearButton.setLocation(110, 47 + 37 * 2 + 18);
		clearButton.setSize(110, 25);
		this.add(clearButton);

		setSize(300, 300); // manually computed sizes
		this.update();
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * The update method is called by most event handler methods in the FilmListApp.
	  * When called, the updated film list is displayed and the remove button is set to enabled when a film in the film list
	  * is selected.
	  * This method does not take in any parameter nor does it return any value.
	  * 
	  * @author asen 18048443	 
	 **/ 
	public void update()
	{
		this.filmList.setListData(this.model.getFilmList());		
		this.removeButton.setEnabled(this.filmList.getSelectedIndex() != -1);	
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This update method takes in filmList, and ArrayList of Films and is called by the eventHandleSearchButton() method in 
	  * the FilmListApp. When called, it instantiates a new ArrayList of Films, newModel, the new read-only film list is 
	  * constructed from an array of film objects in the new FilmList object and the add button is disabled.
	  * This method does not return any value.
	  * 
	  * @param filmList, an ArrayList of Film objects.	 
	  * @author asen 18048443	 
	 **/
	public void updateSearchMovie(ArrayList<Film> filmList)
	{
		FilmList newModel = new FilmList(filmList);
		this.filmList.setListData(newModel.getFilmList());		
		this.addButton.setEnabled(false);
	}
}