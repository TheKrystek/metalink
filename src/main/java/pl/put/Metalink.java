package pl.put;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Vector;

/**
 * Author: Krystian Świdurski
 */
public class Metalink extends Task {
    public static final String SERVER_FILES_URL = "server.files.url";
    public static final String USER_DIR = "user.dir";
    private String url, file;
    private Vector filesets = new Vector();
    private MetalinkFile metalinkFile = new MetalinkFile();
    private String userDir;

    @Override
    public void execute() throws BuildException {
        super.execute();
        url = getUrl();
        validate();
        userDir = getProject().getProperty(USER_DIR);

        for (Object obj : filesets) {
            if (obj instanceof FileSet)
                addFiles(((FileSet) obj).getDir());
        }

        XMLWriter writer = new XMLWriter();
        try {
            writer.write(metalinkFile, file);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new BuildException("Could not save file!");
        }
    }

    private void addFiles(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                creteFileInfo(f);
            }
        } else {
            creteFileInfo(file);
        }
    }

    private void creteFileInfo(File file) {
        if (file.isDirectory())
            return;

        FileInfo info = new FileInfo();
        String name = file.getPath().replace(userDir + "\\", "");
        info.name = name;
        info.url = url + name.replace("\\", "/");
        info.hash.value = MD5Util.calcMD5(file);
        info.size = file.length();
        metalinkFile.add(info);
    }

    private void validate() {
        if (url == null) throw new BuildException("Attribute \"url\" is not specified!");
        if (file == null) throw new BuildException("Attribute \"file\" is not specified!");
    }

    private String getUrl() {
        if (url == null) {
            url = getProject().getProperty(SERVER_FILES_URL);
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void addFileset(FileSet fileset) {
        filesets.add(fileset);
    }
}
