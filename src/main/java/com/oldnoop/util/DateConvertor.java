package com.oldnoop.util;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConvertor implements Converter<String, Date> {

	@Override
	public Date convert(String text) {
		if (text == null) {
			return null;
		}
		text = text.trim();
		try {
			if (DateFormatUtil.YYYY_MM_DD.length() == text.length()) {
				return DateFormatUtil.getYmdFormat().parse(text);
			}

			if (DateFormatUtil.YYYY_MM_DD_HH_MM_SS.length() == text.length()) {
				return DateFormatUtil.getYmdHmsFormat().parse(text);
			}

		} catch (Exception e) {

		}
		return null;
	}

}
