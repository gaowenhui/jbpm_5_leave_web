package com.toft.widgets.workflow.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class CompressFile {
	private static CompressFile instance = new CompressFile();

	private static final int BUFFEREDSIZE = 1024;

	public static CompressFile getInstance() {
		return instance;
	}

	public synchronized void zip(String inputFilename, String zipFilename)
			throws Exception {
		zip(new File(inputFilename), zipFilename);
	}

	public synchronized void zip(File inputFile, String zipFilename)
			throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFilename));
		try {
			zip(inputFile, out, "");
			out.setEncoding("gbk");
		} catch (IOException e) {
			throw e;
		} finally {
			out.close();
		}
	}

	private synchronized void zip(File inputFile, ZipOutputStream out,
			String base) throws Exception {
		if (inputFile.isDirectory()) {
			File[] inputFiles = inputFile.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < inputFiles.length; i++) {
				zip(inputFiles[i], out, base + inputFiles[i].getName());
			}
		} else {
			if (base.length() > 0) {
				out.putNextEntry(new ZipEntry(base));
			} else {
				out.putNextEntry(new ZipEntry(inputFile.getName()));
			}
			FileInputStream in = new FileInputStream(inputFile);
			try {
				int c;
				byte[] by = new byte[BUFFEREDSIZE];
				while ((c = in.read(by)) != -1) {
					out.write(by, 0, c);
				}
			} catch (IOException e) {
				throw e;
			} finally {
				in.close();
			}
		}
	}

	public synchronized List unzip(String zipFilename) throws Exception {
		List list = new ArrayList();
		ZipFile zipFile = new ZipFile(zipFilename);
		Enumeration en = zipFile.getEntries();
		ZipEntry zipEntry = null;
		while (en.hasMoreElements()) {
			zipEntry = (ZipEntry) en.nextElement();
			if (zipEntry.isDirectory()) {
				continue;
			} else {
				InputStream in = zipFile.getInputStream(zipEntry);
				BufferedReader breader = null;
				StringBuffer buf = null;
				try {
					breader = new BufferedReader(new InputStreamReader(in,
							"UTF-8"));
					buf = new StringBuffer();
					while (breader.ready())
						buf.append((char) breader.read());
					breader.close();
					String s = buf.toString();
					list.add(s);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					in.close();
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		CompressFile bean = new CompressFile();
		try {
			bean.zip("d:/temp", "d:/test.zip");
			List list = bean.unzip("d:/test.zip");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}