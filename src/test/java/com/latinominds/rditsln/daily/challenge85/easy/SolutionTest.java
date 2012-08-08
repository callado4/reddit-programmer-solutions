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
		
		String answerStr = solution.sumAndSortMatrix(input);
		assertNotNull(answerStr);
		
		InputStream expectedAnswer = getClass().getResourceAsStream("simple-answer.txt");

		String[] answerArray = answerStr.split("\n");
		List<String> expAnsList = IOUtils.readLines(expectedAnswer);

		assertEquals(expAnsList.size(), answerArray.length);
		int i = 0;
		for (String expectedLine : expAnsList) {
			assertEquals(expectedLine, answerArray[i++]);
		}
	}
}
