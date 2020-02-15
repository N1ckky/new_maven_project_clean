package main.java.yandex.product.disk.service;

import static main.java.Framework.ui.Browser.getInstance;

import java.util.ArrayList;

public class BrowserParamsService {
    private static ArrayList<String> tabs;

    public BrowserParamsService switchTab(int tabNumber) {
        getInstance().switchToWindow(tabs.get(tabNumber));
        return this;
    }

    public BrowserParamsService getAllOppenedTabs() {
        tabs = new ArrayList<String>(getInstance().getWindowHandles());
        return this;
    }
}