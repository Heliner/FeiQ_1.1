package Test;

import java.io.File;
import java.io.IOException;

public class FileDemo {
	public static void main(String[] s) throws IOException {
		File file = new File("a");
		if (!file.exists()) {
			file.createNewFile();
			System.out.println("文件创建完毕!");
		}
		
	}
}
