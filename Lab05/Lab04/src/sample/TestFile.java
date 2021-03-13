package sample;

import java.text.DecimalFormat;
public class TestFile {
    public String Filename;
    public double SpamProbability;
    public String ActualClass;

    public TestFile(String filename, double spamProbability, String actualClass) {
        this.Filename = filename;
        this.SpamProbability = spamProbability;
        this.ActualClass = actualClass;
    }

    public String getFilename(){
        return this.Filename;
    }

    public double getSpamProbability(){
        return this.SpamProbability;
    }

    public String getSpamProbRounded(){
        DecimalFormat df = new DecimalFormat("0.00000");
        return df.format(this.SpamProbability);
    }

    public String getActualClass(){
        return this.ActualClass;
    }

    public void setFilename(String value) {
        this.Filename = value;
    }

   public void setSpamProbability(double val) {
       this.SpamProbability = val;
    }

   public void setActualClass(String value) {
       this.ActualClass = value; }
}