package com.osmosis.duckr.bo;

import org.apache.commons.lang3.StringUtils;

public class Error implements BO{
	public String code = StringUtils.EMPTY;
	public String message = StringUtils.EMPTY;
}
