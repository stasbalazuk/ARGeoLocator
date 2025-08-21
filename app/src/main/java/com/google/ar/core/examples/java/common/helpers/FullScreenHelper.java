package com.google.ar.core.examples.java.common.helpers;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;

/** Helper to set up the Android full screen mode. */
public final class FullScreenHelper {

  /**
   * Sets the Android fullscreen flags. Expected to be called from {@link
   * Activity#onWindowFocusChanged(boolean hasFocus)}.
   *
   * @param activity the Activity on which the full screen mode will be set.
   * @param hasFocus the hasFocus flag passed from the {@link Activity#onWindowFocusChanged(boolean
   *     hasFocus)} callback.
   */
  public static void setFullScreenOnWindowFocusChanged(Activity activity, boolean hasFocus) {
    if (!hasFocus) return;

    View decorView = activity.getWindow().getDecorView();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      WindowInsetsController insetsController = decorView.getWindowInsetsController();
      if (insetsController != null) {
        insetsController.hide(WindowInsets.Type.systemBars());
        insetsController.setSystemBarsBehavior(
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
      }
    } else {
      // Deprecated but still needed for older devices
      decorView.setSystemUiVisibility(
              View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                      | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                      | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                      | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                      | View.SYSTEM_UI_FLAG_FULLSCREEN
                      | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
  }
}