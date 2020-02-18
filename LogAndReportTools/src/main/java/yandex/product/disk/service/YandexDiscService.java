package main.java.yandex.product.disk.service;

import main.java.Framework.logger.Log;
import org.openqa.selenium.By;

import static main.java.Framework.ui.Browser.getInstance;

public class YandexDiscService {

    public static boolean isFileInCurrentFolder(By searchWordFileLocator, String folderTitle) {
        String setFolderName = String.format("File in %s folder: ", folderTitle);
        try {
            getInstance().getWrappedDriver().findElement(searchWordFileLocator);
            Log.info(setFolderName + true);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Log.info(setFolderName + false);
            return false;
        }
    }
}