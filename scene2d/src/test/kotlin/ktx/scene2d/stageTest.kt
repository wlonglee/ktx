package ktx.scene2d

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Test

class StageWidgetTest : NeedsLibGDX() {
  @Test
  fun `should add Actor to the Stage`() {
    val stage = Stage(mock(), mock())
    val actor = Actor()
    val stageWidget = StageWidget(stage)

    stageWidget.storeActor(actor)

    assertTrue(actor in stage.actors)
    assertTrue(actor in stage.root.children)
  }

  @Test
  fun `should add multiple top-level actors to the Stage within actors block`() {
    val stage = Stage(mock(), mock())
    lateinit var label: Label
    lateinit var table: Table
    lateinit var button: Button

    stage.actors {
      label = label("Test")
      table = table {
        button = button()
      }
      assertSame(stage, this.stage)
    }

    assertTrue(label in stage.actors)
    assertTrue(label in stage.root.children)
    assertTrue(table in stage.actors)
    assertTrue(table in stage.root.children)
    assertFalse(button in stage.actors)
    assertFalse(button in stage.root.children)
    assertTrue(button in table.children)
  }

  @Test
  fun `should store root actors in Stage when using actors block`() {
    val stage = Stage(mock(), mock())
    lateinit var window: Window
    lateinit var dialog: Dialog
    lateinit var label: Label

    stage.actors {
      window = window("Test")
      dialog = dialog("Test") {
        label = label("Test")
      }
    }

    assertTrue(window in stage.actors)
    assertTrue(window in stage.root.children)
    assertTrue(dialog in stage.actors)
    assertTrue(dialog in stage.root.children)
    assertFalse(label in stage.actors)
    assertFalse(label in stage.root.children)
    assertTrue(label in dialog.children)
  }
}
