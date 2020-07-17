package com.mady.common.spring.resource;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/8 9:58
 * @description
 */
public class ClassPathResource extends AbstractResource {

    @Override
    public boolean exists() {
        return super.exists();
    }

    @Override
    public URL getURL() throws IOException {
        return super.getURL();
    }

    @Override
    public URI getURI() throws IOException {
        return super.getURI();
    }

    @Override
    public File getFile() throws IOException {
        return super.getFile();
    }

    @Override
    public long contentLength() throws IOException {
        return super.contentLength();
    }

    @Override
    public long lastModified() throws IOException {
        return super.lastModified();
    }

    @Override
    public Resource createRelative(String relativePath) throws IOException {
        return super.createRelative(relativePath);
    }

    @Override
    public String getFilename() {
        return super.getFilename();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public boolean isReadable() {
        return super.isReadable();
    }

    @Override
    public boolean isOpen() {
        return super.isOpen();
    }

    @Override
    public boolean isFile() {
        return super.isFile();
    }
}
