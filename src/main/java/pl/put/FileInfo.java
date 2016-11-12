package pl.put;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Author: Krystian Świdurski
 */

@XmlRootElement(name = "file")
public class FileInfo {
    public String name, url;
    public Hash hash = new Hash();
    public long size;
}
