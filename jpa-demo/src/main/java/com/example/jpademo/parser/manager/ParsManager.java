package com.example.jpademo.parser.manager;

import com.example.jpademo.bd.BDProduct;
import com.example.jpademo.bd.BDUser;
import com.example.jpademo.parser.interfaces.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

@Component
public class ParsManager {

    @Autowired
    private Parser<BDUser> userParser;

    @Autowired
    private Parser<BDProduct> productParser;

    public BDUser parseUser(String path) throws Exception {
        InputStream input = new FileInputStream(Paths.get(path + userParser.getExtension()).toFile());
        return userParser.parse(input);
    }

    public void write(BDUser user, String path) throws Exception {
        OutputStream output = new FileOutputStream(Paths.get(path + userParser.getExtension()).toFile());
        userParser.write(user, output);
    }

    public BDProduct parseProduct(String path) throws Exception {
        InputStream input = new FileInputStream(Paths.get(path + productParser.getExtension()).toFile());
        return productParser.parse(input);
    }

    public void write(BDProduct product, String path) throws Exception {
        OutputStream output = new FileOutputStream(Paths.get(path + productParser.getExtension()).toFile());
        productParser.write(product, output);
    }


}
