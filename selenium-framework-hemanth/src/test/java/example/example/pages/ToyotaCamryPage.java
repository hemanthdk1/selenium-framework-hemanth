package example.example.pages;

import example.example.context.WebDriverContext;
import example.example.util.ReportUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * The Class represents ToyotaHomePage.
 *
 *
 */
public class ToyotaCamryPage extends BasePage {

	@FindBy(xpath = "//span[normalize-space()='Configure & Buy']")
	private WebElement configureAndBuyBtn;
	@FindBy(xpath = "//p[@class='HeroBanner_HeroBanner_ModelName__W1iQa']")
	private WebElement camryBannerText;

	@FindBy(xpath = "//p[@class='HeroBanner_HeroBanner_YearVariant__TbcX5']")
	private WebElement camryYearText;

	public ToyotaCamryPage(WebDriver driver) {
		super(driver);
	}

	public void validateToyotaCamryPage() {
		String title = WebDriverContext.getDriver().getTitle();
		Assert.assertTrue(title.contains("Buy The New Toyota Camry Sedan 2023 in The UAE | Toyota"),"Title of the website doesn't match");
		ReportUtil.logMessage("Validating Camry Page Title","Camry Page Title matches expected title");
		Assert.assertTrue(camryBannerText.getText().contains("CAMRY"),"Camry Banner Text does not match");
		Assert.assertTrue(camryYearText.getText().contains("2023"),"Camry Year Text does not match");
		ReportUtil.logMessage("Validating Camry 2023 Banner","Camry 2023 Banner Text matches");
	}

	public void navigateToConfigurePage() {
		configureAndBuyBtn.click();
		ReportUtil.logMessage("Click Configure And Buy Button","Configure And Buy Button clicked");

	}

}
