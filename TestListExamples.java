import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  @Override
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

class StringOrder implements StringChecker {
  @Override
  public boolean checkString(String s) {
    for (int i = 0; i < s.length() - 1; i++)
      if (s.charAt(i) > s.charAt(i + 1)) return false;
    return true;
  }
}

public class TestListExamples {
  IsMoon ism;
  StringOrder strord;
  List<String> emptyList;
  List<String> sorted1;
  List<String> sorted2;
  List<String> expected;
  List<String> beforeFilter1;
  List<String> afterFilter1;
  List<String> beforeFilter2;
  List<String> afterFilter2;

  @Before
  public void setUp() {
    ism = new IsMoon();
    strord = new StringOrder();

    emptyList = Arrays.asList();
    sorted1 = Arrays.asList("a", "c", "e", "f", "h");
    sorted2 = Arrays.asList("a", "b", "d", "g", "i");
    expected = Arrays.asList("a", "a", "b", "c", "d", "e", "f",  "g", "h", "i");
    beforeFilter1 = Arrays.asList("star", "moon", "earth", "sun", "jupiter", "moon");
    afterFilter1 = Arrays.asList("moon", "moon");
    beforeFilter2 = Arrays.asList("abc", "abd", "bca", "bcd", "bdc", "cad", "cba", "cde");
    afterFilter2 = Arrays.asList("abc", "abd", "bcd", "cde");
  }

  @Test(timeout = 500)
  public void testMergeBothEmpty() {
    List<String> merged = ListExamples.merge(emptyList, emptyList);
    assertEquals(merged, emptyList);
  }

  @Test(timeout = 500)
  public void testMergeOneEmpty1() {
    List<String> merged1 = ListExamples.merge(sorted1, emptyList);
    assertEquals(merged1, sorted1);
  }

  @Test(timeout = 500)
  public void testMergeOneEmpty2() {
    List<String> merged2 = ListExamples.merge(emptyList, sorted1);
    assertEquals(merged2, sorted1);
  }

  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeComplex1() {
    List<String> merged1 = ListExamples.merge(sorted1, sorted2);
    assertEquals(merged1, expected);
  }

  @Test(timeout = 500)
  public void testMergeComplex2() {
    List<String> merged2 = ListExamples.merge(sorted2, sorted1);
    assertEquals(merged2, expected);
  }

  @Test(timeout = 500)
  public void testFilterEmpty() {
    List<String> filtered = ListExamples.filter(emptyList, ism);
    assertEquals(filtered, emptyList);
  }

  @Test(timeout = 500)
  public void testFilterMoon() {
    List<String> filtered = ListExamples.filter(beforeFilter1, ism);
    assertEquals(filtered, afterFilter1);
  }

  @Test(timeout = 500)
  public void testFilterOrder() {
    List<String> filtered = ListExamples.filter(beforeFilter2, strord);
    assertEquals(filtered, afterFilter2);
  }
}
