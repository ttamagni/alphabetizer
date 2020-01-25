package edu.vt.nis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AlphabetizerTest {

  @Test
  void case_sensitive_sort() {

    StringHelper[] helper = {
      new StringHelper("123Hokies", "123Heikos"),
      new StringHelper("3 Blind Mice", "3BMcdeiiln")
    };
    
    boolean isCaseSensitive = true;
    String pattern = null;

    run_test(helper, isCaseSensitive, pattern);
  
  }
  
  @Test
  void case_insensitive_sort_test() {

    StringHelper[] helper = {
      new StringHelper("123Hokies", "123eHikos"),
      new StringHelper("3 Blind Mice", "3BcdeiilMn")
    };
    
    boolean isCaseSensitive = false;
    String pattern = null;

    run_test(helper, isCaseSensitive, pattern);
  
  }
  
  @Test
  void exclude_chars_case_sensitive_sort_test() {

    StringHelper[] helper = {
      new StringHelper("123Hokies", "Heikos"),
      new StringHelper("3 Blind Mice", "BMcdeiiln")
    };
    
    boolean isCaseSensitive = true;
    String pattern = "[^A-Za-z]";

    run_test(helper, isCaseSensitive, pattern);
  
  }

  @Test
  void exclude_chars_case_insensitive_sort_test() {

    StringHelper[] helper = {
      new StringHelper("123Hokies", "eHikos"),
      new StringHelper("3 Blind Mice", "BcdeiilMn")
    };
    
    boolean isCaseSensitive = false;
    String pattern = "[^A-Za-z]";

    run_test(helper, isCaseSensitive, pattern);
  
  }

  @Test
  void exclude_whitespace_sort_test() {

    StringHelper[] helper = {
      new StringHelper("   123  Hokies   ", "123Heikos"),
      new StringHelper("   3 Blind Mice  ", "3BMcdeiiln")
    };
    
    boolean isCaseSensitive = true;
    String pattern = null; 

    run_test(helper, isCaseSensitive, pattern);
  
  }

  @Test
  void exclude_numbers_sort_test() {

    StringHelper[] helper = {
      new StringHelper("!0123Ho456kies789$", "!$Heikos"),
      new StringHelper("#3 Blind000Mice*999", "#*BMcdeiiln")
    };
    
    boolean isCaseSensitive = true;
    String pattern = "[0-9]"; 

    run_test(helper, isCaseSensitive, pattern);
  
  }
  
  @Test
  void alphanumberic_chars_sort_test() {
    StringHelper[] helper = {
      new StringHelper("!0123Ho456kies789$", "0123456789Heikos"),
      new StringHelper("#3 Blind000Mice*999", "0003999BMcdeiiln")
    };
    
    boolean isCaseSensitive = true;
    String pattern = "[^a-zA-Z0-9]";

    run_test(helper, isCaseSensitive, pattern);
  
  }
  
  @Test
  void set_case_sensitivity_test() {
    Alphabetizer a = new Alphabetizer("dummy string");
    a.setCaseSensitive(true);
    
    assertTrue(a.isCaseSensitive());
  
    a.setCaseSensitive(false);

    assertFalse(a.isCaseSensitive());
  }

  @Test
  void set_chars_to_ignore_test() {
    Alphabetizer a = new Alphabetizer("dummy string");
    a.setCharsToIgnore("dummy pattern");
    
    assertEquals("dummy pattern", a.getCharsToIgnore());

  
  }
  
  
  private void run_test(final StringHelper[] helper,
          final boolean isCaseSensitive,
          final String pattern) {
  
    for (StringHelper str : helper) {

      Alphabetizer a = new Alphabetizer(str.getTestString());
      a.setCaseSensitive(isCaseSensitive);
    
      if (pattern != null) {
        a.setCharsToIgnore(pattern);
      }
    
      assertEquals(str.getExpectedString(), a.sort());  
    }
  
  }
  
  
  class StringHelper {

    private String testString;
    private String expectedString;

    public StringHelper(String test, String expected) {
    
      this.testString = test;
      this.expectedString = expected;
    }

    public String getTestString() {
      return testString;
    }

    public void setTestString(String testString) {
      this.testString = testString;
    }

    public String getExpectedString() {
      return expectedString;
    }

    public void setExpectedString(String expectedString) {
      this.expectedString = expectedString;
    }
  }

}
