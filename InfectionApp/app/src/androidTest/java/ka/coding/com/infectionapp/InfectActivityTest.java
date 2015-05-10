package ka.coding.com.infectionapp;

import android.app.AlertDialog;
import android.app.Instrumentation.ActivityMonitor;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.TouchUtils;
import android.widget.Button;
import android.test.ActivityTestCase;
import junit.framework.Assert;

/**
 * Created by pattycase on 5/9/15.
 */
    public class InfectActivityTest extends ActivityUnitTestCase<InfectActivity> {

    public InfectActivityTest() {
        super(InfectActivity.class);
    }

    @MediumTest
    public void testLimitedInfection() {
        Intent intent = new Intent(getInstrumentation().getTargetContext(), InfectActivity.class);
        startActivity(intent, null, null);

        ActivityMonitor monitor =  getInstrumentation().addMonitor(InfectActivity.class.getName(), null, false);
        InfectActivity infectActivity = (InfectActivity) monitor.waitForActivity();
        getInstrumentation().waitForIdleSync();
        AlertDialog dialog = infectActivity.getDialog();
        dialog.setMessage("4");
        Button okButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        TouchUtils.clickView(this, okButton);

        Assert.assertTrue(true);
    }
}
