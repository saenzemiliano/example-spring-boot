package com.example.springboot.demospringboot.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.apache.commons.io.IOUtils;

public class CompressUtil {

	private static final Logger logger = Logger.getLogger(CompressUtil.class.getName());

	public static boolean unzip(String path, String pathDest) throws Exception {

		InputStream initialStream = new FileInputStream(new File(path));
		return unzip(initialStream, pathDest);

	}

	public static boolean unzip(InputStream inputStream, String pathDest) throws Exception {

		byte[] buffer = new byte[inputStream.available()];
		inputStream.read(buffer);
		return unzip(buffer, pathDest);

	}

	public static boolean unzip(byte[] blob, String pathDest) throws Exception {

		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		
		SeekableInMemoryByteChannel inMemoryByteChannel = new SeekableInMemoryByteChannel(blob);
		ZipFile zipFile = new ZipFile(inMemoryByteChannel);
		
		try {
			Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
			while (entries.hasMoreElements()) {
				ZipArchiveEntry zipArchiveEntry = entries.nextElement();
				File file = new File(pathDest, zipArchiveEntry.getName());
				if (zipArchiveEntry.isDirectory()) {
					file.mkdirs();
				} else {
					inputStream = zipFile.getInputStream(zipArchiveEntry);
					File parent = file.getParentFile();
					if (parent != null && parent.exists() == false) {
						parent.mkdirs();
					}
					fileOutputStream = new FileOutputStream(file);
					try {
						IOUtils.copy(inputStream, fileOutputStream);
					} finally {
						closeInputStream(inputStream);
						closeOutputStream(fileOutputStream);
						closeZipFile(zipFile);
					}
				}
			}
		} finally {
			closeInputStream(inputStream);
			closeOutputStream(fileOutputStream);
			closeZipFile(zipFile);
		}

		return true;

	}

	private static void closeZipFile(ZipFile zipFile) {
		try {
			if (zipFile != null) {
				ZipFile.closeQuietly(zipFile);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private static void closeInputStream(InputStream inputStream) {
		try {
			if (inputStream != null)
				inputStream.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private static void closeOutputStream(OutputStream outStream) {
		try {
			if (outStream != null)
				outStream.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}

	}

}
