public class HelloWorld {

  // java native method
  public native void displayHelloWorld();

  static {
    // load DLL, HelloWorldNativeImpl -- load lib names
    System.loadLibrary("HelloWorldNativeImpl");
  }

  public static void main(String[] args) {
    HelloWorld h = new HelloWorld();
    h.displayHelloWorld();
  }

}
