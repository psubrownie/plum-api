package technology.plum.api.beans;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Scott on 3/30/2016.
 */
public class Scene implements Serializable {
    @SerializedName(value="name", alternate={"scene_name"})
    private String name;
    private String hid;
    private String sid;
    private List<Setting> settings;

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public static class Setting implements Serializable{
        private Integer fade;
        private Integer level;
        private String llid;

        public Integer getFade() {
            return fade;
        }

        public void setFade(Integer fade) {
            this.fade = fade;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public String getLlid() {
            return llid;
        }

        public void setLlid(String llid) {
            this.llid = llid;
        }
    }
}
