package com.example.lol_a_z_backend.controller.api.model;

import com.example.lol_a_z_backend.model.Champion;
import lombok.Data;

import java.util.Map;

@Data public class ApiResponse {
	private String type;
	private String format;
	private String version;
	private Map<String, Champion> data;

}
