package com.jay2u8809.codesamples.common.utils;

import com.jay2u8809.codesamples.common.code.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * 파일 관련 유틸
 * 업로드한 파일의 저장 & 서버에 저장된 파일 삭제 등의 기능 제공
 */
public class FileService {
    /**
     * 업로드 된 파일을 지정된 경로에 저장하고, 저장된 파일명을 리턴
     * @param mfile 업로드된 파일
     * @param uploadPath 저장할 경로
     * @return 저장된 파일명
     */
    public static String saveFile(MultipartFile mfile, String uploadPath) {

        //업로드된 파일이 없거나 크기가 0이면 저장하지 않고 null을 리턴
        if (mfile == null || mfile.isEmpty() || mfile.getSize() == 0) {
            return null;
        }

        //저장 폴더가 없으면 생성
        File path = new File(uploadPath);
        if (!path.isDirectory()) {
            path.mkdirs();
        }

        //원본 파일명
        String originalFilename = mfile.getOriginalFilename();

        //저장할 파일명을 오늘 날짜의 년월일로 생성
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String savedFilename = sdf.format(new Date());

        //원본 파일의 확장자
        String ext;
        int lastIndex = originalFilename.lastIndexOf('.');

        //확장자가 없는 경우
        if (lastIndex == -1) {
            ext = "";
        }
        //확장자가 있는 경우
        else {
            ext = "." + originalFilename.substring(lastIndex + 1);
        }

        //저장할 전체 경로를 포함한 File 객체
        File serverFile = null;

        //같은 이름의 파일이 있는 경우의 처리
        while (true) {
            serverFile = new File(uploadPath + "/" + savedFilename + ext);
            //같은 이름의 파일이 없으면 나감.
            if ( !serverFile.isFile()) break;
            //같은 이름의 파일이 있으면 이름 뒤에 long 타입의 시간정보를 덧붙임.
            savedFilename = savedFilename + new Date().getTime();
        }

        //파일 저장
        try {
            mfile.transferTo(serverFile);
        } catch (Exception e) {
            savedFilename = null;
            e.printStackTrace();
        }

        return savedFilename + ext;
    }

    /**
     * 서버에 저장된 파일의 전체 경로를 전달받아, 해당 파일을 삭제
     * @param fullpath 삭제할 파일의 경로
     * @return 삭제 여부
     */
    public static boolean deleteFile(String fullpath) {
        //파일 삭제 여부를 리턴할 변수
        boolean result = false;

        //전달된 전체 경로로 File객체 생성
        File delFile = new File(fullpath);

        //해당 파일이 존재하면 삭제
        if (delFile.isFile()) {
            delFile.delete();
            result = true;
        }

        return result;
    }


    /*
     * @comment	:	해당 폴더의 파일 리스트를 읽는 메소드
     * @param	:	폴더 경로
     * @return	:	파일 목록
     * @author	:	정보승
     */
    public static ArrayList<File> getDirFileList(String dirPath){

        // 디렉토리 파일 리스트
        ArrayList<File> dirFileList = null;

        // 파일 목록을 요청한 디렉토리를 가지고 파일 객체를 생성함
        File dir = new File(dirPath);

        // 디렉토리가 존재한다면
        if (dir.exists())
        {
            // 파일 목록을 구함
            File[] files = dir.listFiles();

            // 파일 배열을 파일 리스트로 변화함
            dirFileList = new ArrayList<File>(Arrays.asList(files));
        }

        return dirFileList;

    }


    /*
     * @param	:	임시 폴더에서 사용자의 계정폴더로 이미지 파일을 복사하는 메소드
     * @author	:	정보승
     */
    public static void fileCopy(String tempFile ,String uploadFile) {

        try {

            FileInputStream fis = new FileInputStream(tempFile);
            FileOutputStream fos = new FileOutputStream(uploadFile);

            int data = 0;

            while((data=fis.read())!=-1) {

                fos.write(data);
            }

            fis.close();
            fos.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

//		return false;
    }

    public static File transferMultipartToFile(MultipartFile multipartFile) throws IOException, IllegalStateException {

        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        File file = new File(generateFileName(multipartFile.getOriginalFilename(), multipartFile.getContentType()));
        if (!file.createNewFile()) {
            return null;
        }

        multipartFile.transferTo(file);

        return file;
    }

    public static File convertMultipartToFile(MultipartFile multipartFile) throws IOException {

        File file = new File(generateFileName(multipartFile.getOriginalFilename(), multipartFile.getContentType()));
        if (!file.createNewFile()) {
            return null;
        }

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        return file;
    }

    public static String generateFileName(String originalFileName, String contentType) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        String fileExtension = "";
        if (contentType != null) {
            if (contentType.contains(FileType.JPEG.name()) || contentType.contains(FileType.JPG.name())) {
                fileExtension = ".jpg";
            } else if (contentType.contains(FileType.PNG.name())) {
                fileExtension = ".png";
            } else if (contentType.contains(FileType.GIF.name())) {
                fileExtension = ".gif";
            }
        }

        return  (originalFileName == null ? "" : originalFileName + "_") +
                current_date +
                "_" +
                System.nanoTime() +
                fileExtension;
    }

}

