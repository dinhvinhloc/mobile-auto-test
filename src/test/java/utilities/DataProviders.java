package utilities;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviders {


    @DataProvider(name = "Timely")
    public static Object[][] getDataSuite1(Method m) {

        System.out.println(m.getName());

        ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
        String testcase = m.getName();
        return DataUtils.getData(testcase, excel);

    }

}
