package technology.plum.api.beans;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import technology.plum.api.HouseService;
import technology.plum.api.PlumContext;

public class LogicalLoad {
    private List<String> lpids;
    private List<Lightpad> lightPads;
    private String llid;

    @SerializedName("logical_load_name")
    private String name;

    public List<String> getLpids() {
	return lpids;
    }

    public void setLpids(List<String> lpids) {
	this.lpids = lpids;
    }

    public List<Lightpad> getLightPads() {
	return lightPads;
    }

    public void setLightPads(List<Lightpad> lightPads) {
	this.lightPads = lightPads;
    }

    public String getLlid() {
	return llid;
    }

    public void setLlid(String llid) {
	this.llid = llid;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    //
    private static class LogicalLoadGlow {

    }

    public void setLevel(PlumContext ctx, Integer level) {
	try {
	    HouseService.setLevel(ctx, this, level);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
