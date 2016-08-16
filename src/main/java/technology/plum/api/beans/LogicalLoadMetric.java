package technology.plum.api.beans;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class LogicalLoadMetric implements Serializable {
	private int level;

    @SerializedName("lightpad_metrics")
    private List<Metric> lightpadMetrics;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Metric> getLightpadMetrics() {
        return lightpadMetrics;
    }

    public void setLightpadMetrics(List<Metric> lightpadMetrics) {
        this.lightpadMetrics = lightpadMetrics;
    }

    public static class Metric implements Serializable{
        private int level;
        private String lpid;
        private int power;

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public String getLpid() {
            return lpid;
        }

        public void setLpid(String lpid) {
            this.lpid = lpid;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
