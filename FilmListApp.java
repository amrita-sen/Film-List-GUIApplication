package filmapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import filmapp.Film;
import filmapp.FilmList;
import filmapp.FilmListApp;
import filmapp.FilmListPanel;
import filmapp.ReadFilmListDataFile;
/**
  * 
  * @author Mark Lanthier (original)
  * @see <a href=
  *      "http://people.scs.carleton.ca/~lanthier/teaching/COMP1406/Notes/Code/Chapter5/">
  *      FilmListApp</a> 
  *
  * The Controller of the film app. It mediates between the model and view, and contains all event handling code. 
  *
  * @author asen 18048443 (edited)
 **/
public class FilmListApp extends JFrame
{
	private FilmList model;			// Stores a list of film objects.
	private FilmListPanel view;     // Maintains all JComponents to display the FilmList model.
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This private method handles the event of selection from a list.
	  * If a film is selected from an existing list of films displayed is selected, the remove button is enabled.
	  *   
	  * @author asen 18048443	 
	 **/
	private void eventHandleListSelection() 
	{
		//do not call update since that would re-initialise the list...
		//instead, specifically update the view of the remove button
		this.view.getRemoveButton().setEnabled(this.view.getFilmList().getSelectedIndex()!=-1);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This private method handles the event of releasing a key. If the text field is not empty, the Add button is enabled.
	  *   
	  * @param event is a KeyEvent.  
	  * @author asen 18048443	 
	 **/
	private void eventHandleKeyReleased(KeyEvent event)
	{
		this.view.getAddButton().setEnabled(!this.view.getFilmNameTextField().getText().isEmpty());
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This private method handles the event of clicking the add button. 
	  * If the text field is not empty, the Add button is enabled and the film title entered is added to the end of the film list.
	  * This method also saves the changes to the text file database after the new film has been added.
	  *   
	  * @author asen 18048443	 
	 **/
	private void eventHandleAddButton()
	{
		String text = this.view.getFilmNameTextField().getText().trim();
		String rating = this.view.getFilmRatingTextField().getText().trim();
		String year = this.view.getYearFilmReleasedTextField().getText().trim();
		int yearx = 0;

		try 
		{
			yearx = Integer.parseInt(year);			
		}
		catch(NumberFormatException e)
		{

		}
		Film myFilm = new Film(text,yearx, rating);
		
		if((!text.isEmpty()) && (!this.model.hasFilmTitles(myFilm)))
		{
			this.model.addFilm(myFilm);
			this.view.getFilmNameTextField().setText("Click to enter Film Title...");
			this.view.getFilmRatingTextField().setText("Click to enter Rating...");
			this.view.getYearFilmReleasedTextField().setText("Click to enter Release Year...");
			
			ReadFilmListDataFile.saveFilmList(model);
		} 
		
		this.view.update();	
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This private method handles the event of clicking the remove button. 
	  * It checks if the index of the selected film from the list is valid. Then removes the selected film from the film list and saves
	  * the changes in the text file database.
	  *   
	  * @author asen 18048443	 
	 **/
	private void eventHandleRemoveButton()
	{
		int index = this.view.getFilmList().getSelectedIndex();
		
		if(index != -1)
		{
			Film selectedFilm = this.view.getFilmList().getSelectedValue();
			
			this.model.removeFilmTitle(selectedFilm);		
			
			ReadFilmListDataFile.saveFilmList(model);
		} 
				
		this.view.update();
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This private method handles the event of clicking the search button. 
	  * It creates a new ArrayList from the film objects that have the search keyword in their titles.
	  * If, no films have the search keyword in their title, the ArrayList will be empty and a message box will pop-up informing
	  * the user that no matches were found.
	  * If the new ArrayList is not empty, the list of film objects will be displayed.
	  *   
	  * @author asen 18048443	 
	 **/
	private void eventHandleSearchButton()
	{
		String text = this.view.getFilmSearchTextField().getText().trim();

		Film f = new Film(text, 2000, "R");
		
		ArrayList<Film> searchList= this.model.searchFilm(f);
		
		if (searchList.isEmpty())
		{
			this.view.updateSearchMovie(searchList);
			JOptionPane.showMessageDialog(null, "No matches found!");			
		}
		else
		{
			this.view.updateSearchMovie(searchList);
		}	
	} 
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This private method handles the event of clicking the clear button. 
	  * It will clear the search field of the search field and set it to default text and populate the model with list loaded 
	  * from a text file. It also enables the add button.
	  *   
	  * @author asen 18048443	 
	 **/
	private void eventHandleClearButton()
	{
		this.view.getFilmSearchTextField().setText("Click and enter Film Title to search Films..."); 
		
		ReadFilmListDataFile.viewAllFilmList(this.model);
		this.view.update();
		this.view.getAddButton().setEnabled(true);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This private method handles the event of opening the film app window.
	  * It will display text as defined and display the default view by calling the update method from the FilmPanelClass. 
	  *   
	  * @author asen 18048443	 
	 **/
	private void eventHandleWindowOpened()
	{
		System.out.println("Window opened!");
		this.view.update();
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  * This private method handles the event of closing the film app window.
	  * It saves the text file database when the application exits and displays "Window closed" text to the console.
	  *   
	  * @author asen 18048443	 
	 **/
	private void eventHandleWindowClosed()
	{
		ReadFilmListDataFile.saveFilmList(model);
		System.out.println("Window closed!");
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/**
	  *  The constructor initialises the model and loads data from a text file database.
	  *  The view is constructed and added to the content pane.
	  *  All event handlers for the buttons,lists,fields etc are registered with the appropriate component. 
	  *  JFrame is fixed size and has no layout manager.
	  *  
	  *  @param name is a String representing the film title.
	  *  @author asen 18048443
	 **/
	public FilmListApp(String name) 
	{
		super(name);

		this.model =  new FilmList();
		ReadFilmListDataFile.viewAllFilmList(this.model);

		this.view = new FilmListPanel(this.model);
		
		// Event listeners for the view.
		this.view.getFilmNameTextField().addMouseListener(new MouseAdapter() {
		     
			/**
			  * This public method will clear the FilmNameTextField when a mouse is clicked on the field, to allow the user to type enter text.
			  *   
			  * @author asen 18048443	 
			 **/
			 public void mouseClicked(MouseEvent e) {
		    	  view.getFilmNameTextField().setText("");
		        }
		      });
		
		this.view.getFilmRatingTextField().addMouseListener(new MouseAdapter() {
			
			 /**
			   * This public method will clear the FilmRatingTextField when a mouse is clicked on the field, to allow the user to type enter text.
			   *   
			   * @author asen 18048443	 
			 **/  
			 public void mouseClicked(MouseEvent e) {
		    	  view.getFilmRatingTextField().setText("");
		        }
		      });
		
		this.view.getYearFilmReleasedTextField().addMouseListener(new MouseAdapter() {
		     
			 /**
			   * This public method will clear the YearFilmReleasedTextField when a mouse is clicked on the field, to allow the user to type enter text.
			   *   
			   * @author asen 18048443	 
			 **/
			 public void mouseClicked(MouseEvent e) {
		    	  view.getYearFilmReleasedTextField().setText("");
		        }
		      });
		
		this.view.getFilmSearchTextField().addMouseListener(new MouseAdapter() {
			  
			/**
			   * This public method will clear the FilmSearchTextFiel when a mouse is clicked on the field, to allow the user to type enter text.
			   *   
			   * @author asen 18048443	 
			 **/
		      public void mouseClicked(MouseEvent e) {
		    	  view.getFilmSearchTextField().setText("");
		        }
		      });

		this.view.getFilmNameTextField().addKeyListener(new KeyAdapter() {
			/**
			   * This public method takes in an event of type KeyEvent and calls the eventHandleKeyReleased method by
			   * passing in the event.
			   *   
			   * @param event is a KeyEvent.  
			   * @author asen 18048443	 
			 **/
			public void keyReleased(KeyEvent event) 
			{
				eventHandleKeyReleased(event);        
			}	
		});

		this.view.getRemoveButton().addActionListener(new ActionListener() {
			
			/**
			   * This public method overrides the method declaration in the super type and takes in an event of type 
			   * ActionEvent and calls the eventHandleRemoveButton method by.
			   * 			   
			   * @param event is a ActionEvent.  
			   * @author asen 18048443	 
			 **/
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				eventHandleRemoveButton();		
			}
		});

		this.view.getAddButton().addActionListener( new ActionListener() {
			
			/**
			   * This public method overrides the method declaration in the super type and takes in an event of type 
			   * ActionEvent and calls the eventHandleAddButton method by.
			   * 			   
			   * @param event is a ActionEvent.  
			   * @author asen 18048443	 
			 **/
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				eventHandleAddButton();		
			}
		});
		
		this.view.getSearchButton().addActionListener( new ActionListener() {
			
			/**
			   * This public method overrides the method declaration in the super type and takes in an event of type 
			   * ActionEvent and calls the eventHandleSearchButton method by.
			   * 			   
			   * @param event is a ActionEvent.  
			   * @author asen 18048443	 
			 **/
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				eventHandleSearchButton();		
			}
		}); 
		
		this.view.getClearButton().addActionListener( new ActionListener() {
			
			/**
			   * This public method overrides the method declaration in the super type and takes in an event of type 
			   * ActionEvent and calls the eventHandleClearButton method by.
			   * 			   
			   * @param event is a ActionEvent.  
			   * @author asen 18048443	 
			 **/
			@Override
			public void actionPerformed(ActionEvent event) 
			{
				eventHandleClearButton();		
			}
		});

		this.view.getFilmList().addListSelectionListener(new ListSelectionListener() {
			
			/**
			   * This public method overrides the method declaration in the super type and takes in an event of type 
			   * ListSelectionEvent and calls the eventHandleListSelection method by.
			   * 			   
			   * @param event is a SelectionEvent.  
			   * @author asen 18048443	 
			 **/
			@Override
			public void valueChanged(ListSelectionEvent event)
			{
				eventHandleListSelection();
			}
		});
		
		this.view.getFilmNameTextField().addFocusListener(new FocusListener() {
			
		/**
		   * This public method sets the state text of the FilmNameTextField if the focus is lost.
		   * If FilmNameTextField is empty, the text will be set to default text as specified.
		   * 			   
		   * @param event is a FocusEvent.  
		   * @author asen 18048443	 
		 **/
		public void focusLost(FocusEvent event)
		{			
			if(view.getFilmNameTextField().getText().trim().equals(""))
			{
				view.getFilmNameTextField().setText("Click to enter Film Title...");
			}
		}
		
		/**
		   * This public method sets the state text of the FilmNameTextField if the focus is gained.
		   * If FilmNameTextField has the specified text, FilmNameTextField will be cleared.
		   * 			   
		   * @param event is a FocusEvent.  
		   * @author asen 18048443	 
		 **/
		public void focusGained(FocusEvent event)
		{			
			if(view.getFilmNameTextField().getText().trim().equals("Click to enter Film Title..."))
			{
				view.getFilmNameTextField().setText("");
			}
		}
		}); 
		
		this.view.getFilmRatingTextField().addFocusListener(new FocusListener() {
			
			/**
			   * This public method sets the state text of the FilmRatingTextField if the focus is lost.
			   * If FilmRatingTextField is empty, the text will be set to default text as specified.
			   * 			   
			   * @param event is a FocusEvent.  
			   * @author asen 18048443	 
			 **/
			public void focusLost(FocusEvent event)
			{			
				if(view.getFilmRatingTextField().getText().trim().equals(""))
				{
					view.getFilmRatingTextField().setText("Click to enter Rating...");
				}
			}
			
			/**
			   * This public method sets the state text of the FilmRatingTextField if the focus is gained.
			   * If FilmRatingTextField has the specified text, FilmRatingTextField will be cleared.
			   * 			   
			   * @param event is a FocusEvent.  
			   * @author asen 18048443	 
			 **/
			public void focusGained(FocusEvent event)
			{			
				if(view.getFilmRatingTextField().getText().trim().equals("Click to enter Rating..."))
				{
					view.getFilmRatingTextField().setText("");
				}
			}
		} );
		
		this.view.getYearFilmReleasedTextField().addFocusListener(new FocusListener() {
			
			/**
			   * This public method sets the state text of the YearFilmReleasedTextField if the focus is lost.
			   * If YearFilmReleasedTextField is empty, the text will be set to default text as specified.
			   * 			   
			   * @param event is a FocusEvent.  
			   * @author asen 18048443	 
			 **/
			public void focusLost(FocusEvent event)
			{			
				if(view.getYearFilmReleasedTextField().getText().trim().equals(""))
				{
					view.getYearFilmReleasedTextField().setText("Click to enter Release Year...");
				}
			}
			
			/**
			   * This public method sets the state text of the YearFilmReleasedTextField if the focus is gained.
			   * If YearFilmReleasedTextField has the specified text, YearFilmReleasedTextField will be cleared.
			   * 			   
			   * @param event is a FocusEvent.  
			   * @author asen 18048443	 
			 **/
			public void focusGained(FocusEvent event)
			{			
				if(view.getYearFilmReleasedTextField().getText().trim().equals("Click to enter Release Year..."))
				{
					view.getYearFilmReleasedTextField().setText("");
				}
			}
		} );
		
		this.view.getFilmSearchTextField().addFocusListener(new FocusListener() {
			
			/**
			   * This public method sets the state text of the FilmSearchTextField if the focus is lost.
			   * If FilmSearchTextField is empty, the text will be set to default text as specified.
			   * 			   
			   * @param event is a FocusEvent.  
			   * @author asen 18048443	 
			 **/
			public void focusLost(FocusEvent event)
			{			
				if(view.getFilmSearchTextField().getText().trim().equals(""))
				{
					view.getFilmSearchTextField().setText("Click and enter Film Title to search Films...");
				}
			}
			
			/**
			   * This public method sets the state text of the FilmSearchTextField if the focus is gained.
			   * If FilmSearchTextField has the specified text, FilmSearchTextField will be cleared.
			   * 			   
			   * @param event is a FocusEvent.  
			   * @author asen 18048443	 
			 **/
			public void focusGained(FocusEvent event)
			{			
				if(view.getFilmSearchTextField().getText().trim().equals("Click and enter Film Title to search Films..."))
				{
					view.getFilmSearchTextField().setText("");
				}
			}
		} );
		
		this.addWindowListener(new WindowAdapter()
		{
			/**
			   * This public method overrides the method declaration in the super type and takes in an event of type 
			   * WindowEvent and calls the eventHandleWindowOpened method.
			   * 			   
			   * @param event is a WindowEvent.  
			   * @author asen 18048443	 
			 **/
			@Override
			public void windowOpened(WindowEvent event) 
			{
				eventHandleWindowOpened();
			}
			
			/**
			   * This public method takes in an event of type WindowEvent and calls the eventHandleWindowClosed method.			   * 
			   * 			   
			   * @param event is a WindowEvent.  
			   * @author asen 18048443	 
			 **/
			public void windowClosing(WindowEvent event) 
			{
				eventHandleWindowClosed();
			}			
		});
		
		// Choose to lay out components manually
		this.getContentPane().setLayout(null);
		
		//add the view to this content pane.
		this.getContentPane().add(this.view);

		// Set program to stop when window closed
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(323, 350); // manually computed sizes
		setResizable(false);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	/***
	 * This is the main method of the film app.that creates a new JFrame using the FilmListApp constructor and sets the JFrame
	 * focusable window state and visibility to default values.
	 * 
	 * @param args Unused. 
	 * @author asen 18048443	 
	 */
	public static void main(String[] args) 
	{
		JFrame frame = new FilmListApp("My Film List Application");
		frame.setFocusableWindowState(false); 
		frame.setVisible(true);
		frame.setFocusableWindowState(true);		
	}
}

