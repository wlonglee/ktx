package ktx.scene2d.vis

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Cell
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Scaling
import com.kotcrab.vis.ui.layout.FloatingGroup
import com.kotcrab.vis.ui.layout.GridGroup
import com.kotcrab.vis.ui.layout.HorizontalFlowGroup
import com.kotcrab.vis.ui.layout.VerticalFlowGroup
import com.kotcrab.vis.ui.util.adapter.ListAdapter
import com.kotcrab.vis.ui.widget.*
import com.kotcrab.vis.ui.widget.color.BasicColorPicker
import com.kotcrab.vis.ui.widget.color.ExtendedColorPicker
import com.kotcrab.vis.ui.widget.spinner.Spinner
import com.kotcrab.vis.ui.widget.spinner.SpinnerModel
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPane
import com.kotcrab.vis.ui.widget.toast.Toast
import com.kotcrab.vis.ui.widget.toast.ToastTable
import ktx.scene2d.*
import com.badlogic.gdx.utils.Array as GdxArray

/**
 * Constructs a top-level [VisWindow] widget.
 * @param title will be displayed as window's title.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked on the [VisWindow] widget. Inlined.
 * @return a new [VisWindow] instance.
 */
@Scene2dDsl
inline fun RootWidget.visWindow(
  title: String,
  style: String = defaultStyle,
  init: KVisWindow.() -> Unit = {}
): KVisWindow = storeActor(KVisWindow(title, style)).apply(init)

/**
 * Constructs a top-level [VisDialog] widget.
 * @param title will be displayed as window's title.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked on the [VisDialog] widget. Inlined.
 * @return a new [VisDialog] instance.
 */
@Scene2dDsl
inline fun RootWidget.visDialog(
  title: String,
  style: String = defaultStyle,
  init: KVisDialog.() -> Unit = {}
): KVisDialog = storeActor(KVisDialog(title, style)).apply(init)

/**
 * Constructs a top-level [ToastTable] widget. Utility for constructing [Toast] instances.
 * @param defaultSpacing if true, default VisUI spacing will be applied to the table.
 * @param init will be invoked on the [ToastTable] widget. Inlined.
 * @return a new [ToastTable] instance.
 */
@Scene2dDsl
inline fun RootWidget.toastTable(
  defaultSpacing: Boolean = false,
  init: KToastTable.() -> Unit = {}
): KToastTable = storeActor(KToastTable(defaultSpacing)).apply(init)

/**
 * @param text will be displayed on the label.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisLabel] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visLabel(
  text: CharSequence,
  style: String = defaultStyle,
  init: (@Scene2dDsl VisLabel).(S) -> Unit = {}
): VisLabel = actor(VisLabel(text, style), init)

/**
 * @param text will be displayed on the label.
 * @param url URL that will be opened by the label. If not given, will default to [text].
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [LinkLabel] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.linkLabel(
  text: CharSequence,
  url: CharSequence = text,
  style: String = defaultStyle,
  init: (@Scene2dDsl LinkLabel).(S) -> Unit = {}
): LinkLabel = actor(LinkLabel(text, url, style), init)

/**
 * @param drawable will be rendered by this image.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisImage] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visImage(
  drawable: Drawable,
  scaling: Scaling = Scaling.stretch,
  align: Int = Align.center,
  init: (@Scene2dDsl VisImage).(S) -> Unit = {}
): VisImage = actor(VisImage(drawable, scaling, align), init)

/**
 * @param drawableName name of a drawable stored in the VisUI skin.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisImage] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visImage(
  drawableName: String,
  init: (@Scene2dDsl VisImage).(S) -> Unit = {}
): VisImage = actor(VisImage(drawableName), init)

/**
 * @param texture will be rendered by this image.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisImage] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visImage(
  texture: Texture,
  init: (@Scene2dDsl VisImage).(S) -> Unit = {}
): VisImage = actor(VisImage(texture), init)

/**
 * @param ninePatch will be rendered by this image.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisImage] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visImage(
  ninePatch: NinePatch,
  init: (@Scene2dDsl VisImage).(S) -> Unit = {}
): VisImage = actor(VisImage(ninePatch), init)

/**
 * @param textureRegion will be rendered by this image.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisImage] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visImage(
  textureRegion: TextureRegion,
  init: (@Scene2dDsl VisImage).(S) -> Unit = {}
): VisImage = actor(VisImage(textureRegion), init)

/**
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined. Allows to fill list's items.
 * @return a [VisList] widget instance added to this group.
 * @param I type of items stored by this widget. Usually items are converted to string and displayed.
 * @param S type of actor containers used by the parent. Usually [Cell], [Node] or [Actor].
 */
@Scene2dDsl
inline fun <I, S> KWidget<S>.visList(
  style: String = defaultStyle,
  init: KVisList<I>.(S) -> Unit = {}
): KVisList<I> {
  val list = KVisList<I>(style)
  list.init(storeActor(list))
  list.refreshItems()
  return list
}

/**
 * @param items optional LibGDX array of list widget items. Defaults to null.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @return a [VisList] instance added to this group.
 * @param I type of items stored by this widget. Usually items are converted to string and displayed.
 */
@Scene2dDsl
fun <I> KWidget<*>.visListOf(
  items: GdxArray<I>? = null,
  style: String = defaultStyle
): KVisList<I> {
  val list = KVisList<I>(style)
  storeActor(list)
  if (items != null && items.size > 0) {
    list.setItems(items)
  }
  return list
}

/**
 * @param min minimum value displayed by the bar. Defaults to 0.
 * @param min maximum value displayed by the bar. Defaults to 1.
 * @param step the size of a single step between two values. Defaults to 0.01.
 * @param vertical true if the widget is vertical, false if horizontal.
 * @param style name of the widget style. Defaults to [defaultVerticalStyle] if the widget is vertical or
 * [defaultHorizontalStyle] if the widget is horizontal.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisProgressBar] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visProgressBar(
  min: Float = 0f,
  max: Float = 100f,
  step: Float = 1f,
  vertical: Boolean = false,
  style: String = if (vertical) defaultVerticalStyle else defaultHorizontalStyle,
  init: (@Scene2dDsl VisProgressBar).(S) -> Unit = {}
): VisProgressBar = actor(VisProgressBar(min, max, step, vertical, style), init)

/**
 * @param items optional LibGDX array of the [VisSelectBox] items. Defaults to null.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @return a [VisSelectBox] instance added to this group.
 * @param I type of items stored by this widget. Usually items are converted to string and displayed.
 */
@Scene2dDsl
fun <I> KWidget<*>.visSelectBoxOf(
  items: GdxArray<I>? = null,
  style: String = defaultStyle
): KVisSelectBox<I> {
  val selectBox = KVisSelectBox<I>(style)
  storeActor(selectBox)
  if (items != null && items.size > 0) {
    selectBox.items = items
  }
  return selectBox
}

/**
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined. Allows to fill list's items.
 * @return a [VisSelectBox] instance added to this group.
 * @param I type of items stored by this widget. Usually items are converted to string and displayed.
 * @param S type of actor containers used by the parent. Usually [Cell], [Node] or [Actor].
 */
@Scene2dDsl
inline fun <I, S> KWidget<S>.visSelectBox(
  style: String = defaultStyle,
  init: KVisSelectBox<I>.(S) -> Unit = {}
): KVisSelectBox<I> {
  val selectBox = KVisSelectBox<I>(style)
  selectBox.init(storeActor(selectBox))
  selectBox.refreshItems()
  return selectBox
}

/**
 * @param min minimum value displayed by the slider. Defaults to 0.
 * @param min maximum value displayed by the slider. Defaults to 1.
 * @param step the size of a single step between two values. Defaults to 0.01.
 * @param vertical true if the widget is vertical, false if horizontal.
 * @param style name of the widget style. Defaults to [defaultVerticalStyle] if the widget is vertical or
 * [defaultHorizontalStyle] if the widget is horizontal.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisSlider] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visSlider(
  min: Float = 0f,
  max: Float = 100f,
  step: Float = 1f,
  vertical: Boolean = false,
  style: String = if (vertical) defaultVerticalStyle else defaultHorizontalStyle,
  init: (@Scene2dDsl VisSlider).(S) -> Unit = {}
): VisSlider = actor(VisSlider(min, max, step, vertical, style), init)

/**
 * @param text initial text displayed by the area. Defaults to empty string.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisTextArea] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visTextArea(
  text: String = "",
  style: String = defaultStyle,
  init: (@Scene2dDsl VisTextArea).(S) -> Unit = {}
): VisTextArea = actor(VisTextArea(text, style), init)

/**
 * @param text initial text displayed by the area. Defaults to empty string.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [HighlightTextArea] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.highlightTextArea(
  text: String = "",
  style: String = defaultStyle,
  init: (@Scene2dDsl HighlightTextArea).(S) -> Unit = {}
): HighlightTextArea = actor(HighlightTextArea(text, style), init)

/**
 * @param text initial text displayed by the area. Defaults to empty string.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [ScrollableTextArea] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.scrollableTextArea(
  text: String = "",
  style: String = defaultStyle,
  init: (@Scene2dDsl ScrollableTextArea).(S) -> Unit = {}
): ScrollableTextArea = actor(ScrollableTextArea(text, style), init)

/**
 * @param text initial text displayed by the field. Defaults to empty string.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisTextField] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visTextField(
  text: String = "",
  style: String = defaultStyle,
  init: (@Scene2dDsl VisTextField).(S) -> Unit = {}
): VisTextField = actor(VisTextField(text, style), init)

/**
 * @param text initial text displayed by the field. Defaults to empty string.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisValidatableTextField] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visValidatableTextField(
  text: String = "",
  style: String = defaultStyle,
  init: (@Scene2dDsl VisValidatableTextField).(S) -> Unit = {}
): VisValidatableTextField = actor(VisValidatableTextField(text, style), init)

/**
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [BusyBar] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.busyBar(
  style: String = defaultStyle,
  init: (@Scene2dDsl BusyBar).(S) -> Unit = {}
): BusyBar = actor(BusyBar(style), init)

/**
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [Separator] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.separator(
  style: String = defaultStyle,
  init: (@Scene2dDsl Separator).(S) -> Unit = {}
): Separator = actor(Separator(style), init)

/**
 * @param text will be displayed as [VisTextButton] text.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisTextButton] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visTextButton(
  text: String,
  style: String = defaultStyle,
  init: KVisTextButton.(S) -> Unit = {}
): KVisTextButton = actor(KVisTextButton(text, style), init)

/**
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisImageButton] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visImageButton(
  style: String = defaultStyle,
  init: KVisImageButton.(S) -> Unit = {}
): KVisImageButton = actor(KVisImageButton(style), init)

/**
 * @param text will be displayed as [VisImageTextButton] text.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisImageTextButton] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visImageTextButton(
  text: String,
  style: String = defaultStyle,
  init: KVisImageTextButton.(S) -> Unit = {}
): KVisImageTextButton = actor(KVisImageTextButton(text, style), init)

/**
 * @param text will be displayed as [VisCheckBox] text.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisCheckBox] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visCheckBox(
  text: String,
  style: String = defaultStyle,
  init: KVisCheckBox.(S) -> Unit = {}
): KVisCheckBox = actor(KVisCheckBox(text, style), init)

/**
 * @param text will be displayed as [VisRadioButton] text.
 * @param style name of the widget style. Defaults to "radio".
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisRadioButton] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visRadioButton(
  text: String,
  style: String = "radio",
  init: KVisRadioButton.(S) -> Unit = {}
): KVisRadioButton = actor(KVisRadioButton(text, style), init)

/**
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisTree] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visTree(
  style: String = defaultStyle,
  init: KVisTree.(S) -> Unit = {}
): KVisTree = actor(KVisTree(style), init)

/**
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [BasicColorPicker] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.basicColorPicker(
  style: String = defaultStyle,
  init: KBasicColorPicker.(S) -> Unit = {}
): KBasicColorPicker = actor(KBasicColorPicker(style), init)

/**
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [ExtendedColorPicker] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.extendedColorPicker(
  style: String = defaultStyle,
  init: KExtendedColorPicker.(S) -> Unit = {}
): KExtendedColorPicker = actor(KExtendedColorPicker(style), init)

/**
 * @param name label of the [Spinner].
 * @param model defines how the [Spinner] values are chosen.
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [Spinner] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.spinner(
  name: String,
  model: SpinnerModel,
  style: String = defaultStyle,
  init: KSpinner.(S) -> Unit = {}
): KSpinner = actor(KSpinner(style, name, model), init)

/**
 * @param defaultSpacing if true, default VisUI spacing will be applied to the table.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisTable] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visTable(
  defaultSpacing: Boolean = false,
  init: KVisTable.(S) -> Unit = {}
): KVisTable = actor(KVisTable(defaultSpacing), init)

/**
 * @param spacing item spacing of this group.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [HorizontalFlowGroup] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.horizontalFlowGroup(
  spacing: Float = 0f,
  init: KHorizontalFlowGroup.(S) -> Unit = {}
): KHorizontalFlowGroup = actor(KHorizontalFlowGroup(spacing), init)

/**
 * @param spacing item spacing of this group.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VerticalFlowGroup] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.verticalFlowGroup(
  spacing: Float = 0f,
  init: KVerticalFlowGroup.(S) -> Unit = {}
): KVerticalFlowGroup = actor(KVerticalFlowGroup(spacing), init)

/**
 * @param itemSize size of stored items.
 * @param spacing item spacing of this group.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [GridGroup] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.gridGroup(
  itemSize: Float = 256f,
  spacing: Float = 8f,
  init: KGridGroup.(S) -> Unit = {}
): KGridGroup = actor(KGridGroup(itemSize, spacing), init)

/**
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [FloatingGroup] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.floatingGroup(
  init: KFloatingGroup.(S) -> Unit = {}
): KFloatingGroup = actor(KFloatingGroup(), init)

/**
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [FloatingGroup] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.dragPane(
  init: KDragPane.(S) -> Unit = {}
): KDragPane = actor(KDragPane(), init)

/**
 * @param style name of the widget style. Defaults to [defaultStyle].
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisScrollPane] instance added to this group. Note that this actor may have only a single child.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visScrollPane(
  style: String = defaultStyle,
  init: (@Scene2dDsl VisScrollPane).(S) -> Unit = {}
): KVisScrollPane = actor(KVisScrollPane(style), init)

/**
 * @param vertical true if the widget is vertical, false if horizontal.
 * @param style name of the widget style. Defaults to [defaultVerticalStyle] if the widget is vertical or
 * [defaultHorizontalStyle] if the widget is horizontal.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [VisSplitPane] instance added to this group. Note that this actor can store only two children.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.visSplitPane(
  vertical: Boolean = false,
  style: String = if (vertical) defaultVerticalStyle else defaultHorizontalStyle,
  init: (@Scene2dDsl VisSplitPane).(S) -> Unit = {}
): KVisSplitPane = actor(KVisSplitPane(vertical, style), init)

/**
 * @param vertical true if the widget is vertical, false if horizontal.
 * @param style name of the widget style. Defaults to [defaultVerticalStyle] if the widget is vertical or
 * [defaultHorizontalStyle] if the widget is horizontal.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [MultiSplitPane] instance added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.multiSplitPane(
  vertical: Boolean = false,
  style: String = if (vertical) defaultVerticalStyle else defaultHorizontalStyle,
  init: KMultiSplitPane.(S) -> Unit = {}
): KMultiSplitPane = actor(KMultiSplitPane(vertical, style), init)

/**
 * @param defaultSpacing if true, default VisUI spacing will be applied to this widget's table.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [CollapsibleWidget] instance with a [VisTable] added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.collapsible(
  defaultSpacing: Boolean = false,
  init: KCollapsible.(S) -> Unit = {}
): KCollapsible = actor(KCollapsible(KVisTable(defaultSpacing)), init)

/**
 * @param defaultSpacing if true, default VisUI spacing will be applied to this widget's table.
 * @param init will be invoked with the widget as "this". Consumes actor container (usually a [Cell] or [Node]) that
 * contains the widget. Might consume the actor itself if this group does not keep actors in dedicated containers.
 * Inlined.
 * @return a [HorizontalCollapsibleWidget] instance with a [VisTable] added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.horizontalCollapsible(
  defaultSpacing: Boolean = false,
  init: KHorizontalCollapsible.(S) -> Unit = {}
): KHorizontalCollapsible = actor(KHorizontalCollapsible(KVisTable(defaultSpacing)), init)

/**
 * @param order buttons order. See [ButtonBar] static variables.
 * @param tableInit will be invoked _after_ [init] on the table storing button bar widgets. Consumes actor container
 * (usually a [Cell] or [Node]) that contains the widget. Might consume the actor itself if this group does not keep
 * actors in dedicated containers. Inlined.
 * @param init will be invoked with the [ButtonBar] as "this". Inlined.
 * @return a [HorizontalCollapsibleWidget] instance with a [VisTable] added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.buttonBar(
  order: String? = null,
  tableInit: VisTable.(S) -> Unit = {},
  init: (@Scene2dDsl ButtonBar).() -> Unit = {}
): ButtonBar {
  val bar = if (order == null) ButtonBar() else ButtonBar(order)
  bar.init()
  actor(bar.createTable(), tableInit)
  return bar
}

/**
 * @param itemAdapter defines [ListView] items.
 * @param style name of the [ListView] style to apply.
 * @param init will be invoked with the [ListView] instance as "this". Inlined.
 * @return a new instance of [ListView]. [ListView.mainTable] will be added to this group.
 */
@Scene2dDsl
inline fun <I> KWidget<*>.listView(
  itemAdapter: ListAdapter<I>,
  style: String = defaultStyle,
  init: ListView<I>.() -> Unit = {}
): ListView<I> {
  val view = ListView(itemAdapter, style)
  storeActor(view.mainTable)
  return view.also(init)
}

/**
 * @param style name of the [TabbedPane.TabbedPaneStyle] style to apply.
 * @param init will be invoked with the [TabbedPane] instance as "this". Consumes container
 * (such as a [Cell] or [Node]) with the [TabbedPane.mainTable], or the table itself if the parent
 * does not store actors in containers.
 * @return a new instance of [TabbedPane]. [TabbedPane.mainTable] will be added to this group.
 */
@Scene2dDsl
inline fun <S> KWidget<S>.tabbedPane(
  style: String = defaultStyle,
  init: KTabbedPane.(S) -> Unit = {}
): KTabbedPane {
  val pane = KTabbedPane(style)
  val table = pane.table
  var storage: S? = null
  actor(table, { storage = it })
  pane.init(storage!!)
  return pane
}
