package hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.WritableComparable;


public class VariableCountry implements WritableComparable {
     
      private Text countryT;
      private FloatWritable valueT;
      
      private Text countryTM;
      private FloatWritable valueTM;
      
      private Text countryTm;
      private FloatWritable valueTm;
      
      private Text countryPP;
      private FloatWritable valuePP;
      
      private Text countryV;
      private FloatWritable valueV;
      
      private Text countryRA;
      private FloatWritable valueRA;
      
      private Text countrySN;
      private FloatWritable valueSN;
      
      private Text countryTS;
      private FloatWritable valueTS;
      
      private Text countryFG;
      private FloatWritable valueFG;
      
      private Text countryTN;
      private FloatWritable valueTN;
      
      private Text countryGR;
      private FloatWritable valueGR;
      
      
      public VariableCountry() {             
            super();
     		this.countryT = new Text();
     		this.valueT = new FloatWritable();
     		this.countryTM = new Text();
     		this.valueTM = new FloatWritable();
     		this.countryTm = new Text();
     		this.valueTm = new FloatWritable();
     		this.countryPP = new Text();
     		this.valuePP = new FloatWritable();
     		this.countryV = new Text();
     		this.valueV = new FloatWritable();
     		this.countryRA = new Text();
     		this.valueRA = new FloatWritable();
     		this.countrySN = new Text();
     		this.valueSN = new FloatWritable();
     		this.countryTS = new Text();
     		this.valueTS = new FloatWritable();
     		this.countryFG = new Text();
     		this.valueFG = new FloatWritable();
     		this.countryTN = new Text();
     		this.valueTN = new FloatWritable();
     		this.countryGR = new Text();
     		this.valueGR = new FloatWritable();
      }
     
      
      
      public VariableCountry(Text countryT, FloatWritable valueT, Text countryTM, FloatWritable valueTM,
			Text countryTm2, FloatWritable valueTm2, Text countryPP, FloatWritable valuePP, Text countryV,
			FloatWritable valueV, Text countryRA, FloatWritable valueRA, Text countrySN, FloatWritable valueSN,
			Text countryTS, FloatWritable valueTS, Text countryFG, FloatWritable valueFG, Text countryTN,
			FloatWritable valueTN, Text countryGR, FloatWritable valueGR) {
		super();
		this.countryT = countryT;
		this.valueT = valueT;
		this.countryTM = countryTM;
		this.valueTM = valueTM;
		this.countryTm = countryTm2;
		this.valueTm = valueTm2;
		this.countryPP = countryPP;
		this.valuePP = valuePP;
		this.countryV = countryV;
		this.valueV = valueV;
		this.countryRA = countryRA;
		this.valueRA = valueRA;
		this.countrySN = countrySN;
		this.valueSN = valueSN;
		this.countryTS = countryTS;
		this.valueTS = valueTS;
		this.countryFG = countryFG;
		this.valueFG = valueFG;
		this.countryTN = countryTN;
		this.valueTN = valueTN;
		this.countryGR = countryGR;
		this.valueGR = valueGR;
	}



	@Override
      public void readFields(DataInput in) throws IOException {
			this.countryT.readFields(in);
	  		this.valueT.readFields(in);
	  		this.countryTM.readFields(in);
	  		this.valueTM.readFields(in);
	  		this.countryTm.readFields(in);
	  		this.valueTm.readFields(in);
	  		this.countryPP.readFields(in);
	  		this.valuePP.readFields(in);
	  		this.countryV.readFields(in);
	  		this.valueV.readFields(in);
	  		this.countryRA.readFields(in);
	  		this.valueRA.readFields(in);
	  		this.countrySN.readFields(in);
	  		this.valueSN.readFields(in);
	  		this.countryTS.readFields(in);
	  		this.valueTS.readFields(in);
	  		this.countryFG.readFields(in);
	  		this.valueFG.readFields(in);
	  		this.countryTN.readFields(in);
	  		this.valueTN.readFields(in);
	  		this.countryGR.readFields(in);
	  		this.valueGR.readFields(in);
      }

      @Override
      public void write(DataOutput out) throws IOException {
    	  	this.countryT.write(out);
	  		this.valueT.write(out);
	  		this.countryTM.write(out);
	  		this.valueTM.write(out);
	  		this.countryTm.write(out);
	  		this.valueTm.write(out);
	  		this.countryPP.write(out);
	  		this.valuePP.write(out);
	  		this.countryV.write(out);
	  		this.valueV.write(out);
	  		this.countryRA.write(out);
	  		this.valueRA.write(out);
	  		this.countrySN.write(out);
	  		this.valueSN.write(out);
	  		this.countryTS.write(out);
	  		this.valueTS.write(out);
	  		this.countryFG.write(out);
	  		this.valueFG.write(out);
	  		this.countryTN.write(out);
	  		this.valueTN.write(out);
	  		this.countryGR.write(out);
	  		this.valueGR.write(out);
      }

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public String toString() {
		return countryT + ";" + valueT + "\t" + countryTM + ";" + valueTM + 
				"\t" + countryTm + ";" + valueTm + "\t" + countryPP + ";" + valuePP + 
				"\t" + countryV + ";" + valueV + "\t" + countryRA + ";" + valueRA + 
				"\t" + countrySN + ";" + valueSN + "\t" + countryTS + ";" + valueTS + 
				"\t" + countryFG + ";" + valueFG + "\t" + countryTN + ";" + valueTN + 
				"\t" + countryGR + ";" + valueGR; 
	}



	public Text getCountryT() {
		return countryT;
	}

	public void setCountryT(Text countryT) {
		this.countryT = countryT;
	}

	public FloatWritable getValueT() {
		return valueT;
	}

	public void setValueT(FloatWritable valueT) {
		this.valueT = valueT;
	}

	public Text getCountryTM() {
		return countryTM;
	}

	public void setCountryTM(Text countryTM) {
		this.countryTM = countryTM;
	}

	public FloatWritable getValueTM() {
		return valueTM;
	}

	public void setValueTM(FloatWritable valueTM) {
		this.valueTM = valueTM;
	}

	public Text getCountryTm() {
		return countryTm;
	}

	public void setCountryTm(Text countryTm) {
		this.countryTm = countryTm;
	}

	public FloatWritable getValueTm() {
		return valueTm;
	}

	public void setValueTm(FloatWritable valueTm) {
		this.valueTm = valueTm;
	}

	public Text getCountryPP() {
		return countryPP;
	}

	public void setCountryPP(Text countryPP) {
		this.countryPP = countryPP;
	}

	public FloatWritable getValuePP() {
		return valuePP;
	}

	public void setValuePP(FloatWritable valuePP) {
		this.valuePP = valuePP;
	}

	public Text getCountryV() {
		return countryV;
	}

	public void setCountryV(Text countryV) {
		this.countryV = countryV;
	}

	public FloatWritable getValueV() {
		return valueV;
	}

	public void setValueV(FloatWritable valueV) {
		this.valueV = valueV;
	}

	public Text getCountryRA() {
		return countryRA;
	}

	public void setCountryRA(Text countryRA) {
		this.countryRA = countryRA;
	}

	public FloatWritable getValueRA() {
		return valueRA;
	}

	public void setValueRA(FloatWritable valueRA) {
		this.valueRA = valueRA;
	}

	public Text getCountrySN() {
		return countrySN;
	}

	public void setCountrySN(Text countrySN) {
		this.countrySN = countrySN;
	}

	public FloatWritable getValueSN() {
		return valueSN;
	}

	public void setValueSN(FloatWritable valueSN) {
		this.valueSN = valueSN;
	}

	public Text getCountryTS() {
		return countryTS;
	}

	public void setCountryTS(Text countryTS) {
		this.countryTS = countryTS;
	}

	public FloatWritable getValueTS() {
		return valueTS;
	}

	public void setValueTS(FloatWritable valueTS) {
		this.valueTS = valueTS;
	}

	public Text getCountryFG() {
		return countryFG;
	}

	public void setCountryFG(Text countryFG) {
		this.countryFG = countryFG;
	}

	public FloatWritable getValueFG() {
		return valueFG;
	}

	public void setValueFG(FloatWritable valueFG) {
		this.valueFG = valueFG;
	}

	public Text getCountryTN() {
		return countryTN;
	}

	public void setCountryTN(Text countryTN) {
		this.countryTN = countryTN;
	}

	public FloatWritable getValueTN() {
		return valueTN;
	}

	public void setValueTN(FloatWritable valueTN) {
		this.valueTN = valueTN;
	}

	public Text getCountryGR() {
		return countryGR;
	}

	public void setCountryGR(Text countryGR) {
		this.countryGR = countryGR;
	}

	public FloatWritable getValueGR() {
		return valueGR;
	}

	public void setValueGR(FloatWritable valueGR) {
		this.valueGR = valueGR;
	}
}