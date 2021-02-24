

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class Zip {
    static String[] encodings = {"EUC-KR", "UTF-8"};

    /**
     * 압축풀기 메소드
     * 
     * @param zipFileName 압축파일
     * @param directory 압축 풀 폴더
     */
    public static void decompress(String zipFileName, String directory, int encoding) throws Throwable {
        File zipFile = new File(zipFileName);
        if (zipFile.isDirectory()) {
            File[] childs = zipFile.listFiles();
            for (File child : childs) {
                decompress(child.getCanonicalPath(), directory, encoding);
            }
        } else {
            FileInputStream fis = null;
            ZipInputStream zis = null;
            ZipEntry zipentry = null;
            try {
                fis = new FileInputStream(zipFile);// 파일 스트림
                zis = new ZipInputStream(fis, Charset.forName(encodings[encoding]));// Zip 파일 스트림

                while ((zipentry = zis.getNextEntry()) != null) {// entry가 없을때까지 뽑기
                    String filename = zipentry.getName();
                    File file = new File(directory, filename);
                    if (zipentry.isDirectory()) {// entiry가 폴더면 폴더 생성
                        file.mkdirs();
                    } else {
                        createFile(file, zis);// 파일이면 파일 만들기
                    }
                }
                System.out.println("success:"+zipFileName);
            } catch (IllegalArgumentException e) {
                if (encoding < encodings.length) {
                    decompress(zipFileName, directory, encoding + 1);
                   
                } else {
                    e.printStackTrace();
                    System.out.println("fail:" + zipFileName);
                }
            } catch (java.io.FileNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                if (zis != null)
                    zis.close();
                if (fis != null)
                    fis.close();
            }
        }
    }

    /**
     * 파일 만들기 메소드
     * 
     * @param file 파일
     * @param zis Zip스트림
     */
    private static void createFile(File file, ZipInputStream zis) throws Throwable {
        File parentDir = new File(file.getParent());
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = new byte[256];
            int size = 0;
            while ((size = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, size);
            }
        } catch (Throwable e) {
            throw e;
        }
    }

    // tutor에서 일괄 다운로드 받은 파일을 한번 압축해제 하신 다음에 압축 해제한 폴더로 경로 지정하시고 돌리면 됩니당 ㅋㅋ
    public static void main(String[] args) {
        // String wholePath = "C:\\Users\\yangyu\\Desktop\\코로나 업무\\월말평가\\4월월말\\3회차_월말평가_Web+DB\\";
        String wholePath = "C:\\Users\\ssuny\\Desktop\\4기\\평가채점\\과목평가\\1102디버그퀘스트\\제출폴더\\";
        File wholeFolder = new File(wholePath);
        File[] students = wholeFolder.listFiles();
        for (File f : students) {
            String apath = f.getAbsolutePath();
            String apath1 = apath.substring(0, apath.lastIndexOf("\\"));
            String apath2 = apath.substring(apath.length() - 3, apath.length());
            File stuDir = new File(apath1 + "\\" + apath2);
            if (!stuDir.exists())
                stuDir.mkdir();

            for (File ff : f.listFiles()) {
                try {
                    Zip.decompress(ff.getAbsolutePath(), stuDir.getAbsolutePath(), 0);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
