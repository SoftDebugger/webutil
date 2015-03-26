package cn.buglife.webutil.web.util;

import org.apache.log4j.Level;

/**
 * 自定义LOG4j级别LIFE
 *
 * Created by CrazyHarry on 2015/3/27.
 */
public class Life extends Level {
    private static final long serialVersionUID = 1L;

    protected Life(int level, String levelStr, int syslogEquivalent) {
        super(level, levelStr, syslogEquivalent);
    }

    /**
     * 定义log的权重为介于OFF和FATAL之间，便于打印LIFE级别的log
     */
    public static final int LIFE_INT = OFF_INT - 10;

    public static final Level LIFE = new Life(LIFE_INT, "LIFE", 10);

    public static Level toLevel(String logArgument) {
        if (logArgument != null && logArgument.toUpperCase().equals("LIFE")) {
            return LIFE;
        }
        return (Level) toLevel(logArgument);
    }

    public static Level toLevel(int val) {
        if (val == LIFE_INT) {
            return LIFE;
        }
        return (Level) toLevel(val, Level.DEBUG);
    }

    public static Level toLevel(int val, Level defaultLevel) {
        if (val == LIFE_INT) {
            return LIFE;
        }
        return Level.toLevel(val, defaultLevel);
    }

    public static Level toLevel(String logArgument, Level defaultLevel) {
        if (logArgument != null && logArgument.toUpperCase().equals("LIFE")) {
            return LIFE;
        }
        return Level.toLevel(logArgument, defaultLevel);
    }
}
