package org.liberty.android.ptplugin.screenoff;

import android.content.Context;
import eu.chainfire.libsuperuser.Shell;
import android.util.Log;
import java.util.List;

public class ScreenOffUtil {

    private static Shell.Interactive rootSession;

    public static void screenOff() {
        openShell();
    }

    private static void openShell() {
        rootSession = new Shell.Builder().
            useSU().
            setWantSTDERR(true).
            setWatchdogTimeout(5).
            setMinimalLogging(true).
            open(new Shell.OnCommandResultListener() {
                // Callback to report whether the shell was successfully started up
                @Override
                public void onCommandResult(int commandCode, int exitCode, List<String> output) {
                    if (exitCode != Shell.OnCommandResultListener.SHELL_RUNNING) {
                        Log.e("MainReceiver", "Error opening root shell: exitCode " + exitCode);
                    } else {
                        sendRootCommand();
                    }
                }
            });
    }

    private static void sendRootCommand() {
        rootSession.addCommand(new String[] { "input keyevent 26" }, 0,
                new Shell.OnCommandResultListener() {
                    public void onCommandResult(int commandCode, int exitCode, List<String> output) {
                        if (exitCode < 0) {
                            Log.e("MainReceiver", "Error executing commands: exitCode " + exitCode);
                        }
                    }
                });
    }
}

