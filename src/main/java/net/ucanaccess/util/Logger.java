/*
Copyright (c) 2012 Marco Amadei.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package net.ucanaccess.util;

import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.logging.Level;

public final class Logger {
    public enum Messages {
        HSQLDB_DRIVER_NOT_FOUND,
        COMPLEX_TYPE_UNSUPPORTED,
        KEEP_MIRROR_AND_OTHERS,
        UNKNOWN_EXPRESSION,
        DEFAULT_VALUES_DELIMETERS,
        USER_AS_COLUMNNAME,
        ROW_COUNT,
        TRIGGER_UPDATE_CF_ERR,
        INVALID_CHARACTER_SEQUENCE,
        STATEMENT_DDL,
        CONSTRAINT,
        LOBSCALE,
        FUNCTION_ALREADY_ADDED,
        NO_SELECT
    }

    private static final org.slf4j.Logger internalLog = LoggerFactory.getLogger(Logger.class);
    private static PrintWriter    logPrintWriter;
    private static ResourceBundle messageBundle = ResourceBundle.getBundle("net.ucanaccess.util.logger_messages");

    private Logger() {
    }

    public static void dump() {
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement el : Thread.currentThread().getStackTrace()) {
            builder.append(el.toString() + "\n");
        }
        internalLog.debug(builder.toString());
    }

    public static void turnOffJackcessLog() {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.healthmarketscience.jackcess");
        logger.setLevel(Level.OFF);
    }

    public static PrintWriter getLogPrintWriter() {
        return logPrintWriter;
    }

    public static String getMessage(String cod) {
        return messageBundle.getString(cod);
    }

    public static String getMessage(String cod, Object... pars) {
        return String.format(messageBundle.getString(cod), pars);
    }

    public static void log(Object obj) {
        internalLog.info(obj.toString());
    }

    public static void logMessage(Messages cod) {
        log(messageBundle.getString(cod.name()));
    }

    public static String getLogMessage(Messages cod) {
        return messageBundle.getString(cod.name());
    }

    public static void logWarning(String warning) {
        internalLog.warn(warning);
    }

    public static void logWarning(Messages cod) {
        logWarning(messageBundle.getString(cod.name()));
    }

    public static void logParametricWarning(Messages cod, String... par) {
        logWarning(String.format(messageBundle.getString(cod.name()), (Object[]) par));
    }

    public static void setLogPrintWriter(PrintWriter _logPrintWriter) {
        Logger.logPrintWriter = _logPrintWriter;
    }
}
