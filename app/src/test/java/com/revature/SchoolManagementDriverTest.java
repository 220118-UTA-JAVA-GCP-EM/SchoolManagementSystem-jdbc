/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.revature;

import org.junit.Test;
import static org.junit.Assert.*;

public class SchoolManagementDriverTest {
    @Test public void appHasAGreeting() {
        SchoolManagementDriver classUnderTest = new SchoolManagementDriver();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}