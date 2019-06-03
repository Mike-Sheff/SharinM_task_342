package ru.netologia.sharinm_task_342;

import android.app.Activity;
import android.content.Intent;

public class Utils
{
    private static int sTheme;
    private static int sIndent;

    public final static int THEME_DEFAULT = 3;
    public final static int THEME_BLACK = 0;
    public final static int THEME_GREEN = 1;
    public final static int THEME_BLUE = 2;


    public final static int INDENT_MARGIN1 = 0;
    public final static int INDENT_MARGIN3 = 1;
    public final static int INDENT_MARGIN10 = 2;
    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;

        activity.finish();

        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void changeToIndent(Activity activity, int indent)
    {
        sIndent = indent;

        activity.finish();

        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            case THEME_BLACK:
                activity.setTheme(R.style.AppThemeBlack);
                break;
            case THEME_GREEN:
                activity.setTheme(R.style.AppThemeGreen);
                break;
            case THEME_BLUE:
                activity.setTheme(R.style.AppThemeBlue);
                break;
            case THEME_DEFAULT:
                activity.setTheme(R.style.AppTheme);
                break;
        }
    }

    public static void onActivityCreateSetIndent(Activity activity)
    {
        switch (sIndent)
        {
            case INDENT_MARGIN1:
                activity.setTheme(R.style.AppTheme_Margin1);
                break;
            case INDENT_MARGIN3:
                activity.setTheme(R.style.AppTheme_Margin3);
                break;
            case INDENT_MARGIN10:
                activity.setTheme(R.style.AppTheme_Margin10);
                break;
        }
    }
}