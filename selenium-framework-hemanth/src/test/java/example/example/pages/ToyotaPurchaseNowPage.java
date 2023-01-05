package example.example.pages;

import example.example.context.WebDriverContext;
import example.example.factory.PageinstancesFactory;
import example.example.util.ReportUtil;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * The Class represents ToyotaHomePage.
 *
 *
 */
public class ToyotaPurchaseNowPage extends BasePage {

	List<String> windowHandles2;
	public static final String ACCOUNT_SID="AC45fa9bae60427fcd1b71f032c8103454";
	public static final String AUTH_TOKEN="8ba3bc1d9960e6b2a68b1e3b9dcaab04";

	/*
	* Overview Step Page Elements
	 */
	@FindBy(xpath = "//div[@id='StepsId']//div//div//ul/li")
	private List<WebElement> stepIDConfigDiv;
	@FindBy(xpath = "//div[contains(@class,'PurchaseStepBanner_carousalCaption__1R17x px-1')]")
	private WebElement overviewCarNameBannerTxt;

	@FindBy(xpath = "//h3[normalize-space()='Overview']/following-sibling::div[1]/div[2]")
	private WebElement overviewBodyTypeTxt;

	@FindBy(xpath = "//h3[normalize-space()='Overview']/following-sibling::div[2]/div[2]")
	private WebElement overviewModelYearTxt;

	@FindBy(xpath = "//h3[normalize-space()='Overview']/following-sibling::div[3]/div[2]")
	private WebElement overviewModelGradeTxt;

	@FindBy(xpath = "//span[@class='Choices_Choices_submitBtnText__29fvN']")
	private WebElement overviewMakeYourChoiceBtn;

	/*
	 * Choices Step Page Elements
	 */
	@FindBy(xpath = "//select[@name='area']")
	private WebElement choicesAreaDropDown;

	@FindBy(xpath = "//select[contains(@name,'retailer')]")
	private WebElement choicesShowroomDropDown;
	@FindBy(xpath = "//input[@value='Confirm']")
	private WebElement choicesConfirmBtn;
	@FindBy(xpath = "//div[@class='col-lg-8']//button[1]")
	private WebElement choicesSelectYourFinancesBtn;

	/*
	 * Finance Step Page Elements
	 */
	@FindBy(xpath = "//h4[normalize-space()='Next choose your finance type']")
	private WebElement financesNextChooseYourFinanceTypeTxt;
	@FindBy(xpath = "//div[normalize-space()='Quote']")
	private WebElement financesQuoteTxt;
	@FindBy(xpath = "//span[contains(@class,'Finance_Finance_payDepositBtnText__egKxS')]/parent::button")
	private WebElement financesPayDepositBtn;
	@FindBy(xpath = "//div[normalize-space()='Quote']/following-sibling::div//div[contains(@class,'mediumFontType')]")
	private List<WebElement> financesQuoteList;

	/*
	 * Deposit Step Page Elements
	 */
	@FindBy(xpath = "//h4[contains(text(),'To continue please register or sign in to your Toy')]")
	private WebElement depositRegisterTxt;

	@FindBy(xpath = "//button[normalize-space()='Register Now']")
	private WebElement depositRegisterNowBtn;

	@FindBy(xpath = "//input[@value='2000']")
	private WebElement depositValueSliderInputBtn;



	/*
	 * User Details Page Elements
	 */
	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailInputField;
	
	@FindBy(xpath = "//button[@id='emailVerificationControl_but_send_code']")
	private WebElement emailVerificationBtn;

	@FindBy(xpath = "//input[@id='VerificationCode']")
	private WebElement verificationCodeInputField;

	@FindBy(xpath = "//input[@id='newPassword']")
	private WebElement newPasswordInputField;

	@FindBy(xpath = "//input[@id='reenterPassword']")
	private WebElement confirmPasswordInputField;

	@FindBy(xpath = "//input[@id='givenName']")
	private WebElement firstNameInputField;

	@FindBy(xpath = "//input[@id='surname']")
	private WebElement lastNameInputField;

	@FindBy(xpath = "//button[@id='continue']")
	private WebElement signUpBtn;
	
	@FindBy(id="countryCode")
	private WebElement countryCode;
	
	@FindBy(id="number")
	private WebElement phoneNumber;
	
	@FindBy(xpath = "//button[@id='verifyCode']")
	private WebElement sendCode;

	@FindBy(xpath = "//button[@id='cancel']")
	private WebElement cancelBtn;


	public ToyotaPurchaseNowPage(WebDriver driver) {
		super(driver);
	}

	public void validatePurchaseNowPage() {
		Assert.assertTrue(stepIDConfigDiv.size()==6,"Purchase Now page has less than 6 steps");
		ReportUtil.logMessage("Validate 6 steps available on top","Total 6 steps displayed on top viz. Overview, Choices, Finance, Deposit, Application, Confirmation");
	}

	public void validateOverviewAndMoveToChoicesStep(){
		Assert.assertTrue(overviewCarNameBannerTxt.getText().contains("2.5L CAMRY 2023 SE"),"Car Name Banner text not available");
		scrollTo(overviewBodyTypeTxt);
		Assert.assertTrue(overviewBodyTypeTxt.getText().contains("Sedan"),"Sedan text did not match");
		scrollTo(overviewModelYearTxt);
		Assert.assertTrue(overviewModelYearTxt.getText().contains("2023"),"Year did not match expected value");
		scrollTo(overviewModelGradeTxt);
		Assert.assertTrue(overviewModelGradeTxt.getText().contains("SE"),"Model Grade did not match expected text");
		scrollTo(overviewMakeYourChoiceBtn);
		ReportUtil.logMessage("Validate Overview Page","Validated details on overview page");
		overviewMakeYourChoiceBtn.click();
		ReportUtil.logMessage("Click on make your choice button","Make Your Choice button clicked");
	}

	public void confirmChoicesAndMoveToFinancesStep() {
		Select choicesAreaDropDwn = new Select(choicesAreaDropDown);
		choicesAreaDropDwn.selectByVisibleText("Dubai");
		Select choicesShowroomDropDwn = new Select(choicesShowroomDropDown);
		choicesShowroomDropDwn.selectByVisibleText("Toyota Dubai Airport");
		choicesConfirmBtn.click();
		ReportUtil.logMessage("Select values for Area and Showroom Dropdown and click confirm","Selected Dubai for Area and Toyota Dubai Airport for Showroom and clicked confirm");
		scrollTo(choicesSelectYourFinancesBtn);
		Assert.assertTrue(choicesSelectYourFinancesBtn.isEnabled(),"Select your finance button not enabled even after confirming choices");
		choicesSelectYourFinancesBtn.click();
		ReportUtil.logMessage("Click on Select Your Finances button","Select Your Finances button clicked");
	}

	public void selectFinancesAndMoveToDepositStep(){
		Assert.assertTrue(financesNextChooseYourFinanceTypeTxt.isDisplayed(), "Next choose your finance type text is not displayed");
		scrollTo(financesPayDepositBtn);
		int i = 0;
		String[] expectedAttributes = {"Toyota Choices","AED 110,900","AED 22,180"};
		for (WebElement finacesQuoteAttribute : financesQuoteList) {
			Assert.assertTrue(finacesQuoteAttribute.getText().contains(expectedAttributes[i]),"Quote Attribute Value does not match with expected value");
			i++;
			if (i > 2)
				break;
		}
		financesPayDepositBtn.click();
	}

	public void registerAccountOnDepositStep(){
		EmailFakePage emailFakePage = PageinstancesFactory.getInstance(EmailFakePage.class);
		Assert.assertTrue(depositRegisterTxt.isDisplayed(), "Register now text not displayed");
		ReportUtil.logMessage("Validate deposit page","Deposit page validated");
		depositRegisterNowBtn.click();
		ReportUtil.logMessage("Click Register Now button","Register now button clicked");
		windowHandles2 = new ArrayList<>(WebDriverContext.getDriver().getWindowHandles());
		WebDriverContext.getDriver().switchTo().window(windowHandles2.get(2));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		ReportUtil.addScreenShot("Register New Account Window ");

		emailInputField.sendKeys(EmailFakePage.emailFakeEmail);
		emailVerificationBtn.click();
		ReportUtil.logMessage("Enter email and click verification button","Email entered and clicked on verification button");

		WebDriverContext.getDriver().switchTo().window(windowHandles2.get(1));

		emailFakePage.copyActivationCode();

		WebDriverContext.getDriver().switchTo().window(windowHandles2.get(2));

		verificationCodeInputField.sendKeys(EmailFakePage.activationCode);

		newPasswordInputField.sendKeys("123@abcd");
		confirmPasswordInputField.sendKeys("123@abcd");
		firstNameInputField.sendKeys("Abc");
		lastNameInputField.sendKeys("Xyz");
		ReportUtil.addScreenShot("Entered verfication code and other registration details on register new account window");
		signUpBtn.click();
		try {
			Thread.sleep(3000);	
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		Select country= new Select(countryCode);
		country.selectByValue("+1");
		phoneNumber.sendKeys("9895821267");
		sendCode.click();
		try {
			Thread.sleep(3000);	
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
//		//get OTP
//		Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
//		String message = getMessage();
//		ReportUtil.logMessage("OTP", message);
//		System.out.println(message);
//		try {
//			Thread.sleep(5000);	
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
		ReportUtil.logMessage("Click on cancel button on Phone verification window","Cancel button cliked");
		cancelBtn.click();

		WebDriverContext.getDriver().switchTo().window(windowHandles2.get(0));
		ReportUtil.logMessage("Switch Back to original Toyota website window","Switched back to original toyota website window");
		moveSliderInputElement(depositValueSliderInputBtn);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getMessage()
	{
		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND)==0)
				.filter(m->m.getTo().equals("+19895821267")).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException :: new);
	}
	
	public static Stream<Message> getMessages()
	{	
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		return StreamSupport.stream(messages.spliterator(),false);
	}

	public void scrollTo(WebElement element) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) WebDriverContext.getDriver();
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",element);
	}

	public void moveSliderInputElement(WebElement sliderElement) {
		Dimension sliderSize = sliderElement.getSize();
		int sliderWidth = sliderSize.getWidth();
		int xCoord = sliderElement.getLocation().getX();
		Actions builder = new Actions(WebDriverContext.getDriver());
		builder.moveToElement(sliderElement)
				.click()
				.dragAndDropBy
						(sliderElement,xCoord + 100, 0)
				.build()
				.perform();

	}


}
