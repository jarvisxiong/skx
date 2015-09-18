package com.skx.misc.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
 * @author Hu Changwei
 * @date 2013-12-25
 * 
 */
public class JsonUtil {

	public static class CustomExclusionStrategy implements ExclusionStrategy {

		@Override
		public boolean shouldSkipField(FieldAttributes f) {
			for (Annotation an : f.getAnnotations()) {
				if (an instanceof JsonIgnore) {
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}
	}

	private static final Gson JsonProxyInt;
	private static final Gson JsonProxyRaw;
	private static final Gson JsonProxyFormatter;
	private static boolean DateAsLong = false;
	static {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(java.util.Date.class, new DateLongSerializer()).setDateFormat(DateFormat.LONG);
		builder.registerTypeAdapter(java.util.Date.class, new DateLongDeserializer()).setDateFormat(DateFormat.LONG);
		JsonProxyInt = builder.setExclusionStrategies(new CustomExclusionStrategy()).create();
		//
		builder = new GsonBuilder();
		builder.registerTypeAdapter(java.util.Date.class, new DateTextSerializer()).setDateFormat(DateFormat.FULL);
		builder.registerTypeAdapter(java.util.Date.class, new DateTextDeserializer()).setDateFormat(DateFormat.FULL);
		JsonProxyRaw = builder.setExclusionStrategies(new CustomExclusionStrategy()).create();
		//
		builder = new GsonBuilder();
		builder.registerTypeAdapter(java.util.Date.class, new DateTextSerializer()).setDateFormat(DateFormat.FULL);
		builder.registerTypeAdapter(java.util.Date.class, new DateTextDeserializer()).setDateFormat(DateFormat.FULL);
		JsonProxyFormatter = builder.setExclusionStrategies(new CustomExclusionStrategy()).setPrettyPrinting().create();
	}

	public static synchronized void setDateAsLong(boolean dateAsLong) {
		DateAsLong = dateAsLong;
	}

	@SuppressWarnings("rawtypes")
	public static String formatAsMap(String jsonStr) {
		Map data = fromJson(jsonStr, Map.class);
		return JsonProxyFormatter.toJson(data);
	}

	@SuppressWarnings("rawtypes")
	public static String formatAsList(String jsonStr) {
		List data = fromJson(jsonStr, List.class);
		return JsonProxyFormatter.toJson(data);
	}

	public static String toJson(Object src) {
		return toJson(src, DateAsLong);
	}

	public static String toJson(Object src, boolean dateAsLong) {
		if (src == null) {
			return null;
		}
		return dateAsLong ? JsonProxyInt.toJson(src) : JsonProxyRaw.toJson(src);
	}

	public static String toJson(Object src, Type typeOfSrc) {
		return toJson(src, typeOfSrc, DateAsLong);
	}

	public static String toJson(Object src, Type typeOfSrc, boolean dateAsLong) {
		if (src == null) {
			return null;
		}
		return dateAsLong ? JsonProxyInt.toJson(src, typeOfSrc) : JsonProxyRaw.toJson(src, typeOfSrc);
	}

	public static <T> T fromJson(String json, Class<T> type) {
		return fromJson(json, type, DateAsLong);
	}

	public static <T> T fromJson(String json, Class<T> type, boolean dateAsLong) {
		if (json == null) {
			return null;
		}
		return dateAsLong ? JsonProxyInt.fromJson(json, type) : JsonProxyRaw.fromJson(json, type);
	}

	// TypeToken
	public static Object fromJson(String json, Type typeOfT) {
		return fromJson(json, typeOfT, DateAsLong);
	}

	public static Object fromJson(String json, Type typeOfT, boolean dateAsLong) {
		if (json == null) {
			return null;
		}
		return dateAsLong ? JsonProxyInt.fromJson(json, typeOfT) : JsonProxyRaw.fromJson(json, typeOfT);
	}

	// -------------- 日期序列/反序列化 --------------

	static class DateLongSerializer implements JsonSerializer<Date> {
		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			long val = src == null ? -1L : src.getTime();
			return new JsonPrimitive(val);
		}

	}

	static class DateLongDeserializer implements JsonDeserializer<Date> {
		@Override
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			JsonPrimitive primitive = json.getAsJsonPrimitive();
			long val = primitive.isNumber() ? primitive.getAsLong() : -1L;
			return val < 0 ? null : new Date(val);
		}
	}

	// -----------
	static class DateTextSerializer implements JsonSerializer<Date> {
		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			String val = src == null ? null : DateUtil.toStdDateTimeXStr(src);
			return new JsonPrimitive(val);
		}
	}

	static class DateTextDeserializer implements JsonDeserializer<Date> {
		@Override
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			JsonPrimitive primitive = json.getAsJsonPrimitive();
			String val = primitive.isString() ? primitive.getAsString() : null;
			try {
				return val == null ? null : DateUtil.fromStdDateTimeStr(val);
			} catch (ParseException e) {
				throw new JsonParseException(e.getMessage());
			}
		}
	}
}
