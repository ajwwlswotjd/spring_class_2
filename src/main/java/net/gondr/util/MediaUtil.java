package net.gondr.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtil {
	private static Map<String, MediaType> mediaMap;
	
	static {
		// 정적 클래스 생성자
		mediaMap = new HashMap<>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	public static MediaType getMediaType(String type)
	{
		return mediaMap.get(type.toUpperCase());
	}
}
