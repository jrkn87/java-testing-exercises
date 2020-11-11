package pl.jrkn87.junit;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.util.logging.Logger;

public class IAExceptionHandlerExtension implements TestExecutionExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(IAExceptionHandlerExtension.class.getName());

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if (throwable instanceof IllegalArgumentException) {
            LOGGER.info("Ignore IllegalArgumentException!");
        } else {
            throw throwable;
        }
    }
}
