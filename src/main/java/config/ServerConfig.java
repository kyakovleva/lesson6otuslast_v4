package config;

import config.model.Contact;
import enums.Cities;
import enums.Countries;
import enums.EnglishLevels;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

import java.util.List;

@Sources("classpath:config.properties")
public interface ServerConfig extends Config {

	@Key("email")
	String email();

	@Key("password")
	String password();

	@Key("phone1")
	String phone1();

	@Separator(",")
	@Key("contacts.list")
	@ConverterClass(ContactConverter.class)
	List<Contact> contacts();

	@Key("name")
	String name();

	@Key("surname")
	String surname();

	@Key("birth")
	String birth();

	@Key("country")
	Countries country();

	@Key("city")
	Cities city();

	@Key("languageLevel")
	EnglishLevels engLevel();
}
