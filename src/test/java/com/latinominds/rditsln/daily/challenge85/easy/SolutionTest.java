package com.latinominds.rditsln.daily.challenge85.easy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class SolutionTest {

	private final Solution solution = new Solution();

	@Test
	public void testSimpleMatrix() throws IOException {
		InputStream input = getClass().getResourceAsStream("simple-input.txt");
		InputStream expectedAnswer = getClass().getResourceAsStream("simple-answer.txt");
		
		checkIfOutputCorrect(input, expectedAnswer);
	}

	@Test
	public void testLargeMatrix() throws IOException {
		InputStream input = getClass().getResourceAsStream("large-input.txt");
		InputStream expectedAnswer = getClass().getResourceAsStream("large-answer.txt");

		checkIfOutputCorrect(input, expectedAnswer);
	}

	private void checkIfOutputCorrect(InputStream input, InputStream expectedAnswer) throws IOException {
		String answerStr = solution.sumAndSortMatrix(input);
		System.out.println("======== answerStr ===============");
		System.out.println(answerStr);
		System.out.println("======== answerStr ===============");
		assertNotNull(answerStr);

		String[] answerArray = answerStr.split("\n");
		List<String> expAnsList = IOUtils.readLines(expectedAnswer);

		assertEquals(expAnsList.size(), answerArray.length);
		int i = 0;
		for (String expectedLine : expAnsList) {
			assertEquals(expectedLine.trim(), answerArray[i++].trim());
		}
	}
}
