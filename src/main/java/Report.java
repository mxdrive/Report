import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Report {

    public static void main(String args[]) throws FileNotFoundException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/home/qatester/IdeaProjects/Report/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://reports.s-pro.io/auth/login/");
        driver.findElement(By.cssSelector("#id_username")).sendKeys("achernenko@s-pro.io");
        driver.findElement(By.cssSelector("#id_password")).sendKeys("qwerty123");
        driver.findElement(By.cssSelector("#id_password")).submit();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close")));
        driver.findElement(By.cssSelector(".close")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".glyphicon.glyphicon-plus")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).click();
//        Thread.sleep(7000);

        String fileName = "/home/qatester/IdeaProjects/Report/report.txt";
        String title = ".//*[@id='app-container']/div/div[3]/form/div[1]/div[4]/div[1]/div[";
        String title2 = "]/div[5]/input";
        String number = ".//*[@id='app-container']/div/div[3]/form/div[1]/div[4]/div[1]/div[";
        String number2 = "]/div[6]/input";
        String time = ".//*[@id='app-container']/div/div[3]/form/div[1]/div[4]/div[1]/div[";
        String time2 = "]/div[7]/div/select[2]";
        String hour = ".//*[@id='app-container']/div/div[3]/form/div[1]/div[4]/div[1]/div[";
        String hour2 = "]/div[7]/div/select[1]";

        try (
                InputStream fis = new FileInputStream(fileName);
                InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(isr)
        ) {
            while (br.readLine() != null) {
                driver.findElement(By.cssSelector(".btn.btn-lg.btn-default")).click();
            }
            if (!Files.readAllLines(Paths.get(fileName)).get(Files.readAllLines(Paths.get(fileName)).size() - 1).equals("")) {
                driver.findElement(By.cssSelector(".btn.btn-lg.btn-default")).click();
            }
            for (WebElement e : driver.findElements(By.cssSelector("#tracking"))) {
                new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(e));
                e.click();
            }
            for (int i = 0; i < Files.readAllLines(Paths.get(fileName)).size(); i++) {
                if (Files.readAllLines(Paths.get(fileName)).get(i).equals("")) {
                    break;
                } else {
                    driver.findElement(By.xpath(number + (i + 1) + number2)).sendKeys(Files.readAllLines(Paths.get(fileName)).get(i).split("}")[0]);
                    driver.findElement(By.xpath(title + (i + 1) + title2)).sendKeys(Files.readAllLines(Paths.get(fileName)).get(i).split("}")[1]);
                    if (!Files.readAllLines(Paths.get(fileName)).get(i).split("}")[2].contains("/")) {
                        driver.findElement(By.xpath(time + (i + 1) + time2)).sendKeys(Files.readAllLines(Paths.get(fileName)).get(i).split("}")[2]);
                    } else {
                        driver.findElement(By.xpath(time + (i + 1) + time2)).sendKeys(Files.readAllLines(Paths.get(fileName)).get(i).split("}")[2].split("/")[1]);
                        driver.findElement(By.xpath(hour + (i + 1) + hour2)).sendKeys(Files.readAllLines(Paths.get(fileName)).get(i).split("}")[2].split("/")[0]);
                    }
                    driver.findElements(By.cssSelector(".Select-placeholder")).get(0).click();
                    try {
                        new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".Select-option"))));
                    } catch (Exception ignored) {
                    }
                    if (Files.readAllLines(Paths.get(fileName)).get(i).split("}")[0].contains("HB")) {
                        for (WebElement e :
                                driver.findElements(By.cssSelector(".Select-option"))) {
                            if (e.getText().contains("HUB")){
                                e.click();
                                break;
                            }
                        }
                    } else
                        if (Files.readAllLines(Paths.get(fileName)).get(i).split("}")[0].contains("BIL")) {
                            for (WebElement e :
                                    driver.findElements(By.cssSelector(".Select-option"))) {
                                if (e.getText().contains("Billy")){
                                    e.click();
                                    break;
                                }
                            }
                    } else
                        if (Files.readAllLines(Paths.get(fileName)).get(i).split("}")[0].contains("KTV")) {
                            for (WebElement e :
                                    driver.findElements(By.cssSelector(".Select-option"))) {
                                if (e.getText().contains("Kath")){
                                    e.click();
                                    break;
                                }
                            }
                    } else
                        if (Files.readAllLines(Paths.get(fileName)).get(i).split("}")[0].contains("MP")) {
                            for (WebElement e :
                                    driver.findElements(By.cssSelector(".Select-option"))) {
                                if (e.getText().contains("Moving")){
                                    e.click();
                                    break;
                                }
                            }
                        }
                }
            }
            driver.findElement(By.xpath(title + (driver.findElements(By.cssSelector(".glyphicon.glyphicon-remove")).size()) + title2)).sendKeys("Exploratory testing");
            driver.findElement(By.xpath(title + (driver.findElements(By.cssSelector(".glyphicon.glyphicon-remove")).size() - 1) + title2)).sendKeys("Writing autotests");
        } catch (IOException ignored) {
        }

    }

}