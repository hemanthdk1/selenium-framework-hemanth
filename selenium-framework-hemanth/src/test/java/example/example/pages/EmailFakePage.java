package example.example.pages;

import example.example.context.WebDriverContext;
import example.example.factory.PageinstancesFactory;
import example.example.util.ReportUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * The Class represents GooglePage.
 *
 *
 */
public class EmailFakePage extends BasePage {

	ArrayList<String> windowHandles;
	public static String emailFakeEmail;
	public static String activationCode;

	@FindBy(xpath = "//span[@id='email_ch_text']")
	private WebElement emailID;

	@FindBy(xpath = "//td[normalize-space()='Verify your account with the activation code below']/parent::tr/following-sibling::tr/td")
	private WebElement activationCodeTxt;

	public EmailFakePage(WebDriver driver) {
		super(driver);
	}


	public void navigateToEmailFake() {
		((JavascriptExecutor)WebDriverContext.getDriver()).executeScript("window.open()");

		windowHandles = new ArrayList<>(WebDriverContext.getDriver().getWindowHandles());
		WebDriverContext.getDriver().switchTo().window(windowHandles.get(1));
		WebDriverContext.getDriver().get("https://emailfake.com/");

		ReportUtil.addScreenShot("Switched to Fake Email Website to Copy Email");
		copyEmail();
	}

	public void switchToToyotaWebsite(){
		WebDriverContext.getDriver().switchTo().window(windowHandles.get(0));
	}

	public void copyEmail () {

		emailFakeEmail = emailID.getText();

	}

	public void copyActivationCode(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		WebDriverContext.getDriver().navigate().refresh();
		WebDriverWait wait = new WebDriverWait(WebDriverContext.getDriver(),10);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[normalize-space()='Verify your account with the activation code below']/parent::tr/following-sibling::tr/td")));
		} catch (TimeoutException timeoutException) {
			WebDriverContext.getDriver().navigate().refresh();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[normalize-space()='Verify your account with the activation code below']/parent::tr/following-sibling::tr/td")));
		}
		ReportUtil.addScreenShot("Switched to email fake website to copy the activation code");
		activationCode=activationCodeTxt.getText();
	}

}
