package com.amazon.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	static ExtentReports report;
	public static String screenshotFolderpath;

    public static  ExtentReports getreports() {

        if (report == null) {
            report = new ExtentReports();

            Date d = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String timestamp = dateFormat.format(d);

            String reportsFolder = System.getProperty("user.dir") + File.separator + "reports" + File.separator + timestamp;
            
            String screenshotFolderpath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + timestamp;
            String reportsFolderpath = System.getProperty("user.dir") + "//reports//" + d.toString().replaceAll(":","-");

            File reportDir = new File(reportsFolder);
            File screenshotDir = new File(screenshotFolderpath);

            reportDir.mkdirs();
            screenshotDir.mkdirs();
            
            

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportsFolderpath);
            
            sparkReporter.config().setReportName("Amazon Login Test");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setTimelineEnabled(true);

            report.attachReporter(sparkReporter);

            report.setSystemInfo("OS", "WINDOWS");
            report.setSystemInfo("Browser", "Google Chrome");
        }

        return report;
    }
}
