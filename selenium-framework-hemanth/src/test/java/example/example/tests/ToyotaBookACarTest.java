package example.example.tests;

import example.example.context.WebDriverContext;
import example.example.factory.PageinstancesFactory;
import example.example.pages.*;
import example.example.util.TestProperties;
import org.testng.annotations.Test;


public class ToyotaBookACarTest extends BaseTest {

	
	@Test(testName = "Book a Toyota Car", description = "Configure and Buy a car on Toyota website and register an account")
	public void bookAToyotaCar() {
		WebDriverContext.getDriver().get(TestProperties.getProperty("BaseURL"));

		ToyotaHomePage toyotaHomePage = PageinstancesFactory.getInstance(ToyotaHomePage.class);
		ToyotaCamryPage toyotaCamryPage = PageinstancesFactory.getInstance(ToyotaCamryPage.class);
		ToyotaConfigurePage toyotaConfigurePage = PageinstancesFactory.getInstance(ToyotaConfigurePage.class);
		ToyotaPurchaseNowPage toyotaPurchaseNowPage = PageinstancesFactory.getInstance(ToyotaPurchaseNowPage.class);
		EmailFakePage emailFakePage = PageinstancesFactory.getInstance(EmailFakePage.class);

		toyotaHomePage.validateHomePage();
		toyotaHomePage.navigateToCameyPage();


		toyotaCamryPage.validateToyotaCamryPage();
		toyotaCamryPage.navigateToConfigurePage();

		toyotaConfigurePage.validateConfigurePage();
		toyotaConfigurePage.chooseAndAddToCart();

		toyotaPurchaseNowPage.validatePurchaseNowPage();
		toyotaPurchaseNowPage.validateOverviewAndMoveToChoicesStep();
		toyotaPurchaseNowPage.confirmChoicesAndMoveToFinancesStep();
		toyotaPurchaseNowPage.selectFinancesAndMoveToDepositStep();

		emailFakePage.navigateToEmailFake();
		emailFakePage.switchToToyotaWebsite();

		toyotaPurchaseNowPage.registerAccountOnDepositStep();

	}

}
