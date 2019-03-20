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
		OutputStream outStream = null;
		ZipFile zipFile = null;
		
		try {
			SeekableInMemoryByteChannel inMemoryByteChannel = new SeekableInMemoryByteChannel(blob);
			zipFile = new ZipFile(inMemoryByteChannel);
			
			Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
			while (entries.hasMoreElements()) {
				ZipArchiveEntry zipArchiveEntry = entries.nextElement();
				
				if (zipArchiveEntry.getName().endsWith(File.separator)) {
					File dir = new File(pathDest + File.separator + zipArchiveEntry.getName());
					// TODO: No funciona cuando se intenta descomprimir un directorio
					if (!dir.exists()) {
						dir.mkdirs();
					}
					
					continue;
				}

				File outFile = new File(pathDest + File.separator + zipArchiveEntry.getName());

				if (outFile.isDirectory()) {
					continue;
				}

				if (outFile.exists()) {
					continue;
				}

				inputStream = zipFile.getInputStream(zipArchiveEntry);
				byte[] buffer = new byte[inputStream.available()];
				inputStream.read(buffer);

				outStream = new FileOutputStream(outFile);
				outStream.write(buffer);
				// cierro archivos

			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		} finally {
			closeInputStream(inputStream);
			closeOutputStream(outStream);
			closeZipFile(zipFile);
		}
		
		return true;
	}

	private static void closeZipFile(ZipFile zipFile) {
		try {
			if (zipFile != null)
				zipFile.close();
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
