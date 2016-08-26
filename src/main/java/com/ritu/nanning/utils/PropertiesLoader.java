package com.ritu.nanning.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

/**
 * Properties file loader
 * 
 * @author Joe
 * 
 */
public class PropertiesLoader {

	private Properties properties;
	private final String path;

	public PropertiesLoader(String path) {
		this.properties = loadProperties(path);
		this.path = path;
	}

	public Properties getProperties() {
		return properties;
	}

	/**
	 * check whether is contain the key
	 * 
	 * @param key
	 * @return
	 */
	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}

	/**
	 * 
	 * @return
	 */
	public Set<String> getKeys() {
		return properties.stringPropertyNames();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	private String getValue(String key) {
		String systemProperty = System.getProperty(key);
		if (systemProperty != null) {
			return systemProperty;
		}
		return properties.getProperty(key);
	}

	/**
	 */
	public String getProperty(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return value;
	}

	/**
	 */
	public String getProperty(String key, String defaultValue) {
		String value = getValue(key);
		return value != null ? value : defaultValue;
	}

	public Integer getInteger(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Integer.valueOf(value);
	}

	public String getString(String key) {
		return getValue(key);
	}

	public Integer getInteger(String key, Integer defaultValue) {
		String value = getValue(key);
		return value != null ? Integer.valueOf(value) : defaultValue;
	}

	/**
	 */
	public Double getDouble(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Double.valueOf(value);
	}

	/**
	 */
	public Double getDouble(String key, Integer defaultValue) {
		String value = getValue(key);
		return value != null ? Double.valueOf(value) : defaultValue;
	}

	/**
	 */
	public Boolean getBoolean(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Boolean.valueOf(value);
	}

	/**
	 */
	public Boolean getBoolean(String key, boolean defaultValue) {
		String value = getValue(key);
		return value != null ? Boolean.valueOf(value) : defaultValue;
	}

	public void refresh() {
		properties = loadProperties(path);
	}

	/**
	 */
	private Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();

		for (String location : resourcesPaths) {
			InputStream is = null;
			try {
				is = new FileInputStream(location);
				props.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}
		return props;
	}

	public void put(String key, String value) {
		if (properties != null) {
			properties.put(key, value);
		}
	}

	public void saveProperties() {
		saveProperties(path);
	}

	@SuppressWarnings("deprecation")
	public void saveProperties(String path) {
		if (properties != null) {
			OutputStream os = null;
			try {
				os = new FileOutputStream(path);
				properties.save(os, " ");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (os != null) {
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}

}
