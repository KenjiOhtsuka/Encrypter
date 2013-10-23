import java.io.IOException;

public class Encrypter {
  static final int BUFFER_LENGTH = 1024;
  
  public static void main(final String param[]) throws IOException {
    if (param.length < 3) {
      System.out.println("引数には 入力ファイル、出力ファイル と 暗号化キー(複数可) を指定してください。");
      System.exit(1);
    } 
    if (param[0].equalsIgnoreCase(param[1])) {
      System.out.println("入力ファイル と 出力ファイル は別名で指定してください(大文字・小文字は区別しません)。");
      System.exit(1);
    }
    // Check paramters
    java.io.File targetFile = 
      new java.io.File(param[0]);
    if (!targetFile.exists() || !targetFile.isFile()) {
      StringBuffer sb = new StringBuffer();
      sb.append("指定されたファイル ");
      sb.append(param[0]);
      sb.append(" が見つかりません。");
      System.out.println(sb.toString());
      System.exit(1);
    }
    String outFileName = param[1];
    byte seedBytes[][] = new byte[param.length - 2][];
    for (int i = 0; i < seedBytes.length; i++) {
      seedBytes[i] = param[i + 2].getBytes();
    }
    java.io.FileInputStream inFile = 
      new java.io.FileInputStream(targetFile);
    java.io.FileOutputStream outFile = 
      new java.io.FileOutputStream(outFileName);
    byte b[] = new byte[seedBytes.length];
    int pos = 0;
    int c;
    while ((c = inFile.read(b)) > 0) {
      for (int i = 0; i < BUFFER_LENGTH && i < c; i++, pos++) {
        for (int j = 0; j < seedBytes.length; j++) {
          b[i] ^= seedBytes[j][pos % seedBytes[j].length];
        }
      }
      outFile.write(b);
    }
    outFile.close();
    inFile.close();
  }
}
