package kr.koreait.myboard;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Utils {
	
	//String > int 바꿔주는 메소드입니다.
	public static int parseStringToInt(String str, int def) {
		try {
			def = Integer.parseInt(str);
		} catch(Exception e) {}		
		return def;
	}
	
	//파일 업로드
	public static String uploadFile(HttpServletRequest request, String filePath) {
		String fileNm = null;
		
		//서버가 돌아가고 있는 소스의 절대경로
		String path = request.getSession().getServletContext().getRealPath("img/" + filePath);
		int size = 1024 * 1024 * 10;
		
		//폴더 실제하는지 체크 (없으면 폴더 생성)
		File file = new File(path);		
		if(!file.exists()) {
			System.out.println("폴더 없음!!");			
			//폴더 생성			
			file.mkdirs();
		}
		
		try {
			MultipartRequest mr = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
						
			Enumeration files = mr.getFileNames();
			String str = (String)files.nextElement();			
			fileNm = mr.getFilesystemName(str);
			System.out.println("fileNm: " + fileNm);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return fileNm;
	}

}
