package com.cqlybest.weixin.bean;



/**
 * 回复音乐消息
 */
public class ResponseMusicMessage extends ResponseMessage {

  private Music music;

  public Music getMusic() {
    return music;
  }

  public void setMusic(Music music) {
    this.music = music;
  }

  public ResponseMusicMessage() {
    super();
    setMsgType("music");
  }

  public static class Music {

    private String title;

    private String description;

    private String musicUrl;// 音乐链接

    private String hqMusicUrl;// 高质量音乐链接，WIFI环境优先使用该链接播放音乐

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getMusicUrl() {
      return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
      this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
      return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
      this.hqMusicUrl = hqMusicUrl;
    }

  }

}
