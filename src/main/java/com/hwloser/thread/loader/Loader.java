package com.hwloser.thread.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Loader {

  private static transient final String name = "Loader-name";
  private volatile int age;

  public Loader() {
    this(10);
  }

  public Loader(int age) {
    this.age = age;
  }

  public static void main(String[] args) throws Exception {
    Loader l = new Loader();
    l.classForName();

    System.out.println(" -------------------------- next -------------------------------------");
    l.classGetName();

    System.out.println(" -------------------------- next -------------------------------------");
    l.classNewInstance();

    System.out.println(" -------------------------- next -------------------------------------");
    l.classGetClassloader();

    System.out.println(" -------------------------- next -------------------------------------");
    l.classGetComponentType();

    System.out.println(" -------------------------- next -------------------------------------");
    l.classGetSuperClass();

    System.out.println(" -------------------------- next -------------------------------------");
    l.classGetConstructor();
  }

  private void classGetConstructor()
      throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<?> clazz = Class.forName("com.hwloser.thread.loader.Loader");
    Constructor<?>[] cs = clazz.getConstructors();
    for (Constructor<?> c : cs) {
      System.out.println(c);

      System.out.println("c.getDeclaringClass()");
      System.out.println(c.getAnnotatedReceiverType());

      System.out.println(" ------------------- ");
      c.newInstance();
    }
  }

  private void classNewInstance()
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    Class<?> clazz = Class.forName("com.hwloser.thread.loader.Loader");
    Loader loader = (Loader) clazz.newInstance();
    System.out.println(loader.age);
  }

  private void classGetSuperClass() throws Exception {
    Class<?> clazz = Class.forName("com.hwloser.thread.loader.Loader");
    System.out.println(clazz.getSuperclass());
  }

  private void classForName() throws ClassNotFoundException, IllegalAccessException {
    Class<?> clazz = Class.forName("com.hwloser.thread.loader.Loader");
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      System.out.println(field);

      System.out.println(" ------------- field get types ------------------------- ");
      System.out.println(field.getGenericType());
      System.out.println(field.getType());

      System.out.println(" ------------- field get modifiers ------------------------- ");
      int modifierNums = field.getModifiers();
      String modifiers = Modifier.toString(modifierNums);
      System.out.println(modifiers);

      System.out.println(" ------------- field get modifiers ------------------------- ");

      field.setAccessible(true);

      System.out.println(" ------------------------- ");
      System.out.println();
    }
  }

  private void classGetName() throws ClassNotFoundException {
    Class<?> clazz = Class.forName("com.hwloser.thread.loader.Loader");
    System.out.println(clazz.getName());
    System.out.println(clazz.getCanonicalName());
    System.out.println(clazz.getSimpleName());
  }

  private void classGetClassloader() throws ClassNotFoundException {
    Class<?> clazz = Class.forName("com.hwloser.thread.loader.Loader");
    ClassLoader cl = clazz.getClassLoader();
    System.out.println(cl);

    System.out.println(" +++++++++++++++++++++++++++ ");
    Class<?> aClazz = Class.forName("java.lang.reflect.Array");
    ClassLoader acl = aClazz.getClassLoader();
    System.out.println(aClazz);
    System.out.println(acl);

    Class<?> s = Class.forName("sun.awt.HKSCS");
    System.out.println(s);
    System.out.println(s.getClassLoader());
  }

  private void classGetComponentType() throws Exception {
    Class<?> clazz = Class.forName("com.hwloser.thread.loader.Loader");
    Class<?> c = clazz.getComponentType();
    System.out.println(c);

  }

}
