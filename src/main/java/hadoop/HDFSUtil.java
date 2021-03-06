package hadoop;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HDFSUtil {

    public  static   Configuration conf = new Configuration();
    private static  String HDFS_URI = "";
    private static  String USR_PERMISSION = "root";
    static {
        conf.set("fs.defaultFS", "");
    }
    private HDFSUtil() {
    }

    public static void main(String[] args) throws Exception {
        HDFSUtil.createFile("/1.txt","sss");
    }
    /**
     * 新建文件
     *
     * @param filePath
     * @param data
     * @throws IOException
     */
    public static void createFile( String filePath,
                                  byte[] data) throws Exception{
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), conf, USR_PERMISSION);
        FSDataOutputStream outputStream = fs.create(new Path(filePath));
        outputStream.write(data);
        outputStream.close();
        fs.close();
    }

    /**
     * 新建文件
     *
     * @param filePath
     * @param data
     * @throws IOException
     */
    public static void createFile(String filePath,
                                  String data) throws Exception {
        createFile(filePath, data.getBytes());
    }

    /**
     * 从本地上传到HDFS
     * @param conf
     * @param localPath 本地文件路径
     * @param remotePath HDF文件S路径
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static void copyFileFromLocal(Configuration conf,String localPath,String remotePath) throws Exception{
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), conf, USR_PERMISSION);
        fs.copyFromLocalFile(new Path(localPath), new Path(remotePath));
    }

    /**
     * 归删除文件
     * @param conf
     * @param filePath
     * @return
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static boolean deleteFileRecursive(Configuration conf, String filePath) throws Exception{
        return deleteFile(conf,filePath,true);
    }

    /**
     * 非递归删除文件
     * @param conf
     * @param filePath
     * @return
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static boolean deleteFile(Configuration conf, String filePath) throws Exception{
        return deleteFile(conf,filePath,false);
    }

    /**
     * 删除文件
     * @param conf
     * @param filePath
     * @param recursive
     * @return
     * @throws IllegalArgumentException
     * @throws IOException
     */
    private static boolean deleteFile(Configuration conf, String filePath,boolean recursive) throws Exception{
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), conf, USR_PERMISSION);
        return fs.delete(new Path(filePath),recursive);
    }

    /**
     * 创建文件夹
     *
     * @param conf
     * @param dirPath
     * @return
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static boolean mkdir(Configuration conf, String dirPath) throws Exception {
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), conf, USR_PERMISSION);
        return fs.mkdirs(new Path(dirPath));
    }

    /**
     * 读取文件内容
     * @param conf
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String readFile(Configuration conf, String filePath) throws Exception {
        String res = null;
        FileSystem fs = null;
        FSDataInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            fs =FileSystem.get(new URI(HDFS_URI), conf, USR_PERMISSION);
            inputStream = fs.open(new Path(filePath));
            outputStream = new ByteArrayOutputStream(inputStream.available());
            IOUtils.copyBytes(inputStream, outputStream, conf);
            res = outputStream.toString();
        } finally {
            if (inputStream != null)
                IOUtils.closeStream(inputStream);
            if (outputStream != null)
                IOUtils.closeStream(outputStream);
        }
        return res;
    }

    /**
     * 判断路径在HDFS上是否存在
     *
     * @param conf
     *            配置
     * @param path
     *            路径
     * @return
     * @throws IOException
     */
    public static boolean exits(Configuration conf, String path) throws Exception {
        FileSystem fs = FileSystem.get(new URI(HDFS_URI), conf, USR_PERMISSION);
        return fs.exists(new Path(path));
    }

}
