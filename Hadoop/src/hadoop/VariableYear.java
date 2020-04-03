package hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.WritableComparable;


public class VariableYear implements WritableComparable {
     
      private IntWritable yearT;
      private FloatWritable valueT;
      
      private IntWritable yearTM;
      private FloatWritable valueTM;
      
      private IntWritable yearTm;
      private FloatWritable valueTm;
      
      private IntWritable yearPP;
      private FloatWritable valuePP;
      
      private IntWritable yearV;
      private FloatWritable valueV;
      
      private IntWritable yearRA;
      private FloatWritable valueRA;
      
      private IntWritable yearSN;
      private FloatWritable valueSN;
      
      private IntWritable yearTS;
      private FloatWritable valueTS;
      
      private IntWritable yearFG;
      private FloatWritable valueFG;
      
      private IntWritable yearTN;
      private FloatWritable valueTN;
      
      private IntWritable yearGR;
      private FloatWritable valueGR;
      
      
      public VariableYear() {             
            super();
     		this.yearT = new IntWritable();
     		this.valueT = new FloatWritable();
     		this.yearTM = new IntWritable();
     		this.valueTM = new FloatWritable();
     		this.yearTm = new IntWritable();
     		this.valueTm = new FloatWritable();
     		this.yearPP = new IntWritable();
     		this.valuePP = new FloatWritable();
     		this.yearV = new IntWritable();
     		this.valueV = new FloatWritable();
     		this.yearRA = new IntWritable();
     		this.valueRA = new FloatWritable();
     		this.yearSN = new IntWritable();
     		this.valueSN = new FloatWritable();
     		this.yearTS = new IntWritable();
     		this.valueTS = new FloatWritable();
     		this.yearFG = new IntWritable();
     		this.valueFG = new FloatWritable();
     		this.yearTN = new IntWritable();
     		this.valueTN = new FloatWritable();
     		this.yearGR = new IntWritable();
     		this.valueGR = new FloatWritable();
      }
     
      
      
      public VariableYear(IntWritable yearT, FloatWritable valueT, IntWritable yearTM, FloatWritable valueTM,
			IntWritable yearTm2, FloatWritable valueTm2, IntWritable yearPP, FloatWritable valuePP, IntWritable yearV,
			FloatWritable valueV, IntWritable yearRA, FloatWritable valueRA, IntWritable yearSN, FloatWritable valueSN,
			IntWritable yearTS, FloatWritable valueTS, IntWritable yearFG, FloatWritable valueFG, IntWritable yearTN,
			FloatWritable valueTN, IntWritable yearGR, FloatWritable valueGR) {
		super();
		this.yearT = yearT;
		this.valueT = valueT;
		this.yearTM = yearTM;
		this.valueTM = valueTM;
		this.yearTm = yearTm2;
		this.valueTm = valueTm2;
		this.yearPP = yearPP;
		this.valuePP = valuePP;
		this.yearV = yearV;
		this.valueV = valueV;
		this.yearRA = yearRA;
		this.valueRA = valueRA;
		this.yearSN = yearSN;
		this.valueSN = valueSN;
		this.yearTS = yearTS;
		this.valueTS = valueTS;
		this.yearFG = yearFG;
		this.valueFG = valueFG;
		this.yearTN = yearTN;
		this.valueTN = valueTN;
		this.yearGR = yearGR;
		this.valueGR = valueGR;
	}



	@Override
      public void readFields(DataInput in) throws IOException {
			this.yearT.readFields(in);
	  		this.valueT.readFields(in);
	  		this.yearTM.readFields(in);
	  		this.valueTM.readFields(in);
	  		this.yearTm.readFields(in);
	  		this.valueTm.readFields(in);
	  		this.yearPP.readFields(in);
	  		this.valuePP.readFields(in);
	  		this.yearV.readFields(in);
	  		this.valueV.readFields(in);
	  		this.yearRA.readFields(in);
	  		this.valueRA.readFields(in);
	  		this.yearSN.readFields(in);
	  		this.valueSN.readFields(in);
	  		this.yearTS.readFields(in);
	  		this.valueTS.readFields(in);
	  		this.yearFG.readFields(in);
	  		this.valueFG.readFields(in);
	  		this.yearTN.readFields(in);
	  		this.valueTN.readFields(in);
	  		this.yearGR.readFields(in);
	  		this.valueGR.readFields(in);
      }

      @Override
      public void write(DataOutput out) throws IOException {
    	  	this.yearT.write(out);
	  		this.valueT.write(out);
	  		this.yearTM.write(out);
	  		this.valueTM.write(out);
	  		this.yearTm.write(out);
	  		this.valueTm.write(out);
	  		this.yearPP.write(out);
	  		this.valuePP.write(out);
	  		this.yearV.write(out);
	  		this.valueV.write(out);
	  		this.yearRA.write(out);
	  		this.valueRA.write(out);
	  		this.yearSN.write(out);
	  		this.valueSN.write(out);
	  		this.yearTS.write(out);
	  		this.valueTS.write(out);
	  		this.yearFG.write(out);
	  		this.valueFG.write(out);
	  		this.yearTN.write(out);
	  		this.valueTN.write(out);
	  		this.yearGR.write(out);
	  		this.valueGR.write(out);
      }

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public String toString() {
		return yearT + ";" + valueT + "\t" + yearTM + ";" + valueTM + 
				"\t" + yearTm + ";" + valueTm + "\t" + yearPP + ";" + valuePP + 
				"\t" + yearV + ";" + valueV + "\t" + yearRA + ";" + valueRA + 
				"\t" + yearSN + ";" + valueSN + "\t" + yearTS + ";" + valueTS + 
				"\t" + yearFG + ";" + valueFG + "\t" + yearTN + ";" + valueTN + 
				"\t" + yearGR + ";" + valueGR; 
	}



	public IntWritable getYearT() {
		return yearT;
	}

	public void setYearT(IntWritable yearT) {
		this.yearT = yearT;
	}

	public FloatWritable getValueT() {
		return valueT;
	}

	public void setValueT(FloatWritable valueT) {
		this.valueT = valueT;
	}

	public IntWritable getYearTM() {
		return yearTM;
	}

	public void setYearTM(IntWritable yearTM) {
		this.yearTM = yearTM;
	}

	public FloatWritable getValueTM() {
		return valueTM;
	}

	public void setValueTM(FloatWritable valueTM) {
		this.valueTM = valueTM;
	}

	public IntWritable getYearTm() {
		return yearTm;
	}

	public void setYearTm(IntWritable yearTm) {
		this.yearTm = yearTm;
	}

	public FloatWritable getValueTm() {
		return valueTm;
	}

	public void setValueTm(FloatWritable valueTm) {
		this.valueTm = valueTm;
	}

	public IntWritable getYearPP() {
		return yearPP;
	}

	public void setYearPP(IntWritable yearPP) {
		this.yearPP = yearPP;
	}

	public FloatWritable getValuePP() {
		return valuePP;
	}

	public void setValuePP(FloatWritable valuePP) {
		this.valuePP = valuePP;
	}

	public IntWritable getYearV() {
		return yearV;
	}

	public void setYearV(IntWritable yearV) {
		this.yearV = yearV;
	}

	public FloatWritable getValueV() {
		return valueV;
	}

	public void setValueV(FloatWritable valueV) {
		this.valueV = valueV;
	}

	public IntWritable getYearRA() {
		return yearRA;
	}

	public void setYearRA(IntWritable yearRA) {
		this.yearRA = yearRA;
	}

	public FloatWritable getValueRA() {
		return valueRA;
	}

	public void setValueRA(FloatWritable valueRA) {
		this.valueRA = valueRA;
	}

	public IntWritable getYearSN() {
		return yearSN;
	}

	public void setYearSN(IntWritable yearSN) {
		this.yearSN = yearSN;
	}

	public FloatWritable getValueSN() {
		return valueSN;
	}

	public void setValueSN(FloatWritable valueSN) {
		this.valueSN = valueSN;
	}

	public IntWritable getYearTS() {
		return yearTS;
	}

	public void setYearTS(IntWritable yearTS) {
		this.yearTS = yearTS;
	}

	public FloatWritable getValueTS() {
		return valueTS;
	}

	public void setValueTS(FloatWritable valueTS) {
		this.valueTS = valueTS;
	}

	public IntWritable getYearFG() {
		return yearFG;
	}

	public void setYearFG(IntWritable yearFG) {
		this.yearFG = yearFG;
	}

	public FloatWritable getValueFG() {
		return valueFG;
	}

	public void setValueFG(FloatWritable valueFG) {
		this.valueFG = valueFG;
	}

	public IntWritable getYearTN() {
		return yearTN;
	}

	public void setYearTN(IntWritable yearTN) {
		this.yearTN = yearTN;
	}

	public FloatWritable getValueTN() {
		return valueTN;
	}

	public void setValueTN(FloatWritable valueTN) {
		this.valueTN = valueTN;
	}

	public IntWritable getYearGR() {
		return yearGR;
	}

	public void setYearGR(IntWritable yearGR) {
		this.yearGR = yearGR;
	}

	public FloatWritable getValueGR() {
		return valueGR;
	}

	public void setValueGR(FloatWritable valueGR) {
		this.valueGR = valueGR;
	}
	
	

}