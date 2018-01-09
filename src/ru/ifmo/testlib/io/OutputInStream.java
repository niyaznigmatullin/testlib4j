package ru.ifmo.testlib.io;

import java.io.File;

import ru.ifmo.testlib.AbstractInStream;
import ru.ifmo.testlib.Outcome;

/**
 * An implementation of {@link AbstractInStream} for an output file.
 *
 * This implementation produces a PE outcome for a presentation error.
 *
 * @author Maxim Buzdalov
 * @author Andrew Stankevich
 * @author Dmitry Paraschenko
 * @author Sergey Melnikov
 */
public class OutputInStream extends AbstractInStream {
	public OutputInStream(File file) {
		super(file);
	}

	@Override
	protected Outcome.Type mapOutcomeType(Outcome.Type type) {
		return type;
	}
}
