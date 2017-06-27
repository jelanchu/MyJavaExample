import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class TestPropertiesConfiguration {
	public TestPropertiesConfiguration() {
		
	}
	public void read() {
		PropertiesConfiguration properties;
		try {
			properties = new PropertiesConfiguration();
			properties.setDelimiterParsingDisabled(true);
			properties.load("lpr-rule.properties");
			String weightJson = properties.getString("confidenceWeights", "");
			System.out.println(weightJson);
			List<String> format6 = properties.getList("format6");
			System.out.println(String.join(",", format6));
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
