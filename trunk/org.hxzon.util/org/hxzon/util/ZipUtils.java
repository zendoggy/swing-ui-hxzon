package org.hxzon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

	public static void zip(String outputFileName, String inputFileName) {
		ZipOutputStream out;
		try {
			out = new ZipOutputStream(new FileOutputStream(outputFileName));
			zip(out, new File(inputFileName), "");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void zip(String inputFileName) {
		zip(inputFileName + ".zip", inputFileName);
	}

	private static void zip(ZipOutputStream out, File f, String base)
			throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			// System.out.println(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}

	public static void unzip(String outputFileName) {
		int index = outputFileName.lastIndexOf(".");
		String inputFileName = outputFileName.substring(0, index);
		unzip(outputFileName, inputFileName);
	}

	public static void unzip(String zipFilename, String outputDirectory) {
		try {
			File outFile = new File(outputDirectory);
			if (!outFile.exists()) {
				outFile.mkdirs();
			}
			ZipFile zipFile = new ZipFile(zipFilename);
			Enumeration en = zipFile.entries();
			ZipEntry zipEntry = null;
			while (en.hasMoreElements()) {
				zipEntry = (ZipEntry) en.nextElement();
				if (zipEntry.isDirectory()) {
					// mkdir directory
					String dirName = zipEntry.getName();
					dirName = dirName.substring(0, dirName.length() - 1);

					File f = new File(outFile.getPath() + "/" + dirName);
					f.mkdirs();

				} else {
					// unzip file
					File f = new File(outFile.getPath() + "/"
							+ zipEntry.getName());
					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(f);
					int c;
					byte[] by = new byte[1024];
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] temp) {
//		ZipUtils.zip("D:/asn1-dec");// 你要压缩的文件夹
//		ZipUtils.unzip("D:/asn1-dec.zip");
//	}
}
