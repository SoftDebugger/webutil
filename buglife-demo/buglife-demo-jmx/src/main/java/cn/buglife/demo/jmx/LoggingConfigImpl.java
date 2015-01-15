package cn.buglife.demo.jmx;

/**
 * Created by CrazyHarry on 2015/1/5.
 */
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Notification;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.config.PropertyPrinter;
import org.apache.log4j.spi.LoggerRepository;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;

public class LoggingConfigImpl implements LoggingConfig, NotificationPublisherAware {


    private Map<NotificationType, Long> notificationTypeMap = new HashMap<NotificationType, Long>();
    private NotificationPublisher publisher;

    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.publisher = notificationPublisher;
    }

    public String[] getLoggers(String filter) {
        LoggerRepository r = LogManager.getLoggerRepository();

        Enumeration<Logger> enumList = r.getCurrentLoggers();

        Logger logger = null;
        List<String> resultList = new ArrayList<String>();
        while (enumList.hasMoreElements()) {
            logger = (Logger) enumList.nextElement();
            if (filter == null
                    || (filter != null && logger.getName().contains(filter))) {
                resultList.add(logger.getName());
            }
        }

        return (String[]) resultList.toArray(new String[] {});
    }

    public void assignInfoLevel(String target) {
        assignLogLevel(target, Level.INFO);
    }

    public void assignWarnLevel(String target) {
        assignLogLevel(target, Level.WARN);
    }

    public void assignErrorLevel(String target) {
        assignLogLevel(target, Level.ERROR);
    }

    public void assignDebug(String target) {
        assignLogLevel(target, Level.DEBUG);
    }

    public void assignFatalLevel(String target) {
        assignLogLevel(target, Level.FATAL);
    }

    public void deactivateLogging(String target) {
        assignLogLevel(target, Level.OFF);
    }

    public void assignTraceLevel(String target) {
        assignLogLevel(target, Level.TRACE);
    }

    private void assignLogLevel(String target, Level level) {
        String message = level.toString() + " for '" + target + "'";
        Logger existingLogger = LogManager.exists(target);
        if(existingLogger != null) {
            Level currentLevel = existingLogger.getLevel();
            if(currentLevel == null) {
                message = "initial to " + message;
            } else {
                message = "from " + currentLevel.toString() + " to " + message;
            }
        }

        LogManager.getLogger(target).setLevel(level);
        sendNotification(NotificationType.CHANGE_LOG_LEVEL, message);
    }

    private synchronized void sendNotification(NotificationType notificationType, String message) {
        Long counter = 0L;
        if(!notificationTypeMap.containsKey(notificationType))
            notificationTypeMap.put(notificationType, counter);

        counter = notificationTypeMap.get(notificationType);
        notificationTypeMap.put(notificationType, Long.valueOf(counter + 1));

        Notification notification = new Notification(notificationType.toString(), this, counter);
        notification.setUserData(message);
        publisher.sendNotification(notification);
    }

    public void resetConfiguration() {


        ClassLoader cl = getClass().getClassLoader();
        LogManager.resetConfiguration();
        URL log4jprops = cl.getResource("log4j.properties");
        if (log4jprops != null) {
            PropertyConfigurator.configure(log4jprops);
        }
        sendNotification(NotificationType.RESET_CONFIGURATION , "used file: " + log4jprops.getFile());
    }

    public String printLog4jConfig() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        PropertyPrinter pp = new PropertyPrinter(pw);
        pp.print(pw);
        // System.out.println(sw.toString());
        return sw.toString();
    }


}

