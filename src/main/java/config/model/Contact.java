package config.model;

import enums.ContactTypes;
import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface Contact extends Config {
    @Key("contacts.${contact}.type")
    ContactTypes type();
    @Key("contacts.${contact}.text")
    String text();
}
