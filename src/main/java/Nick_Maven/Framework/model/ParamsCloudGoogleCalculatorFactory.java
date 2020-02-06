package Nick_Maven.Framework.model;

import Nick_Maven.Framework.service.TestDataReaderService;

public class ParamsCloudGoogleCalculatorFactory {

    private static final String EXPECTED_VM_CLASS = "testdata.ExpectedVM_CLASS";
    private static final String EXPECTED_INSTANCE = "testdata.ExpectedINSTANCE";
    private static final String EXPECTED_REGION = "testdata.ExpectedREGION";
    private static final String EXPECTED_LOCAL_SSD = "testdata.ExpectedLOCAL_SSD";
    private static final String EXPECTED_COMMITMENT_TERM = "testdata.ExpectedCOMMITMENT_TERM";
    private static final String EXPECTED_TOTAL_PRICE = "testdata.ExpectedTOTAL_PRICE";

    public static ParamsCloudGoogleCalculator fromPropertyParamsCloudGoogleCalculator() {
        return new ParamsCloudGoogleCalculator(TestDataReaderService.getTestData(EXPECTED_VM_CLASS),
                TestDataReaderService.getTestData(EXPECTED_INSTANCE), TestDataReaderService.getTestData(EXPECTED_REGION),
                TestDataReaderService.getTestData(EXPECTED_LOCAL_SSD), TestDataReaderService.getTestData(EXPECTED_COMMITMENT_TERM),
                TestDataReaderService.getTestData(EXPECTED_TOTAL_PRICE));
    }
}