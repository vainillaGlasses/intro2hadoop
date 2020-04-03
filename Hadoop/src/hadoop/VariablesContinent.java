package hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.WritableComparable;


public class VariablesContinent implements WritableComparable {
     
      private IntWritable year;

      private FloatWritable valueT;
      private FloatWritable valueTM;
      private FloatWritable valueTm;
      private FloatWritable valuePP;
      private FloatWritable valueV;
      private FloatWritable valueRA;
      private FloatWritable valueSN;
      private FloatWritable valueTS;
      private FloatWritable valueFG;
      private FloatWritable valueTN;
      private FloatWritable valueGR;
      
      
      public VariablesContinent() {             
            super();

     		this.year = new IntWritable();
     		this.valueT = new FloatWritable();
     		this.valueTM = new FloatWritable();
     		this.valueTm = new FloatWritable();
     		this.valuePP = new FloatWritable();
     		this.valueV = new FloatWritable();
     		this.valueRA = new FloatWritable();
     		this.valueSN = new FloatWritable();
     		this.valueTS = new FloatWritable();
     		this.valueFG = new FloatWritable();
     		this.valueTN = new FloatWritable();
     		this.valueGR = new FloatWritable();
      }
     

      public VariablesContinent(IntWritable year, FloatWritable valueT, FloatWritable valueTM, FloatWritable valueTm2,
  			FloatWritable valuePP, FloatWritable valueV, FloatWritable valueRA, FloatWritable valueSN,
  			FloatWritable valueTS, FloatWritable valueFG, FloatWritable valueTN, FloatWritable valueGR) {
  		super();
  		this.year = year;
  		this.valueT = valueT;
  		this.valueTM = valueTM;
  		valueTm = valueTm2;
  		this.valuePP = valuePP;
  		this.valueV = valueV;
  		this.valueRA = valueRA;
  		this.valueSN = valueSN;
  		this.valueTS = valueTS;
  		this.valueFG = valueFG;
  		this.valueTN = valueTN;
  		this.valueGR = valueGR;
  	}
      
	  @Override
      public void readFields(DataInput in) throws IOException {
			this.year.readFields(in);
			
	  		this.valueT.readFields(in);
	  		this.valueTM.readFields(in);
	  		this.valueTm.readFields(in);
	  		this.valuePP.readFields(in);
	  		this.valueV.readFields(in);
	  		this.valueRA.readFields(in);
	  		this.valueSN.readFields(in);
	  		this.valueTS.readFields(in);
	  		this.valueFG.readFields(in);
	  		this.valueTN.readFields(in);
	  		this.valueGR.readFields(in);
      }

      @Override
      public void write(DataOutput out) throws IOException {
    	  	this.year.write(out);
	  		this.valueT.write(out);
	  		this.valueTM.write(out);
	  		this.valueTm.write(out);
	  		this.valuePP.write(out);
	  		this.valueV.write(out);
	  		this.valueRA.write(out);
	  		this.valueSN.write(out);
	  		this.valueTS.write(out);
	  		this.valueFG.write(out);
	  		this.valueTN.write(out);
	  		this.valueGR.write(out);
      }

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public String toString() {
		return valueT + 
                        "\t" + valueTM + 
			"\t" + valueTm + 
			"\t" + valuePP + 
			"\t" + valueV + 
			"\t" + valueRA + 
			"\t" + valueSN + 
			"\t" + valueTS + 
			"\t" + valueFG + 
			"\t" + valueTN + 
			"\t" + valueGR; 
	}

	public IntWritable getYear() {
		return year;
	}



	public void setYear(IntWritable year) {
		this.year = year;
	}



	public FloatWritable getValueT() {
		return valueT;
	}



	public void setValueT(FloatWritable valueT) {
		this.valueT = valueT;
	}



	public FloatWritable getValueTM() {
		return valueTM;
	}



	public void setValueTM(FloatWritable valueTM) {
		this.valueTM = valueTM;
	}



	public FloatWritable getValueTm() {
		return valueTm;
	}



	public void setValueTm(FloatWritable valueTm) {
		this.valueTm = valueTm;
	}



	public FloatWritable getValuePP() {
		return valuePP;
	}



	public void setValuePP(FloatWritable valuePP) {
		this.valuePP = valuePP;
	}



	public FloatWritable getValueV() {
		return valueV;
	}



	public void setValueV(FloatWritable valueV) {
		this.valueV = valueV;
	}



	public FloatWritable getValueRA() {
		return valueRA;
	}



	public void setValueRA(FloatWritable valueRA) {
		this.valueRA = valueRA;
	}



	public FloatWritable getValueSN() {
		return valueSN;
	}



	public void setValueSN(FloatWritable valueSN) {
		this.valueSN = valueSN;
	}



	public FloatWritable getValueTS() {
		return valueTS;
	}



	public void setValueTS(FloatWritable valueTS) {
		this.valueTS = valueTS;
	}



	public FloatWritable getValueFG() {
		return valueFG;
	}



	public void setValueFG(FloatWritable valueFG) {
		this.valueFG = valueFG;
	}



	public FloatWritable getValueTN() {
		return valueTN;
	}



	public void setValueTN(FloatWritable valueTN) {
		this.valueTN = valueTN;
	}



	public FloatWritable getValueGR() {
		return valueGR;
	}



	public void setValueGR(FloatWritable valueGR) {
		this.valueGR = valueGR;
	}
    
	
	

}