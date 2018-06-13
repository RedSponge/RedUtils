# Red Utils
###### A library made by RedSponge to ease on 2d Java game development

Red Utils (Remastered) is a library made by me (RedSponge) in Java, which is designed to make creating 2d games a very easy process!
As of now the library supports:

* Input listeners for both mouse and keyboard
* Audio (currently supports ogg)
* Efficient texture loading, and various rendering methods for it
* Multithreading

### Getting started
First thing to do is download the RedUtilsRemastered jar file (latest version is 1.09)
[You can download it here!](https://github.com/RedSponge/RedUtilsRemastered/blob/master/out/1.09/RedUtilsRemastered-1.09v_java8.jar?raw=true)

and add it as a library/dependency to your project.

After you did that, your main class needs to extend `GraphicsApp`

```
public class Example extends GraphicsApp
```

You will need to implement three methods: `init`, `tick` and `render` as well as a constructor:
```
GraphicsApp(String title, int screen_width, int screen_height, int ticksPerSecond, int framesPerSecond)
```
A more complicated version of the constructor contains also `boolean printTPS, boolean printFPS, int numThreads`

**IMPORTANT:** The method `start()` must be called at the end of the constructor or the app won't run

In the end, you should have a class that looks like this:
```
public class Example extends GraphicsApp {
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
```
