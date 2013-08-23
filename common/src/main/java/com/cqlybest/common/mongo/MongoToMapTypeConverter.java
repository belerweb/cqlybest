package com.cqlybest.common.mongo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.googlecode.mjorm.convert.ConversionContext;
import com.googlecode.mjorm.convert.ConversionException;
import com.googlecode.mjorm.convert.JavaType;
import com.googlecode.mjorm.convert.TypeConversionHints;
import com.googlecode.mjorm.convert.TypeConverter;
import com.mongodb.BasicDBObject;

public class MongoToMapTypeConverter implements TypeConverter<BasicDBObject, Map<String, Object>> {

  public boolean canConvert(Class<?> sourceClass, Class<?> targetClass) {
    return BasicDBObject.class.equals(sourceClass) && Map.class.isAssignableFrom(targetClass);
  }

  public Map<String, Object> convert(BasicDBObject source, JavaType targetType,
      ConversionContext context, TypeConversionHints hints) throws ConversionException {
    // create and convert
    Map<String, Object> ret = new HashMap<String, Object>();
    for (Entry<String, Object> entry : source.entrySet()) {

      // get value
      Object value = entry.getValue();

      // TODO convert

      ret.put(entry.getKey(), value);
    }
    return ret;
  }

}
