package fupp.mvp_demo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Before
    public void setUp() throws Exception{

    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("diveo.srs_app", appContext.getPackageName());

        String sUserName = "admin", sPassword = "123456";
        assertEquals(4, 2 + 2);

//        Api.getInstance().mService.login(new LoginCommand(new _User(sUserName, sPassword)))
//                .subscribe(user -> Logger.d("Test_Login", "Success"),
//                        e -> Logger.t("Test_Login").d("Failed")
//                );
    }
}
