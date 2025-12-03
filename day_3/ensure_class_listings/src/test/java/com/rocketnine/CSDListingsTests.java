package com.rocketnine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CSDListingsTests {
    WebDriver driver;
//    WebDriver staticDriver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();

        driver.get("https://rocketninesolutions.com/top-notch-agile-training/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleIs("Top-Notch Agile Training – Rocket Nine Solutions"));

//        driver = new SafariDriver();
    }

    // TODO - refactor tests to only visit page once
    // should be ok since tests do not interact and alter state of the page
//    @BeforeAll
//    public static void beforeAll() {
//        WebDriver staticDriver = new ChromeDriver();
//    }

    @Test
    @Timeout(value = 6, unit = SECONDS)
    void catYogaClassListing_shouldNotBeListed() {
        String pageSource = driver.getPageSource();
        boolean certifiedScrumDeveloper = Objects.requireNonNull(pageSource).contains("Cat Yoga");
        assertFalse(certifiedScrumDeveloper, "we should not be offering public Cat Yoga class");
    }

    @Test
    @Timeout(value = 60, unit = SECONDS)
    void allCoursesPaulOffers_ShouldBeListed() {
        // This page lists ALL rocket nine classes over the next couple of months
        String pageSource = driver.getPageSource();
        boolean certifiedScrumDeveloper = Objects.requireNonNull(pageSource).contains("Certified Scrum Developer");
        assertTrue(certifiedScrumDeveloper, "Could not locate Certified Scrum Developer");

        boolean advanced = pageSource.contains("Advanced Certified Scrum Developer");
        assertTrue(advanced, "Could not locate Advanced Certified Scrum Developer");

        boolean bdd = pageSource.contains("\"Behavior-Driven Development");
        assertTrue(bdd, "Could not locate BDD class");

        boolean lean = pageSource.contains(":\"Lean Software Develop");
        assertTrue(lean, "Could not locate Lean SW class");

        boolean cspd = pageSource.contains(":\"Certified Scrum Professional Developer");
        assertTrue(cspd, "Could not locate CSP-D class");

        // TODO -
        //  is it better to check each of these as a separate test?
        // is this too tightly coupled to the page implementation?
        // Will the test pass if there is hidden text with the magic phrases but no actual courses listed?
        // what if Scott has a lot of classes in the next weeks so mine don't show up?

    }

    //TODO - Additional test that there is one of my CSP-D on SA site

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
