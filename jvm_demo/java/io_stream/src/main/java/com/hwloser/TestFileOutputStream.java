package com.hwloser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.UUID;

public class TestFileOutputStream {

  public static void main(String[] args) throws FileNotFoundException {
    String tempDir = System.getProperty("java.io.tmpdir");

    File subFile = new File(tempDir, UUID.randomUUID().toString());

    PrintWriter p = new PrintWriter(new FileOutputStream(subFile));
    p.write("nihao");
    p.flush();
    p.close();

    System.out.println(subFile);
  }
}
