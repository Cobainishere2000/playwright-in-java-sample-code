package com.serenitydojo.playwright;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

import java.lang.reflect.Array;
import java.util.Arrays;


//This class makes @UsePlaywright default page, browser objects configurable
//We need to pass this class to @UsePlaywright(HeadLessChromeBrowserConfigForUsePlaywright class)
//like this we can skip the setup and teardown methods

public class HeadLessChromeBrowserConfigForUsePlaywright implements OptionsFactory {
    @Override
    public Options getOptions(){
        return new Options().setLaunchOptions(
                new BrowserType.LaunchOptions()
                        .setArgs(Arrays.asList("--no-sandbox", "--disable-gpu" , "--disable-extensions")))
                        .setHeadless(false).setTestIdAttribute("data-test");

    }
}
