package gwt.test.annotations;

import java.util.logging.Level;

public enum LogLevel {
	WARNING(Level.WARNING),SEVERE(Level.SEVERE),OFF(Level.OFF),INFO(Level.INFO),FINEST(Level.FINEST),FINER(Level.FINER),FINE(Level.FINE),CONFIG(Level.CONFIG);
	
	private final Level level;
	
	LogLevel(Level level) {
		this.level = level;
	}
	
	Level getLevel() {
		return this.level;
	}
}
