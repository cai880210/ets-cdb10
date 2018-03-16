package org.opengis.cite.cdb10.cdbStructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.opengis.cite.cdb10.TestFixture;

public class VerifyMModelTextureStructureTests extends TestFixture<MModelTextureStructureTests> {

	public VerifyMModelTextureStructureTests() {
		this.testSuite = new MModelTextureStructureTests();
	}

	@Test(expected = AssertionError.class)
	public void verifyTNAMPrefix_TooLong() throws IOException {
		// setup
		Files.createDirectories(this.cdb_root.resolve(Paths.get("MModel", "601_MModelTexture",
				"ABC")));

		// execute
		this.testSuite.verifyTNAMPrefix();
	}

	@Test(expected = AssertionError.class)
	public void verifyTNAMPrefix_WrongCase() throws IOException {
		// setup
		Files.createDirectories(this.cdb_root.resolve(Paths.get("MModel", "601_MModelTexture",
				"a")));

		// execute
		this.testSuite.verifyTNAMPrefix();
	}

	@Test
	public void verifyTNAMPrefix_Good() throws IOException {
		// setup
		Files.createDirectories(this.cdb_root.resolve(Paths.get("MModel", "601_MModelTexture",
				"A")));

		// execute
		this.testSuite.verifyTNAMPrefix();
	}



	@Test(expected = AssertionError.class)
	public void verifyTNAMSecond_TooLong() throws IOException {
		// setup
		Files.createDirectories(this.cdb_root.resolve(Paths.get("MModel", "601_MModelTexture",
				"A", "BCD")));

		// execute
		this.testSuite.verifyTNAMSecond();
	}

	@Test(expected = AssertionError.class)
	public void verifyTNAMSecond_WrongCase() throws IOException {
		// setup
		Files.createDirectories(this.cdb_root.resolve(Paths.get("MModel", "601_MModelTexture",
				"A", "b")));

		// execute
		this.testSuite.verifyTNAMSecond();
	}

	@Test
	public void verifyTNAMSecond_Good() throws IOException {
		// setup
		Files.createDirectories(this.cdb_root.resolve(Paths.get("MModel", "601_MModelTexture",
				"A", "B")));

		// execute
		this.testSuite.verifyTNAMSecond();
	}



	@Test(expected = AssertionError.class)
	public void verifyTNAM_TooShort() throws IOException {
		// setup
		Files.createDirectories(this.cdb_root.resolve(Paths.get("MModel", "601_MModelTexture",
				"A", "B", "A")));

		// execute
		this.testSuite.verifyTNAM();
	}

	@Test(expected = AssertionError.class)
	public void verifyTNAM_TooLong() throws IOException {
		// setup
		Files.createDirectories(this.cdb_root.resolve(Paths.get("MModel", "601_MModelTexture",
				"A", "B", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")));

		// execute
		this.testSuite.verifyTNAM();
	}

	@Test(expected = AssertionError.class)
	public void verifyTNAM_WrongChars() throws IOException {
		// setup
		Files.createDirectories(this.cdb_root.resolve(Paths.get("MModel", "601_MModelTexture",
				"A", "B", "$$AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")));

		// execute
		this.testSuite.verifyTNAM();
	}

	@Test(expected = AssertionError.class)
	public void verifyTNAM_Mismatch() throws IOException {
		// setup
		Files.createDirectories(this.cdb_root.resolve(Paths.get("MModel", "601_MModelTexture",
				"A", "B", "AC_1")));

		// execute
		this.testSuite.verifyTNAM();
	}

	@Test
	public void verifyTNAM_Good() throws IOException {
		// setup
		Files.createDirectories(this.cdb_root.resolve(Paths.get("MModel", "601_MModelTexture",
				"A", "B", "AB_1")));

		// execute
		this.testSuite.verifyTNAM();
	}

}