package cn.yanghuisen.shortvideo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 2025
 * @version 1.0
 * @date 2020/1/31 19:21
 * @Description:
 */
public class ShortVideo {

    public static Map<String,String> getPPX(String url,boolean cell) {
        Map<String,String> map = new HashMap<>();
        PPX ppx = new PPX(url,cell);
        map.put("VideoID",ppx.getVideoId());
        map.put("OriginTitle",ppx.getVideoOriginTitle());
        map.put("OriginUrl",ppx.getVideoOriginUrl());
        map.put("AuthorID",ppx.getVideoAuthorId());
        map.put("AuthorName",ppx.getVideoAuthorName());
        map.put("AuthorImage",ppx.getVideoAuthorImage());
        return map;
    }

    public static Map<String,String> getDY(String url){
        Map<String,String> map = new HashMap<>();
        DY dy = new DY(url);
        map.put("VideoID",dy.getVideoId());
        map.put("OriginTitle",dy.getVideoOriginTitle());
        map.put("OriginUrl",dy.getVideoOriginUrl());;
        return map;
    }
    public static Map<String,String> getWS(String url){
        Map<String,String> map = new HashMap<>();
        WS ws = new WS(url);
        map.put("VideoID",ws.getVideoId());
        map.put("OriginTitle",ws.getVideoOriginTitle());
        map.put("OriginUrl",ws.getVideoOriginUrl());
        map.put("AuthorID",ws.getVideoAuthorId());
        map.put("AuthorName",ws.getVideoAuthorName());
        map.put("AuthorImage",ws.getVideoAuthorImage());
        return map;
    }
}
