package com.latinominds.rditsln.daily.challenge85.easy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/***
 * 
 * From: http://www.reddit.com/r/dailyprogrammer/comments/xq0yv/832012_challenge_85_easy_rowcolumn_sorting/
 * 
 * 
 * @author Jose Martinez
 * 
 */
public class Solution {

	class IndexSum implements Comparable<IndexSum> {
		IndexSum(int index, int sum) {
			this.index = index;
			this.sum = sum;
		}

		int index;
		int sum;

		public int compareTo(IndexSum obj) {
			if (obj == null) {
				return 1;
			}
			return Integer.valueOf(sum).compareTo(Integer.valueOf(obj.sum));
		}
	}

	public String sumAndSortMatrix(InputStream input) throws IOException {
		StringBuilder result = new StringBuilder();
		List<String> lines = IOUtils.readLines(input);

		removeEmptyLines(lines);

		if (lines.isEmpty()) {
			throw new IllegalArgumentException("empty input");
		}

		// size of lines tells us how many rows, take size of columns from first line
		int rowSize = lines.size();
		int colSize = lines.get(0).split("\\s").length;

		// create 2 dimensional array of ints
		int matrix[][] = new int[rowSize][colSize];

		List<IndexSum> rowSums = new ArrayList<Solution.IndexSum>();

		int rowIndex = 0;
		for (String line : lines) {
			String[] numbers = line.split("\\s");
			int rowSum = 0;
			for (int i = 0; i < numbers.length; i++) {
				matrix[rowIndex][i] = Integer.valueOf(numbers[i]).intValue();
				rowSum += matrix[rowIndex][i];
			}
			rowSums.add(new IndexSum(rowIndex, rowSum));
			rowIndex++;
		}

		// add row sums to result
		result.append("Rows:");
		for (IndexSum isum : rowSums) {
			result.append(' ').append(isum.sum);
		}
		result.append('\n');

		List<IndexSum> colSums = new ArrayList<Solution.IndexSum>();

		for (int i = 0; i < colSize; i++) {
			int colSum = 0;
			for (int j = 0; j < rowSize; j++) {
				colSum += matrix[j][i];
			}
			colSums.add(new IndexSum(i, colSum));
		}

		// add column sums to result
		result.append("Columns:");
		for (IndexSum isum : colSums) {
			result.append(' ').append(isum.sum);
		}
		result.append('\n').append('\n');

		// sort rows sums and add to result
		Collections.sort(rowSums);
		for (IndexSum isum : rowSums) {
			int[] rowarr = matrix[isum.index];
			addNumberLine(rowarr, result);
			// this can be made more efficient by re-using
			// the strings from the input list
		}
		result.append('\n');

		// sort the column sums and add to the result
		Collections.sort(colSums);
		for (int i = 0; i < rowSize; i++) {
			int[] rowarr = indexedByCol(matrix[i], colSums);
			addNumberLine(rowarr, result);
		}

		// remove last new line added
		result.deleteCharAt(result.length() - 1);

		return result.toString();
	}

	private int[] indexedByCol(int[] row, List<IndexSum> colSums) {
		int[] reordered = new int[row.length];
		for (int i = 0; i < colSums.size(); i++) {
			reordered[i] = row[colSums.get(i).index];
		}
		return reordered;
	}

	private void addNumberLine(int[] intarray, StringBuilder result) {
		for (int i = 0; i < intarray.length; i++) {
			result.append(intarray[i]);
			if (i < intarray.length - 1) {
				result.append(' ');
			}
		}
		result.append('\n');
	}

	private void removeEmptyLines(List<String> lines) {
		// use list iterator to remove empty lines
		ListIterator<String> it = lines.listIterator();
		while (it.hasNext()) {
			if (StringUtils.isBlank(it.next())) {
				it.remove();
			}
		}
	}

}
