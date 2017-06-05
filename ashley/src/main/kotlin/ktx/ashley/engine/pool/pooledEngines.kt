package ktx.ashley.engine.pool

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine
import ktx.ashley.AshleyDsl

/**
 * Get or create a [Component] from the [PooledEngine].
 *
 * @param T the [Component] to get or create
 * @param configure inlined function with [T] as the receiver to allow further configuration.
 * @return the pooled [Component]
 * @see PooledEngine.createComponent
 */
inline fun <reified T: Component> PooledEngine.create(configure: T.() -> Unit): T {
  return create<T>().also(configure)
}

/**
 * Get or create a [Component] from the [PooledEngine].
 *
 * @param T the [Component] to get or create
 * @return the pooled [Component]
 * @see PooledEngine.createComponent
 */
inline fun <reified T: Component> PooledEngine.create(): T = createComponent(T::class.java)

/**
 * An [Entity] created by a [PooledEngine].
 *
 * Provides methods for adding [Component]s to the [PooledEngine] and the [Entity].
 *
 * @property engine the [PooledEngine] to create [Component]s
 * @property entity the [Entity] to add [Component]s to
 * @constructor creates a [PooledEntity]
 */
@AshleyDsl
class PooledEntity(val engine: PooledEngine, val entity: Entity) {

  /**
   * Get or creates a pooled instance of the component [T] and adds it to this [entity][PooledEntity].
   *
   * @param T the [Component] type to get or create
   * @param configure inlined function with [T] as the receiver to allow additional configuration of the [Component]
   * @return the pooled [Component]
   * @see [create]
   */
  inline fun <reified T: Component> with(configure: (@AshleyDsl T).() -> Unit): T {
    return with<T>().also(configure)
  }

  /**
   * Get or creates a pooled instance of the component [T] and adds it to this [entity][PooledEntity].
   *
   * @param T the [Component] type to get or create
   * @return the pooled [Component]
   * @see [create]
   */
  inline fun <reified T: Component> with(): T {
    val component = engine.create<T>()
    entity.add(component)
    return component
  }
}

/**
 * Builder function for [PooledEngine].
 *
 * @param configure inlined function with *this* [PooledEngine] as the receiver to allow further configuration.
 */
inline fun PooledEngine.add(configure: (@AshleyDsl PooledEngine).() -> Unit) {
  configure(this)
}

/**
 * Create and add a pooled [Entity] to the [PooledEngine].
 *
 * @param configure inlined function with the pooled [PooledEntity] as the receiver to allow further configuration of
 *  the [Entity]. The [PooledEntity] holds the [Entity] created and the [PooledEngine] that created it.
 * @return the pooled [Entity]
 */
inline fun PooledEngine.entity(configure: PooledEntity.() -> Unit): Entity {
  val entity = createEntity()
  configure(PooledEntity(this, entity))
  addEntity(entity)
  return entity
}

