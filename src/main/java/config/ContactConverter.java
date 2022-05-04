package config;

import config.model.Contact;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ContactConverter implements Converter<Contact> {
    @Override
    public Contact convert(Method method, String input) {
        Map<String, String> imports = new HashMap<>();
        imports.put("contact", input);
        return ConfigFactory.create(Contact.class, imports);
    }

}
