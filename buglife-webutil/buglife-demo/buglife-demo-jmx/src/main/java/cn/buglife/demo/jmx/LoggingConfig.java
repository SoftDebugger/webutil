package cn.buglife.demo.jmx;

/**
 * Created by CrazyHarry on 2015/1/5.
 */
import org.apache.log4j.Level;

public interface LoggingConfig {

    /**
     * @param filter returns only loggers, which contain the filter string
     * @return all available loggers
     */
    public String[] getLoggers(String filter);

    /**
     * assigns the {@link Level#INFO} to the given class
     *
     * @param target the FQCN of the class
     */
    public void assignInfoLevel(String target);

    /**
     * assigns the {@link Level#WARN} to the given class
     *
     * @param target the FQCN of the class
     */
    public void assignWarnLevel(String target);

    /**
     * assigns the {@link Level#ERROR} to the given class
     *
     * @param target the FQCN of the class
     */
    public void assignErrorLevel(String target);

    /**
     * assigns the {@link Level#DEBUG} to the given class
     *
     * @param target the FQCN of the class
     */
    public void assignDebug(String target);

    /**
     * assigns the {@link Level#FATAL} to the given class
     *
     * @param target the FQCN of the class
     */
    public void assignFatalLevel(String target);

    /**
     * assigns the {@link Level#TRACE} to the given class
     *
     * @param target the FQCN of the class
     */
    public void assignTraceLevel(String target);

    /**
     * deactivates the logging of the given class
     *
     * @param target the FQCN of the class
     */
    public void deactivateLogging(String target);

    /**
     * reloads the log4j configuration from the <code>log4j.properties</code> file in the classpath
     */
    public void resetConfiguration();

    /**
     * @return the log4j configuration from the <code>log4j.properties</code> file in the classpath
     */
    public String printLog4jConfig();
}
