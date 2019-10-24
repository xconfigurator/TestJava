package cn.edu.hebau.liuyang.io;

import java.io.File;

public class TestRenamer {

	private static final String FILE_PATH = "W:/FFOutput";
	
	public static void main(String[] args) {
		File file = new File(FILE_PATH);
		String[] names = file.list();
		for (String str : names) {
			File f = new File(FILE_PATH + File.separator + str);
			if (!f.isDirectory()) {
				File newFile = new File("马原_" + f.getName());
				System.out.println(newFile.getName());
				f.renameTo(newFile);
			}
		}
	}

}
