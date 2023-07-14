package com.hc.toolrental.model;

public class Tool {
	private String toolCode;
    private String toolType;
    private String brand;

    public Tool(String toolCode, String toolType, String brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }
}
