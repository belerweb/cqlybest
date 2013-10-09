package com.cqlybest.common.dao;

import com.googlecode.mjorm.ObjectDescriptor;
import com.googlecode.mjorm.ObjectDescriptorRegistry;
import com.googlecode.mjorm.PropertyDescriptor;
import com.googlecode.mjorm.convert.converters.MongoToPojoTypeConverter;
import com.googlecode.mjorm.convert.converters.PojoToMongoTypeConverter;
import com.mongodb.DBObject;

/**
 * Abstract class that uses {@link ObjectDescriptor}s and {@link PropertyDescriptor}s to map objects
 * to and from mongo's {@link DBObject}s.
 */
public class DescriptorObjectMapper extends AbstractObjectMapper {

  private ObjectDescriptorRegistry registry;

  public DescriptorObjectMapper() {
    registry = new ObjectDescriptorRegistry();
    super.registerTypeConverter(new PojoToMongoTypeConverter(registry));
    super.registerTypeConverter(new MongoToPojoTypeConverter(registry));
  }

  /**
   * Registers a new {@link ObjectDescriptor}.
   * 
   * @param descriptor the {@link ObjectDescriptor]
   */
  protected void registerObjectDescriptor(ObjectDescriptor descriptor) {
    registry.registerDescriptor(descriptor);
  }

}
