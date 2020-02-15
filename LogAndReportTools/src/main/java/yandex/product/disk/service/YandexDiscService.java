package main.java.yandex.product.disk.service;

import main.java.Framework.logger.Log;
import org.openqa.selenium.By;

import static main.java.Framework.ui.Browser.getInstance;
import static main.java.yandex.product.disk.pages.YandexDiskFilesPage.*;

public class YandexDiscService {
    public static boolean checkFolderName;
    public static boolean checkWordFileExist;

    public static boolean checkIsWordFileExist() {
        getInstance().refreshBrowser();
        By searchWordFileLocator = By.xpath(FILE_LOCATOR_PATTERN);
        checkWordFileExist = getInstance().isVisible(searchWordFileLocator);
        Log.info("Is the Word file exist: " + checkWordFileExist);
        return checkWordFileExist;
    }

    public static boolean checkFolderName() {
        checkFolderName = folderName.equals(realFolderName);
        Log.info("Created and opened folders are the same: " + checkFolderName);
        return checkFolderName;
    }

    public static boolean checkFileNotInFilesFolder() {
        getInstance().waitReadyState();
        By searchWordFileLocator = By.xpath(FILE_LOCATOR_PATTERN);
        return checkerFileInCurrentFolder(searchWordFileLocator, "FILES");
    }

    public static boolean checkFileIsInGarbage() {
        By searchWordFileLocator = By.xpath(FILE_LOCATOR_PATTERN);
        getInstance().waitForVisibilityOfElement(searchWordFileLocator);
        return checkerFileInCurrentFolder(searchWordFileLocator, "GARBAGE");
    }

    public static boolean checkFileNotInGarbage() {
        getInstance().waitReadyState();
        By searchWordFileLocator = By.xpath(FILE_LOCATOR_PATTERN);
        if (getInstance().isVisible(searchWordFileLocator)){
            return false;
        }
        return checkerFileInCurrentFolder(searchWordFileLocator, "GARBAGE");
    }

    private static boolean checkerFileInCurrentFolder(By searchWordFileLocator, String folderTitle) {
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