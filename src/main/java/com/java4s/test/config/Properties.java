package com.java4s.test.config;

import org.openqa.selenium.remote.BrowserType;

public class Properties {

    private static final String DEFAULT_BROWSER = BrowserType.CHROME;
    private static final String BASE_HOME_URL = "http://localhost:8080/test6/employee";
    private static final String POST_URL = "http://localhost:8080/test6/regTest";


    public static String getBaseUrl(){
        return BASE_HOME_URL;
    }

    public static String getPostUrl(){
        return POST_URL;
    }

    public static String getBrowser(){
        return System.getProperty(EnvironmentVariables.BROWSER.toString(), DEFAULT_BROWSER);
    }

    enum EnvironmentVariables {

        BROWSER("browser");

        private String value;
        EnvironmentVariables(String value) {

            this.value = value;
        }

        @Override
        public String toString() {

            return value;
        }
    }


}
