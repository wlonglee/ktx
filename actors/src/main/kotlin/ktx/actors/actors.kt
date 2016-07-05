package ktx.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage

/**
 * Min value of alpha in LibGDX Color instances.
 */
const val MIN_ALPHA = 0f
/**
 * Max value of alpha in LibGDX Color instances.
 */
const val MAX_ALPHA = 1f

/**
 * @return true if the actor is not null and is currently shown on a [Stage].
 */
fun Actor?.isShown(): Boolean = this != null && this.stage != null

/**
 * Utility method overload. Allows to set actor's position with ints rather than floats.
 * @param x actor's position on X axis in stage units.
 * @param y actor's position on Y axis in stage units.
 */
fun Actor.setPosition(x: Int, y: Int) = setPosition(x.toFloat(), y.toFloat())

/**
 * Modifies this actor position to be centered within the passed bounds. Uses actor's size to calculate the offsets.
 * @param width total available width in stage units. Defaults to stage width.
 * @param height total available height in stage units. Defaults to stage height.
 * @param normalize if true, position will be converted to ints. Defaults to true
 */
fun Actor.centerPosition(width: Float = this.stage.width, height: Float = this.stage.height, normalize: Boolean = true) {
  val x = (width - this.width) / 2f
  val y = (height - this.height) / 2f
  if (normalize) {
    this.setPosition(x.toInt(), y.toInt())
  } else {
    this.setPosition(x, y)
  }
}

/**
 * @param actor might be stored in this group.
 * @return true if this group is the direct parent of the passed actor.
 */
operator fun Group.contains(actor: Actor): Boolean = actor.parent === this

/**
 * Alias for [Group.addActor] method. Allows to add actors to the group with + operator.
 * @param actor will be added to the group.
 * @return this (for chaining).
 */
operator fun Group.plus(actor: Actor): Group {
  this.addActor(actor)
  return this
}

/**
 * Alias for [Group.removeActor] method. Allows to remove actors from the group with - operator.
 * @param actor will be removed from the group.
 * @return this (for chaining).
 */
operator fun Group.minus(actor: Actor): Group {
  this.removeActor(actor)
  return this
}

/**
 * @param actor might be on this stage.
 * @return true if the passed actor's stage equals this.
 */
operator fun Stage.contains(actor: Actor): Boolean = actor.stage === this

/**
 * Alias for [Stage.addActor] method. Allows to add actors to the stage with + operator.
 * @param actor will be added to stage.
 * @return this (for chaining).
 */
operator fun Stage.plus(actor: Actor): Stage {
  this.addActor(actor)
  return this
}

/**
 * Removes actors from root group of the stage. If actor is not added directly to the stage, this method might have no effect.
 * @param actor will be removed if it is added directly to the stage.
 * @return this (for chaining).
 */
operator fun Stage.minus(actor: Actor): Stage {
  this.root - actor
  return this
}

/**
 * Links to this actor's [Actor.color] instance. Allows to easily modify alpha color value.
 */
var Actor.alpha: Float
  get() = this.color.a
  set(value) {
    this.color.a = value
  }

/**
 * Links to this [Stage.root].color instance. Allows to easily modify alpha color value of stage.
 */
var Stage.alpha: Float
  get() = this.root.color.a
  set(value) {
    this.root.color.a = value
  }


/**
 * Modifies current stage keyboard focus if this actor is currently shown.
 * @param focus if true, will set current keyboard focus to this actor. If false and the actor is currently focused,
 *    will clear keyboard focus of actor's stage.
 */
fun Actor.setKeyboardFocus(focus: Boolean = true) {
  if (this.stage != null) {
    if (focus) {
      stage.keyboardFocus = this
    } else if (stage.keyboardFocus === this) {
      stage.keyboardFocus = null
    }
  }
}

/**
 * Modifies current stage scroll focus if this actor is currently shown.
 * @param focus if true, will set current scroll focus to this actor. If false and the actor is currently focused,
 *    will clear scroll focus of actor's stage.
 */
fun Actor.setScrollFocus(focus: Boolean = true) {
  if (this.stage != null) {
    if (focus) {
      stage.scrollFocus = this
    } else if (stage.scrollFocus === this) {
      stage.scrollFocus = null
    }
  }
}