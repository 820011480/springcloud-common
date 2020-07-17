package com.mady.common.spring.resource;

import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/8 9:57
 * @description
 */
public class FileSystemResource extends AbstractResource {
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件
     */
    @Nullable
    private File file;

    private Path filePath;

    /**
     * 文件路径构造器
     * @param path
     */
    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must not be null");
        this.path = StringUtils.cleanPath(path);
        this.file = new File(path);
        this.filePath = this.file.toPath();
    }

    /**
     * file 构造器
     * @param file
     */
    public FileSystemResource(File file) {
        Assert.notNull(file, "File must not be null");
        this.path = file.getPath();
        this.file = file;
        this.filePath = this.file.toPath();
    }

    /**
     * 构造器
     * @param filePath
     */
    public FileSystemResource(Path filePath) {
        Assert.notNull(filePath, "filePath must not be null");
        this.path = StringUtils.cleanPath(filePath.toString());
        this.file = null;
        this.filePath = filePath;
    }


    public FileSystemResource(FileSystem fileSystem, String path) {
        Assert.notNull(fileSystem, "FileSystem must not be null");
        Assert.notNull(path, "Path must not be null");
        this.path = StringUtils.cleanPath(path);
        this.file = null;
        this.filePath = fileSystem.getPath(this.path).normalize();
    }


    @Override
    public boolean exists() {
        return this.file != null ? file.exists() : Files.exists(this.filePath);
    }

    @Override
    public URL getURL() throws IOException {
        return (this.file != null ? this.file.toURI().toURL() : this.filePath.toUri().toURL());
    }

    @Override
    public URI getURI() throws IOException {
        return this.file != null ? this.file.toURI() : this.filePath.toUri();
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
