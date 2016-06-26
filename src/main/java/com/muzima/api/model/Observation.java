/*
 * Copyright (c) 2014. The Trustees of Indiana University.
 *
 * This version of the code is licensed under the MPL 2.0 Open Source license with additional
 * healthcare disclaimer. If the user is an entity intending to commercialize any application
 * that uses this code in a for-profit venture, please contact the copyright holder.
 */

package com.muzima.api.model;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.muzima.search.api.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Obs extends JPanel{
    private static JLabel msg;
    private String URL,PW,User;
    private Connection con;
    private Statement smt;
    private DriverManager drivermanager;
    private static  String tableName;
    private static  List<String> colName =null;
   Func func=new Func();
   
   public Obs(){
      
    URL="";
    PW="";
    User="user";
       
       
        try {
            func.   DBConnect();
        } catch (SQLException ex) {
            Logger.getLogger(Obs.class.getName()).log(Level.SEVERE, null, ex);
        }
        msg = new JLabel();
        msg.setForeground(Color.CYAN);
        add(msg);
        
   }//END OF OBS CONSTRUCTOR
    public static void main(String[] args) {
       // TODO code application logic here
       Obs obs = new Obs();
       
      
       
   }
   private class Func {
       
   //METHOD TO MONITORS OBS INFO
       public void  msginfo_() throws SQLException{
          String qry="select * from table Obs";
           try {
               con=DriverManager.getConnection(URL,User,PW);
           } catch (SQLException ex) {
               Logger.getLogger(Obs.class.getName()).log(Level.SEVERE, null, ex);
           }
           ResultSet rs=(ResultSet) con.prepareStatement(qry);
       
           ResultSetMetaData rmd=rs.getMetaData();
          
          int colCnt=rmd.getColumnCount();
          for(int i=0;i<colCnt;colCnt++){
          colName = Arrays.asList(rmd.getColumnName(i)); //GET COLUMN NAMES          
               
           Array  data=rs.getArray(rmd.getColumnName(i));//GET COLUMN VALUE AT THE CURRENT ROW
           
            
           
   }}
       
       
       
       
   //CONNNECT TO DATA BASE
   private void DBConnect() throws SQLException{
       try {
           Class.forName("com.mysql.jdbc.Driver");
       } catch (ClassNotFoundException ex) {
           JOptionPane.showMessageDialog(null,"SQL ERROR","CANNOT FIND SERVER",2);
       }
      
       con=DriverManager.getConnection(URL,User,PW);
       smt=con.createStatement();
       
   }
   
}
   
}
public class Observation extends OpenmrsSearchable {

    private Person person;

    private Encounter encounter;

    private Concept concept;

    private Concept valueCoded;

    private Date valueDatetime;

    private Double valueNumeric;

    private String valueText;

    private Date observationDatetime;

    private boolean voided;

    public Person getPerson() {
        return person;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(final Encounter encounter) {
        this.encounter = encounter;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(final Concept concept) {
        this.concept = concept;
    }

    public Concept getValueCoded() {
        return valueCoded;
    }

    public void setValueCoded(final Concept valueCoded) {
        this.valueCoded = valueCoded;
    }

    public Date getValueDatetime() {
        return valueDatetime;
    }

    public void setValueDatetime(final Date valueDatetime) {
        this.valueDatetime = valueDatetime;
    }

    public Double getValueNumeric() {
        return valueNumeric;
    }

    public void setValueNumeric(final Double valueNumeric) {
        this.valueNumeric = valueNumeric;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(final String valueText) {
        this.valueText = valueText;
    }

    /**
     * Get the date and time of the observation.
     *
     * @return the date and time of the observation.
     */
    public Date getObservationDatetime() {
        return observationDatetime;
    }

    /**
     * Set the date and time of the observation.
     *
     * @param observationDatetime the date and time of the observation.
     */
    public void setObservationDatetime(final Date observationDatetime) {
        this.observationDatetime = observationDatetime;
    }

    public String getValueAsString() {
        if (getConcept().getName().equals(StringUtil.EMPTY)) {
            throw new UnsupportedOperationException("The concept has not been loaded fully");
        }
        if (getConcept().isNumeric() && valueNumeric != null) {
            return getStringOfNumeric();
        } else if (getConcept().isDatetime() && valueDatetime != null) {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(valueDatetime);
        } else if (getConcept().isCoded() && valueCoded != null) {
            return getValueCoded().getName();
        } else {
            if (valueText != null)
                return valueText;
        }
        return "";
    }

    private String getStringOfNumeric() {
        if (getConcept().isPrecise())
            return valueNumeric.toString();
        else
            return String.valueOf(valueNumeric.intValue());
    }

    public boolean isVoided() {
        return voided;
    }

    public void setVoided(final boolean voided) {
        this.voided = voided;
    }
}
