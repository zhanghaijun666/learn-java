
package com.utils;



import com.mybatisv2.framework.config.ParameterMappingV2;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理#{}中的参数的
 */

public class ParameterMappingTokenHandler implements TokenHandler {
	private List<ParameterMappingV2> parameterMappings = new ArrayList<>();

	// content是参数名称
	// content 就是#{}中的内容
	@Override
	public String handleToken(String content) {
		parameterMappings.add(buildParameterMapping(content));
		return "?";
	}

	private ParameterMappingV2 buildParameterMapping(String content) {
		ParameterMappingV2 parameterMapping = new ParameterMappingV2(content);
		return parameterMapping;
	}

	public List<ParameterMappingV2> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMappingV2> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

}
