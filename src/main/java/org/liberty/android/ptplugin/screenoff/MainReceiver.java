package org.liberty.android.ptplugin.screenoff;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import eu.chainfire.libsuperuser.Shell;
import android.util.Log;
import java.util.List;

public class MainReceiver extends PowerTogglesPlugin {
    
    @Override
	public void changeState(Context context, boolean newState) {
        ScreenOffUtil.screenOff();
        Log.v("MainReceiver", "PT Screen off is changing state");
		sendStateUpdate(this.getClass(), false, context);
    }
}

