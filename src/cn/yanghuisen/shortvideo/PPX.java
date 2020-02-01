package cn.yanghuisen.shortvideo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

/**
 * @author 2025
 * @version 1.0
 * @date 2020/1/31 19:27
 * @Description: 皮皮虾
 */
class PPX {
    private String videoUrl;
    private String videoId;
    private String videoJson;
    private String videoOriginUrl;
    private String videoOriginTitle;
    private String videoAuthorId;
    private String videoAuthorName;

    public PPX(String video_url) {
        this.videoUrl = video_url;
        this.videoId = getID();
        this.videoJson = getJson();
        this.videoOriginUrl = getOriginUrl();
        this.videoOriginTitle = getOriginTitle();
        this.videoAuthorId = getAuthorId();
        this.videoAuthorName = getAuthorName();
    }

    private String getJson(){
        URL url = null;
        String result = "";
        try {
            url = new URL("https://h5.pipix.com/bds/webapi/item/detail/?item_id="+this.videoId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
            conn.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            while ((line=br.readLine())!=null){
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getID(){
        String id = "";
        try {
            URL url = new URL(this.videoUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            String s = conn.getHeaderField("Location");
            id = s.substring(s.indexOf("item/")+5,s.indexOf("?"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    private String  getOriginUrl(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("item").getJSONObject("origin_video_download");
        JSONArray jsonArray = jsonObject.getJSONArray("url_list");
        jsonObject = jsonArray.getJSONObject(0);
        return jsonObject.getString("url");
    }

    private String  getOriginTitle(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("item").getJSONObject("video");
        return jsonObject.getString("text");
    }

    private String  getAuthorId(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("item").getJSONObject("author");
        return jsonObject.getString("id_str");
    }

    private String  getAuthorName(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("item").getJSONObject("author");
        return jsonObject.getString("name");
    }

    public String getVideoId() {
        return videoId;
    }

    public String getVideoOriginUrl() {
        return videoOriginUrl;
    }

    public String getVideoOriginTitle() {
        if (this.videoOriginTitle.isEmpty()){
            return new Date().getTime()+"";
        }
        return videoOriginTitle;
    }

    public String getVideoAuthorId() {
        return videoAuthorId;
    }

    public String getVideoAuthorName() {
        return videoAuthorName;
    }
}
