package ru.ifmo.testlib;

/**
 * Describes Wrong Answer outcome, which was produced on very long {@link String} read.
 * This is a class used in testlib only.
 *
 * @author Niyaz Nigmatullin
 */
class LengthLimitExceeded extends Outcome {
    private String readPart;

    /**
     * Creates the instance using a part of the string that was already read.
     *
     * @param readPart a {@link String} containing a part of the data that was already read
     */
    LengthLimitExceeded(String readPart) {
        super(Type.WA, String.format("Length limit exceeded: found \"%s...\"", readPart));
        this.readPart = readPart;
    }

    /**
     * Returns a {@link String} containing a part of the data that was already read.
     *
     * @return returns specified {@link String}
     */
    String getReadPart() {
        return readPart;
    }
}
