package com.google.code.aosddl;

import java.util.Locale;
import java.util.Properties;

import android.os.Build;

public class CapabilityLoader {
	private static Properties bundle;

	public static Capabilities getCapabilities() {
		if (CapabilityLoader.bundle == null) {
			// XXX We get a lot of inconsistent values, so we need to make sure
			// we can find the right bundle. For instance both Samsung and
			// Motorola will sometimes have the Build.MANUFACTURER with an upper
			// case first char and sometimes with a lower case. Sony Ericsson
			// has a space that our bundle won't like as well. HTC sometimes has
			// a space in the Build.MODEL. -JFBP
			Locale deviceId = getDeviceLocale();
			CapabilityLoader.bundle = ConfigLoader.load(deviceId);

			// ResourceBundle.getBundle("device", deviceId,
			// CapabilityLoader.class.getClassLoader());

		}

		return new Capabilities() {

			public boolean isUsingDefaultValues() {
				return Boolean.valueOf(
						CapabilityLoader.bundle.getProperty(
								"usingDefaultValues", "false")).booleanValue();
			}

			public Float getTextScaleMultiplier() {
				return new Float(CapabilityLoader.bundle.getProperty(
						"textScaleMultiplier", "1.0"));
			}

			public boolean hasValidBuildSerial() {
				return Boolean.valueOf(
						CapabilityLoader.bundle.getProperty("validBuildSerial",
								"false")).booleanValue();
			}

			public boolean isTablet() {
				return Boolean.valueOf(
						CapabilityLoader.bundle.getProperty("tablet", "false"))
						.booleanValue();
			}

		};
	}

	public static Locale getDeviceLocale() {
		return new Locale(normilizeManufacturer(Build.MANUFACTURER),
				normilizeDevice(Build.DEVICE), normilizeModel(Build.MODEL));
	}

	private static String normilizeDevice(String device) {
		return device.toUpperCase();
	}

	private static String normilizeModel(String model) {
		return model.toUpperCase().replace(" ", "");
	}

	private static String normilizeManufacturer(String manufacturer) {
		return manufacturer.toLowerCase().replace(" ", "");
	}
}
