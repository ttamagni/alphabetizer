package edu.vt.nis;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that can alphabetically sort the characters within a string.
 *
 * @author <a href="mailto:ttamagni@gmail.com">TJ Tamagni</a> 
 */
public class Alphabetizer {

  /**
   * The string that will be alphabetized.
   */
  private final String alphaString;

  /**
   * Indicates whether the alphabetizer cares about the case of the string.
   */
  private boolean isCaseSensitive = true;

  /**
   * Regexp pattern used to eliminate certain characters when sorting.
   */
  private String charsToIgnore;
  

  /**
   * Constructs a new Alphabetizer object.
   *
   * @param inputString The string to be alphabetized
   */
  public Alphabetizer(String inputString) {

    //Trim all trailing and leading whitespace.
    this.alphaString = inputString.trim();

  }

  /**
   * Method that will alphabetize the characters of a string.
   *  
   * @return A string whose characters have been alphabetized.
   */
  public String sort() {

    String stringToSort = this.alphaString;

    if (charsToIgnore != null) {
      stringToSort = stripChars(this.alphaString);
    }

    //Can only do this in Java 8 and above
    List<Character> lst = stringToSort.chars()
        .mapToObj(e -> (char)e).collect(Collectors.toList());

    //Create anonymous inner class to compare the characters
    lst.sort(new Comparator<Character>() {

      boolean isCaseSensitive = true;

      /**
       * Method used to compare two characters.
       * 
       * @return The value 0 if the argument string is equal to this string; 
       *     a value less than 0 if this string is lexicographically less than
       *     the string argument; and a value greater than 0 if this string
       *     is lexicographically greater than the string argument.
       */
      public int compare(Character c1, Character c2) {

        String s1 = Character.toString(c1);
        String s2 = Character.toString(c2);

        if (!this.isCaseSensitive) {
          s1 = s1.toLowerCase();
          s2 = s2.toLowerCase();
        }

        return s1.compareTo(s2);
      }

      /**
       * Setter method of the isCaseSensistive class variable.
       *
       * @param isCaseSensitive Should the compare method use case 
       *     sensitivity when comparing two characters.
       * @return Comparator&lt;Character&gt; object containing case sensitivity
       *     rules
       */
      public Comparator<Character> setParams(boolean isCaseSensitive) {
       
        this.isCaseSensitive = isCaseSensitive;
        return this;
      }

    }.setParams(this.isCaseSensitive));

    StringBuilder str = new StringBuilder();

    //Loop through the sorted list and add the characters to form an alphabetized string.
    for (char c : lst) {
      str.append(c);
    }

    //Trim string to ignore any whitespace that may have been contained in string
    return str.toString().trim();
  }

  /**
   * Method to strip out any characters not intended to be used.
   *
   * @param str The source string
   * @return String containing only alpha characters 
   */
  private String stripChars(String str) {

    return str.replaceAll(this.charsToIgnore, "");
  }

  /**
   * Get value of {@link edu.vt.nis.Alphabetizer#isCaseSensitive}.
   *
   * @return Class variable isCaseSensitive
   */
  public boolean isCaseSensitive() {
    return isCaseSensitive;
  }

  /**
   * Sets value of {@link edu.vt.nis.Alphabetizer#isCaseSensitive}.
   *
   * @param isCaseSensitive Indicates if the alphabetizer should
   *     worry about case
   */
  public void setCaseSensitive(final boolean isCaseSensitive) {
    this.isCaseSensitive = isCaseSensitive;
  }

  /**
   * Get value of {@link edu.vt.nis.Alphabetizer#charsToIgnore}.
   *
   * @return Class variable charsToIgnore
   */
  public String getCharsToIgnore() {
    return charsToIgnore;
  }

  /**
   * Sets value of {@link edu.vt.nis.Alphabetizer#charsToIgnore}.
   *
   * @param pattern Indicates the regexp pattern used when ignoring
   *     characters
   */
  public void setCharsToIgnore(final String pattern) {
    this.charsToIgnore = pattern;
  }

}
