package com.cucumber.stepdefinition;

import java.io.IOException;

import com.aalam.baseclass.FunctionalLibrary;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class Hooks {

	@After
	public void afterHook(Scenario scenario) throws IOException, Throwable {
		if (scenario.isFailed()) {
			Reporter.addScreenCaptureFromPath(
					FunctionalLibrary.takescreenShot(scenario.getId().toString()).getAbsolutePath());
		}
	}
}
