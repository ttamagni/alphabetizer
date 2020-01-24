package edu.vt.nis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class containing main method to test {@link edu.vt.nis.Alphabetizer} class.
 *
 * @author <a href="mailto:ttamagni@gmail.com">TJ Tamagni</a>
 *
 */
public class RunAlphabetizer {

  /**
   * Main execution method. Reads in a series of strings and outputs
   * the strings with the characters in alphabetical order.
   *
   * @param args Command line arguments contained in a string array
   */
  public static void main(String[] args) {

    //Regexp pattern that matches all non-alpha characters.
    String pattern = "[^A-Za-z]";
 
    BufferedReader reader =
        new BufferedReader(new InputStreamReader(System.in));

    String inputString = null;
    
    //TO-DO: Validate string length
    
    System.out.println("\nEnter a string. Type \"exit\" to quit\n");
    
    try {
      while ((inputString = reader.readLine()) != null) {

        if (inputString.equalsIgnoreCase("EXIT")) {
          break;
        }

        Alphabetizer a = new Alphabetizer(inputString);
        a.setCaseSensitive(false);
        a.setCharsToIgnore(pattern);

        System.out.println(a.sort());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }

}
