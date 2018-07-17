package com.babich.userstore.web;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

import static freemarker.template.Configuration.VERSION_2_3_21;


public class PageGenerator {

    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = getTemplate(filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    private Template getTemplate(String name) throws IOException {
        cfg.clearTemplateCache();
        URL resource = getClass().getClassLoader().getResource(".");
        String path = resource.getPath();
        cfg.setDirectoryForTemplateLoading(new File(path));
        return cfg.getTemplate(name);
    }

    public PageGenerator() {
        cfg = new Configuration(VERSION_2_3_21);
    }
}