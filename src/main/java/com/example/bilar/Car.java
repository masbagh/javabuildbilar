package com.example.bilar;



public class Car {
    private Integer id;
    private static Integer num = 0;
    public Car(){
    super();
    id = num++;
    }
 public Car(String namn, String model, String color, int year){ 
     super();
     id = num++;
     this.namn = namn;
     this.model =  model;
     this.color  = color;
     this.year =year;

 }

    public Integer getId() {
        return id;
      }
      
     public void setId(Integer id) {
     this.id = id;
      }    
  
    private String namn;
    private String model;
    private String color;
    private int year;


    public String getNamn() {
        return namn;
    }
    public void setNamn(String s) {
        namn = s;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String v) {
        model = v;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String v) {
        color = v;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int v) {
        year = v;
    }







}

