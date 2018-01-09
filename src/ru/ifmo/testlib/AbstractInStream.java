package ru.ifmo.testlib;

import java.io.*;
import java.math.*;

/**
 * Abstract implementation of {@link InStream} interface.
 *
 * @author Maxim Buzdalov
 * @author Andrew Stankevich
 * @author Dmitry Paraschenko
 * @author Sergey Melnikov
 */
public abstract class AbstractInStream implements InStream {
	/** A file to read data from. */
	private File file;

	/** Current character. */
	private int currChar;

	/** A reader used to read data. */
	private BufferedReader reader;

	/**
	 * Creates new {@link InStream} for specified file.
	 * 
	 * @param file a file to read data from
	 */
	public AbstractInStream(File file) {
		this.file = file;
		reset();
	}

	public void reset() {
		try {
			if (reader != null) {
				reader.close();
			}
			reader = new BufferedReader(new FileReader(file));
		} catch (IOException ex) {
		    // The output file might not exist, because the participant is "evil".
			throw quit(Outcome.Type.PE, "File not found");
		}
		scanChar();
	}

	public void close() {
		try {
			reader.close();
		} catch (IOException ex) {
			// Even if the participant is totally "evil", this must not happen
			throw new Outcome(Outcome.Type.FAIL, "Cannot close file", ex);
		}
	}

	public int currChar() {
		return currChar;
	}

	public int nextChar() {
		scanChar();
		return currChar;
	}

	public boolean isEoF() {
		return currChar == EOF_CHAR;
	}

	public boolean isEoLn() {
		return isEoF() || currChar == '\r' || currChar == '\n';
	}

	public boolean seekEoF() {
		while (!isEoF() && Character.isWhitespace(currChar)) {
			nextChar();
		}
		return isEoF();
	}

	public boolean seekEoLn() {
		while (!isEoLn() && Character.isWhitespace(currChar)) {
			nextChar();
		}
		return isEoLn();
	}

	public void skipLine() {
		while (!isEoLn()) {
			nextChar();
		}
		if (currChar == '\r') nextChar();
		if (currChar == '\n') nextChar();
	}

	public void skip(String skip) {
		while (!isEoF() && skip.indexOf((char) currChar) >= 0) {
			nextChar();
		}
	}

	public String nextToken(String before, String after) {
		while (!isEoF() && before.indexOf((char) currChar) >= 0) {
			nextChar();
		}
		if (isEoF()) {
			throw quit(Outcome.Type.PE, "Unexpected end of file");
		}
		StringBuilder builder = new StringBuilder();
		while (!isEoF() && after.indexOf((char) currChar) < 0) {
			builder.append((char) currChar);
			nextChar();
		}
		return builder.toString();
	}

	public String nextToken(String skip) {
		return nextToken(skip, skip);
	}

	public String nextToken() {
		return nextToken(" \t\r\n");
	}

	public int nextInt() {
		String word = nextToken();
		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException ex) {
			throw quit(Outcome.Type.PE, "A 32-bit signed integer expected, %s found", word);
		}
	}

	@Override
	public int nextInt(int from, int to, String variableName) {
		int result = nextInt();
		if (result < from || result > to) {
			if (variableName.isEmpty()) {
				throw quit(Outcome.Type.WA, "Integer %d violates the range [%d, %d]", result, from, to);
			} else {
				throw quit(Outcome.Type.WA, "Integer parameter [name=%s] equals to %d " +
								"violates the range [%d, %d]", variableName, result, from, to);
			}
		}
		return result;
	}

	@Override
	public int nextInt(int from, int to) {
		return nextInt(from, to, "");
	}

	public long nextLong() {
		String word = nextToken();
		try {
			return Long.parseLong(word);
		} catch (NumberFormatException ex) {
			throw quit(Outcome.Type.PE, "A 64-bit signed integer expected, %s found", word);
		}
	}

	@Override
	public long nextLong(long from, long to, String variableName) {
		long result = nextLong();
		if (result < from || result > to) {
			if (variableName.isEmpty()) {
				throw quit(Outcome.Type.WA, "Integer %d violates the range [%d, %d]", result, from, to);
			} else {
				throw quit(Outcome.Type.WA, "Integer parameter [name=%s] equals to %d " +
								"violates the range [%d, %d]", variableName, result, from, to);
			}
		}
		return result;
	}

	@Override
	public long nextLong(long from, long to) {
		return nextLong(from, to, "");
	}

	public BigInteger nextBigInteger() {
		String word = nextToken();
		try {
			return new BigInteger(word);
		} catch (NumberFormatException ex) {
			throw quit(Outcome.Type.PE, "An integer expected, %s found", word);
		}
	}

	public float nextFloat() {
		String word = nextToken();
		try {
			return Float.parseFloat(word);
		} catch (NumberFormatException ex) {
			throw quit(Outcome.Type.PE, "A float number expected, %s found", word);
		}
	}

	public double nextDouble() {
		String word = nextToken();
		try {
			double v = Double.parseDouble(word);
			if (Double.isInfinite(v) || Double.isNaN(v)) {
    			throw new NumberFormatException(word);
			}
			return v;
		} catch (NumberFormatException ex) {
			throw quit(Outcome.Type.PE, "A double number expected, %s found", word);
		}
	}

	public String nextLine() {
		StringBuilder sb = new StringBuilder();
		while (!isEoLn()) {
			sb.append((char) (currChar));
			nextChar();
		}
		if (currChar == '\r') nextChar();
		if (currChar == '\n') nextChar();

		return sb.toString();
	}

	/**
	 * Returns the next character from the stream. Used by {@see nextChar()} and similar methods.
	 * 
	 * @return next character or {@see EOF_CHAR} if the end of file was reached
	 */
	private int scanChar() {
		try {
			currChar = reader.read();
			if (currChar == -1) {
				currChar = EOF_CHAR;
			}
			return currChar;
		} catch (IOException ex) {
			throw quit(Outcome.Type.PE, "");
		}
	}

	@Override
	public Outcome quit(Outcome.Type type, String format, Object... obj) {
		throw Outcome.quit(mapOutcomeType(type), format, obj);
	}

	/**
	 * Maps {@code Outcome.Type} to the one suitable for specific {@code InStream}
	 *
	 * @param type the {@code Outcome.Type} to map
	 * @return the resulting {@code Outcome.Type}
	 */
	protected abstract Outcome.Type mapOutcomeType(Outcome.Type type);
}
