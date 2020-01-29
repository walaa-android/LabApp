package com.example.labmedixapplication.model;

public class BottomSheetModel {
    private String overview;
    private String testsample;
    private String testdetiles;

    public BottomSheetModel(String overview, String testsample,String testdetiles) {
        this.overview = overview;
        this.testsample = testsample;
        this.testdetiles = testdetiles;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTestsample() {
        return testsample;
    }

    public void setTestsample(String testsample) {
        this.testsample = testsample;
    }

    public String getTestdetiles() {
        return testdetiles;
    }

    public void setTestdetiles(String testdetiles) {
        this.testdetiles = testdetiles;
    }
}
