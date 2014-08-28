package org.mongodb.morphia.converters;


import org.mongodb.morphia.mapping.MappedField;

import java.util.Locale;


/**
 * @author Uwe Schaefer, (us@thomas-daily.de)
 * @author scotthernandez
 */
public class LocaleConverter extends TypeConverter implements SimpleValueConverter {

  public LocaleConverter() {
    super(Locale.class);
  }

  @Override
  public Object decode(final Class targetClass, final Object fromDBObject, final MappedField optionalExtraInfo) {
    return parseLocale(fromDBObject.toString());
  }

  @Override
  public Object encode(final Object val, final MappedField optionalExtraInfo) {
    if (val == null) {
      return null;
    }

    return val.toString();
  }

  public static Locale parseLocale(final String localeString) {
    if ((localeString != null) && (localeString.length() != 0)) {
      final int index = localeString.indexOf("_");
      final int index2 = localeString.indexOf("_", index + 1);
      Locale l;
      if (index == -1) {
          l = new Locale(localeString);
      } else if (index2 == -1) {
          l = new Locale(localeString.substring(0, index), localeString.substring(index + 1));
      } else {
          l = new Locale(localeString.substring(0, index), localeString.substring(index + 1, index2), localeString.substring(index2 + 1));
      }
      return l;
    }
    
    return null;
  }
}
