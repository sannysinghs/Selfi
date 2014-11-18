package com.selfi.utils;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

public class MStrUtils {
	public static Spanned HTMLEncode(String str){
		Spanned result = null;
		if (!TextUtils.isEmpty(str)) {
			result = Html.fromHtml(str);
		}
		return result;
	}
}
