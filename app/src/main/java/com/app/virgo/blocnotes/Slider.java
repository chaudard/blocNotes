package com.app.virgo.blocnotes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by danymichel on 14/01/18.
 */

public class Slider extends LinearLayout {
    /* Est-ce que le menu est ouvert ? */
    protected boolean isOpen = true;
    /* Le menu à dissimuler */
    protected RelativeLayout toHide = null;
    /* Vitesse désirée pour l'animation */
    protected final static int SPEED = 300;

    /* Listener pour l'animation de fermeture du menu */
    Animation.AnimationListener closeListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            //On dissimule le menu
            toHide.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    /* Listener pour l'animation d'ouverture du menu */
    Animation.AnimationListener openListener = new Animation.AnimationListener() {
        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            //On affiche le menu
            toHide.setVisibility(View.VISIBLE);
        }
    };

    /**
     * Constructeur utilisé pour l'initialisation en Java.
     * @param context Le contexte de l'activité.
     */
    public Slider(Context context) {
        super(context);
    }

    /**
     * Constructeur utilisé pour l'initialisation en XML.
     * @param context Le contexte de l'activité.
     * @param attrs Les attributs définis dans le XML.
     */
    public Slider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * Utilisée pour ouvrir ou fermer le menu.
     * @return true si le menu est désormais ouvert.
     */
    public boolean toggle() {
        //Animation de transition.
        TranslateAnimation animation = null;

        //On passe de ouvert à fermé (ou vice versa)
        isOpen = !isOpen;

        //Si le menu est déjà ouvert
        if (isOpen)
        {
            //Animation de translation du bas vers le haut
            animation = new TranslateAnimation(0.0f, 0.0f,
                    -toHide.getHeight(), 0.0f);
            animation.setAnimationListener(openListener);
        } else
        {
            //Sinon, animation de translation du haut vers le bas
            animation = new TranslateAnimation(0.0f, 0.0f,
                    0.0f, -toHide.getHeight());
            animation.setAnimationListener(closeListener);
        }

        //On détermine la durée de l'animation
        animation.setDuration(SPEED);
        //On ajoute un effet d'accélération
        animation.setInterpolator(new AccelerateInterpolator());
        //Enfin, on lance l'animation
        startAnimation(animation);

        return isOpen;
    }


    public void setToHide(RelativeLayout toHide) {
        this.toHide = toHide;
    }

}
