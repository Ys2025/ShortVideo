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
    private String videoAuthorImage;
    private boolean cell;

    public PPX(String video_url,boolean cell) {
        this.videoUrl = video_url;
        this.cell = cell;
        this.videoId = getID();
        this.videoJson = getJson();
        if (!cell){
            this.videoOriginUrl = getOriginUrl();
            this.videoOriginTitle = getOriginTitle();
            this.videoAuthorId = getAuthorId();
            this.videoAuthorName = getAuthorName();
            this.videoAuthorImage = getAuthorImage();
        }else {
            this.videoOriginUrl = getCellOriginUrl();
            this.videoOriginTitle = getCellOriginTitle();
            this.videoAuthorId = getCellAuthorId();
            this.videoAuthorName = getCellAuthorName();
            this.videoAuthorImage = getCellAuthorImage();
        }

    }

    private String getJson(){
        URL url = null;
        String result = "";
        try {
            if (!this.cell){
                url = new URL("https://h5.pipix.com/bds/webapi/item/detail/?item_id="+this.videoId);
            }else {
                url = new URL("https://h5.pipix.com/bds/webapi/cell/detail/?cell_id="+this.videoId+"&cell_type=8&source=share");
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
            conn.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

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

            // true 层模式
            if (!cell){
                id = s.substring(s.indexOf("item/")+5,s.indexOf("?"));
            }else {
                id = s.substring(s.indexOf("cell_id=")+8,s.indexOf("&carrier"));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }


    // 无水印视频Url
    private String  getOriginUrl(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("item").getJSONObject("origin_video_download");
        JSONArray jsonArray = jsonObject.getJSONArray("url_list");
        jsonObject = jsonArray.getJSONObject(0);
        return jsonObject.getString("url");
    }

    // 层主无水印视频Url
    private String  getCellOriginUrl(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("comment").getJSONObject("video_fallback");
        JSONArray jsonArray = jsonObject.getJSONArray("url_list");
        jsonObject = jsonArray.getJSONObject(0);
        return jsonObject.getString("url");
    }

    // 无水印视频标题
    private String  getOriginTitle(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("item").getJSONObject("video");
        return jsonObject.getString("text");
    }

    // 层主无水印视频标题
    private String  getCellOriginTitle(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("comment");
        return jsonObject.getString("text");
    }

    // 视频作者ID
    private String  getAuthorId(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("item").getJSONObject("author");
        return jsonObject.getString("id_str");
    }

    // 层主视频作者ID
    private String  getCellAuthorId(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("comment").getJSONObject("user");
        return jsonObject.getString("id_str");
    }

    // 视频作者名称
    private String  getAuthorName(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("item").getJSONObject("author");
        return jsonObject.getString("name");
    }

    // 层主视频作者名称
    private String  getCellAuthorName(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("comment").getJSONObject("user");
        return jsonObject.getString("name");
    }

    // 视频作者头像
    private String  getAuthorImage(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("item").getJSONObject("author").getJSONObject("avatar");
        JSONArray jsonArray = jsonObject.getJSONArray("url_list");
        jsonObject = jsonArray.getJSONObject(0);
        return jsonObject.getString("url");
    }

    // 层主视频作者头像
    private String  getCellAuthorImage(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        jsonObject = jsonObject.getJSONObject("data").getJSONObject("comment").getJSONObject("user").getJSONObject("avatar");
        JSONArray jsonArray = jsonObject.getJSONArray("url_list");
        jsonObject = jsonArray.getJSONObject(0);
        return jsonObject.getString("url");
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

    public String getVideoAuthorImage() {
        return videoAuthorImage;
    }
}
