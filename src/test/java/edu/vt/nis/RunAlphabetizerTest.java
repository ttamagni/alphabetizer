package edu.vt.nis;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsNot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(RunAlphabetizer.class)
public class RunAlphabetizerTest {

  @Mock
  private BufferedReader bufferedReader;
  
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  
  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
  }


  @Test
  public void test_main() throws Exception {
  
    PowerMockito.whenNew(BufferedReader.class).withAnyArguments().thenReturn(bufferedReader);

    PowerMockito.when(bufferedReader.readLine()).thenReturn("123Hokies").thenReturn(null);

    RunAlphabetizer.main(new String[] { });
  
    org.hamcrest.MatcherAssert.assertThat(
        outContent.toString(), 
        CoreMatchers.containsString("eHikos")
    );

    org.hamcrest.MatcherAssert.assertThat(
        outContent.toString(),
        IsNot.not(CoreMatchers.containsString("123eHikos"))
    );
    
  }

}
