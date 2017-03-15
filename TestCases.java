
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCases {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://cs1632ex.herokuapp.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /*
    This test will look to see if the element that holds the pictures of
    the cathedral is an ordered list 'ol'.
     */
    @Test
    public void testCathedralPicsOrderedList() throws Exception {
        driver.get(baseUrl + "/cathy");
        driver.findElement(By.linkText("Cathedral Pics")).click();
        assertTrue(isElementPresent(By.cssSelector("ol > li")));
    }

    /*
    This test will see if the factorial program would produce the correct output on a normal number.
    The input in this test was 5.
     */
    @Test
    public void testFactorialResult() throws Exception {
        driver.get(baseUrl + "/fib");
        driver.findElement(By.linkText("Factorial")).click();
        driver.findElement(By.name("value")).clear();
        driver.findElement(By.name("value")).sendKeys("5");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        assertEquals("Factorial of 5 is 120!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    This test will see if the Factorial program would output the correct value on the edge case of 1.
     */
    @Test
    public void testFactorialResult1() throws Exception {
        driver.get(baseUrl + "/fact");
        driver.findElement(By.linkText("Factorial")).click();
        driver.findElement(By.name("value")).clear();
        driver.findElement(By.name("value")).sendKeys("1");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        assertEquals("Factorial of 1 is 1!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    This test will see if the factorial program would output the correct value on the edge case of 100.
     */
    @Test
    public void testFactorialResult100() throws Exception {
        driver.get(baseUrl + "/fact");
        driver.findElement(By.linkText("Factorial")).click();
        driver.findElement(By.name("value")).clear();
        driver.findElement(By.name("value")).sendKeys("100");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        try {
            assertEquals("Factorial of 100 is 93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000!", driver.findElement(By.cssSelector("h2")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /*
    This test will see if the Factorial program would output the correct default output if given an invalid decimal string.
    This test finds a defect and will fail.
     */
    @Test
    public void testFactorialResultDecimal() throws Exception {
        driver.get(baseUrl + "/fact");
        driver.findElement(By.linkText("Factorial")).click();
        driver.findElement(By.name("value")).clear();
        driver.findElement(By.name("value")).sendKeys("5.1");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        assertEquals("Factorial of 5.1 is 1!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    This test will see if the Factorial program would output the correct default output if given a number above 100.
    The input of this program was 101.
     */
    @Test
    public void testFactorialResultGreaterThan100() throws Exception {
        driver.get(baseUrl + "/fact");
        driver.findElement(By.linkText("Factorial")).click();
        driver.findElement(By.name("value")).clear();
        driver.findElement(By.name("value")).sendKeys("101");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        assertEquals("Factorial of 101 is 1!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    This test will see if the Factorial program would output the correct default value if given a number less than 1.
    The input for the program was -1.
     */
    @Test
    public void testFactorialResultLessThan1() throws Exception {
        driver.get(baseUrl + "/factres/?value=101");
        driver.findElement(By.linkText("Factorial")).click();
        driver.findElement(By.name("value")).clear();
        driver.findElement(By.name("value")).sendKeys("-1");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        assertEquals("Factorial of -1 is 1!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    This test will see if the Fibonacci program would output the correct value when passing a number between 1-100.
    The number passed into the program was 5
     */
    @Test
    public void testFibonacciResult() throws Exception {
        driver.get(baseUrl + "/fib");
        driver.findElement(By.linkText("Fibonacci")).click();
        driver.findElement(By.id("tb1")).clear();
        driver.findElement(By.id("tb1")).sendKeys("5");
        driver.findElement(By.id("sub")).click();
        assertEquals("Fibonacci of 5 is 8!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    This test will see if the Fibonacci program can handle a decimal value and output the correct output.
    The number passed into the program was 10.1
    This causes the test to fail because this is a defect.
     */
    @Test
    public void testFibonacciResultDecimal() throws Exception {
        driver.get(baseUrl + "/fib");
        driver.findElement(By.linkText("Fibonacci")).click();
        driver.findElement(By.id("tb1")).clear();
        driver.findElement(By.id("tb1")).sendKeys("10.1");
        driver.findElement(By.id("sub")).click();
        assertEquals("Fibonacci of 10.1 is 1!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    Tests to see if the Fibonacci program would output the correct value when passing in the edge case of 1.
     */
    @Test
    public void testFibonacciResultEdgeCase1() throws Exception {
        driver.get(baseUrl + "/fib");
        driver.findElement(By.linkText("Fibonacci")).click();
        driver.findElement(By.id("tb1")).clear();
        driver.findElement(By.id("tb1")).sendKeys("1");
        driver.findElement(By.id("sub")).click();
        assertEquals("Fibonacci of 1 is 1!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    Tests to see if the Fibonacci program would output the correct value when passing in the edge case of 100
     */
    @Test
    public void testFibonacciResultEdgeCase100() throws Exception {
        driver.get(baseUrl + "/fib");
        driver.findElement(By.linkText("Fibonacci")).click();
        driver.findElement(By.id("tb1")).clear();
        driver.findElement(By.id("tb1")).sendKeys("100");
        driver.findElement(By.id("sub")).click();
        assertEquals("Fibonacci of 100 is 354224848179261915075!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    Tests to see if passing a string into the argument for the hello program would produce the correct output.
    The string passed into the program was Alex
     */
    @Test
    public void testHelloAddedName() throws Exception {
        driver.get("https://cs1632ex.herokuapp.com/hello/Alex");
        assertEquals("Hello CS1632, from Alex!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    Tests to see if passing a string starting with % into the hello program would produce the correct output.
    The string passed into the program was %Alex
    This will result in a fail. This is a defect in the hello program.
     */
    @Test
    public void testHelloAddedPercentName() throws Exception {
        driver.get("https://cs1632ex.herokuapp.com/hello/%Alex");
        assertEquals("Hello CS1632, from %Alex!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    This tests the hello program with no arguments passed into it. The output should be the default.
     */
    @Test
    public void testHelloTextNoArguments() throws Exception {
        driver.get(baseUrl + "/hello");
        driver.findElement(By.linkText("Hello")).click();
        assertEquals("Hello CS1632, from Prof. Laboon!", driver.findElement(By.cssSelector("h2")).getText());
    }

    /*
    This test checks the home page to see if the "Used for CS1632 Software Quality Assurance, taught by Bill Laboon" is
    present on the webpage
     */
    @Test
    public void testHomePageLaboonText() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.linkText("CS1632 D3 Home")).click();
        try {
            assertEquals("Used for CS1632 Software Quality Assurance, taught by Bill Laboon", driver.findElement(By.cssSelector("div.row > p.lead")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /*
    This test looks for the string "Welcome, friend, to a land of pure calculation" on the home page.
     */
    @Test
    public void testHomePageWelcomeText() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.linkText("CS1632 D3 Home")).click();
        try {
            assertTrue(driver.findElement(By.cssSelector("p.lead")).getText().matches("^Welcome, friend,\nto a land of pure calculation\\.[\\s\\S]*$"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /*
    This test looks to see if the links/tabs on the Cathedral page are correct.
     */
    @Test
    public void testVerifyCathedralPicsPageLink() throws Exception {
        driver.get(baseUrl + "/cathy");
        assertEquals("CS1632 D3 Home", driver.findElement(By.linkText("CS1632 D3 Home")).getText());
        assertEquals("Factorial", driver.findElement(By.linkText("Factorial")).getText());
        assertEquals("Fibonacci", driver.findElement(By.linkText("Fibonacci")).getText());
        assertEquals("Hello", driver.findElement(By.linkText("Hello")).getText());
        assertEquals("Cathedral Pics", driver.findElement(By.linkText("Cathedral Pics")).getText());
    }

    /*
    This test looks to see if the links/tabs on the Factorial page are correct.
     */
    @Test
    public void testVerifyFactorialPageLink() throws Exception {
        driver.get(baseUrl + "/fact");
        assertEquals("CS1632 D3 Home", driver.findElement(By.linkText("CS1632 D3 Home")).getText());
        assertEquals("Factorial", driver.findElement(By.linkText("Factorial")).getText());
        assertEquals("Fibonacci", driver.findElement(By.linkText("Fibonacci")).getText());
        assertEquals("Hello", driver.findElement(By.linkText("Hello")).getText());
        assertEquals("Cathedral Pics", driver.findElement(By.linkText("Cathedral Pics")).getText());
    }

    /*
    This test looks to see if the links/tabs on the Fibonacci page are correct.
     */
    @Test
    public void testVerifyFibonacciPageLink() throws Exception {
        driver.get(baseUrl + "/fib");
        assertEquals("CS1632 D3 Home", driver.findElement(By.linkText("CS1632 D3 Home")).getText());
        assertEquals("Factorial", driver.findElement(By.linkText("Factorial")).getText());
        assertEquals("Fibonacci", driver.findElement(By.linkText("Fibonacci")).getText());
        assertEquals("Hello", driver.findElement(By.linkText("Hello")).getText());
        assertEquals("Cathedral Pics", driver.findElement(By.linkText("Cathedral Pics")).getText());
    }

    /*
    This test looks to see if the links/tabs on the Hello page are correct.
     */
    @Test
    public void testVerifyHelloPageLink() throws Exception {
        driver.get(baseUrl + "/hello");
        assertEquals("CS1632 D3 Home", driver.findElement(By.linkText("CS1632 D3 Home")).getText());
        assertEquals("Factorial", driver.findElement(By.linkText("Factorial")).getText());
        assertEquals("Fibonacci", driver.findElement(By.linkText("Fibonacci")).getText());
        assertEquals("Hello", driver.findElement(By.linkText("Hello")).getText());
        assertEquals("Cathedral Pics", driver.findElement(By.linkText("Cathedral Pics")).getText());
    }

    /*
    This test looks to see if the links/tabs on the Home page are correct.
     */
    @Test
    public void testVerifyHomePageLink() throws Exception {
        driver.get(baseUrl + "/");
        assertEquals("CS1632 D3 Home", driver.findElement(By.linkText("CS1632 D3 Home")).getText());
        assertEquals("Factorial", driver.findElement(By.linkText("Factorial")).getText());
        assertEquals("Fibonacci", driver.findElement(By.linkText("Fibonacci")).getText());
        assertEquals("Hello", driver.findElement(By.linkText("Hello")).getText());
        assertEquals("Cathedral Pics", driver.findElement(By.linkText("Cathedral Pics")).getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
