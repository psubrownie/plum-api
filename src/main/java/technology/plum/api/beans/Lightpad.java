package technology.plum.api.beans;

import com.google.gson.annotations.SerializedName;

//{"config":{"slowFadeTime":15000,"touchRate":0.5,"cluster":"production","uuid":"xxxxxxx-xxxxxxx","logRemote":false,"forceGlow":false,"occupancyAction":"undefined","occupancyTimeout":600,"fadeOffTime":0,"glowIntensity":1.0,"name":"","fadeOnTime":0,"defaultLevel":102,"glowTracksDimmer":false,"dimEnabled":true,"glowFade":1000,"amqpEnabled":true,"trackingSpeed":1000,"minimumLevel":13,"versionLocked":false,"glowTimeout":5,"serialNumber":"0000000","pirSensitivity":430,"glowEnabled":true,"glowColor":{"white":0,"red":3,"green":229,"blue":226},"maxWattage":420},
//"is_provisioned":true,"llid":"xxx-xxxx","custom_gestures":1,"lightpad_name":"xxx-xxxx","lpid":"xxxx-xxxx"}
public class Lightpad {
    private String lpid;
    @SerializedName("lightpad_name")
    private String name;

    private LightpadConfig config;

    public static class LightpadConfig {
	private Double slowFadeTime;
	private Float touchRate;
	private String cluster;
	private String uuid;
	private Boolean forceGlow;
	private Integer occupancyTimeout;
	private Float glowIntensity;
	private Float fadeOnTime;
	private Float defaultLevel;
	private Boolean dimEnabled;
	private Integer glowFade;

	public Double getSlowFadeTime() {
	    return slowFadeTime;
	}

	public void setSlowFadeTime(Double slowFadeTime) {
	    this.slowFadeTime = slowFadeTime;
	}

	public Float getTouchRate() {
	    return touchRate;
	}

	public void setTouchRate(Float touchRate) {
	    this.touchRate = touchRate;
	}

	public String getCluster() {
	    return cluster;
	}

	public void setCluster(String cluster) {
	    this.cluster = cluster;
	}

	public String getUuid() {
	    return uuid;
	}

	public void setUuid(String uuid) {
	    this.uuid = uuid;
	}

	public Boolean getForceGlow() {
	    return forceGlow;
	}

	public void setForceGlow(Boolean forceGlow) {
	    this.forceGlow = forceGlow;
	}

	public Integer getOccupancyTimeout() {
	    return occupancyTimeout;
	}

	public void setOccupancyTimeout(Integer occupancyTimeout) {
	    this.occupancyTimeout = occupancyTimeout;
	}

	public Float getGlowIntensity() {
	    return glowIntensity;
	}

	public void setGlowIntensity(Float glowIntensity) {
	    this.glowIntensity = glowIntensity;
	}

	public Float getFadeOnTime() {
	    return fadeOnTime;
	}

	public void setFadeOnTime(Float fadeOnTime) {
	    this.fadeOnTime = fadeOnTime;
	}

	public Float getDefaultLevel() {
	    return defaultLevel;
	}

	public void setDefaultLevel(Float defaultLevel) {
	    this.defaultLevel = defaultLevel;
	}

	public Boolean getDimEnabled() {
	    return dimEnabled;
	}

	public void setDimEnabled(Boolean dimEnabled) {
	    this.dimEnabled = dimEnabled;
	}

	public Integer getGlowFade() {
	    return glowFade;
	}

	public void setGlowFade(Integer glowFade) {
	    this.glowFade = glowFade;
	}

    }

    public String getLpid() {
	return lpid;
    }

    public void setLpid(String lpid) {
	this.lpid = lpid;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public LightpadConfig getConfig() {
	return config;
    }

    public void setConfig(LightpadConfig config) {
	this.config = config;
    }
}
