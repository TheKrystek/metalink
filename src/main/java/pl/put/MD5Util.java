package pl.put;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * Author: Krystian Świdurski
 */
public class MD5Util {

    public static String calcMD5(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            String md5 = DigestUtils.md5Hex(fis);
            fis.close();
            return md5;
        } catch (Exception e) {
            return "";
        }
    }

}
