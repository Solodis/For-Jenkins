package com.epam.vyacheslav_utenkov.java.lesson7.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kohsuke.args4j.Option;

public class Settings {

	@Option(name = "--testng", usage  = "path to testNG xml", required = true)
	public String pathTestNG;
	
	@Option(name = "--MailURL", usage  = "path to mail URL", required = true)
	public String pathMailURL;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
