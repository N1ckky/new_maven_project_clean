package Nick_Maven.Framework.service;

import java.util.ResourceBundle;

public class TestDataReaderService {
        private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

        public static String getTestData(String key){
            return resourceBundle.getString(key);
        }
}