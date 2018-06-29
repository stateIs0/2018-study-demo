package cn.think.in.java.learing.nio.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Test {

  public static void main(String[] args) throws IOException {
    File file = new File("ioTest.txt");
    FileOutputStream fo = new  FileOutputStream(file );
    fo.write("hello world".getBytes());

    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    br.read();

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
    pw = new PrintWriter(file);
    pw.write(1);

  }
}
