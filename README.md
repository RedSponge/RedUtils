# Red Utils
###### A library made by RedSponge to ease on 2d Java game development

Red Utils (Remastered) is a library made by me (RedSponge) in Java, which is designed to make creating 2d games a very easy process!
As of now the library supports:

* Input listeners for both mouse and keyboard
* Audio (currently supports ogg)
* Efficient texture loading, and various rendering methods for it
* Multithreading

### Getting started
First thing to do is download the RedUtilsRemastered jar file (check releases for download or just go to the builds folder)

and add it as a library/dependency to your project.

After you did that, your main class needs to extend `GraphicsApp`

```java
public class Example extends GraphicsApp
```

You will need to implement three methods: `init`, `tick` and `render` as well as a constructor:
```java
GraphicsApp(String title, int screen_width, int screen_height, int ticksPerSecond, int framesPerSecond)
```
A more complicated version of the constructor contains also `boolean printTPS, boolean printFPS, int numThreads`

**IMPORTANT:** The method `start()` must be called at the end of the constructor or the app won't run

In the end, you should have a class that looks like this:
```java
public class ExampleApp extends GraphicsApp {
  public Example() {
    super("An example app", 640, 480, 60, 60)  
    start()
  }
  
  @Override
  public void init() {
  
  }
  
  @Override
  public void tick() {
  
  }
  
  @Override
  public void render() {
  
  }
}
```

In your main method, just create a new instance of the main class.
```java
public static void main(String[] args) {
  new Example();
}
```

### Main Class Instances (Version 1.10 and up)

To create an instance of the class all you need to do is add a public static field of your class with the `@Instance` annotation above it.
```java
@Instance
public static ExampleApp INSTANCE;
```
Its value will be injected using reflection during the initiation sequence.

### Mouse And Keyboard Input (Version 1.08 and up)

Every `GraphicsApp` instance has a protected `InputManager` field that is registered to its frame, the input manager has the following methods:

*  `registerToDisplay(GraphicsDisplay display)` - Adds the mouse and key listeners to the display, automatically done by GraphicsApp
  
*  `isKeyHeld(int key)` - returns true as long as the key is pressed\held
*  `isKeyJustPressed(int key)` - returns true for the first tick the key is held
*  `isKeyReleased(int key)` - returns true as long as the key is not pressed\held
*  `isKeyJustReleased(int key)` - returns true for the first tick after the key has been released
  
*  `isMouseButtonHeld(int button)` - returns true as long as the button is pressed\held
*  `isMouseButtonJustPressed(int button)` - returns true for the first tick the button is held
*  `isMouseButtonReleased(int button)` - returns true as long as the button is not pressed\held
*  `isMouseButtonJustReleased(int button)` returns true for the first tick after the button has been released
  
*  `getMouseX()` - returns current mouse X position
*  `getMouseY()` - returns current mouse Y position

To get the inputManager outside of the main class, use the `GraphicsApp.getInputManager()` on an instance of your class

### Rendering and the Graphics2D Object
To render, use the `Graphics2D g` field which is a protected field in the `GraphicsApp` class, and can be used freely:
```java
@Override
public void render() {
  g.drawRect(100, 100, 200, 200);
  g.fill(aRectangleIDeclaredEarlier);
}
```

### Textures

#### Internal (Version 1.09 and up)
Using the `Texture.load(String path)` method you can load internal textures (from a res folder for example). if you're using png, you can type the name without the ".png" extension, otherwise, add the extension in the path.

##### To create a res(ources) folder:

1. Right click the project icon
2. New > Folder/Directory
3. Call it something like `resources` or `res` (or anything else you'd like to call it)
4. **Eclipse**: Right click the folder, Build Path > Use as Source Folder

**Intellij**: Right click the folder, Mark directory as > Sources Root

The folder should now look like the `src` folder and can now contain images and audio for internal loading

#### External
Not implemented yet.

#### Rendering
To render a texture, use the `Texture.render(...)` method, there are many variations of it so choose what suits you. all of them request the `Graphics2D g` object and the x and y coordinates.

### Audio (Version 1.07 and up)
To get audio objects, use the  `AudioManager` class to retrieve `IAudioClip` instances.

`IAudioClip` instances have methods such as `play()`, `stop()`, `pause()` and `loop()`.

#### Supported Formats:
* Ogg - `AudioManager.Ogg.getClip(String path)` (Since: **1.07**)
* Wave - `AudioManager.Wave.getClip(String path)` (Since: **1.11**)
