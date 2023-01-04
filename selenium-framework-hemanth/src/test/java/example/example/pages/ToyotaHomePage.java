package example.example.pages;

import example.example.context.WebDriverContext;
import example.example.util.ReportUtil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * The Class represents ToyotaHomePage.
 *
 *
 */
public class ToyotaHomePage extends BasePage {

	@FindBy(xpath = "//a[@class='nav-link navbar-toggle' and text()='Models']")
	private WebElement modelTopMenuLink;
	@FindBy(xpath = "//img[@class='logoHeaderimg toyota']")
	private WebElement toyotaLogoImg;

	@FindBy(xpath = "(//div[@data-auto='camry']/div/a)[2]")
	private WebElement camryImgLink;

	public ToyotaHomePage(WebDriver driver) {
		super(driver);
	}

	public void validateHomePage() {
		String title = WebDriverContext.getDriver().getTitle();
		Assert.assertTrue(title.contains("Buy New and Used Toyota Cars in The United Arab Emirates | Toyota"),"Title of the website doesn't match");
		ReportUtil.logMessage("Validating Title","Title matches expected title");
		Assert.assertTrue(toyotaLogoImg.isDisplayed(),"Toyota Image not loaded");
		ReportUtil.logMessage("Validating Presence of Logo","Logo is present on the website");
	}

	public void navigateToCameyPage() {
		modelTopMenuLink.click();
		camryImgLink.click();
	}

}
