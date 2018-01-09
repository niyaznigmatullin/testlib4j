package ru.ifmo.testlib;

import java.io.Closeable;
import java.math.BigInteger;

/**
 * InStream
 *
 * @author Maxim Buzdalov
 * @author Andrew Stankevich
 * @author Dmitry Paraschenko
 * @author Sergey Melnikov
 */
public interface InStream extends Closeable {
    /**
     * A special value denoting end of file.
     */
    int EOF_CHAR = -1;

    /**
     * Returns the next character from the stream, of {@link #EOF_CHAR} if end of file is reached.
     * Moves the current position in the stream to the next character.
     *
     * @return next character from the stream
     */
    int nextChar();

    /**
     * Returns the next character for the input stream.
     * If end of file is reached, returns {@link #EOF_CHAR}.
     * Doesn't move the current position -
     * the following {#nextChar()} or {#currChar()} call returns the same character.
     *
     * @return current character for the stream
     */
    int currChar();

    /**
     * Returns whether the end of file was reached.
     *
     * @return whether the end of file was reached.
     */
    boolean isEoF();

    /**
     * Returns whether the end of line was reached.
     *
     * @return whether the end of line was reached.
     */
    boolean isEoLn();

    /**
     * Skips spaces and tab-characters and returns whether the end of file was reached.
     *
     * @return whether the end of file was reached after skipping all spaces and tab-characters.
     */
    boolean seekEoF();

    /**
     * Skips spaces and tab-characters and returns whether the end of line was reached.
     *
     * @return whether the end of line was reached after skipping all spaces and tab-characters.
     */
    boolean seekEoLn();

    /**
     * Skips current line.
     */
    void skipLine();

    /**
     * Skips all characters from specified string.
     *
     * @param skip the {@link String} containing characters to be skipped.
     */
    void skip(String skip);

    /**
     * Resets the stream.
     */
    void reset();

    /**
     * Closes the stream.
     */
    void close();

    /**
     * Returns next {@link String} token with specified delimiters.
     *
     * @param before the {@link String} containing characters to be used as delimiters before the token.
     * @param after the {@link String} containing characters to be used as delimiters after the token.
     * @param limit an {@code int} denoting the maximum number of characters to be in a token,
     *              if longer token found, then {@link LengthLimitExceeded} is thrown
     *
     * @return next {@link String} token;
     */
    String nextToken(String before, String after, int limit);

    /**
     * Returns next {@link String} token with specified delimiters.
     *
     * @param before the {@link String} containing characters to be used as delimiters before the token.
     * @param after the {@link String} containing characters to be used as delimiters after the token.
     *
     * @return next {@link String} token;
     */
    String nextToken(String before, String after);

    /**
     * Returns next {@link String} token with specified delimiters.
     *
     * @param skip the {@link String} containing characters to be used as delimiters before and after the token.
     * @param limit an {@code int} denoting the maximum number of characters to be in a token,
     *              if longer token found, then {@link LengthLimitExceeded} is thrown
     *
     * @return next {@link String} token;
     */
    String nextToken(String skip, int limit);

    /**
     * Returns next {@link String} token with specified delimiters.
     *
     * @param skip the {@link String} containing characters to be used as delimiters before and after the token.
     *
     * @return next {@link String} token;
     */
    String nextToken(String skip);

    /**
     * Returns next {@link String} token. Whitespace characters are used as delimiters.
     * @param limit an {@code int} denoting the maximum number of characters to be in a token,
     *              if longer token found, then {@link LengthLimitExceeded} is thrown
     *
     * @return next {@link String} token.
     */
    String nextToken(int limit);

    /**
     * Returns next {@link String} token. Whitespace characters are used as delimiters.
     *
     * @return next {@link String} token.
     */
    String nextToken();

    /**
     * Returns next {@code int}. Whitespace characters are used as delimiters.
     *
     * @return next {@code int}.
     */
    int nextInt();

    /**
     * Returns next {@code int} checking it being in range from {@code from} to {@code to} inclusive.
     * Whitespace characters are used as delimiters.
     *
     * @param from {@code int} the starting point of the range
     * @param to {@code int} the ending point of the range
     * @return next {@code int}
     */
    int nextInt(int from, int to);

    /**
     * Returns next {@code int} checking it being in range from {@code from} to {@code to} inclusive.
     * Whitespace characters are used as delimiters.
     *
     * @param from {@code int} the starting point of the range
     * @param to {@code int} the ending point of the range
     * @param variableName {@code String} the name of variable
     * @return next {@code int}
     */
    int nextInt(int from, int to, String variableName);

    /**
     * Returns next {@code long}. Whitespace characters are used as delimiters.
     *
     * @return next {@code long}.
     */
    long nextLong();

    /**
     * Returns next {@code long} checking it being in range from {@code from} to {@code to} inclusive.
     * Whitespace characters are used as delimiters.
     *
     * @param from {@code long} the starting point of the range
     * @param to {@code long} the ending point of the range
     * @return next {@code long}
     */
    long nextLong(long from, long to);

    /**
     * Returns next {@code long} checking it being in range from {@code from} to {@code to} inclusive.
     * Whitespace characters are used as delimiters.
     *
     * @param from {@code long} the starting point of the range
     * @param to {@code long} the ending point of the range
     * @param variableName {@code String} the name of variable
     * @return next {@code long}
     */
    long nextLong(long from, long to, String variableName);

    /**
     * Returns next {@link BigInteger}. Whitespace characters are used as delimiters.
     *
     * @return next {@link BigInteger}.
     */
    BigInteger nextBigInteger();

    /**
     * Returns next {@code float}. Whitespace characters are used as delimiters.
     *
     * @return next {@code float}.
     */
    float nextFloat();

    /**
     * Returns next {@code double}. Whitespace characters are used as delimiters.
     *
     * @return next {@code double}.
     */
    double nextDouble();

    /**
     * Returns unread part of current line of length up to {@code int limit}.
     * @param limit an {@code int} denoting the maximum number of characters to be left in a line,
     *              if unread part is longer, then {@link LengthLimitExceeded} is thrown
     *
     * @return unread part of current line.
     */
    String nextLine(int limit);

    /**
     * Returns unread part of current line.
     *
     * @return unread part of current line.
     */
    String nextLine();

    /**
     * Quits according to the type of {@code InStream}
     *
     * @param type the {@code Outcome.Type} describing the verdict
     * @param format the {@code String} format string
     * @param obj {@code Object...} the list of arguments
     */
    Outcome quit(Outcome.Type type, String format, Object... obj);
}
