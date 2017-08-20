package com.nidone.fitness.service;


import org.apache.commons.io.IOUtils;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-config.xml" })
public abstract class BaseTestCase extends AbstractJUnit4SpringContextTests {
    public static void main(String[] args) throws Exception {
        String s = IOUtils.toString(new FileInputStream(new File("D:\\tt.jpg")));
        byte[] buffer = new BASE64Decoder().decodeBuffer(s);

        FileOutputStream out = new FileOutputStream("d:\\s.jpg");
        out.write(buffer);
        out.close();
    }
}