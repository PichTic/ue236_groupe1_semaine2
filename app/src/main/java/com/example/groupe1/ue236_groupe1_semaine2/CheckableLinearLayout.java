package com.example.groupe1.ue236_groupe1_semaine2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * On créé un layout personnalisé de façon à pouvoir utilisé des checkboxes en image
 */
public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};

    private boolean mChecked = false;

    /**
     * Constructeur d'une checkbox
     * @param context
     * @param attrs
     */
    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Getter de la checkbox
     * @return
     */
    public boolean isChecked() {

        return mChecked;
    }

    /**
     * Setter de la checkbox
     * On doit rafraîchir l'état de l'image pour refléter le changement
     * @param b
     */
    public void setChecked(boolean b) {
        if (b != mChecked) {
            mChecked = b;
            refreshDrawableState();
        }
    }

    /**
     * On change l'état de la checkbox
     */
    public void toggle() {
        setChecked(!mChecked);
    }

    /**
     * On redéfinit l'évènement de création de l'image pour
     * qu'il colle à notre besoin
     * @param extraSpace
     * @return
     */
    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}
