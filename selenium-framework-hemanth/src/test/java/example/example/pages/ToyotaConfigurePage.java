package example.example.pages;

import example.example.context.WebDriverContext;
import example.example.util.ReportUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

/**
 * The Class represents ToyotaHomePage.
 *
 *
 */
public class ToyotaConfigurePage extends BasePage {

	@FindBy(xpath = "//div[@id='StepsIdConfig']//li")
	private List<WebElement> stepIDConfigDiv;
	@FindBy(xpath = "//div[@id='StepsIdConfig']//li[1]")
	private WebElement modelAndSpecTxt;

	@FindBy(xpath = "//div[@id='StepsIdConfig']//li[2]")
	private WebElement colourAndStyleTxt;

	@FindBy(xpath = "//h4[normalize-space()='2.5L CAMRY 2023']/parent::div//span[text()='Choose']/parent::button")
	private WebElement chooseBtn;

	@FindBy(xpath = "//img[@class='Stepper_success_image__1F4VE']")
	private WebElement step1SuccessImg;

	@FindBy(xpath = "//span[normalize-space()='Available now']/parent::button")
	private WebElement availableNowBtn;

	@FindBy(xpath = "//span[normalize-space()='Buy now']/parent::button")
	private WebElement buyNowBtn;

	public ToyotaConfigurePage(WebDriver driver) {
		super(driver);
	}


	public void validateConfigurePage() {
		Assert.assertTrue(stepIDConfigDiv.size()==2,"More than 2 steps found on configure page");
		Assert.assertTrue(modelAndSpecTxt.isDisplayed(),"Model and Spec Text not displayed as first step");
		Assert.assertTrue(colourAndStyleTxt.isDisplayed(),"Colour And Style Text not displayed as second step");
		ReportUtil.logMessage("Validating Steps on Configuration Page","Configure page has 2 steps namely, 1. Model and Spec & 2. Colour and Style");
	}

	public void chooseAndAddToCart() {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) WebDriverContext.getDriver();
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",chooseBtn);
		chooseBtn.click();
		ReportUtil.logMessage("Click on choose button","Choose Button clicked");
		javascriptExecutor.executeScript("window.scrollTo(0,0)");
		Assert.assertTrue(step1SuccessImg.isDisplayed());
		ReportUtil.addScreenShot("Step 1 becomes a green tick on clicking choose");
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",availableNowBtn);
		availableNowBtn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",buyNowBtn);
		buyNowBtn.click();
		ReportUtil.logMessage("Click on Available Now and Buy Now button","Available Now and Buy Now button clicked");
	}
}
