package cn.think.in.java.learing.loader;

public class HotSwapClassLoader extends ClassLoader {

  public HotSwapClassLoader() {
    super(HotSwapClassLoader.class.getClassLoader());

  }

  public Class loadByte(byte[] classByte) {
    return defineClass(null, classByte, 0, classByte.length);
  }


}

class ClassModifier {

  public static final int CONTANT_POOL_COUNT_INDEX = 8;
  public static final int CONTANT_UTF8_INFO = 1;
  public static final int[] CONSTANT_ITEN_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};

  public static final int u1 = 1;
  public static final int u2 = 2;

  private byte[] classByte;

  public ClassModifier(byte[] classByte) {
    this.classByte = classByte;
  }

  public byte[] modifyUTF8Constant(String oldStr, String newStr) {
    int cpc = getConstantPoolCount();
    int offect = CONTANT_POOL_COUNT_INDEX + u2;
    for (int i = 0; i < cpc; i++) {
      int tag = ByteUtils.bytes2Int(classByte, offect, u1);
      if (tag == CONTANT_UTF8_INFO) {
        int len = ByteUtils.bytes2Int(classByte, offect + u1, u2);
        offect += (u1 + u2);
        String str = ByteUtils.bytes2String(classByte, offect, len);
        if (str.equalsIgnoreCase(oldStr)) {
          byte[] strBytes = ByteUtils.string2Bytes(newStr);
          byte[] strLen = ByteUtils.int2Bytes(newStr.length(), u2);
          classByte = ByteUtils.bytesReplace(classByte, offect - u2, u2, strLen);
          classByte = ByteUtils.bytesReplace(classByte, offect, len, strBytes);
          return classByte;
        } else {
          offect += len;
        }
      } else {
        offect += CONSTANT_ITEN_LENGTH[tag];
      }

    }
    return classByte;
  }

  public int getConstantPoolCount() {
    return ByteUtils.bytes2Int(classByte, CONTANT_POOL_COUNT_INDEX, u2);
  }
}

class ByteUtils {

  public static int bytes2Int(byte[] b, int start, int len) {
    int sum = 0;
    int end = start + len;
    for (int i = start; i < end; i++) {
      int n = ((int) b[i]) & 0xff;
      n <<= (--len) * 8;
      sum = n + sum;
    }
    return sum;
  }

  public static byte[] int2Bytes(int value, int len) {
    byte[] b = new byte[len];
    for (int i = 0; i < len; i++) {
      b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
    }
    return b;
  }

  public static String bytes2String(byte[] b, int start, int len) {
    return new String(b, start, len);
  }

  public static byte[] string2Bytes(String str) {
    return str.getBytes();
  }

  public static byte[] bytesReplace(byte[] originalBytes, int offset, int len,
      byte[] replaceBytes) {
    byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
    System.arraycopy(originalBytes, 0, newBytes, 0, offset);
    System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
    System.arraycopy(originalBytes, offset + len, newBytes, offset + replaceBytes.length,
        originalBytes.length - offset - len);
    return newBytes;
  }
}
