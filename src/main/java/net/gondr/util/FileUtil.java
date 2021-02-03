package net.gondr.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class FileUtil {
	//업로드한 파일을 읽어들여서 세로 128 사이즈로 자동 리사이징 해주는 매서드
	public static String makeThumbnail(String uploadPath, String filename)
			throws Exception{
		BufferedImage sourceImage = ImageIO.read(new File(uploadPath, filename));
		BufferedImage destImage 
			= Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 128);
		
		String thumbName = uploadPath + File.separator + "s_" + filename;
		File newFile = new File(thumbName);
		String ext = filename.substring(filename.lastIndexOf(".") + 1); //확장자 구하기
		ImageIO.write(destImage, ext.toUpperCase(), newFile);
		
		return thumbName.substring(uploadPath.length()).replace(File.separatorChar, '/');		
	}
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData)
			throws Exception {
		UUID uid = UUID.randomUUID();
		String saveName = uid.toString() + "_" + originalName;
		File upDir = new File(uploadPath);
		if(!upDir.exists()) {
			upDir.mkdir(); //업로드 폴더가 없다면 생성한다.
		}
		
		File target = new File(uploadPath, saveName);
		FileCopyUtils.copy(fileData, target);
		
		String ext = originalName.substring(originalName.lastIndexOf(".") + 1); //확장자
		String uploadFilename = null;
		if(MediaUtil.getMediaType(ext) != null) {
			uploadFilename = makeThumbnail(uploadPath, saveName);
		}else {
			uploadFilename = (uploadPath + File.separator + saveName)
									.replace(File.separatorChar, '/');
		}
		
		return uploadFilename;
	}
}








