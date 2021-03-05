package sample;

public class StudentRecord {

private String SID;
private float Assignments;
private float Midterm;
private float FinalExam;
private double FinalMark;
private String LetterGrade;

public StudentRecord(String sid, float assignments, float midterm,float finalExam){
    this.SID = sid;
    this.Assignments = assignments;
    this.Midterm = midterm;
    this.FinalExam = finalExam;
    this.FinalMark = (.2*this.Assignments + .3* this.Midterm + .5*this.FinalExam);

    if (this.FinalMark >= 80 ){
        this.LetterGrade = "A"; }

    else if (this.FinalMark >=70){
        this.LetterGrade = "B";
    }
    else if (this.FinalMark >=60){
        this.LetterGrade = "C";
    }
    else if (this.FinalMark >=50){
        this.LetterGrade = "D";
    }
    else {
        this.LetterGrade = "F";
    }

}
    public String getSID(){return SID; }
    public Float getAssignments(){return Assignments;}
   public Float getMidterm(){return Midterm;}
    public Float getFinalExam(){return FinalExam;}
  public Double getFinalMark(){return FinalMark;}
  public String getLetterGrade(){return LetterGrade;}

    }
