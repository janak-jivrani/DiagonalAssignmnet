package com.assignment.diagnal.animations

import android.animation.Animator
import android.animation.AnimatorSet
import android.view.View
import com.assignment.diagnal.animations.Attention.ATTENTION
import com.assignment.diagnal.animations.Attention.ATTENTION_BOUNCE
import com.assignment.diagnal.animations.Attention.ATTENTION_FLASH
import com.assignment.diagnal.animations.Attention.ATTENTION_PULSE
import com.assignment.diagnal.animations.Attention.ATTENTION_RUBERBAND
import com.assignment.diagnal.animations.Attention.ATTENTION_SHAKE
import com.assignment.diagnal.animations.Attention.ATTENTION_STAND_UP
import com.assignment.diagnal.animations.Attention.ATTENTION_SWING
import com.assignment.diagnal.animations.Attention.ATTENTION_TA_DA
import com.assignment.diagnal.animations.Attention.ATTENTION_WAVE
import com.assignment.diagnal.animations.Attention.ATTENTION_WOBBLE
import com.assignment.diagnal.animations.Bounce.BOUNCE
import com.assignment.diagnal.animations.Bounce.BOUNCE_IN
import com.assignment.diagnal.animations.Bounce.BOUNCE_IN_DOWN
import com.assignment.diagnal.animations.Bounce.BOUNCE_IN_LEFT
import com.assignment.diagnal.animations.Bounce.BOUNCE_IN_RIGHT
import com.assignment.diagnal.animations.Bounce.BOUNCE_IN_UP
import com.assignment.diagnal.animations.Fade.FADE
import com.assignment.diagnal.animations.Fade.FADE_IN
import com.assignment.diagnal.animations.Fade.FADE_IN_DOWN
import com.assignment.diagnal.animations.Fade.FADE_IN_LEFT
import com.assignment.diagnal.animations.Fade.FADE_IN_RIGHT
import com.assignment.diagnal.animations.Fade.FADE_IN_UP
import com.assignment.diagnal.animations.Fade.FADE_OUT
import com.assignment.diagnal.animations.Fade.FADE_OUT_DOWN
import com.assignment.diagnal.animations.Fade.FADE_OUT_LEFT
import com.assignment.diagnal.animations.Fade.FADE_OUT_RIGHT
import com.assignment.diagnal.animations.Fade.FADE_OUT_UP
import com.assignment.diagnal.animations.Flip.FLIP
import com.assignment.diagnal.animations.Flip.FLIP_IN_X
import com.assignment.diagnal.animations.Flip.FLIP_IN_Y
import com.assignment.diagnal.animations.Flip.FLIP_OUT_X
import com.assignment.diagnal.animations.Flip.FLIP_OUT_Y
import com.assignment.diagnal.animations.Rotate.ROTATE
import com.assignment.diagnal.animations.Rotate.ROTATE_IN
import com.assignment.diagnal.animations.Rotate.ROTATE_IN_DOWN_LEFT
import com.assignment.diagnal.animations.Rotate.ROTATE_IN_DOWN_RIGHT
import com.assignment.diagnal.animations.Rotate.ROTATE_IN_UP_LEFT
import com.assignment.diagnal.animations.Rotate.ROTATE_IN_UP_RIGHT
import com.assignment.diagnal.animations.Rotate.ROTATE_OUT
import com.assignment.diagnal.animations.Rotate.ROTATE_OUT_DOWN_LEFT
import com.assignment.diagnal.animations.Rotate.ROTATE_OUT_DOWN_RIGHT
import com.assignment.diagnal.animations.Rotate.ROTATE_OUT_UP_LEFT
import com.assignment.diagnal.animations.Rotate.ROTATE_OUT_UP_RIGHT
import com.assignment.diagnal.animations.Slide.SLIDE
import com.assignment.diagnal.animations.Slide.SLIDE_IN_DOWN
import com.assignment.diagnal.animations.Slide.SLIDE_IN_LEFT
import com.assignment.diagnal.animations.Slide.SLIDE_IN_RIGHT
import com.assignment.diagnal.animations.Slide.SLIDE_IN_UP
import com.assignment.diagnal.animations.Slide.SLIDE_OUT_DOWN
import com.assignment.diagnal.animations.Slide.SLIDE_OUT_LEFT
import com.assignment.diagnal.animations.Slide.SLIDE_OUT_RIGHT
import com.assignment.diagnal.animations.Slide.SLIDE_OUT_UP
import com.assignment.diagnal.animations.Zoom.ZOOM
import com.assignment.diagnal.animations.Zoom.ZOOM_IN
import com.assignment.diagnal.animations.Zoom.ZOOM_IN_DOWN
import com.assignment.diagnal.animations.Zoom.ZOOM_IN_LEFT
import com.assignment.diagnal.animations.Zoom.ZOOM_IN_RIGHT
import com.assignment.diagnal.animations.Zoom.ZOOM_IN_UP
import com.assignment.diagnal.animations.Zoom.ZOOM_OUT
import com.assignment.diagnal.animations.Zoom.ZOOM_OUT_DOWN
import com.assignment.diagnal.animations.Zoom.ZOOM_OUT_LEFT
import com.assignment.diagnal.animations.Zoom.ZOOM_OUT_RIGHT
import com.assignment.diagnal.animations.Zoom.ZOOM_OUT_UP

//https://github.com/rommansabbir/AnimationX
//AnimationXExtensionsKt.animationXAttention(binding.txtinvite, Attention.ATTENTION_BOUNCE, 1000, true, null)

fun View.animationXAttention(
    animationKey: String,
    duration: Long = 1000,
    repeat: Boolean = false,
    listener: Animator.AnimatorListener? = null
) {
    renderAnimation(ATTENTION, animationKey, duration, repeat, listener)
}

fun View.animationXBounce(
    animationKey: String,
    duration: Long = 1000,
    repeat: Boolean = false,
    listener: Animator.AnimatorListener? = null
) {
    renderAnimation(BOUNCE, animationKey, duration, repeat, listener)
}

fun View.animationXFade(
    animationKey: String,
    duration: Long = 1000,
    repeat: Boolean = false,
    listener: Animator.AnimatorListener? = null
) {
    renderAnimation(FADE, animationKey, duration, repeat, listener)
}

fun View.animationXFlip(
    animationKey: String,
    duration: Long = 1000,
    repeat: Boolean = false,
    listener: Animator.AnimatorListener? = null
) {
    renderAnimation(FLIP, animationKey, duration, repeat, listener)
}

fun View.animationXRotate(
    animationKey: String,
    duration: Long = 1000,
    repeat: Boolean = false,
    listener: Animator.AnimatorListener? = null
) {
    renderAnimation(ROTATE, animationKey, duration, repeat, listener)
}

fun View.animationXSlide(
    animationKey: String,
    duration: Long = 1000,
    repeat: Boolean = false,
    listener: Animator.AnimatorListener? = null
) {
    renderAnimation(SLIDE, animationKey, duration, repeat, listener)
}

fun View.animationXZoom(
    animationKey: String,
    duration: Long = 1000,
    repeat: Boolean = false,
    listener: Animator.AnimatorListener? = null
) {
    renderAnimation(ZOOM, animationKey, duration, repeat, listener)
}

fun View.renderAnimation(
    key: String,
    animationKey: String,
    duration: Long,
    repeat: Boolean = false,
    listener: Animator.AnimatorListener? = null
) {
    val aniObject = AnimationX().setDuration(duration)
        .setAnimation(
            showAnimation(
                this,
                key,
                animationKey,
                AnimationX().getNewAnimatorSet(),
                repeat
            )
        )
    aniObject.getNewAnimatorSet().addListener(listener)
    aniObject.start()
}

private fun showAnimation(
    view: View,
    key: String,
    animationKey: String,
    animatorSet: AnimatorSet,
    repeat: Boolean = false,
): AnimatorSet {
    return when (key) {
        /**
         * Attention
         */
        ATTENTION -> {
            val attention = Attention
            return when (animationKey) {
                ATTENTION_BOUNCE -> Attention.bounce(view, animatorSet, repeat)
                ATTENTION_FLASH -> Attention.flash(view, animatorSet, repeat)
                ATTENTION_PULSE -> Attention.pulse(view, animatorSet, repeat)
                ATTENTION_RUBERBAND -> Attention.ruberBand(view, animatorSet, repeat)
                ATTENTION_SHAKE -> Attention.shake(view, animatorSet, repeat)
                ATTENTION_STAND_UP -> Attention.standUp(view, animatorSet, repeat)
                ATTENTION_SWING -> Attention.swing(view, animatorSet, repeat)
                ATTENTION_TA_DA -> Attention.taDa(view, animatorSet, repeat)
                ATTENTION_WAVE -> Attention.wave(view, animatorSet, repeat)
                ATTENTION_WOBBLE -> Attention.wobble(view, animatorSet, repeat)
                else -> Attention.bounce(view, animatorSet, repeat)
            }
        }

        /**
         * Bounce
         */
        BOUNCE -> {
            val bounce = Bounce
            return when (animationKey) {
                BOUNCE_IN_DOWN -> Bounce.inDown(view, animatorSet, repeat)
                BOUNCE_IN_UP -> Bounce.inUp(view, animatorSet, repeat)
                BOUNCE_IN_LEFT -> Bounce.inLeft(view, animatorSet, repeat)
                BOUNCE_IN_RIGHT -> Bounce.inRight(view, animatorSet, repeat)
                BOUNCE_IN -> Bounce.`in`(view, animatorSet, repeat)
                else -> Bounce.inDown(view, animatorSet, repeat)
            }
        }

        /**
         * Fade
         */
        FADE -> {
            val fade = Fade
            return when (animationKey) {
                FADE_IN_DOWN -> Fade.inDown(view, animatorSet, repeat)
                FADE_IN_UP -> Fade.inUp(view, animatorSet, repeat)
                FADE_IN_LEFT -> Fade.inLeft(view, animatorSet, repeat)
                FADE_IN_RIGHT -> Fade.inRight(view, animatorSet, repeat)
                FADE_OUT_DOWN -> Fade.outDown(view, animatorSet, repeat)
                FADE_OUT_UP -> Fade.outUp(view, animatorSet, repeat)
                FADE_OUT_LEFT -> Fade.outLeft(view, animatorSet, repeat)
                FADE_OUT_RIGHT -> Fade.outRight(view, animatorSet, repeat)
                FADE_IN -> Fade.`in`(view, animatorSet, repeat)
                FADE_OUT -> Fade.out(view, animatorSet, repeat)
                else -> Fade.inDown(view, animatorSet, repeat)
            }
        }

        /**
         * Flip
         */
        FLIP -> {
            val flip = Flip
            return when (animationKey) {
                FLIP_IN_X -> Flip.inX(view, animatorSet, repeat)
                FLIP_IN_Y -> Flip.inY(view, animatorSet, repeat)
                FLIP_OUT_X -> Flip.outX(view, animatorSet, repeat)
                FLIP_OUT_Y -> Flip.outY(view, animatorSet, repeat)
                else -> Flip.inX(view, animatorSet, repeat)
            }
        }

        /**
         * Rotate
         */
        ROTATE -> {
            val rotate = Rotate
            return when (animationKey) {
                ROTATE_IN_DOWN_LEFT -> Rotate.inDownLeft(view, animatorSet, repeat)
                ROTATE_IN_DOWN_RIGHT -> Rotate.inDownRight(view, animatorSet, repeat)
                ROTATE_IN_UP_LEFT -> Rotate.inUpLeft(view, animatorSet, repeat)
                ROTATE_IN_UP_RIGHT -> Rotate.inUpRight(view, animatorSet, repeat)
                ROTATE_OUT_DOWN_LEFT -> Rotate.outDownLeft(view, animatorSet, repeat)
                ROTATE_OUT_DOWN_RIGHT -> Rotate.outDownRight(view, animatorSet, repeat)
                ROTATE_OUT_UP_LEFT -> Rotate.outUpLeft(view, animatorSet, repeat)
                ROTATE_OUT_UP_RIGHT -> Rotate.outUpRight(view, animatorSet, repeat)
                ROTATE_IN -> Rotate.`in`(view, animatorSet, repeat)
                ROTATE_OUT -> Rotate.out(view, animatorSet, repeat)
                else -> Rotate.inDownLeft(view, animatorSet, repeat)
            }
        }

        /**
         * Slide
         */
        SLIDE -> {
            val slide = Slide
            return when (animationKey) {
                SLIDE_IN_DOWN -> Slide.inDown(view, animatorSet, repeat)
                SLIDE_IN_UP -> Slide.inUp(view, animatorSet, repeat)
                SLIDE_IN_LEFT -> Slide.inLeft(view, animatorSet, repeat)
                SLIDE_IN_RIGHT -> Slide.inRight(view, animatorSet, repeat)
                SLIDE_OUT_DOWN -> Slide.outDown(view, animatorSet, repeat)
                SLIDE_OUT_UP -> Slide.outUp(view, animatorSet, repeat)
                SLIDE_OUT_LEFT -> Slide.outLeft(view, animatorSet, repeat)
                SLIDE_OUT_RIGHT -> Slide.outRight(view, animatorSet, repeat)
                else -> Slide.inDown(view, animatorSet, repeat)
            }
        }

        /**
         * Zoom
         */
        ZOOM -> {
            val zoom = Zoom
            return when (animationKey) {
                ZOOM_IN_DOWN -> Zoom.inDown(view, animatorSet, repeat)
                ZOOM_IN_UP -> Zoom.inUp(view, animatorSet, repeat)
                ZOOM_IN_LEFT -> Zoom.inLeft(view, animatorSet, repeat)
                ZOOM_IN_RIGHT -> Zoom.inRight(view, animatorSet, repeat)
                ZOOM_OUT_DOWN -> Zoom.outDown(view, animatorSet, repeat)
                ZOOM_OUT_UP -> Zoom.outUp(view, animatorSet, repeat)
                ZOOM_OUT_LEFT -> Zoom.outLeft(view, animatorSet, repeat)
                ZOOM_OUT_RIGHT -> Zoom.outRight(view, animatorSet, repeat)
                ZOOM_IN -> Zoom.`in`(view, animatorSet, repeat)
                ZOOM_OUT -> Zoom.out(view, animatorSet, repeat)
                else -> Zoom.inDown(view, animatorSet, repeat)
            }
        }
        else -> Attention.bounce(view, AnimationX().getNewAnimatorSet(), repeat)
    }

}