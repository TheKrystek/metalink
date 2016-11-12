package pl.put;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Krystian Świdurski
 */
@XmlRootElement(name = "metalink")
public class MetalinkFile {
    public Date published = new Date();
    public List<FileInfo> files = new ArrayList<FileInfo>();

    public void add(FileInfo fileInfo) {
        files.add(fileInfo);
    }
}
