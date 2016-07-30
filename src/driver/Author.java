//**********************************************************
//Assignment3:
//UTORID user_name:kushtovm 
//
//Author:Magomed-Lors Kushtov
//
//
//Honor Code: I pledge that this program represents my own
//program code and that I have coded on my own. I received
//help from no one in designing and debugging my program.
//*********************************************************

package driver;

import java.util.ArrayList;
import java.util.List;


/*
 * This program extracts and stores all of the contents of the wanted Author.
 * When creating an instance, the user inputs the HTML file from which 
 * to extract the information from. The different methods then parse the data
 * using regular expressions and stores the relevant information into the 
 * proper variables
 */
public class Author {
  WriteToConsole printObject;
  WriteToFile printFileObject;
  String rawHTMLString;
    
  List<String> authorName;
  List<String> numberOfCitations;
  List<String> i10IndexAfter2009;
  List<String> topPublications;
  List<String> coAuthorsList;
  static List<String> totalCoAuthors = new ArrayList<String>();
  static int numberOfTotalCoAuthors = 0;
  
  ExtractAuthorName name;
  ExtractNumberOfCitations citations;
  ExtractTopPublications publications;
  ExtractITenIndex itenIndex;
  ExtractTotalCitations totalPaperCitations;
  ExtractCoAuthors coAuthors;
  
  int numberOfCoAuthors;
  int totalCitations = 0;
  /*
   * Constructor that creates the author instance
   */
  public Author(String AuthorUrlString) throws Exception {
    
    name = new ExtractAuthorName(AuthorUrlString);
    this.authorName = name.extract();
    
    citations = new ExtractNumberOfCitations(AuthorUrlString);
    this.numberOfCitations = citations.extract();
    
    publications = new ExtractTopPublications(AuthorUrlString);
    this.topPublications = publications.extract();
    
    itenIndex = new ExtractITenIndex(AuthorUrlString);
    this.i10IndexAfter2009 = itenIndex.extract();

    totalPaperCitations = new ExtractTotalCitations(AuthorUrlString);
    for(String cts : totalPaperCitations.extract()) {
      this.totalCitations = (this.totalCitations + Integer.valueOf(cts));
    }
    
    coAuthors = new ExtractCoAuthors(AuthorUrlString);
    this.coAuthorsList = coAuthors.extract();
    this.numberOfCoAuthors = coAuthorsList.size();

    numberOfTotalCoAuthors = numberOfTotalCoAuthors + numberOfCoAuthors;
    
    totalCoAuthors.addAll(coAuthorsList);
    java.util.Collections.sort(totalCoAuthors);
    
    
    this.printObject = new WriteToConsole(this);
    
  }
 
}