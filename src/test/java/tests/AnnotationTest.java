package tests;

import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


public class AnnotationTest {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before suite");
    }
    
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before class");
    }
    
    @BeforeTest
    public void beforeTest() {
        System.out.println("Before test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After test");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After class");
    }
    
    @AfterSuite
    public void afterSuite() {
        System.out.println("after suite");
    }
}
