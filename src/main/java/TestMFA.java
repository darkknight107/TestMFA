import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestMFA {

    public static void main(String [] args){
        FirefoxDriver fDriver = new FirefoxDriver();
        loginToSystem(fDriver);
        twoFactorAuthenticate(fDriver);
    }

    public static void loginToSystem(FirefoxDriver driver){
        //enter URL
        driver.get("https://github.com/login");
        //enter email
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"login_field\"]"));
        emailField.sendKeys("ENTER EMAIL HERE");
        //enter password
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordField.sendKeys("ENTER PASSWORD HERE");
        //click on Login button
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]/form/div[3]/input[7]"));
        loginButton.click();
    }

    public static void twoFactorAuthenticate(FirefoxDriver driver){
        String otpKeyStr = "ENTER OTP KEY HERE";
        String authCode= getTwoFactorCode(otpKeyStr);
        WebElement twoFacAuthCodeField = driver.findElement(By.xpath("//*[@id=\"otp\"]"));
        twoFacAuthCodeField.sendKeys(authCode);
        WebElement verifyButton= driver.findElement(By.xpath("/html/body/div[3]/main/div/div[5]/form/button"));
        verifyButton.click();

    }

    public static String getTwoFactorCode(String secretKey){
        Totp totp= new Totp(secretKey);
        String codeNow= totp.now();
        System.out.println("Code is " + codeNow);
        return codeNow;
    }
}
