package main.java.yandex.product.disk.service;

import main.java.Framework.logger.Log;
import main.java.yandex.product.disk.pages.NavigationBlockPage;

import java.util.LinkedHashSet;
import java.util.Set;

import static main.java.yandex.product.disk.Configs.*;

public class NavigationBlockService {

    public static boolean titlesComparisonInNavBlock() {
        boolean navigationBlockTitlesAsExpected;
        Set<String> expectedTitlesOfBlock = new LinkedHashSet<>();
        expectedTitlesOfBlock.add(EXPECTED_LAST_FILES_TAG);
        expectedTitlesOfBlock.add(EXPECTED_FILES_TAG);
        expectedTitlesOfBlock.add(EXPECTED_FOTO_TAG);
        expectedTitlesOfBlock.add(EXPECTED_ALBUM_TAG);
        expectedTitlesOfBlock.add(EXPECTED_SHARED_TAG);
        expectedTitlesOfBlock.add(EXPECTED_HISTORY_TAG);
        expectedTitlesOfBlock.add(EXPECTED_ARCHIVE_TAG);
        expectedTitlesOfBlock.add(EXPECTED_GARBAGE_TAG);
        navigationBlockTitlesAsExpected = NavigationBlockPage.getRealTitlesOfBlock.equals(expectedTitlesOfBlock);
        for (String s:expectedTitlesOfBlock){
            Log.debug(s);
        }
        for (String s: NavigationBlockPage.getRealTitlesOfBlock){
            Log.debug(s);
        }
        Log.info("All navigation titles are as expected: " + navigationBlockTitlesAsExpected);
        return navigationBlockTitlesAsExpected;
    }
}