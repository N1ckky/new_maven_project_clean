package Nick_Maven.Framework.service;

import Nick_Maven.Framework.model.ParamsCloudGoogleCalculator;
import Nick_Maven.Framework.model.ParamsCloudGoogleCalculatorFactory;

import static Nick_Maven.Framework.test.CommonConditions.tempList;

public class ListEstimateCheckService {
    public static String myVmClass;
    public static String myInstance;
    public static String myRegion;
    public static String mySSD;
    public static String myCommitmentTerm;
    public static String myPrice;

    public static boolean listEstimateCheck() {
        ParamsCloudGoogleCalculator paramsCloudGoogleCalculator = ParamsCloudGoogleCalculatorFactory.fromPropertyParamsCloudGoogleCalculator();
        return (myVmClass.equals(paramsCloudGoogleCalculator.getVmClass())
                && myInstance.equals(paramsCloudGoogleCalculator.getInstance())
                && myRegion.equals(paramsCloudGoogleCalculator.getRegion())
                && mySSD.equals(paramsCloudGoogleCalculator.getLocalSsd())
                && myCommitmentTerm.equals(paramsCloudGoogleCalculator.getCommitmentTerm())
                && myPrice.equals(paramsCloudGoogleCalculator.getTotalPrice()));
    }

    public static void addConcatedTotalPriceToTempList(String totalPrice) {
        tempList.add(getConcatedTotalPriceFromPage(totalPrice));
    }

    public static String getConcatedTotalPriceFromPage(String totalPrice) {
        myPrice = totalPrice
                .substring(totalPrice.indexOf("USD") + 4, totalPrice.indexOf("per") - 1);
        return myPrice;
    }

    public static String getConcatedInstancesFromPage(String instance) {
        myInstance = instance
                .substring(instance.indexOf(':') + 2);
        return myInstance;
    }

    public static String getConcatedVmClassFromPage(String vmClass) {
        myVmClass = vmClass
                .substring(vmClass.indexOf(':') + 2);
        return myVmClass;
    }

    public static String getConcatedRegionFromPage(String region) {
        myRegion = region
                .substring(region.indexOf(':') + 2);
        return myRegion;
    }

    public static String getConcatedSSDFromPage(String ssd) {
        mySSD = ssd
                .substring(ssd.indexOf("space") + 6);
        return mySSD;
    }

    public static String getConcatedCommitmentTermFromPage(String commitmentTerm) {
        myCommitmentTerm = commitmentTerm
                .substring(commitmentTerm.indexOf(':') + 2);
        return myCommitmentTerm;
    }
}