/**
 * FileName: music
 * Author:   陈江超
 * Date:     2019/7/23 21:24
 * Description: 音乐
 */
package com.github.domain;

/**
 * 〈一句话功能简述〉<br>
 * 〈音乐〉
 *
 * @author 陈江超
 * @create 2019/7/23
 * @since 1.0.0
 */
public class music {
    private int musicid;
    private String musicname;
    private String url;

    public int getMusicid() {
        return musicid;
    }

    public void setMusicid(int musicid) {
        this.musicid = musicid;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "music{" +
                "musicid=" + musicid +
                ", musicname='" + musicname + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
