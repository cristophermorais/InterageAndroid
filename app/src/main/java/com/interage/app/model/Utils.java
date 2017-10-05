package com.interage.app.model;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Cristopher Morais on 24/09/2017.
 */

public class Utils {
    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Resources r, int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
