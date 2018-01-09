package ru.ifmo.testlib.io;

import java.io.File;

import ru.ifmo.testlib.AbstractInStream;
import ru.ifmo.testlib.Outcome;

/**
 * An implementation of {@link AbstractInStream} for an answer file.
 *
 * This implementation produces a FAIL outcome on a presentation error.
 *
 * @author Maxim Buzdalov
 * @author Andrew Stankevich
 * @author Dmitry Paraschenko
 * @author Sergey Melnikov
 */
public class AnswerInStream extends AbstractInStream {
	public AnswerInStream(File file) {
		super(file);
	}

	@Override
	protected Outcome.Type mapOutcomeType(Outcome.Type type) {
		return type == Outcome.Type.WA || type == Outcome.Type.PE ? Outcome.Type.FAIL : type;
	}
}
