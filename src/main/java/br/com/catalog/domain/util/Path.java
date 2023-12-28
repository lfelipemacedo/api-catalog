package br.com.catalog.domain.util;

import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class Path {

    public static String validateBasePath(String basePath) {
        if (StringUtils.isBlank(basePath)) {
            throw new IllegalArgumentException("Revision`s path is required");
        }

        if (basePath.startsWith("/")) {
            if (!Pattern.matches("^[a-zA-Z0-9_.-]*$", basePath.split("/")[1])) {
                throw new IllegalArgumentException("Revision`s path must not contain special chars");
            }
            return basePath;
        }
        throw new IllegalArgumentException("Revision`s path must start with a '/'");
    }

    public static String validateDestination(String destination) {
        if (!StringUtils.isBlank(destination)) {
            try {
                return new URL(destination).toString();
            } catch (MalformedURLException ex) {
                throw new IllegalArgumentException("Revision`s destination must be valid");
            }
        }
        return destination;
    }
}
