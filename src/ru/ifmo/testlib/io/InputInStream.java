package ru.ifmo.testlib.io;

import ru.ifmo.testlib.*;

import java.io.File;

/**
 * An implementation of {@link AbstractInStream} for an input file.
 *
 * This implementation produces a FAIL outcome on presentation error.
 *
 * @author Maxim Buzdalov
 * @author Andrew Stankevich
 * @author Dmitry Paraschenko
 * @author Sergey Melnikov
 */
public class InputInStream extends AbstractInStream {
	public InputInStream(File file) {
		super(file);
	}

	@Override
	protected Outcome.Type mapOutcomeType(Outcome.Type type) {
		return type == Outcome.Type.WA || type == Outcome.Type.PE ? Outcome.Type.FAIL : type;
	}
}
