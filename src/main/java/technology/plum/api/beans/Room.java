package technology.plum.api.beans;

import java.util.List;

import com.google.gson.annotations.SerializedName;

//{"rid":"xxx-xxxx","hid":"xxx-xxxx","llids":["xxx-xxxx","xxx-xxxx"],"room_name":"Basement"}
public class Room {
    private String rid;
    private List<String> llids;
    private List<LogicalLoad> lloads;

    @SerializedName("room_name")
    private String name;

    public String getRid() {
	return rid;
    }

    public void setRid(String rid) {
	this.rid = rid;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<String> getLlids() {
	return llids;
    }

    public void setLlids(List<String> llids) {
	this.llids = llids;
    }

    public List<LogicalLoad> getLloads() {
	return lloads;
    }

    public void setLloads(List<LogicalLoad> lloads) {
	this.lloads = lloads;
    }

}
