package pl.brzozowski.maciej.clis.utilities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindIContentTest {
    private FindIContent findIContent;

    @Before
    public void setup() {
        findIContent = new FindIContent();
    }

    @Test
    public void shouldFindStringByRegex() throws Exception {
        String response = "1.0 meters = 123 inches";
        String result = findIContent.findStringByRegex(response, 1, "meters", "inches");
        assertEquals("123", result);
    }


    @Test
    public void shouldFindStringByRegexAndReturnEmptyString() throws Exception {
        String response = "fgagvafgag  ";
        String result = findIContent.findStringByRegex(response, 1, "meters", "inches");
        assertEquals("", result);
    }

}