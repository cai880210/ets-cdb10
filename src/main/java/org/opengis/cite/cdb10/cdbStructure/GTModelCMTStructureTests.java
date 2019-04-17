package org.opengis.cite.cdb10.cdbStructure;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GTModelCMTStructureTests extends Capability1Tests {

	/**
	 * Validates that GTModelCMT filenames have valid codes/names.
	 * Test based on Section 3.4.2, Volume 1, OGC CDB Core Standard (Version 1.0)
	 *
	 * @throws IOException DirectoryStream error
	 */
	@Test
	public void verifyCMTFile() throws IOException {
		Path gtModelGeomPath = Paths.get(this.path, "GTModel", "505_GTModelCMT");

		if (Files.notExists(gtModelGeomPath)) {
			return;
		}

		ArrayList<String> errors = new ArrayList<String>();
		
		DirectoryStream<Path> tnamPrefixDirs = Files.newDirectoryStream(gtModelGeomPath);

		for (Path tnamPrefixDir : tnamPrefixDirs) {
			DirectoryStream<Path> secondDirs = Files.newDirectoryStream(tnamPrefixDir);

			for (Path secondDir : secondDirs) {
				DirectoryStream<Path> textureNames = Files.newDirectoryStream(secondDir);

				for (Path textureName : textureNames) {
					DirectoryStream<Path> files = Files.newDirectoryStream(textureName);

					for (Path file : files) {
						validateCMTFile(file, errors);
					}
				}
			}
		}
		
		Assert.assertTrue(errors.size() == 0, StringUtils.join(errors, "\n"));
	}
	
	private void validateCMTFile(Path file, ArrayList<String> errors) {
		final Pattern cmtFilePattern = Pattern.compile(FilenamePatterns.GTModelCMT);
		
		String filename = file.getFileName().toString();
		Matcher match = cmtFilePattern.matcher(filename);

		if (!match.find()) {
			errors.add("Invalid file name: " + filename);
		} else {
			validateComponentSelectorFormat(match.group("cs1"), 1, filename, errors);
			validateComponentSelectorFormat(match.group("cs2"), 2, filename, errors);
			validateTextureNameCode(match.group("tnam"), file, errors);

			if (!match.group("ext").equals("xml")) {
				errors.add("Invalid file extension for D505: " + filename);
			}
		}
	}
}
