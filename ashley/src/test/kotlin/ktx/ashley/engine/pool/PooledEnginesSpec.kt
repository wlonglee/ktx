package ktx.ashley.engine.pool

import com.badlogic.ashley.core.PooledEngine
import ktx.ashley.Texture
import ktx.ashley.Transform
import ktx.ashley.all
import ktx.ashley.one
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object PooledEnginesSpec : Spek({
  describe("utilities for pooled engines") {
    val engine by memoized {
      PooledEngine()
    }
    describe("creating a component") {
      val component = engine.create<Transform>()
      it("should create a pooled component with reified type") {
        assertThat(component.x).isEqualTo(0f)
        assertThat(component.y).isEqualTo(0f)
      }
    }
    describe("creating a component with configuration") {
      val component = engine.create<Transform> {
        x = 1f
        y = 2f
      }
      it("should create a pooled component with reified type") {
        assertThat(component.x).isEqualTo(1f)
        assertThat(component.y).isEqualTo(2f)
      }
    }
    describe("multiple entity creation DSL") {
      it("should add an entity") {
        engine.add {
          entity {}
        }
        assertThat(engine.entities.size()).isEqualTo(1)
      }

      it("should add multiple entities") {
        engine.add {
          entity {}
          entity {}
        }
        assertThat(engine.entities.size()).isEqualTo(2)
      }
    }

    describe("single entity creation DSL") {
      it("should add an entity and return it") {
        val entity = engine.entity {}
        assertThat(engine.entities.size()).isEqualTo(1)
        assertThat(engine.entities[0]).isEqualTo(entity)
      }
      it("should add an entity with a component with default configuration") {
        engine.entity {
          with<Transform>()
        }
        val transformEntities = engine.getEntitiesFor(one(Transform::class).get())
        val component = transformEntities.single().getComponent(Transform::class.java)
        assertThat(component.x).isEqualTo(0f)
        assertThat(component.y).isEqualTo(0f)
      }
      it("should add an entity with a component with configuration") {
        engine.entity {
          with<Transform> {
            x = 1f
            y = 2f
          }
        }
        val transformEntities = engine.getEntitiesFor(one(Transform::class).get())
        val component = transformEntities.single().getComponent(Transform::class.java)
        assertThat(component.x).isEqualTo(1f)
        assertThat(component.y).isEqualTo(2f)
      }
      it("should add multiple different components") {
        engine.entity {
          with<Transform>()
          with<Texture>()
        }
        val transformEntities = engine.getEntitiesFor(all(Transform::class, Texture::class).get())
        assertThat(transformEntities.size()).isEqualTo(1)

        val entity = transformEntities.single()
        assertThat(entity.getComponent(Transform::class.java)).isNotNull()
        assertThat(entity.getComponent(Texture::class.java)).isNotNull()
      }
    }
  }
})
